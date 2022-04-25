/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.RideSubsystem;

import com.example.CustomerSubsystem.Driver;
import com.example.DatabaseManager.Database;
import java.util.ArrayList;

/**
 *
 * @author admin
 */
public class Area {
    private String Areaname;
    private ArrayList<Driver> subscribers = new ArrayList<Driver>();
    private boolean hasDiscount = false;
    
    
    public Area(String name){
        this.Areaname = name;
    }
    
    public Area getArea() {
       return this;
    }
    
    public String getAreaName() {
       return this.Areaname;
    }
    
    public void AddSubscriber(Driver driver){
        if(!subscribers.contains(driver))    subscribers.add(driver);
    }
    
    public void DeleteSubscriber(Driver driver) {
        subscribers.remove(driver);
    }
    
    public ArrayList<Driver> getSubscribers(){
        return this.subscribers;
    }
    
    public boolean isDiscounted() {
        return hasDiscount;
    }

    public void setDiscounted() {
        hasDiscount = true;
    }
    
    public boolean hasDiscount(){
        return hasDiscount;
    }
    
    public void AddDiscount(){
        hasDiscount = true;
    }
    
    public void notifySubscribers(Ride ride) {
        System.out.println(subscribers);
        System.out.println(subscribers.get(0).getNumberOfPassengers());
        for (int i = 0; i < subscribers.size(); i++) {
            if (subscribers.get(i).getCurrentRide() == null && subscribers.get(i).getNumberOfPassengers() >= ride.getNumberOfPassengers()) {
                subscribers.get(i).update(ride);
                System.out.println("driver: " + subscribers.get(i).getUsername() + " got notified");
            }
        }
    }
    
    @Override
    public String toString(){
        return "[" + getAreaName() + "]";
    }
}
