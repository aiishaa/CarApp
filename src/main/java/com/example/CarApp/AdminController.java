/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.CarApp;

import com.example.CustomerSubsystem.Admin;
import com.example.DatabaseManager.Database;
import java.util.ArrayList;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author admin
 */

@RestController
public class AdminController {
    public Admin currentAdmin;
    @PostMapping("/admin/verify/{username}")
    public String verifyDriver(@PathVariable String username) {
        currentAdmin = AuthenticationManager.getCurrentAdmin();
        if (currentAdmin == null) {
            return "You have no access to this function";
        }
        if (currentAdmin.verifyDriver(username)) {
            return "User verified successfully";
        } else {
            return "User could not be found";
        }
    }
    
    @PostMapping("/admin/suspend/{username}")
    public String suspend(@PathVariable String username) {
        currentAdmin = AuthenticationManager.currentAdmin;
        if (currentAdmin == null) {
            return "You have no access to this function";
        }
        if (currentAdmin.suspendUser(username)) {
            AuthenticationManager.logout(username);
            return "User suspended successfully";
        } else {
            return "User could not be found";
        }
    }
    
    @PostMapping("/admin/applyDiscount/{areaName}")
    public String applyDiscount(@PathVariable String areaName) {
        currentAdmin = AuthenticationManager.currentAdmin;
        if (currentAdmin == null) {
            return "You have no access to this function";
        }
        currentAdmin.applyDiscount(Database.getInstance().getArea(areaName));
        return "A discount will be applied to all rides headed to " + areaName;
    }
    
    @PostMapping("/admin/getAllRides")
    public ArrayList<String> getAllRides() {
        currentAdmin = AuthenticationManager.currentAdmin;
        if (currentAdmin == null) {
            ArrayList<String> temp = new ArrayList<>();
            temp.add("You have no access to this function");
            return temp;
        }
        return currentAdmin.getSystemRides();
    }

    @PostMapping("/admin/getRideEvents/{index}")
    public ArrayList<String> getRideEvents(@PathVariable int index) {
        currentAdmin = AuthenticationManager.currentAdmin;
        if (currentAdmin == null) {
            ArrayList<String> temp = new ArrayList<>();
            temp.add("You have no access to this function");
            return temp;
        }
        return currentAdmin.getRideEvents(index);
    }
}
