package com.example.backend;

import java.util.List;
import java.util.ArrayList;
/**
 * Represents a specific hazard associated with one or more chemicals.
 * It includes details about the type of hazard, a description, storage requirements,
 * and a list of chemicals and emergency contacts related to the hazard.
 * 
 * @author ian chung
 */
public class Hazard{
    private List<Chemical> dangers;
    private String hazardType;
    private String description;
    private String storageRequirements;
    private List<String> emergencyContact;


    //Default constructor
    public Hazard() {
        this.dangers = new ArrayList<>();
        this.emergencyContact = new ArrayList<>();
        this.hazardType = "";
        this.description = "";
        this.storageRequirements = "";
    }
    

    /**
     * Constructor to initialize a hazard with its type, description, and storage requirements.
     *
     * @param htype The type of the hazard.
     * @param d Description of the hazard.
     * @param sr Storage requirements for managing the hazard.
     */
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

    public void addEmergencyContact(String contact) {
        if (contact != null && !contact.trim().isEmpty()) {
            this.emergencyContact.add(contact);
        }
    }

    public void removeEmergencyContact(String contact) {
        this.emergencyContact.remove(contact);
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
    

    /**
     * Provides a simple and clear string representation of the hazard, including all relevant details.
     *
     * @return formatted string containing details about the hazard, associated chemicals, and emergency contacts.
     */
    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        result.append("Hazard{");
        result.append("\nType: ").append(hazardType);
        result.append("\nDescription: ").append(description);
        result.append("\nStorage Requirements: ").append(storageRequirements);

        // Append chemicals information
        result.append("\nChemicals: [");
        if (dangers.isEmpty()) {
            result.append("None");
        } else {
            for (Chemical chemical : dangers) {
                result.append(chemical.getName()).append(", ");
            }
            result.delete(result.length() - 2, result.length()); // Remove the last comma and space
        }
        result.append("]");

        // Append emergency contacts information
        result.append("\nEmergency Contacts: [");
        if (emergencyContact.isEmpty()) {
            result.append("None");
        } else {
            for (String contact : emergencyContact) {
                result.append(contact).append(", ");
            }
            result.delete(result.length() - 2, result.length()); // Remove the last comma and space
        }
        result.append("]");
        result.append("\n}");

        return result.toString();
    }
}
