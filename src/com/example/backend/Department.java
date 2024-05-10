package com.example.backend;

import javax.xml.validation.Schema;
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
    private ArrayList<Solution> comboChem;
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


    public static final String chemFile = "com/example/Data/updated_data.csv";
    public static final String solutionFile = "com/example/Data/new_solutions_Cab.csv";


    /**
     * Class department initializes with a full list of chemicals.
     * Constructor initializes rooms and puts the chemicals
     * in the correct room, shelf, as well as hazard categories
     * @param chems - chemical objects list
     * @author - Evelyn Totman
     */
    public Department(){

            //initialize list then adds Chems from the .csv file
        this.chems = new ArrayList<>();
        for(String next: Sorter.readInv(chemFile)){
            Solution polymorph = Sorter.chemLab(next);
            this.chems.add(polymorph);
        }
            //initializes list then adds Solutions from .csv file
        this.comboChem = new ArrayList<>();
        for(String next: Sorter.readInv(solutionFile)){
            Chemical polymorph = Sorter.wetLabDoc(next);
            this.comboChem.add(polymorph);
        }

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

            //Hashmaps the two lists to appropriate Arrays
        this.cartographer(this.chems);
        this.cartographer(this.comboChem);
    }


        //Evee I moved your work down here so I can
        //recreate the mapping with solutions
    private void cartographer(ArrayList<Chemical> workingSpace){
        for(Chemical c : workingSpace){
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


//getters and setters Alex Comeau---------------------------------------------------------
    public ArrayList<Chemical> getChems() {
        return this.chems;
    }
    public void setChems(ArrayList<Chemical> chems) {
        this.chems = chems;
    }
    public void addChem(Chemical chem){
        this.chems.add(chem);
    }
        //Removes it from everylist
    public void removeChem(Chemical chem){
        this.eyeHazard.remove(chem);
        this.skinHazard.remove(chem);
        this.corrosive.remove(chem);
        this.flammable.remove(chem);
        this.aquaticHazard.remove(chem);
        this.combustible.remove(chem);
        this.digestiveHazard.remove(chem);
        this.oralHazard.remove(chem);
        this.respiratoryHazard.remove(chem);
        this.rooms.remove(chem);
        this.roomNums.remove(chem);
        this.chems.remove(chem);
    }

    public ArrayList<Chemical> getFlammable() {
        return this.flammable;
    }
    public void setFlammable(ArrayList<Chemical> flammable) {
        this.flammable = flammable;
    }
    public void addFlammable(Chemical chem){
        this.flammable.add(chem);
    }
    public void removeFlammable(Chemical chem){
        this.flammable.remove(chem);
    }

    public ArrayList<Chemical> getCorrosive() {
        return this.corrosive;
    }
    public void setCorrosive(ArrayList<Chemical> corrosive) {
        this.corrosive = corrosive;
    }
    public void addCorrosive(Chemical chem){
        this.corrosive.add(chem);
    }
    public void removeCorrosive(Chemical chem){
        this.corrosive.remove(chem);
    }

    public ArrayList<Chemical> getSkinHazard() {
        return this.skinHazard;
    }
    public void setSkinHazard(ArrayList<Chemical> skinHazard) {
        this.skinHazard = skinHazard;
    }
    public void addSkinHazard(Chemical chem){
        this.skinHazard.add(chem);
    }
    public void removeSkinHazard(Chemical chem){
        this.skinHazard.remove(chem);
    }

    public ArrayList<Chemical> getEyeHazard() {
        return this.eyeHazard;
    }
    public void setEyeHazard(ArrayList<Chemical> eyeHazard) {
        this.eyeHazard = eyeHazard;
    }
    public void addEyeHazard(Chemical chem){
        this.eyeHazard.add(chem);
    }
    public void removeEyeHazard(Chemical chem){
        this.eyeHazard.remove(chem);
    }

    public ArrayList<Chemical> getDigestiveHazard() {
        return this.digestiveHazard;
    }
    public void setDigestiveHazard(ArrayList<Chemical> digestiveHazard) {
        this.digestiveHazard = digestiveHazard;
    }
    public void addDigestiveHazard(Chemical chem){
        this.digestiveHazard.add(chem);
    }
    public void removeDigestiveHazard(Chemical chem){
        this.digestiveHazard.remove(chem);
    }


    public ArrayList<Chemical> getRespiratoryHazard() {
        return this.respiratoryHazard;
    }
    public void setRespiratoryHazard(ArrayList<Chemical> respiratoryHazard) {
        this.respiratoryHazard = respiratoryHazard;
    }
    public void addRespiratoryHazard(Chemical chem){
        this.respiratoryHazard.add(chem);
    }
    public void removeRespiratoryHazard(Chemical chem){
        this.respiratoryHazard.remove(chem);
    }

    public ArrayList<Chemical> getAquaticHazard() {
        return this.aquaticHazard;
    }
    public void setAquaticHazard(ArrayList<Chemical> aquaticHazard) {
        this.aquaticHazard = aquaticHazard;
    }
    public void addAquaticHazard(Chemical chem){
        this.aquaticHazard.add(chem);
    }
    public void removeAquaticHazard(Chemical chem){
        this.aquaticHazard.remove(chem);
    }

    public ArrayList<Chemical> getCombustible() {
        return this.combustible;
    }

    public void setCombustible(ArrayList<Chemical> combustible) {
        this.combustible = combustible;
    }
    public void addcombustible(Chemical chem){
        this.combustible.add(chem);
    }
    public void removecombustible(Chemical chem){
        this.combustible.remove(chem);
    }

    public ArrayList<Chemical> getOralHazard() {
        return this.oralHazard;
    }
    public void setOralHazard(ArrayList<Chemical> oralHazard) {
        this.oralHazard = oralHazard;
    }
    public void addOralHazard(Chemical chem){
        this.oralHazard.add(chem);
    }
    public void removeOralHazard(Chemical chem){
        this.oralHazard.remove(chem);
    }

    public HashMap<String,Room> getRooms() {
        return this.rooms;
    }
    public void setRooms(HashMap<String,Room> rooms) {
        this.rooms = rooms;
    }

    public void setComboChem(ArrayList<Solution> setting){
        this.comboChem = setting;
    }
    public ArrayList<Solution> getComboChem(){
        return this.comboChem;
    }
    public void addCombo(Solution adding){
        this.comboChem.add(adding);
    }
    public void removeCombo(Solution removing){
        this.comboChem.remove(removing);
    }

    //to string and equals only check
    //full list as of now
    //Evelyn
//--------------------------------------------------------------------------
        //Adds solutions separately due to a lack of

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