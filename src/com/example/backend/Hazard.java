package com.example.backend;

import java.util.List;
import java.util.ArrayList;
/**
 * @author ian chung
 */
public class Hazard{
    private List<Chemical> dangers;
    private String hazardType;
    private String description;
    private String storageRequirements;
    private List<String> emergencyContact;

    public Hazard(String htype, String d, String sr){
        this.hazardType = htype;
        this.description = d;
        this.storageRequirements = sr;
        this.dangers = new ArrayList<>();
        this.emergencyContact = new ArrayList<>();
    }

    public List<String> getEmergencyContacts(){
        return this.emergencyContact;
    }

    public String updateDescription(String s){
        if(s != null){
            this.description = s;
            return this.description;
        }
        else{
            return this.description;
        }
    }

    public String getDescription(){
        return this.description;
    }

    public String updateStorageRequirements(String s){
        if(s != null){
            this.storageRequirements = s;
            return this.storageRequirements;
        }
        else{
            return this.storageRequirements;
        }
    }

    public String getStorageRequirements(){
        return this.storageRequirements;
    }

    public String getHazardType(){
        return this.hazardType;
    }

    public void addChemical(Chemical c){
        this.dangers.add(c);
    }

    public void removeChemical(Chemical c){
        this.dangers.remove(c);
    }
    
}
