/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.EventHandling;

import com.example.CustomerSubsystem.Driver;
import com.example.CustomerSubsystem.passenger;
import com.example.RideSubsystem.Ride;

/**
 *
 * @author admin
 */
public class AcceptOffer extends Event{

    public AcceptOffer(passenger _passenger) {
        setName(EventName.AcceptOffer);
        addAttribute("Time", IDateTime.getInstance().getDateTime());
        addAttribute("Passenger Name", _passenger.getUsername());
    }
    
}
