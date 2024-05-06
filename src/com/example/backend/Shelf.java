package com.example.backend;

import java.util.ArrayList;
import java.util.List;

public class Shelf {
    private String shelfNumber;
    private int capacity;
    private ArrayList<Chemical> chemicals;

    public Shelf(String shelfNumber, int capacity) {
        this.shelfNumber = shelfNumber;
        this.capacity = capacity;
        this.chemicals = new ArrayList<>();
    }
    //constructor for new shelf with no cap
    //consult miss web for a good default cap
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

    public String toString() {
        return "Shelf number: " + shelfNumber + ", capacity: " + capacity;
    }
}
