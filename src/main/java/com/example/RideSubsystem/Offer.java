/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.RideSubsystem;

import com.example.CustomerSubsystem.Driver;
import com.example.EventHandling.Event;
import com.example.EventHandling.IDateTime;
import java.time.LocalDate;
import java.util.Date;

/**
 *
 * @author admin
 */
public class Offer {
    private final double price;
    private double discountedPrice;
    private Driver driver;

    public Offer(double price, Driver driver) {
        this.driver = driver;
        this.price = price;
        discountedPrice = price;
    }

    public double getPrice() {
        return price;
    }

    public void makeDiscount(int percentage) {
        discountedPrice -= 1.0 * price * percentage / 100;
        discountedPrice = Math.max(0.0, discountedPrice);
    }

    public void applyDiscount(Ride ride) {
        if (ride.getPassenger().isFirstRide()) {
            makeDiscount(10);
        }
        if (ride.getDestination().isDiscounted()) {
            makeDiscount(10);
        }
        if (ride.getNumberOfPassengers() >= 2) {
            makeDiscount(5);
        }
        if (IDateTime.getInstance().isWeekend(new Date())) {
            makeDiscount(5);
        }
        if (ride.getPassenger().getDateOfBirth().substring(4).equals(LocalDate.now().toString().substring(4))) {
            makeDiscount(10);
        }
    }

    public Driver getDriver() {
        return driver;
    }

    public void setDriver(Driver driver) {
        this.driver = driver;
    }

    @Override
    public String toString() {
        return "price: " + discountedPrice +
                " || driver name: " + driver.getUsername() + " || driver phone number: " + driver.getMobileNumber()
                + " || driver license: " + driver.getDriverLicense() + " || driver average rating: " + driver.getRating().getAverageRating();
    }
}