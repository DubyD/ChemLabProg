package com.example.backend;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class SorterTest {
    private String csvFilePath = "src/com/example/Data/updated_data.csv";
    private ArrayList<String> CSVLines;

    public SorterTest(){
        CSVLines = new ArrayList<>();
        chemicalStrings();
    }

    private void chemicalStrings(){
        try (BufferedReader br = new BufferedReader(new FileReader(csvFilePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                CSVLines.add(line);
            }
        } catch (
                IOException e) {
            e.printStackTrace();
        }
    }
    //String name, String company, String room, String shelf, int containers, double amount, String unit, String cas, String hazards
    public ArrayList<Chemical> chemicalArrayList(){
        ArrayList<Chemical> chemicalArrayList = new ArrayList<>();
        CSVLines.remove(0);
        for(String chemStr: CSVLines){
            String[] columns = chemStr.split(",",10);
            chemicalArrayList.add(new Chemical(columns[1], columns[2], columns[3], columns[4],
                    Integer.parseInt(columns[5]), Double.parseDouble(columns[6]), columns[7], columns[8], columns[9]));
        }
        Collections.sort(chemicalArrayList, Comparator.comparing(Chemical::getName));
        return chemicalArrayList;
    }
}