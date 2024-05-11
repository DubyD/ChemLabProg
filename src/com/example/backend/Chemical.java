

package com.example.backend;

import java.util.Arrays;
import java.util.HashMap;

/**
 * Represents a chemical stored in a facility. It includes details like manufacturer,
 * storage location, capacity, and safety information.
 * 
 * Author: Sukhdeep Singh
 */

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
    private HashMap<String, Boolean> hazardFlags = new HashMap<String, Boolean>();
    private boolean runningLow;

    private String purchaseDate;
    private String expirationDate;

    final String[] UNITS = {"ml", "g","oz","lb","mg","l","kg","gal","qt","pt","cup","tbsp","tsp","fl oz","mL","L","g","mg","kg","oz","lb","gal","qt","pt","cup","tbsp","tsp","fl oz"};



    /**
     * Constructs a chemical with comprehensive details including hazards.
     * Throws IllegalArgumentException if the size is negative or the unit is not recognized.
     *
     * @param name Name of the chemical
     * @param company Manufacturer of the chemical
     * @param room Storage room identifier
     * @param shelf Shelf identifier
     * @param containers Number of containers
     * @param amount Amount of chemical per container
     * @param unit Unit of measure for the amount
     * @param cas Chemical Abstracts Service (CAS) number
     * @param hazards Descriptive string of hazards
     */

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
        this.getRunningLow();

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



    /**
     * Constructor for creating a chemical without explicit hazard details.
     * Throws IllegalArgumentException if the size is negative or the unit is not recognized.
     * Constructor used for solution
     * 
     * @param name Name of the chemical
     * @param company Manufacturer of the chemical
     * @param room Storage room identifier
     * @param shelf Shelf identifier
     * @param size Amount of chemical
     * @param sizeUnit Unit of measure for the amount
     */
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

//-------------------------------------------------Getters and setters------------------------

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

    public boolean getSdsSheet() {
        return this.sdsSheet;
    }

    public void setSdsSheet(boolean sdsSheet) {
        this.sdsSheet = sdsSheet;
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

    /**
     * Calculates if the chemical is running low based on its size.
     * If the size is less than 2 (units), the chemical is considered running low.
     *
     * @return boolean indicating if the chemical is running low.
     */
    public boolean getRunningLow(){
        if(this.size < 2){
            this.runningLow = true;
        }else{
           this.runningLow = false;
        }
        return runningLow;
    }

        //
    public void setRunningLow(boolean isIt){
        this.runningLow = isIt;
    }
//-----------------------------------------------------------------------------



    /**
     * Updates the amount of chemical remaining.
     * Throws IllegalArgumentException if the new size is negative.
     *
     * @param newSize the new size of the chemical
     */

    public void updateSize(double newSize) {
        if (size >= 0) {
            this.size = newSize;

        } else {
            // Handle invalid size
            throw new IllegalArgumentException("Size must be a positive number.");
        }
    }

    // needed for SearchResultsPanel
    // made by Alex Comeau
    public boolean hasSDS() {
        return this.sdsSheet;
    }



    /**
     * Provides an array of the chemical's basic information for display or logging purposes.
     * Includes safety sheet presence, name, manufacturer, storage location, and other details.
     *
     * @return an array of String containing chemical details
     */

    public String[] asArray() {

        String hasSafety = "";
        if(this.sdsSheet){
            hasSafety = "X";
        }

        return new String[] { hasSafety, this.name, this.manufacturer, this.room,
                this.shelf, String.valueOf(this.containers), String.valueOf(size), this.sizeUnit, this.casNum, this.hazards};

    }



    /**
     * Provides a string representation of the chemical, detailing all relevant information.
     *
     * @return formatted string of the chemical's details
     */

    @Override
    public String toString() {
        return "Chemical{\n" +
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
