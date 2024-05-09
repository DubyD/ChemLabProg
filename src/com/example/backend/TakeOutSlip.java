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
    private User user;
    private Chemical chemical;
    private double updatedAmount;
    private String decision="";
    private String unitSize="";
    public TakeOutSlip(User userDet, Chemical chemicalInput){
        this.user = userDet;
        this.chemical=chemicalInput;
    }
    public void alterChemical(double amount){
        // amount will be the new value for the chemical
        double previousAmount = this.chemical.getSize();
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
        this.unitSize = this.chemical.getSizeUnit();
    }

    public String takeOutDetails(){
        String details = String.format("User: %s %s chemical %s by this amount, %f %s", this.user.getUsername(),this.decision,this.chemical.getName(),this.updatedAmount,this.unitSize);
        // i.e User John decreased chemical X by this amount, 5mL
        // i.e User Jerry increased chemical Y by this amount, 100mL
        // i.e User Larry altered chemical W by this amount, 0mL
        return details;
    }
    
    public User getUser(){
        return this.user;
    }
    public Chemical getChemical(){
        return this.chemical;
    }
}
