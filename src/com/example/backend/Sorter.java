package com.example.backend;

//Author WD



import java.io.*;
import java.util.ArrayList;
import java.util.List;

// This class will be used to read and write data entries to the updated_data.csv file
// All functions related to retrieving or
public class Sorter{

    //So no one can create an instance of this class
    private Sorter(){

    }

    //no constructor because you cannot creat a static class



    //These three are the same format and can use the same function
    //We need to search through column 10 in order to extract flammability and corrosiveness
    //Hazards are descriptions so they cannot be separated by " ". Save the description
//----------------------------------------------------------------------------------------------------------------------


    //For W412C_inventory the columns are set up as so: (skip line 1)
    //blank,Chemical,Company,Room,Location,Amount of Jars,Amount,Unit,CAS #s,Hazard
    //We need column 2,3(so the professor can determine if they enjoy the product)
    //4,5,6, 7, 8 , and 10

    //For W412D_inventory the columns are set up as so: (skip line 1)
    //blank,Chemical,Company,Room,Shelf,Amount of Jars/Containers,Amount,Unit,CAS #s,Hazard
    //We need column 2,3(so the professor can determine if they enjoy the product)
    //4,5,6, 7, 8, and 10
//----------------------------------------------------------------------------------------------------------------------

    //For new_solutions_Cab (for cabinet) the columns are set up as so: (skip line 1)
    //Name,Quantity,Initials/Date ,Location,Company
    //These are custom chems so may need a subclass of Chemical to store data properly
    //We need columns 1,2,3,4





        //Split file paths by comma
        //Fixed this for now let me know if we are using a different splitter
    public static boolean writeInv(String filePath, List<String> working) {

            //Checks to see if a File exists, if not
        File reading = new File(filePath);
        if(!reading.exists()){
            filePath.replace("\\", "/");
            reading = new File(filePath);
            if(!reading.exists()){
                return false;
            }
        }

        try {
            //loops through each file


                //Create a FileWriter with the specified file path

            FileWriter fileWriter = new FileWriter(filePath);

            working.sort(String::compareToIgnoreCase);
            //Wrap the FileWriter in a BufferedWriter for efficient writing
            try (BufferedWriter bufferedWriter = new BufferedWriter(fileWriter)) {


                // Write the content to the file
                for (String line : working) {
                    bufferedWriter.write(line);

                    bufferedWriter.newLine();
                }
            }

            return true;

        } catch (IOException e) {

        }

    }


    public static List<String> readInv(String filePath){


            //Checks to see if a File exists
        File reading = new File(filePath);
        if(!reading.exists()){
            filePath.replace("\\", "/");
            reading = new File(filePath);
        }

        List<String> reply = new ArrayList<String>();
        try(BufferedReader reader = new BufferedReader(new FileReader(filePath))){

            String nextLine;
            while((nextLine = reader.readLine()) != null){

                reply.add(nextLine);

            }

        }catch(IOException e) {

        }//Autocloses when the BufferedReader is uninitialized

        reply.sort(String::compareToIgnoreCase);

        return reply;
    }



    //For 534_inventory, the columns are set up as so: (skip line 1)
    //SDS,2 Chemical,3 Company,4 Room,5 Location,6 Amount of Jars,7 Amount,8 Unit,9 CAS #s,10 Hazard
    //We need columns 2, 3(so the professor can determine if they enjoy the product)
    //4,5,6, 7, 8, and 10

    // public Chemical(String name, String company, String room, String shelf,
    // int containers, Double amount, String unit, String cas, String hazards){
    public static Chemical chemLab(String line){

        String[] cut = line.split(",", 10);

        int amount = Integer.parseInt(cut[6]);
        Double size = Double.parseDouble(cut[7]);

        Chemical beaker = new Chemical(cut[1], cut[2], cut[3], cut[4], amount, size, cut[5], cut[8], cut[9]);
        return beaker;

    }


        //Name,Quantity,Initials/Date ,Location,Company
        //These are the attributes of the string in the .csv
        //Used for the .csv file
    public static Solution wetLabDoc(String line){
        String[] cut = line.split(",", 5);

        int amount = Integer.parseInt(cut[6]);
        Double size = Double.parseDouble(cut[7]);

        Chemical beaker = new Chemical(cut[1], cut[2], cut[3], cut[4], amount, size, cut[5], cut[8], cut[9]);
        return beaker;
    }

    public static boolean createSolution(List<Chemical> combinations){

    }




















}