package com.example.backend;

import java.util.ArrayList;
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
}
