/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.CarApp;
import com.example.CustomerSubsystem.Admin;
import com.example.CustomerSubsystem.Driver;
import com.example.CustomerSubsystem.UserStatus;
import com.example.CustomerSubsystem.passenger;
import com.example.DatabaseManager.Database;
import com.example.DatabaseManager.DatabaseInterface;
import java.util.ArrayList;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author admin
 */
@RestController
public class AuthenticationManager {
    private static final DatabaseInterface manager = Database.getInstance();
    private static ArrayList<Driver> loggedInDrivers = new ArrayList<>();
    private static ArrayList<passenger> loggedInPassengers = new ArrayList<>();
    public static Admin currentAdmin;
    
    @PostMapping("/register/passenger")
    public static String registerAsPassenger(@RequestBody passenger user) {
        if (manager.isAvailable(user.getUsername())) {
            manager.addPassengerAsRegistered(user);
            return "Registered successfully";
        } else {
            return "The username you entered is unavailable";
        }
    }
    
    @PostMapping("/register/driver")
    public static String registerAsDriver(@RequestBody Driver user) {
        if (manager.isAvailable(user.getUsername())) {
            System.err.println("numodpass " + user.getNumberOfPassengers());
            manager.addRegisteredDriver(user);
            return "Registered successfully";
        } else {
            return "The username you entered is unavailable";
        }
    }

    //@RequestMapping("/login/passenger")
    @PostMapping("/login/passenger/{userName}/{password}")
    public static String loginAsPassenger(@PathVariable String userName, @PathVariable String password) {
        passenger tempPassenger = manager.getRegisteredPassenger(userName);
        loggedInPassengers = manager.LoggedInPassengers();
        if (tempPassenger == null) {
            return "The username you entered is not registered";
        }
        if (tempPassenger.getUserStatus() == UserStatus.suspended) {
            return "This account is currently suspended, Please contact the admin";
        }
        if (tempPassenger.getPassword().equals(password)) {
            if (loggedInPassengers.contains(tempPassenger)) {
                return "User already logged in";
            }
            Database.getInstance().addPassengerAsLoggedIn(tempPassenger);
            return "Logged in successfully";
        } else {
            return "You entered a wrong password";
        }
    }

    @PostMapping("/login/driver/{userName}/{password}")
    public static String loginAsDriver(@PathVariable String userName, @PathVariable String password) {
        loggedInDrivers = manager.LoggedInDrivers();
        Driver driver = manager.getRegisteredDriver(userName);
        if (driver == null) {
            return "The username you entered is not registered";
        }
        if (driver.getUserStatus() == UserStatus.suspended || !driver.getVerified()) {
            return "This account is currently suspended or not verified yet, Please contact the admin";
        }
        if (driver.getPassword().equals(password)) {
            if (loggedInDrivers.contains(driver)) {
                return "User already logged in";
            }
            manager.addLoggedInDriver(driver);
            return "Logged in successfully";
        } 
        else {
            return "You entered a wrong password";
        }
    }

    @PostMapping("/login/Admin/{userName}/{password}")
    public static String loginAsAdmin(@PathVariable String userName, @PathVariable String password) {
        Admin admin = manager.getAdmin(userName);
        if (admin == null) {
            return "The username you entered is not registered";
        }
        
        else if (currentAdmin != null) {
            return "User already logged in";
        }
        
        else if (admin.getPassword().equals(password) && currentAdmin == null) {
            currentAdmin = admin;
            return "Logged in successfully";
        }
        
        else {
            return "You entered a wrong password";
        }
    }

    @PostMapping("/logout/{userName}")
    public static String logout(@PathVariable String userName) {
        if (currentAdmin.getUsername().equals(userName)) {
            currentAdmin = null;
            return "Logged out successfully";
        }

        for (int i = 0; i < loggedInDrivers.size(); i++) {
            if (loggedInDrivers.get(i).getUsername().equals(userName)) {
                loggedInDrivers.remove(loggedInDrivers.get(i));
                return "Logged out successfully";
            }
        }

        for (int i = 0; i < loggedInPassengers.size(); i++) {
            if (loggedInPassengers.get(i).getUsername().equals(userName)) {
                loggedInPassengers.remove(loggedInPassengers.get(i));
                return "Logged out successfully";
            }
        }

        return "You are not logged in";
    }

    static Admin getCurrentAdmin(){
        return currentAdmin;
    }
}
