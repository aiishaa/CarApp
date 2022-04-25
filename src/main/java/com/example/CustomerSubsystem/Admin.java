/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.CustomerSubsystem;

/**
 *
 * @author admin
 */
import com.example.DatabaseManager.Database;
import com.example.RideSubsystem.Area;
import com.example.RideSubsystem.Ride;
import java.util.ArrayList;

/**
 *
 * @author admin
 */
public class Admin extends user{
    Database database = Database.getInstance();
    
    public Admin(String username, String mobileNumber, String email, String password) {
        super(username, mobileNumber, password, email, UserStatus.admin);
    }
    
    public boolean verifyDriver(String username) {
        Driver tempDriver = Database.getInstance().getRegisteredDriver(username);
        if (tempDriver == null) {
            return false;
        } else {
            tempDriver.setVerified(true);
            return true;
        }
    }
    
     public Boolean suspendUser(String username) {
        user u = database.getUser(username);
        if (u == null) {
            return false;
        } else {
            u.setUserStatus(UserStatus.suspended);
            return true;
        }
    }

    public ArrayList<String> getSystemRides() {
        boolean flag = false;
        ArrayList<String> areas = new ArrayList<>();
        ArrayList<Ride> allRides = database.getAllRides();
        for (int i = 0; i < allRides.size(); i++) {
            flag = true;
            areas.add((i + 1) + "- " + allRides.get(i));
        }
        if (!flag) {
            areas.add("No rides yet");
        }
        return areas;
    }

    public ArrayList<String> getRideEvents(int index) {
        boolean flag = false;
        ArrayList<String> events = new ArrayList<>();
        Ride ride = database.getRide(index);
        for (int i = 0; i < ride.getEvents().size(); i++) {
            flag = true;
            events.add(ride.getEvents().get(i).toString());
        }
        if (!flag) {
            events.add("The ride has no events yet");
        }
        return events;
    }

    public void applyDiscount(Area area) {
        area.setDiscounted();
    }
}

