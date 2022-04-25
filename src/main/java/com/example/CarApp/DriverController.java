/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.CarApp;

import com.example.CustomerSubsystem.Driver;
import com.example.DatabaseManager.Database;
import com.example.RideSubsystem.Area;
import com.example.RideSubsystem.Offer;
import com.example.RideSubsystem.Ride;
import java.util.ArrayList;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author admin
 */
@RestController
public class DriverController {
    @PostMapping("/driver/addArea/{DriverName}/{AreaName}")
    String AddFavArea(@PathVariable String DriverName, @PathVariable String AreaName){
        Driver driver = Database.getInstance().getLoggedInDriver(DriverName);
        if(driver == null)  return "failed to make an offer, you aren't logged in";
        Area area = Database.getInstance().getArea(AreaName);
        driver.addFavArea(area);
        return "You added an area successfully";
    }
    
    @PostMapping("/driver/listFavAreas/{DriverName}")
    ArrayList<String> ListFavouriteAreas(@PathVariable String DriverName){
        Driver driver = Database.getInstance().getLoggedInDriver(DriverName);
        if(driver == null)  {ArrayList<String>result = new ArrayList<>(); 
        result.add("failed to list your favourite areas, you aren't logged in");
        }
        return driver.LisFavAreas();
    }
    
    
    @PostMapping("/driver/viewRides/{currentUsername}/{areaNum}")
    public ArrayList<String> viewRide(@PathVariable String currentUsername, @PathVariable int areaNum) {
        Driver driver = Database.getInstance().getLoggedInDriver(currentUsername);
        if (driver == null) {
            ArrayList<String> temp = new ArrayList<>();
            temp.add("You're either not logged in or you have no access to this function");
            return temp;
        }
        return driver.viewRides(areaNum - 1);
    }

    
    @PostMapping("/driver/makeOffer/{DriverName}/{rideIndex}/{price}")
    String MakeAnOffer(@PathVariable String DriverName, @PathVariable int rideIndex, @PathVariable double price){
        Driver driver = Database.getInstance().getLoggedInDriver(DriverName);
        if(driver == null)  return "failed to make an offer, you aren't logged in";
        Offer offer = new Offer(price, driver);
        driver.suggestOffer(rideIndex - 1, offer);
        return "You make an offer successfully";
    }
    
    @PostMapping("/driver/startRide/{DriverName}")
    String StartRide(@PathVariable String DriverName){
        Driver driver = Database.getInstance().getLoggedInDriver(DriverName);
        if(driver == null)  return "failed to start a ride, you aren't logged in";
        if (driver.startRide()) 
            return "You have started a ride successfully";
        else 
            return "You have no current rides";
    }
    
    @PostMapping("/driver/endRide/{DriverName}")
    String EndRide(@PathVariable String DriverName){
        Driver driver = Database.getInstance().getLoggedInDriver(DriverName);
        if(driver == null)  return "failed to end a ride, you aren't logged in";
        if (driver.endRide()) 
            return "You have ended this ride successfully";
        else 
            return "You have no current rides";
    }
    
    @PostMapping("/driver/viewRating/{DriverName}")
    public ArrayList<String> viewRating(@PathVariable String DriverName) {
        Driver driver = Database.getInstance().getLoggedInDriver(DriverName);
        if (driver == null) {
            ArrayList<String> temp = new ArrayList<>();
            temp.add("Failed to view rating, you're not logged in");
            return temp;
        }
        return driver.viewRating();
    }
}
