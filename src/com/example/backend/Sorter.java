package com.example.backend;

//Author WD



import java.io.FileWriter;
import java.io.FileReader;
import java.io.BufferedWriter;
import java.io.BufferedReader;
import java.io.IOException;
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
        //For 534_inventory, the columns are set up as so: (skip line 1)
        //SDS,Chemical,Company,Room,Location,Amount of Jars,Amount,Unit,CAS #s,Hazard
        //We need columns 2, 3(so the professor can determine if they enjoy the product)
        //4,5,6, 7, 8, and 10

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




            public static void writeInv(String [] paths String ){

                    //Initiating variables
                List<String> working = new ArrayList<String>();

                try {
                        //loops through each file
                    for (String path : paths) {
                        working.addAll(readInv(path));
                    }

                    working.sort(String::compareToIgnoreCase);

                        //Create a FileWriter with the specified file path
                    FileWriter fileWriter = new FileWriter("..\\Data\\updated_data.csv");

                        //Wrap the FileWriter in a BufferedWriter for efficient writing
                    try(BufferedWriter bufferedWriter = new BufferedWriter(fileWriter)) {


                        // Write the content to the file
                        for (String line : working) {
                            bufferedWriter.write(line);
                            bufferedWriter.newLine();
                        }
                    }

                }catch(IOException e){

                }
            }


        public static List<String> readInv(String filePath){

            List<String> reply = new ArrayList<String>();
            try(BufferedReader reader = new BufferedReader(new FileReader(filePath))){

                String nextLine;
                while((nextLine = reader.readLine()) != null){

                    reply.add(nextLine);

                }

            }catch(IOException e) {

            }//Autocloses when the BufferedReader is uninitialized


            return reply;
        }

}