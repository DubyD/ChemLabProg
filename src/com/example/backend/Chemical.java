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

    public Chemical(String name, double size, String sizeUnit, String hazards, boolean flammable, String sdsSheet, String manufacturer, String purchaseDate, String expirationDate) {
        this.name = name;
        this.size = size;
        this.sizeUnit = sizeUnit;
        this.hazards = hazards;
        this.flammable = flammable;
        this.roomsStoredAt = new ArrayList<>();
        this.shelvesStoredAt = new ArrayList<>();
        this.sdsSheet = sdsSheet;
        this.manufacturer = manufacturer;
        this.purchaseDate = purchaseDate;
        this.expirationDate = expirationDate;

        validateSize(size);
        validateSizeUnit(sizeUnit);
    }

    // Getters and setters for all attributes


    public List<Room> getRoomsStoredAt() {
        return roomsStoredAt;
    }

    public void addRoomStoredAt(Room room) {
        roomsStoredAt.add(room);
    }

    public List<Shelf> getShelvesStoredAt() {
        return shelvesStoredAt;
    }

    public void addShelfStoredAt(Shelf shelf) {
        shelvesStoredAt.add(shelf);
    }


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

    // needed for RoomPanel
    // made by Alex Comeau
    public boolean hasSDS() {
        return sdsSheet != null;
    }

    public String[] asArray() {
        return new String[] { hasSDS() ? "Yes" : "No", name, manufacturer, roomsStoredAt.toString(),
                shelvesStoredAt.toString(), "Amount of Jars", String.valueOf(size), sizeUnit, "CAS #s", hazards };

    }

    // TODO these are all stubs, please implement them
    public double getCount() {
        return 1;
    }

    public void setCount() {

    }

    public String getCASNumber() {
        return "1";
    }

    public void setCASNumber() {

    }

    public String getHazard() {
        return "Hazard";
    }

    public void setHazard() {

    }

}
