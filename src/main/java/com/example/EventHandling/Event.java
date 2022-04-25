/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.EventHandling;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author admin
 */
public class Event {
    private EventName eventName;
    private Map<String, String> attributes = new HashMap<>();

    public void addAttribute(String attribute, String value) {
        attributes.put(attribute, value);
    }

    public void setName(EventName eventName) {
        this.eventName = eventName;
    }
    
    public String toString() {
        String result = "";
        if (eventName == EventName.AddPrice) {
            result += "Added Price: ";

        } else if (eventName == EventName.AcceptOffer) {
            result += "Accepted Offer: ";

        } else if (eventName == EventName.ArrivedToUserLocation) {
            result += "Arrived To Source: ";

        } else {
            result += "Arrived To Destination: ";

        }
        boolean flag = false;
        for (Map.Entry<String, String> set : attributes.entrySet()) {
            if (flag) {
                result += " || ";
            }
            result += set.getKey() + ": " + set.getValue();
            flag = true;
        }

        return result;
    }
}
