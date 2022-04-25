/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.CustomerSubsystem;

import com.example.DatabaseManager.Database;
import com.example.EventHandling.AddPrice;
import com.example.EventHandling.ArrivedToUserDestination;
import com.example.EventHandling.Event;
import com.example.EventHandling.ArrivedToUserLocation;
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
public class Driver extends user implements DriverObserver{
    private final String nationalID;
    private final String DrivingLicense;
    private Boolean Verified = false;
    private int numberOfPassenegers;
    private final ArrayList<Area> FavAreas = new ArrayList<>();
    private Ride currentRide;
    private final ArrayList<Ride> completedRides = new ArrayList<>();
    private ArrayList<Ride> rides = new ArrayList<>();
    private final ArrayList<Rate> rates;
    private final Rate rating = new Rate();
    private final float balance = 0;
    
    Ride ride = new Ride();
    
    public Driver(String userName, String mobilenumber, String password, String email, String DrivingLicense, String nationalID, int passengerNumber){
        super(userName, mobilenumber, password, email, UserStatus.activated);
        this.DrivingLicense = DrivingLicense;
        this.nationalID = nationalID;
        this.rates = new ArrayList<>();
        this.numberOfPassenegers = passengerNumber;
    }
    
    public void setVerified(boolean is_verifed){
        Verified = is_verifed;
    }

    public Boolean getVerified() {
        return Verified;
    }
   
    public void addFavArea(Area area) {
        FavAreas.add(area);
        area.AddSubscriber(this);
    }
    
    public int getNumberOfPassengers(){
        return this.numberOfPassenegers;
    }
    
    public ArrayList<Ride> list_rides(int FavAreaIndex){
        ArrayList<Ride> desirableRides = new ArrayList<>();
        for(int i = 0; i < rides.size(); i++){
            if(rides.get(i).getSource().equals(FavAreas.get(FavAreaIndex))){
                desirableRides.add(rides.get(i));
            } else {
            }
        }
        if(desirableRides.isEmpty()){
            System.out.println("there is no rides in this area");
        }
        return desirableRides;
    }
   
    public ArrayList<String> LisFavAreas(){
         boolean flag = false;
        ArrayList<String> areas = new ArrayList<>();
        for (int i = 0; i < FavAreas.size(); i++) {
            flag = true;
            areas.add((i + 1) + "- " + FavAreas.get(i).getAreaName());
        }
        if (!flag) {
            areas.add("No favourite areas");
        }
        return areas;
    }
    
    public String getDriverLicense(){
        return DrivingLicense;
    }
    
    public String getNationalID() {
        return nationalID;
    }
    
    @Override
    public void update(Ride r){
        rides.add(r);
        System.out.println("the ride is added");
    }
    
    public void suggestOffer(int idx, Offer offer) {
        Ride ride = rides.get(idx);
        ride.addOffer(offer);
        ride.AddEvent(new AddPrice(this, offer));
    }
    
   public ArrayList<String> viewRides(int index) {
        boolean flag = false;
        ArrayList<String> ridesPrint = new ArrayList<>();
        System.out.println(rides);
        for (int i = 0; i < rides.size(); i++) {
            if (rides.get(i).getSource().equals(FavAreas.get(index)) && !rides.get(i).getAccepted()) {
                ridesPrint.add((i + 1) + "- " + rides.get(i));
                flag = true;
            }
        }
        if (!flag) {
            ridesPrint.add("There isn't any requested rides in area");
        }
        return ridesPrint;
    }

    public ArrayList<String> viewRating() {
        ArrayList<String> ratings = rating.viewAllRating();
        if (ratings.size() == 0)
            ratings.add("There is not any ratings for you yet");
        return ratings;
    }
    
    public Rate getRating() {
        return rating;
    }
    
    public boolean startRide() {
        if (currentRide == null) {
            return false;
        }
        currentRide.AddEvent(new ArrivedToUserLocation(this, currentRide));
        return true;
    }

    public boolean endRide() {
        if (currentRide == null) {
            return false;
        }
        currentRide.AddEvent(new ArrivedToUserDestination(this, currentRide));
        currentRide = null;
        return true;
    }

    public void setCurrentRide(Ride ride) {
        currentRide = ride;
    }

    public Ride getCurrentRide() {
        return currentRide;
    }
    
    @Override
    public String toString() {
        return super.toString() + "Driver License: " + getDriverLicense() + " , " + "National ID: " + getNationalID() + " , "  + getNumberOfPassengers();
    }
}
