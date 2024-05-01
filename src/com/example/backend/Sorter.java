
import java.util.ArrayList;
import java.util.List;

    // This class will be used to read and write data entries to the updated_data.csv file
    // All functions related to retrieving or
public static class Sorter(){


        //no constructor because you cannot creat a static class



            //These three are the same format and can use the same function
            //We need to search through column 10 in order to extract flammability and corrosiveness
//----------------------------------------------------------------------------------------------------------------------
        //For 534_inventory, the columns are set up as so: (skip line 1)
        //SDS,Chemical,Company,Room,Location,Amount of Jars,Amount,Unit,CAS #s,Hazard
        //We need columns 2, 3(so the professor can determine if they enjoy the product)
        //4,5,6, 7 and 8 can be combined, and 10

        //For W412C_inventory the columns are set up as so: (skip line 1)
        //blank,Chemical,Company,Room,Location,Amount of Jars,Amount,Unit,CAS #s,Hazard
        //We need column 2,3(so the professor can determine if they enjoy the product)
        //4,5,6, 7 and 8 can be combined, and 10

        //For W412D_inventory the columns are set up as so: (skip line 1)
        //blank,Chemical,Company,Room,Shelf,Amount of Jars/Containers,Amount,Unit,CAS #s,Hazard
        //We need column 2,3(so the professor can determine if they enjoy the product)
        //4,5,6, 7 and 8 can be combined, and 10
//----------------------------------------------------------------------------------------------------------------------

        //For new_solutions_Cab (for cabinet) the columns are set up as so: (skip line 1)
        //Name,Quantity,Initials/Date ,Location,Company
        //These are custom chems so may need a subclass of Chemical to store data properly
        //We need columns 1,2,3,4




}