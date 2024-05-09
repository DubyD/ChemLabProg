package com.example.backend;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Department.
 * Full inventory list of chemicals.
 * Puts chemicals into their correctly
 * sorted categories.
 * @authors Evelyn T, Will D(minimal), Alex C
 */
public class Department{
    private ArrayList<Chemical> chems;
    private ArrayList<Chemical> flammable;
    private ArrayList<Chemical> corrosive;
    private ArrayList<Chemical> skinHazard;
    private ArrayList<Chemical> eyeHazard;
    private ArrayList<Chemical> digestiveHazard;
    private ArrayList<Chemical> respiratoryHazard;
    private ArrayList<Chemical> aquaticHazard;
    private ArrayList<Chemical> combustible;
    private ArrayList<Chemical> oralHazard;
    private HashMap<String, Room> rooms;
    private ArrayList<String> roomNums;


    /**
     * Class department initializes with a full list of chemicals.
     * Constructor initializes rooms and puts the chemicals
     * in the correct room, shelf, as well as hazard categories
     * @param chems - chemical objects list
     * @author - Evelyn Totman
     */
    public Department(ArrayList<Chemical> chems){
        //initialize lists
        this.chems = chems;
        this.rooms = new HashMap<>(23);
        this.roomNums = new ArrayList<>();
        this.flammable = new ArrayList<>();
        this.combustible = new ArrayList<>();
        this.oralHazard = new ArrayList<>();
        this.aquaticHazard = new ArrayList<>();
        this.corrosive = new ArrayList<>();
        this.skinHazard = new ArrayList<>();
        this.eyeHazard = new ArrayList<>();
        this.digestiveHazard = new ArrayList<>();
        this.respiratoryHazard = new ArrayList<>();

        for(Chemical c : chems){
            HashMap m = c.getHazardFlags();
            String room = c.getRoom();
            String shelf = c.getShelf();
            //initializes rooms and shelves
            //if room or shelf already exists
            //add chem to it
            if(rooms.containsKey(room)){
                Room r = (Room) m.get(room);
                HashMap<String, Shelf> shelves = r.getShelves();
                if(shelves.containsKey(shelf)){
                    Shelf s = (Shelf) shelves.get(shelf);
                    s.addChemical(c);
                }
                else {
                    Shelf temp = new Shelf(shelf);
                    temp.addChemical(c);
                    shelves.put(shelf, temp);
                }
            }
            else {
                Room r = new Room(room);
                Shelf s = new Shelf(shelf);
                r.addShelf(shelf, s);
                rooms.put(room, r);
                roomNums.add(room);
            }
            if(m.containsKey("eye")){
                eyeHazard.add(c);
            }
            if(m.containsKey("skin")){
                skinHazard.add(c);
            }
            if(m.containsKey("corrosive")){
                corrosive.add(c);
            }
            if(m.containsKey("combustible")){
                combustible.add(c);
            }
            if(m.containsKey("oral")){
                oralHazard.add(c);
            }
            if(m.containsKey("digestive")){
                digestiveHazard.add(c);
            }
            if(m.containsKey("flammable")){
                flammable.add(c);
            }
            if(m.containsKey("aquatic")){
                aquaticHazard.add(c);
            }
            if(m.containsKey("respiratory")){
                respiratoryHazard.add(c);
            }
        }
    }

    public Department(){
        this(new ArrayList<>());
    }

    //getters and setters Alex Comeau
    public ArrayList<Chemical> getChems() {
        return this.chems;
    }

    public void setChems(ArrayList<Chemical> chems) {
        this.chems = chems;
    }

    public ArrayList<Chemical> getFlammable() {
        return this.flammable;
    }

    public void setFlammable(ArrayList<Chemical> flammable) {
        this.flammable = flammable;
    }

    public ArrayList<Chemical> getCorrosive() {
        return this.corrosive;
    }

    public void setCorrosive(ArrayList<Chemical> corrosive) {
        this.corrosive = corrosive;
    }

    public ArrayList<Chemical> getSkinHazard() {
        return this.skinHazard;
    }

    public void setSkinHazard(ArrayList<Chemical> skinHazard) {
        this.skinHazard = skinHazard;
    }

    public ArrayList<Chemical> getEyeHazard() {
        return this.eyeHazard;
    }

    public void setEyeHazard(ArrayList<Chemical> eyeHazard) {
        this.eyeHazard = eyeHazard;
    }

    public ArrayList<Chemical> getDigestiveHazard() {
        return this.digestiveHazard;
    }

    public void setDigestiveHazard(ArrayList<Chemical> digestiveHazard) {
        this.digestiveHazard = digestiveHazard;
    }

    public ArrayList<Chemical> getRespiratoryHazard() {
        return this.respiratoryHazard;
    }

    public void setRespiratoryHazard(ArrayList<Chemical> respiratoryHazard) {
        this.respiratoryHazard = respiratoryHazard;
    }

    public ArrayList<Chemical> getAquaticHazard() {
        return this.aquaticHazard;
    }

    public void setAquaticHazard(ArrayList<Chemical> aquaticHazard) {
        this.aquaticHazard = aquaticHazard;
    }

    public ArrayList<Chemical> getCombustible() {
        return this.combustible;
    }

    public void setCombustible(ArrayList<Chemical> combustible) {
        this.combustible = combustible;
    }

    public ArrayList<Chemical> getOralHazard() {
        return this.oralHazard;
    }

    public void setOralHazard(ArrayList<Chemical> oralHazard) {
        this.oralHazard = oralHazard;
    }

    public HashMap<String,Room> getRooms() {
        return this.rooms;
    }

    public void setRooms(HashMap<String,Room> rooms) {
        this.rooms = rooms;
    }

    //to string and equals only check
    //full list as of now
    //Evelyn
    @Override
    public String toString() {
        return chems.toString();
    }

    @Override
    public boolean equals(Object obj) {
        if(obj == null) return false;
        if(obj == this) return true;
        if(obj.getClass().isInstance(new Department())){
            Department t = (Department) obj;
            return chems.equals(t.chems);
        }
        return false;
    }
}