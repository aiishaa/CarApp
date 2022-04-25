/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.EventHandling;

import com.example.CustomerSubsystem.Driver;
import com.example.RideSubsystem.Offer;

/**
 *
 * @author admin
 */
public class AddPrice extends Event{
    public AddPrice(Driver driver, Offer offer) {
        setName(EventName.AddPrice);
        addAttribute("Time", IDateTime.getInstance().getDateTime());
        addAttribute("Driver Name", driver.getUsername());
        addAttribute("Offered Price", Double.toString(offer.getPrice()));
    }
    
}
