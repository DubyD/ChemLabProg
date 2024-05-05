package com.example.backend;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Department.
 * Full inventory list of chemicals.
 * Puts chemicals into their correctly
 * sorted categories.
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
    private HashMap<Integer, Room> rooms;


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

    public HashMap<Integer,Room> getRooms() {
        return this.rooms;
    }

    public void setRooms(HashMap<Integer,Room> rooms) {
        this.rooms = rooms;
    }


}