package com.example.backend;

//Author WD



import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

// This class will be used to read and write data entries to the updated_data.csv file
// All functions related to retrieving or
public class Sorter{


    //no constructor because you cannot creat a static class
    //So no one can create an instance of this class
    private Sorter(){

    }


//----------------------------------------------------------------------------------------------------------------------


    //Reads both:

    //      For updated_data.csv the columns are set up as so: (skip line 1)
    //blank,Chemical,Company,Room,Location,Amount of Jars,Amount,Unit,CAS #s,Hazard
    //We need column 2,3(so the professor can determine if they enjoy the product)
    //4,5,6, 7, 8 , and 10

    //      For new_solutions_Cab.csv (for cabinet) the columns are set up as so: (skip line 1)
    //Name,Quantity,Initials/Date ,Location,Company
    //These are custom chems so may need a subclass of Chemical to store data properly
    //We need columns 1,2,3,4


//----------------------------------------------------------------------------------------------------------------------




        //Split file paths by comma
        //Fixed this for now let me know if we are using a different splitter
    public static boolean writeInv(String filePath, List<Chemical> working) {

            //Maintaining the original header to not delete the first chemical from the code
            //anytime this program is used
        String header = ",Chemical,Company,Room,Shelf,Amount of Jars/Containers,Amount,Unit,CAS #s,Hazard";
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
                //It will overwrite what is currently in the file
            FileWriter fileWriter = new FileWriter(filePath, false);

            //Wrap the FileWriter in a BufferedWriter for efficient writing
            try (BufferedWriter bufferedWriter = new BufferedWriter(fileWriter)) {

                    //Makes sure the code will be reread without deleting the
                    //first chemical during reading
                bufferedWriter.write(header);

                    // Write the content to the file
                for (Chemical next : working) {

                    bufferedWriter.write(Sorter.spaghettifyChem(next));

                    bufferedWriter.newLine();
                }
            }

            return true;

        } catch (IOException e) {

            return false;
        }

    }


    public static List<String> readInv(String filePath){


            //Checks to see if a File exists
        File reading = new File(filePath);
        if(!reading.exists()){
            String temp = filePath.replace("\\", "/");
            reading = new File(temp);
        }

        List<String> reply = new ArrayList<String>();
        try(BufferedReader reader = new BufferedReader(new FileReader(filePath))){

                //Used to track the header
            String nextLine;
            int skipLineOne = 0;

            while((nextLine = reader.readLine()) != null){

                    //skips the header
                if(skipLineOne == 0){

                    skipLineOne = skipLineOne + 1;
                    continue;
                }
                reply.add(nextLine);
            }

        }catch(IOException e) {

        }//Autocloses when the BufferedReader is uninitialized



        return reply;
    }



        /**SDS,2 Chemical,3 Company,4 Room,5 Location,6 Amount of Jars,7 Amount,8 Unit,9 CAS #s,10 Hazard
          *We need columns 2, 3(so the professor can determine if they enjoy the product)
          *4,5,6, 7, 8, and 10
          *public Chemical(String name, String company, String room, String shelf,
          *int containers, Double amount, String unit, String cas, String hazards){
          */
    public static Chemical chemLab(String line){

        String[] cut = line.split(",", 10);

        int amount = Integer.parseInt(cut[6]);
        double size = Double.parseDouble(cut[7]);

        Chemical beaker = new Chemical(cut[1], cut[2], cut[3], cut[4], amount, size, cut[5], cut[8], cut[9]);

        if(!cut[0].equals("")) {
            beaker.setSdsSheet(true);
        }
        return beaker;

    }

        /**deconstructs the chem obj to match how the chems
         *are stored in the file.
         */
    public static String spaghettifyChem(Chemical deconstruct){

        String reply = "";

        for(String next : deconstruct.asArray()){
            reply = reply + next + ",";
        }

        return reply;

    }

       /**adding space for department related sort methods.
        *Would like to have a static sort method that sorts an arraylist
        *of chemicals alphabetically.
        *
        *takes in an unsorted arraylist of chems and returns one sorted
        *alphabetically
        */
    public static List<Chemical> initSort(List<Chemical> list){

        //Takes in the List, iterates through the list (nameOne and Two are created as abstract objects)
        //Takes the name of nameOne, and compares to nameTwo. The built in method will assign them values
        //Based on where in the alphabet the names compare to (-1, 0, 1) and alters the List accordingly
        Collections.sort(list,(nameOne, nameTwo) -> nameOne.getName().compareToIgnoreCase(nameTwo.getName()));

        //Returns altered List.
        return list;
    }

//-------------------------------vvvvvvv--------------working------------vvvvvvvvvvvvv
        //Name,Quantity,Initials/Date ,Location,Company
        //These are the attributes of the string in the .csv
        //Used for the .csv file
    public static Solution wetLabDoc(String line){
        String[] cut = line.split(",", 5);

            //Extracts the amount from the sizeUnit
        String[] extract = cut[1].split("");
        String number = "";
        String sizeUnit = "";

        for(String piece : extract){

            try{

                Integer.parseInt(piece);
                number = number + piece;
            }catch (NumberFormatException e){

                sizeUnit= sizeUnit + piece;
            }
        }

            //Company
        String company = cut[4];
            //To check for effectively empty boxes
            //Or unintended spaces. ex. " "
        String temp = company;
        temp.replace(" ", "");
        if(temp.equals("")){

           company = "MassBay Chemistry Dept.";

        }



                //constructor (Solution)(name, room, shelf, size, sizeUnit, dateAndInitials, company)
                //constructor (chem)( name, room, shelf, size, sizeUnit)
        Solution beaker = new Solution(cut[0], company, "Solutions Cabinet", cut[3], Double.parseDouble(number), sizeUnit, cut[2]);
        return beaker;
    }


        //Used for creating a new Solution
        //rewrites the wetlab doc
        //while also adding it to the Solution List in department
    public static String createSolution(String name, String shelf, String amount, String sizeUnit, String dateAndInitials, String input, List<Chemical> combinations){

        boolean reply;

            //Checks to see if the solution is outsourced
        String company;
        String temp = input;
        temp.replace(" ", "");
        if(temp.equals("")){

            company = "MassBay Chemistry Dept.";

        }else{
            company = input;
        }
            //Makes sure that the size is a number amount
        try {

            double size = Double.parseDouble(amount);
            Solution beaker = new Solution(name, company, "Solutions Cabinet", shelf, size, sizeUnit, dateAndInitials);

                //Checks to make sure that the solution is created
            if(beaker != null){

                reply = true;
            }else{
                reply = false;
            }

                //Checks to make sure a number is formatted to the size variable
        }catch(NumberFormatException e){
            reply = false;
        }

        return reply;

    }




















}