/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.CustomerSubsystem;

import com.example.RideSubsystem.Ride;

/**
 *
 * @author admin
 */
public interface DriverObserver {
    public void update(Ride ride);
}
