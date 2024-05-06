package com.example.backend;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Department.
 * Full inventory list of chemicals.
 * Puts chemicals into their correctly
 * sorted categories.
 * @authors Evelyn T, Will D, Alex C
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


    public Department(ArrayList<Chemical> chems){
        this.chems = chems;
        rooms = new HashMap<>(23);
        flammable = new ArrayList<>();
        combustible = new ArrayList<>();
        oralHazard = new ArrayList<>();
        aquaticHazard = new ArrayList<>();
        corrosive = new ArrayList<>();
        skinHazard = new ArrayList<>();
        eyeHazard = new ArrayList<>();
        digestiveHazard = new ArrayList<>();
        respiratoryHazard = new ArrayList<>();
        for(Chemical c : chems){
            HashMap m = c.getHazardFlags();
            String room = c.getRoom();
            String shelf = c.getShelf();
            if(rooms.containsKey(room)){
                Room r = (Room) m.get(room);
                HashMap shelves = r.getShelves();
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


}