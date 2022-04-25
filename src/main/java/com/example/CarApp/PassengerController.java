/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.CarApp;
        
import com.example.CustomerSubsystem.passenger;
import com.example.CustomerSubsystem.passenger;
import com.example.DatabaseManager.Database;
import com.example.RideSubsystem.Area;
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
public class PassengerController {
    @PostMapping("/passenger/request/{currentUsername}/{source}/{destination}/{numberOfPassengers}")
    public String requestRide(@PathVariable String currentUsername, @PathVariable String source, @PathVariable String destination, @PathVariable int numberOfPassengers) {
        passenger pass = Database.getInstance().getLoggedInPassenger(currentUsername);
        if (pass == null) {
            return "You're either not logged in";
        }
        Area source_ = Database.getInstance().getArea(source);
        Area destination_ = Database.getInstance().getArea(destination);
        Ride ride = new Ride(pass, source_, destination_, numberOfPassengers);
        pass.requestRide(ride);
        return "Ride was requested successfully";
    }

    @PostMapping("/passenger/ViewOffers/{currentUsername}")
    public ArrayList<String> viewOffers(@PathVariable String currentUsername) {
        passenger passenger = Database.getInstance().getLoggedInPassenger(currentUsername);
        if (passenger == null) {
            ArrayList<String> temp = new ArrayList<>();
            temp.add("You're either not logged in or you have no access to this function");
            return temp;
        } else {
            return passenger.checkOffers();
        }
    }

    @PostMapping("/passenger/AcceptOffer/{currentUsername}/{offerNum}")
    public String acceptOffer(@PathVariable String currentUsername, @PathVariable int offerNum) {
        passenger passenger = Database.getInstance().getLoggedInPassenger(currentUsername);
        if (passenger == null) {
            return "You're either not logged in or you have no access to this function";
        }
        if (passenger.acceptOffer(offerNum - 1)) {
            return "Offer is accepted successfully";
        }
        return "Offer was not accepted, you may have entered a wrong offer number";
    }
    
    @PostMapping("/passenger/RateRide/{currentUsername}/{rideIndex}/{rate}")
    public String rateRide(@PathVariable String currentUsername,@PathVariable int rideIndex, @PathVariable int rate){
        passenger pass = Database.getInstance().getLoggedInPassenger(currentUsername);
        if (pass == null) {
            return "You're not logged in";
        }
        Ride ride = pass.getPastRide(rideIndex - 1);
        if (pass.rateRide(ride, rate)) {
            return "Ride was rated successfully";
        }
        return "You have already rated this ride or you've entered a wrong number";
    }
    
}