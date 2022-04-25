/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.CustomerSubsystem;

import com.example.RideSubsystem.Ride;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author admin
 */
public class Rate {
    private Map<Ride, Integer> driverRatings = new HashMap<>();
    private int ratingSum = 0;
    private int numberOfRatings = 0;

    public double getAverageRating() {
        if (driverRatings.size() == 0) {
            return 0;
        }
        return 1.0 * ratingSum / numberOfRatings;
    }

    public void addRating(Ride ride, int rating) {
        if (driverRatings.get(ride) == null) {
            driverRatings.put(ride, rating);
            numberOfRatings++;
            ratingSum += rating;
        }
    }

    public ArrayList<String> viewAllRating() {
        ArrayList<String> ratings = new ArrayList<>();
        for (Map.Entry<Ride, Integer> set : driverRatings.entrySet()) {
            ratings.add(set.getKey().getPassenger().getUsername() + " : " + set.getValue());
        }
        return ratings;
    }
}