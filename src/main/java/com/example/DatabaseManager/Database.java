/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.DatabaseManager;

/**
 *
 * @author admin
 */
import com.example.CustomerSubsystem.Driver;
import com.example.RideSubsystem.Ride;
import com.example.CustomerSubsystem.passenger;
import com.example.CustomerSubsystem.user;
import com.example.RideSubsystem.Area;
import com.example.CustomerSubsystem.Admin;
import java.util.ArrayList;

/**
 *
 * @author admin
 */
public class Database implements DatabaseInterface{
    private static Database instance = null;
    private final ArrayList<Driver> RegisterdDrivers;
    private final ArrayList<Driver> LoggedInDrivers;
    private final ArrayList<passenger> RegisteredPassengers;
    private final ArrayList<passenger> LoggedInPassengers;
    private final ArrayList<Admin> AllAdmins;
    private final ArrayList<Ride> AllRides; 
    private final ArrayList<Area> AllAreas;
    
    private Database(){
        RegisterdDrivers = new ArrayList<>(); 
        LoggedInDrivers = new ArrayList<>(); 
        RegisteredPassengers = new ArrayList<>();
        LoggedInPassengers = new ArrayList<>();
        AllAdmins = new ArrayList<>(); 
        AllRides = new ArrayList<>(); 
        AllAreas = new ArrayList<>(); 
    }
    
    public static Database getInstance() {
        if (instance == null) {
            instance = new Database();
        }
        return instance;
    }
    
       public ArrayList<Driver> listAllPendingDrivers() {
        ArrayList<Driver> pendingDrivers = new ArrayList<>();
        for (int i = 0; i < RegisterdDrivers.size(); i++) {
            if (!RegisterdDrivers.get(i).getVerified()) {
                pendingDrivers.add(RegisterdDrivers.get(i));
            }
        }
        return pendingDrivers;
    }

    @Override
    public user getUser(String userName) {
        for (int i = 0; i < RegisteredPassengers.size(); i++) {
            if (userName.equals(RegisteredPassengers.get(i).getUsername())) {
                return RegisteredPassengers.get(i);
            }
        }

        for (int i = 0; i < RegisterdDrivers.size(); i++) {
            if (userName.equals(RegisterdDrivers.get(i).getUsername())) {
                return RegisterdDrivers.get(i);
            }
        }
        return null;
    }

    
    @Override
    public Boolean addPassengerAsRegistered(passenger passenger) {
        if (RegisteredPassengers.contains(passenger)) {
            return false;
        } else {
            RegisteredPassengers.add(passenger);
            return true;
        }
    }

    @Override
    public Boolean addPassengerAsLoggedIn(passenger passenger) {
        if (LoggedInPassengers.contains(passenger)) {
            return false;
        } else {
            LoggedInPassengers.add(passenger);
            return true;
        }
    }
    
    @Override
    public passenger getRegisteredPassenger(String userName) {
        for (int i = 0; i < RegisteredPassengers.size(); i++) {
            if (userName.equals(RegisteredPassengers.get(i).getUsername())) {
                return RegisteredPassengers.get(i);
            }
        }
        return null;
    }
    
    @Override
    public passenger getLoggedInPassenger(String userName) {
        for (int i = 0; i < LoggedInPassengers.size(); i++) {
            if (userName.equals(LoggedInPassengers.get(i).getUsername())) {
                return LoggedInPassengers.get(i);
            }
        }
        return null;
    }
    
    @Override       
    public Admin getAdmin(String username) {
        for (int i = 0; i < AllAdmins.size(); i++) {
            if (username.equals(AllAdmins.get(i).getUsername())) {
                return AllAdmins.get(i);
            }
        }
        return null;
    }
    
    @Override
    public Boolean addAdmin(Admin admin) {
        if (AllAdmins.contains(admin)) {
            return false;
        } else {
            AllAdmins.add(admin);
            return true;
        }
    }
    
    @Override
    public Boolean addRegisteredDriver(Driver driver) {
        if (RegisterdDrivers.contains(driver)) {
            return false;
        } else {
            RegisterdDrivers.add(driver);
            return true;
        }
    }

    @Override
    public Boolean addLoggedInDriver(Driver driver) {
        if (LoggedInDrivers.contains(driver)) {
            return false;
        } else {
            LoggedInDrivers.add(driver);
            return true;
        }
    }
    
    @Override
    public Driver getRegisteredDriver(String userName) {
        for (int i = 0; i < RegisterdDrivers.size(); i++) {
            if (userName.equals(RegisterdDrivers.get(i).getUsername())) {
                return RegisterdDrivers.get(i);
            }
        }
        return null;
    }

    @Override
    public Driver getLoggedInDriver(String userName) {
        for (int i = 0; i < LoggedInDrivers.size(); i++) {
            if (userName.equals(LoggedInDrivers.get(i).getUsername())) {
                return LoggedInDrivers.get(i);
            }
        }
        return null;
    }
    
    @Override
    public ArrayList<Ride> getAllRides() {
        return AllRides;
    }

    
    @Override
    public Ride getRide(int idx) {
        return AllRides.get(idx - 1);
    }

    @Override
    public void addRide(Ride ride) {
        AllRides.add(ride);
    }
    
    @Override
    public boolean AddArea(Area area){
        if(AllAreas.contains(area))  return false;
        AllAreas.add(area);
        return true;
    }
    
    @Override
    public Area getArea(String area){
        for(int i = 0; i < AllAreas.size(); i++){
            if(AllAreas.get(i).getAreaName().equals(area)){
                return AllAreas.get(i);
            }
        }
        Area _area = new Area(area);
        AllAreas.add(_area);
        return _area;
    }
    
    @Override
    public ArrayList<Area> getAllAreas(){
        return this.AllAreas;
    }
    
    @Override
    public ArrayList<passenger> RegisteredPassengers(){
        return RegisteredPassengers;
    }
    
    @Override
    public ArrayList<passenger> LoggedInPassengers(){
        return LoggedInPassengers;
    }
    
    @Override
    public ArrayList<Driver> RegisteredDrivers(){
        return RegisterdDrivers;
    }
    
    @Override
    public ArrayList<Driver> LoggedInDrivers(){
        return LoggedInDrivers;
    }
    
    @Override
    public Boolean isAvailable(String username) {
        for (int i = 0; i < RegisteredPassengers.size(); i++) {
            if (username.equals(RegisteredPassengers.get(i).getUsername())) {
                return false;
            }
        }

        for (int i = 0; i < RegisterdDrivers.size(); i++) {
            if (username.equals(RegisterdDrivers.get(i).getUsername())) {
                return false;
            } else {
            }
        }
        return true;
    }
}
