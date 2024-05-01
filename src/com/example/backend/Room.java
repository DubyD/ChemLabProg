package com.example.backend;

import java.util.ArrayList;
import java.util.List;

public class Room {
    private int roomNumber;
    private List<Shelf> shelves;
    private int capacity;
    private List<TakeOutSlip> slips;

    public Room(int roomNumber, int capacity) {
        this.roomNumber = roomNumber;
        this.capacity = capacity;
        this.shelves = new ArrayList<>();
        this.slips = new ArrayList<>();
    }

    // Method to add a shelf to the room
    public void addShelf(Shelf shelf) {
        shelves.add(shelf);
    }

    // Method to remove a shelf from the room
    public void removeShelf(Shelf shelf) {
        shelves.remove(shelf);
    }

    // Getters and setters for all attributes
    public int getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(int roomNumber) {
        this.roomNumber = roomNumber;
    }
    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public List<Shelf> getShelves() {
        return shelves;
    }

    public List<TakeOutSlip> getSlips() {
        return slips;
    }

    public String toString() {
        return "Room number: " + roomNumber + ", capacity: " + capacity;
    }
}

