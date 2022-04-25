/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.CustomerSubsystem;

import com.example.DatabaseManager.Database;
import com.example.EventHandling.AcceptOffer;
import com.example.RideSubsystem.Area;
import com.example.RideSubsystem.Offer;
import com.example.RideSubsystem.Ride;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author admin
 */
public class passenger extends user{
    private Map<Ride, String > ratings = new HashMap<>();
    private ArrayList<Ride> pastRides =  new ArrayList<>();;
    private Ride currentRide;
    private String dateOfBirth;
    
    public passenger(){}
    public passenger(String userName, String mobilenumber, String password, String email, String dateOfBirth){
        super(userName, mobilenumber, password, email, UserStatus.activated);
        currentRide = null;
        this.dateOfBirth = dateOfBirth;
        
    }
    
    
    public ArrayList<String> checkOffers() {
        return currentRide.viewOffers();
    }

    public boolean acceptOffer(int offerNum) {
        if (offerNum >= currentRide.getOffers().size() || offerNum < 0) {
            return false;
        }
        Offer accepted = currentRide.getOffers().get(offerNum);
        currentRide.setAcceptedOffer(accepted);
        currentRide.AddEvent(new AcceptOffer(this));
        accepted.getDriver().setCurrentRide(currentRide);
        pastRides.add(currentRide);
        currentRide = null;
        return true;
    }
    
    public boolean isFirstRide() {
        System.out.println("CustomerSubsystem.passenger.isFirstRide()");
        return (pastRides.size() == 0);
    }

    public Ride getPastRide(int index) {
        return pastRides.get(index);
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }
    
    public void requestRide(Ride ride) {
        Database.getInstance().addRide(ride);
        Area area = Database.getInstance().getArea(ride.getSource().getAreaName());
        area.notifySubscribers(ride);
        currentRide = ride;
    }

    public boolean rateRide(Ride ride, int rate) {
        if (rate < 1 || rate > 5) {
            return false;
        }
        for (int i = 0; i < pastRides.size(); i++) {
            if (pastRides.get(i).equals(ride)) { 
                pastRides.get(i).getAcceptedOffer().getDriver().getRating().addRating(ride, rate);
            }
        }
        return true;
    }
}
