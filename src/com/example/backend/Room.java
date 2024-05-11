package com.example.backend;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Represents a room in a library.
 * A room contains shelves and has the capability to manage inventory and track takeout slips.
 *
 * @author 
 */


public class Room {
    private String roomNumber; 
    private HashMap<String, Shelf> shelves; 
    private int capacity;
    private ArrayList<TakeOutSlip> slips;

    /**
     * Constructs a new Room with a specified number and capacity.
     * Initializes the shelves and slips collections.
     *
     * @param roomNumber The unique identifier for the room
     * @param capacity The maximum number of shelves the room can hold
     */
    public Room(String roomNumber, int capacity) {
        this.roomNumber = roomNumber;
        this.capacity = capacity;
        this.shelves = new HashMap<>(23);
        this.slips = new ArrayList<>();
    }


    /**
     * Constructs a new Room with a default capacity of 100.
     *
     * @param roomNumber The unique identifier for the room
     */
    public Room(String roomNumber){
        this(roomNumber, 100);
    }

    // Method to add a shelf to the room
    public void addShelf(String shelfNum, Shelf shelf) {
        shelves.put(shelfNum, shelf);
    }

    // Method to remove a shelf from the room
    public void removeShelf(String shelf) {
        shelves.remove(shelf);
    }

    // Getters and setters for all attributes
    public String getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(String roomNumber) {
        this.roomNumber = roomNumber;
    }
    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public HashMap getShelves() {
        return shelves;
    }

    public ArrayList<TakeOutSlip> getSlips() {
        return slips;
    }



    /**
     * Provides a string representation of the room with its basic details.
     *
     * @return String that represents the room's basic information
     */
    public String toString() {
        return "Room number: " + roomNumber + ", capacity: " + capacity;
    }
}

