/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.EventHandling;

import com.example.CustomerSubsystem.Driver;
import com.example.RideSubsystem.Ride;

/**
 *
 * @author admin
 */
public class ArrivedToUserLocation extends Event{
    public ArrivedToUserLocation(Driver driver, Ride ride) {
        setName(EventName.ArrivedToUserLocation);
        addAttribute("Time", IDateTime.getInstance().getDateTime());
        addAttribute("Driver Name", driver.getUsername());
        addAttribute("Passenger Name", ride.getPassenger().getUsername());
    }
}
