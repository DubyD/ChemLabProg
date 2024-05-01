/**This is the first draft for chemical class-Sukhdeep Singh */


package com.example.backend;

import java.util.List;

public class Chemical {
    private String name;
    private double size;
    private String sizeUnit;
    private String hazards;
    private boolean flammable;
    private List<Room> roomsStoredAt;
    private List<Shelf> shelvesStoredAt;
    private String sdsSheet;
    private String manufacturer;
    private String purchaseDate;
    private String expirationDate;

    //TODO add an attribute for the amount of jars
    public Chemical(String name, double size, String sizeUnit, String hazards, boolean flammable, List<Room> roomsStoredAt, List<Shelf> shelvesStoredAt, String sdsSheet, String manufacturer, String purchaseDate, String expirationDate) {
        
         // We can add more validation logic for hazards, etc., in futurer
        if (!isPositiveSize(size)) {
            throw new IllegalArgumentException("Size must be a positive number.");
        } else if (!isValidSizeUnit(sizeUnit)) {
            throw new IllegalArgumentException("Size unit must be either 'ml' or 'gm'.");
        } 
    
    else {
       

        this.name = name;
        this.size = size;
        this.sizeUnit = sizeUnit;
        this.hazards = hazards;
        this.flammable = flammable;
        this.roomsStoredAt = roomsStoredAt;
        this.shelvesStoredAt = shelvesStoredAt;
        this.sdsSheet = sdsSheet;
        this.manufacturer = manufacturer;
        this.purchaseDate = purchaseDate;
        this.expirationDate = expirationDate;}
    }

    // Getters and setters for all attributes
    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String getPurchaseDate() {
        return purchaseDate;
    }

    public void setPurchaseDate(String purchaseDate) {
        this.purchaseDate = purchaseDate;
    }

    public String getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(String expirationDate) {
        this.expirationDate = expirationDate;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getSize() {
        return size;
    }

    public void setSize(double size) {
        this.size = size;
    }

    public String getSizeUnit() {
        return sizeUnit;
    }

    public void setSizeUnit(String sizeUnit) {
        this.sizeUnit = sizeUnit;
    }

    public String getHazards() {
        return hazards;
    }

    public void setHazards(String hazards) {
        this.hazards = hazards;
    }

    public boolean isFlammable() {
        return flammable;
    }

    public void setFlammable(boolean flammable) {
        this.flammable = flammable;
    }

    public List<Room> getRoomsStoredAt() {
        return roomsStoredAt;
    }

    public void setRoomsStoredAt(List<Room> roomsStoredAt) {
        this.roomsStoredAt = roomsStoredAt;
    }

    public List<Shelf> getShelvesStoredAt() {
        return shelvesStoredAt;
    }

    public void setShelvesStoredAt(List<Shelf> shelvesStoredAt) {
        this.shelvesStoredAt = shelvesStoredAt;
    }

    

    public String getSdsSheet() {
        return sdsSheet;
    }

    public void setSdsSheet(String sdsSheet) {
        this.sdsSheet = sdsSheet;
    }

    // Validation methods, We can add more, this is just first draft
    private boolean isPositiveSize(double size) {
        return size > 0;
    }

    public void updateSize(double newSize) {
        if (isPositiveSize(newSize)) {
            this.size = newSize;
        } else {
            // Handle invalid size
            throw new IllegalArgumentException("Size must be a positive number.");
        }
    }
    
    // Validation method for sizeUnit
    private boolean isValidSizeUnit(String sizeUnit) {
    return sizeUnit.equalsIgnoreCase("ml") || sizeUnit.equalsIgnoreCase("gm");
    }

    public boolean hasSDS() {
        return sdsSheet != null;
    }

    
    // TODO these are all stubs, please implement them
    public double getCount() {
        return 1;
    }

    public void setCount(){
    
    }

    public String getCASNumber(){
        return "1";
    }

    public void setCASNumber(){
    
    }

    public String getHazard(){
        return "Hazard";
    }

    public void setHazard(){
    
    }


}
