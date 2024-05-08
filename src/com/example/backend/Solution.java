package com.example.backend;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Solution extends Chemical{


        //With our Chemicals combined we are:
    private List<Chemical> captainPlanet;
    private String dateAndInitials;



        //Constructor used for solution's super()-
        //(String 'name', String 'room',String 'shelf',
        //double 'size', String 'sizeUnit')
        public Solution(String name, String room, String shelf, double size, String sizeUnit, String dateAndInitials){

            super(name,room,shelf,size,sizeUnit);

            this.dateAndInitials = dateAndInitials;
            this.captainPlanet = new ArrayList<Chemical>();
        }

        public Solution(String name, String room, String shelf, double size, String sizeUnit, String dateAndInitials, List<Chemical> combination){

            super(name,room,shelf,size,sizeUnit);
            this.dateAndInitials = dateAndInitials;
            this.captainPlanet = new ArrayList<Chemical>();

                //inherits the hazards from the combinations of chemicals
            this.setCombinations(combination);
        }

            //Used to set the chemical combination of this solution
        public void setCombinations(List<Chemical> combination){
            this.captainPlanet.addAll(combination);
            for(Chemical next : this.captainPlanet){

                    //Sets specific Hazards
                this.setHazardFlags(next.getHazards());
            }


        }
}
