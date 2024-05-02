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