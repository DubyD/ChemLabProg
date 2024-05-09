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
    private int updatedAmount;
    private String decision="";
    public TakeOutSlip(User userDet, Chemical chemicalInput){
        this.user = userDet;
        this.chemical=chemicalInput;

        // need to setup method to alter the chemicals based on specification
    }
    public void alterChemical(int amount){
        // amount will be the new value for the chemical
        int previousAmount = this.chemical.getSize();
        this.chemical.updateSize(amount);
        if(previousAmount>amount){
            // decreased amount
            this.decision="decreased";
            this.updatedAmount = previousAmount-amount;
        }else if(previousAmount<amount){
            // increased amount
            this.decision="increased";
            this.updatedAmount = amount-previousAmount;
        }else{
            // didn't change?
            this.decision="altered";
            this.updatedAmount = amount;
        }
    }

    public String takeOutDetails(){
        String details = String.format("User: %s altered chemical %s by this amount 5mL", this.user.getUsername(), this.chemical.getName());
        return details;
        // 5mL is spaceholder amount, will need to make it compare chemical sizes before and after alteration.
        // and return that value - AK
    }
}
