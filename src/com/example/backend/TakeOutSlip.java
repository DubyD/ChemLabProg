package com.example.backend;

/**
 * TakeOutSlip
 * @author Ahmed Khan
 * First draft of a class designed to register and update
 * the amount of chemicals taken from containers
 * and drafting up a slip of which user was responsible 
 * for which action.
 */
public class TakeOutSlip {
    private User user; // spaceholder for the details of the user making changes to chemicals
    private Chemical chemical; // spaceholder for the chemical object to be altered
    public TakeOutSlip(User userDet, Chemical chemicalInput){
        this.user = userDet;
        this.chemical=chemicalInput;

        // need to setup method to alter the chemicals based on specification
    }

    public String takeOutDetails(){
        String details = String.format("User: %s altered chemical %s by this amount 5mL", this.user.getUsername(), this.chemical.getName());
        return details;
        // 5mL is spaceholder amount, will need to make it compare chemical sizes before and after alteration.
        // and return that value - AK
    }
}
