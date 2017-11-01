package com.example.coelab.financereport;

/**
 * Created by coeLab on 2017/10/24.
 */

public class FinancialReport {

    private String source;
    private String date;
    private int amount;
    private String notes;

   /* public FinancialReport(){
    }
*/
    public FinancialReport(String source, String date, int amount, String notes) {
        this.source = source;
        this.date = date;
        this.amount = amount;
        this.notes = notes;
    }

    public FinancialReport() {

    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }
}
