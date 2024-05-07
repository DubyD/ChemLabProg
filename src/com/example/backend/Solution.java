package com.example.backend;

import java.util.List;

public class Solution extends Chemical{


        //With our Chemicals combined we are:
    private List<Chemical> captainPlanet;



        //Constructor used for solution's super()-
        //(String 'name', String 'room',String 'shelf',
        //double 'size', String 'sizeUnit')
        public Solution(String name, String room, String shelf, double size, String sizeUnit, List<Chemical> combination){

            super(name,room,shelf,size,sizeUnit);

            this.captainPlanet = combination;
        }
}
