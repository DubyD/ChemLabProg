package com.example.backend;
import java.time.LocalDateTime;

/** 
 * The History class represents a historical record of actions performed in the system.
 * It tracks who performed the action, what action was taken, where it occurred, and when it was last updated.
 * @author: Yunjin
 */ 

public class History {
    private static String historyFileName; 
    private User who;
    private String what; 
    private String where;
    private int amount; 
    private LocalDateTime lastUpdated; 


    public History(User who, String what, String where, int amount) {
        this.who = who;
        this.what = what;
        this.where = where;
        this.amount = amount;
        this.lastUpdated = LocalDateTime.now();
    }

    // Getter and setter methods for all the properties of the History class.

    /**
     * Static method to get the name of the history file.
     * @return The name of the history file.
     */
    public static String getHistoryFileName() {
        return historyFileName;
    }

    /**
     * Static method to set the name of the history file.
     * @param historyFileName The name of the history file.
     */
    public static void setHistoryFileName(String historyFileName) {
        History.historyFileName = historyFileName;
    }

    /**
     * Gets the user who performed the action.
     * @return The user who performed the action.
     */
    public User getWho() {
        return who;
    }

    /**
     * Sets the user who performed the action.
     * @param who The user who performed the action.
     */
    public void setWho(User who) {
        this.who = who;
    }

    /**
     * Gets the action that was taken.
     * @return The action that was taken.
     */
    public String getWhat() {
        return what;
    }

    /**
     * Sets the action that was taken.
     * @param what The action that was taken.
     */
    public void setWhat(String what) {
        this.what = what;
    }

    /**
     * Gets the location where the action occurred.
     * @return The location where the action occurred.
     */
    public String getWhere() {
        return where;
    }

    /**
     * Sets the location where the action occurred.
     * @param where The location where the action occurred.
     */
    public void setWhere(String where) {
        this.where = where;
    }

    /**
     * Gets the amount or quantity associated with the action.
     * @return The amount or quantity associated with the action.
     */
    public int getAmount() {
        return amount;
    }

    /**
     * Sets the amount or quantity associated with the action.
     * @param amount The amount or quantity associated with the action.
     */
    public void setAmount(int amount) {
        this.amount = amount;
    }

    /**
     * Gets the timestamp of when the history was last updated.
     * @return The timestamp of when the history was last updated.
     */
    public LocalDateTime getLastUpdated() {
        return lastUpdated;
    }

    /**
     * Sets the timestamp of when the history was last updated.
     * @param lastUpdated The timestamp of when the history was last updated.
     */
    public void setLastUpdated(LocalDateTime lastUpdated) {
        this.lastUpdated = lastUpdated;
    }

    /**
     * Overrides the toString method to provide a string representation of the History object.
     * @return String representation of the History object.
     */
    @Override
    public String toString() {
        return "History{" +
                "who=" + who.getUsername() +
                ", what='" + what + '\'' +
                ", where='" + where + '\'' +
                ", amount=" + amount +
                ", lastUpdated=" + lastUpdated +
                '}';
    }

    /**
     * Static method to read history from a file.
     * @return A string indicating that history has been read from the file.
     */
    public static String readHistory() {
        return "History read from file";
    }

    /**
     * Static method to revert history.
     * @return True if history is successfully reverted, false otherwise.
     */
    public static boolean revertHistory() {
        return true;
    }

    /**
     * Static method to write history for a takeout slip.
     * @param takeOutSlip The takeout slip for which history is to be written.
     * @return True if history is successfully written, false otherwise.
     */
    public static boolean writeHistory(TakeOutSlip takeOutSlip) {
        return true;
    }
}

    public static boolean writeHistory(TakeOutSlip takeOutSlip) {
        return true;
    }
}
