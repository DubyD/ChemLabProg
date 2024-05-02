/**This is the first draft for chemical class-Sukhdeep Singh */

package com.example.backend;

import java.util.List;

public class Chemical {

    private String sdsSheet;
    private String name;
    private String manufacturer;
    private String roomsStoredAt;
    private String shelvesStoredAt;
    private int containers;
    private double size;
    private String sizeUnit;
    private String casNum;
    private String hazards;
    private boolean flammable;
    private boolean corrosive;
    private boolean skinIrritant;
    private boolean eyeIrritant;
    private boolean oralHazard;
    private boolean combustible;
    private boolean aquaticHazard;
    private boolean respitoryHazard;

    private String purchaseDate;
    private String expirationDate;


        //SDS,Chemical,Company,Room,Location,Amount of Jars,Amount,Unit,CAS #s,Hazard

        //Auto Constructor used in sorter class
    public Chemical(String name, String company, String room, String shelf, int containers, Double amount, String unit, String cas, String hazards){
        this.name = name;
        this.manufacturer = company;
        this.containers = containers;
        this.size = amount;
        this. sizeUnit = unit;
        this.casNum = cas;
        this.hazards = hazards;
            //Sets specific Hazards
        this.setHazards(hazards);

    }

    // Getters and setters for all attributes


    private void setHazards(String list){
        String[] pieces = list.split(",");
        for(String next : pieces){
            String[] parts = next.split(" ");
            for(String bits : parts){
                if(bits.equals("eye")){
                    this.eyeIrritant = true;
                }
                if(bits.equals("skin")){
                    this.skinIrritant = true;
                }
                if(bits.equals("corrosive")){
                    this.corrosive = true;
                }
                if(bits.equals("combustible")){
                    this.combustible = true;
                }
                if(bits.equals("oral") || bits.equals("digestive")){
                    this.oralHazard = true;
                }
                if(bits.equals("flammable")){
                    this.flammable = true;
                }
                if(bits.equals("aquatic")){
                    this.aquaticHazard = true;
                }
                if(bits.equals("respitory")){
                    this.respitoryHazard = true;
                }
            }
        }
    }
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

    // needed for SearchResultsPanel
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
