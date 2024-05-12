package com.example.backend;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a shelf within a room, designed to store chemicals.
 * Each shelf has a number, a capacity, and can hold a list of chemicals up to its capacity.
 *
 * @author 
 */

public class Shelf {
    private String shelfNumber;
    private int capacity;
    private ArrayList<Chemical> chemicals;

    //Default constructor
    
    public Shelf() {
        this.shelfNumber = "";
        this.capacity = 10; // Default capacity
        this.chemicals = new ArrayList<>();
    }
    

    /**
     * Constructs a Shelf with a specified number and capacity.
     * Initializes an empty list for storing chemicals.
     *
     * @param shelfNumber The unique identifier for the shelf
     * @param capacity The maximum number of chemicals the shelf can hold
     */

    public Shelf(String shelfNumber, int capacity) {
        this.shelfNumber = shelfNumber;
        this.capacity = capacity;
        this.chemicals = new ArrayList<>();
    }


    /**
     * Constructs a Shelf with a default capacity.
     * The default capacity is set to 10, based on a recommended value.
     *
     * @param shelfNumber The unique identifier for the shelf
     */
    public Shelf(String shelfNumber){
        this(shelfNumber, 10);
    }


    // Method to add a chemical to the shelf
    public void addChemical(Chemical chemical) {
        chemicals.add(chemical);
    }

    // Method to remove a chemical from the shelf
    public void removeChemical(Chemical chemical) {
        chemicals.remove(chemical);
    }

    // Method to check if the shelf has reached its capacity
    public boolean isFull() {
        return chemicals.size() >= capacity;
    }

    // Getters and setters for all attributes

    public String getShelfNumber() {
        return shelfNumber;
    }

    public void setShelfNumber(String shelfNumber) {
        this.shelfNumber = shelfNumber;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public ArrayList<Chemical> getChemicals() {
        return chemicals;
    }

    /**
     * Provides a string representation of the shelf with its basic details.
     *
     * @return String that represents the shelf's basic information
     */
    public String toString() {
        return "Shelf number: " + shelfNumber + ", capacity: " + capacity;
    }
}
