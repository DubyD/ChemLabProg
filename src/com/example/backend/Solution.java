package com.example.backend;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Solution extends Chemical{


        //With our Chemicals combined we are:
    private List<Chemical> captainPlanet;
    private String dateAndInitials;

      //Default constructor
      public Solution() {
        super("", "", "", "", 0.0, "");
        this.dateAndInitials = "";
        this.captainPlanet = new ArrayList<>();
    }
    

        //Constructor used for solution's super()-
        //(String 'name', String 'room',String 'shelf',
        //double 'size', String 'sizeUnit')
        public Solution(String name, String company, String room, String shelf, double size, String sizeUnit, String dateAndInitials){

            super(name, company, room, shelf, size, sizeUnit);

            this.dateAndInitials = dateAndInitials;
            this.captainPlanet = new ArrayList<Chemical>();
        }

        public Solution(String name, String company, String room, String shelf, double size, String sizeUnit, String dateAndInitials,  List<Chemical> combination){

            super(name, company, room, shelf, size, sizeUnit);
            this.dateAndInitials = dateAndInitials;
            this.captainPlanet = new ArrayList<Chemical>();

                //inherits the hazards from the combinations of chemicals
            this.setCombinations(combination);
        }

            //Used to set the chemical combination of this solution
        public void setCombinations(List<Chemical> combination){
            this.captainPlanet.addAll(combination);
            String hazards = "";
            for(Chemical next : this.captainPlanet){

                    //Sets specific Hazards
                hazards = hazards + next.getHazards();
            }

            this.setHazardFlags(hazards);

        }


            //Name,Quantity,Initials/Date ,Location,Company
        @Override
        public String[] asArray(){
            return new String[]{this.getName(), String.valueOf(this.getSize()) + this.getSizeUnit(),
                            this.dateAndInitials , this.getShelf() , this.getManufacturer()};
        }


        @Override
        public String toString() {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("Solution{\n");
            stringBuilder.append("  Name: ").append(this.getName()).append("\n");
            stringBuilder.append("  Quantity: ").append(this.getSize()).append(" ").append(this.getSizeUnit()).append("\n");
            stringBuilder.append("  Date and Initials: ").append(this.dateAndInitials).append("\n");
            stringBuilder.append("  Location: ").append(this.getShelf()).append("\n");
            stringBuilder.append("  Company: ").append(this.getManufacturer()).append("\n");
            stringBuilder.append("}");

                return stringBuilder.toString();
}

}
