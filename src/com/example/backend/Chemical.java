/**This is the first draft for chemical class-Sukhdeep Singh */

package com.example.backend;

import java.util.Arrays;
import java.util.HashMap;

public class Chemical {

    private String sdsSheet;
    private String name;
    private String manufacturer;
    private String room;
    private String shelf;
    private int containers;
    private double size;
    private String sizeUnit;
    private String casNum;
    private String hazards;
    private boolean flammable = false;
    private HashMap<String, Boolean> hazardFlags = new HashMap<String, Boolean>();

    private String purchaseDate;
    private String expirationDate;

    final String[] UNITS = {"ml", "g","oz","lb","mg","l","kg","gal","qt","pt","cup","tbsp","tsp","fl oz","mL","L","g","mg","kg","oz","lb","gal","qt","pt","cup","tbsp","tsp","fl oz"};

    //Auto Constructor used in sorter class
    public Chemical(String name, String company, String room, String shelf, int containers, Double amount, String unit, String cas, String hazards){

        this.name = name;
        this.manufacturer = company;
        this.room = room;
        this.shelf = shelf;
        this.containers = containers;
        this.size = amount;
        this.sizeUnit = unit;
        this.casNum = cas;

        this.hazards = hazards;




        //Sets specific Hazards
        this.setHazardFlags(hazards);

        if(size < 0){
            throw new IllegalArgumentException("Size must be a positive number.");
        }
        if(!Arrays.asList(UNITS).contains(sizeUnit) || sizeUnit == null){
            //default to g
            sizeUnit = "g";
        }
    }

    //Changed to using hash map for hazard vals
    private void setHazardFlags(String list){
        String[] pieces = list.split(",");
        for(String next : pieces){
            String[] parts = next.split(" ");
            for(String bits : parts){
                if(bits.equals("eye")){
                    hazardFlags.put("eye", true);
                }
                if(bits.equals("skin")){
                    hazardFlags.put(bits, true);
                }
                if(bits.equals("corrosive")){
                    hazardFlags.put(bits, true);
                }
                if(bits.equals("combustible")){
                    hazardFlags.put(bits, true);
                }
                if(bits.equals("oral") || bits.equals("digestive")){
                    hazardFlags.put(bits, true);
                }
                if(bits.equals("flammable")){
                    hazardFlags.put(bits, true);
                }
                if(bits.equals("aquatic")){
                    hazardFlags.put(bits, true);
                }
                if(bits.equals("respitory")){
                    hazardFlags.put(bits, true);
                }
            }
        }
    }

    public Chemical(String name, String room, String shelf, double size, String sizeUnit){
        this.name = name;
        this.room = room;
        this.shelf = shelf;
        this.size = size;
        this.sizeUnit = sizeUnit;


        if(size < 0){
            throw new IllegalArgumentException("Size must be a positive number.");
        }
        if(!Arrays.asList(UNITS).contains(sizeUnit) || sizeUnit == null){
            //default to g
            sizeUnit = "g";
        }

    }

    public HashMap<String, Boolean> getHazardFlags(){
        return hazardFlags;
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

    public void updateSize(double newSize) {
        if (size > 0) {
            this.size = newSize;
        } else {
            // Handle invalid size
            throw new IllegalArgumentException("Size must be a positive number.");
        }
    }


    public String getRoom() {
        return this.room;
    }

    public void setRoom(String room) {
        this.room = room;
    }

    public String getShelf() {
        return this.shelf;
    }

    public void setShelf(String shelf) {
        this.shelf = shelf;
    }

    public int getContainers() {
        return this.containers;
    }

    public void setContainers(int containers) {
        this.containers = containers;
    }

    public String getCasNum() {
        return this.casNum;
    }

    public void setCasNum(String casNum) {
        this.casNum = casNum;
    }

    public boolean getFlammable() {
        return this.flammable;
    }


    public String[] getUNITS() {
        return this.UNITS;
    }



    // needed for SearchResultsPanel
    // made by Alex Comeau
    public boolean hasSDS() {
        return sdsSheet != null;
    }

    public String[] asArray() {
        return new String[] { hasSDS() ? "Yes" : "No", name, manufacturer, room,
                shelf, "Amount of Jars", String.valueOf(size), sizeUnit, "CAS #s", hazards };

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
