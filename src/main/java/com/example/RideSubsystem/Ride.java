/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.RideSubsystem;


import com.example.CustomerSubsystem.Driver;
import com.example.CustomerSubsystem.passenger;
import com.example.DatabaseManager.Database;
import com.example.EventHandling.Event;
import java.util.ArrayList;
import javax.print.attribute.standard.Destination;

/**
 *
 * @author admin
 */
public class Ride {
    private Area Source;
    private Area Destination;
    private passenger Passenger;
    private int numberOfPassenger;
    private Offer acceptedOffer;
    private Boolean Accepted = false;
    private ArrayList<Offer> offers = new ArrayList<>();
    private ArrayList<Event> events = new ArrayList<>();
    private Driver driver;
    public Ride() {
    }
    
    public Ride(passenger pass, Area source, Area destination, int numberOfPassenger) {
        this.Passenger = pass;
        this.Source = source;
        this.Destination = destination;
        this.numberOfPassenger = numberOfPassenger;
        acceptedOffer = null;
    }
    
    public void setRide(passenger pass, Area source, Area destination, int numberOfPassenger) {
        this.Passenger = pass;
        this.Source = source;
        this.Destination = destination;
        this.numberOfPassenger = numberOfPassenger;
        acceptedOffer = null;
    }
    
    public void setSource(Area source){
        this.Source = source;
    }
    public void setDestination(Area destination){
        this.Destination = destination;
    }
    public Area getDestination(){
        return this.Destination;
    } 
    public Area getSource(){
        return this.Source;
    } 

    public Driver getDriver(){
        return this.driver;
    }
    public void SetDriver(Driver driver){
        this.driver = driver;
    }
    public passenger getPassenger(){
        return this.Passenger;
    }
    public void SetPassenger(passenger Passenger){
        this.Passenger = Passenger;
    }
    
    public void setAcceptedOffer(Offer acceptedOffer) {
        Accepted = true;
        this.acceptedOffer = acceptedOffer;
    }

    public Offer getAcceptedOffer() {
        return acceptedOffer;
    }

    public ArrayList<Offer> getOffers() {
        return offers;
    }

    public Boolean getAccepted() {
        return Accepted;
    }

    public void addOffer(Offer offer) {
        offer.applyDiscount(this);
        offers.add(offer);
    }

    public ArrayList<String> viewOffers() {
        boolean flag = false;
        ArrayList<String> offers_ = new ArrayList<>();
        for (int i = 0; i < offers.size(); i++) {
            flag = true;
            offers_.add((i + 1) + "- " + offers.get(i));
        }
        if (!flag) {
            offers_.add("No offers yet");
        }
        return offers_;
    }
    
    public void AddEvent(Event event) {
        events.add(event);
        System.out.println("RideSubsystem.Ride.AddEvent()");
        System.out.println(this.getOffers());
    }

    public ArrayList<Event> getEvents() {
        return events;
    }
    
    public int getNumberOfPassengers(){
        return this.numberOfPassenger;
    }
    
    
    @Override
    public String toString(){
        return "From: " + Source + " to " + Destination;
    }

    
}
