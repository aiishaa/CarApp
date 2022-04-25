/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.DatabaseManager;

/**
 *
 * @author admin
 */

import com.example.CustomerSubsystem.Driver;
import com.example.RideSubsystem.Ride;
import com.example.CustomerSubsystem.passenger;
import com.example.CustomerSubsystem.user;
import com.example.RideSubsystem.Area;
import com.example.CustomerSubsystem.Admin;
import java.util.ArrayList;

public interface DatabaseInterface {
    public ArrayList<Driver> listAllPendingDrivers();

    public user getUser(String userName);
    
    public Admin getAdmin(String username);
            
    public Boolean addPassengerAsRegistered(passenger passenger);
    
    public Boolean addPassengerAsLoggedIn(passenger passenger);
    
    public passenger getRegisteredPassenger(String userName);
    
    public passenger getLoggedInPassenger(String userName);

    public Boolean addRegisteredDriver(Driver driver);
    
    public Boolean addLoggedInDriver(Driver driver);
               
    public Driver getRegisteredDriver(String userName);
    
    public Driver getLoggedInDriver(String userName);

    public Boolean addAdmin(Admin admin);

    public ArrayList<Ride> getAllRides();

    public Ride getRide(int idx);

    public void addRide(Ride ride);
    
    public boolean AddArea(Area area);
    
    public Area getArea(String area);
    
    public ArrayList<Area> getAllAreas();
            
    public ArrayList<passenger> RegisteredPassengers();
    
    public ArrayList<passenger> LoggedInPassengers();
    
    public ArrayList<Driver> RegisteredDrivers();
    
    public ArrayList<Driver> LoggedInDrivers();
    
    public Boolean isAvailable(String username);
}
