package com.example.backend;
import java.time.LocalDateTime;
/** 
 *@author Yunjin 
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

    public static String getHistoryFileName() {
        return historyFileName;
    }

    public static void setHistoryFileName(String historyFileName) {
        History.historyFileName = historyFileName;
    }

    public User getWho() {
        return who;
    }

    public void setWho(User who) {
        this.who = who;
    }

    public String getWhat() {
        return what;
    }

    public void setWhat(String what) {
        this.what = what;
    }

    public String getWhere() {
        return where;
    }

    public void setWhere(String where) {
        this.where = where;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public LocalDateTime getLastUpdated() {
        return lastUpdated;
    }

    public void setLastUpdated(LocalDateTime lastUpdated) {
        this.lastUpdated = lastUpdated;
    }

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

    public static String readHistory() {
        return "History read from file";
    }

    public static boolean revertHistory() {
        return true;
    }

    public static boolean writeHistory(TakeOutSlip takeOutSlip) {
        return true;
    }
}
