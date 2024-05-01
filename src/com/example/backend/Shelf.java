package com.example.backend;

import java.util.ArrayList;
import java.util.List;

public class Shelf {
    private int shelfNumber;
    private int capacity;
    private List<Chemical> chemicals;

    public Shelf(int shelfNumber, int capacity) {
        this.shelfNumber = shelfNumber;
        this.capacity = capacity;
        this.chemicals = new ArrayList<>();
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

    public int getShelfNumber() {
        return shelfNumber;
    }

    public void setShelfNumber(int shelfNumber) {
        this.shelfNumber = shelfNumber;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public List<Chemical> getChemicals() {
        return chemicals;
    }

    public String toString() {
        return "Shelf number: " + shelfNumber + ", capacity: " + capacity;
    }
}
