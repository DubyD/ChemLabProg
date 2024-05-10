/**This is the first draft for chemical class-Sukhdeep Singh */

package com.example.backend;

import java.util.Arrays;
import java.util.HashMap;

public class Chemical {

    private boolean sdsSheet;
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
    public Chemical(String name, String company, String room, String shelf, int containers, double amount, String unit, String cas, String hazards){

        this.name = name;
        this.manufacturer = company;
        this.room = room;
        this.shelf = shelf;
        this.containers = containers;
        this.size = amount;
        this.sizeUnit = unit;
        this.casNum = cas;
        this.sdsSheet = false;

        this.hazards = hazards;
        hazardFlags = new HashMap<>(23);

            //Sets specific Hazards
        this.setHazardFlags(hazards);

        if(size < 0){
            throw new IllegalArgumentException("Size must be a positive number.");
        }
        if(!Arrays.asList(UNITS).contains(sizeUnit) || sizeUnit == null){
            //default to g
            this.sizeUnit = "g";
        }
    }

        //Constructor used for solution
    public Chemical(String name, String company, String room, String shelf, double size, String sizeUnit){
        this.name = name;
        this.manufacturer = company;
        this.room = room;
        this.shelf = shelf;
        this.size = size;
        this.sizeUnit = sizeUnit;

        hazardFlags = new HashMap<>(23);

        if(size < 0){
            throw new IllegalArgumentException("Size must be a positive number.");
        }
        if(!Arrays.asList(UNITS).contains(sizeUnit) || sizeUnit == null){
            //default to g
            this.sizeUnit = "g";
        }

    }

        //Changed to using hash map for hazard vals
        public void setHazardFlags(String list){
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

    public boolean getSdsSheet() {
        return this.sdsSheet;
    }

    public void setSdsSheet(boolean sdsSheet) {
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
        return this.sdsSheet;
    }

    public String[] asArray() {

        String hasSafety = "";
        if(this.sdsSheet){
            hasSafety = "X";
        }

        return new String[] { hasSafety, this.name, this.manufacturer, this.room,
                this.shelf, String.valueOf(this.containers), String.valueOf(size), this.sizeUnit, this.casNum, this.hazards};

    }


    @Override
    public String toString() {
        return "Chemical{" +
                "name= " + this.name + '\n' +
                "company= "+ this.manufacturer + '\n' +
                "location= " + this.room + '\n' +
                "shelf= " + this.shelf + '\n' +
                "units= " + this.containers + '\n' +
                "amount= " + this.size + this.sizeUnit + '\n' +
                "casNumber= " + this.casNum + '\n' +
                "hazards= " + hazards + '\n' +
                "}";
    }

}
