package com.example.myapplication.entities;

public class Cost {
    private int cost_id;
    private int trip_id;
    private String typeofexpense;
    private String amountoftheexpense;
    private String timeoftheexpense;
    private String additionalcomments;

    public int getCost_id() {
        return cost_id;
    }

    public void setCost_id(int cost_id) {
        this.cost_id = cost_id;
    }

    public int getTrip_id() {
        return trip_id;
    }

    public void setTrip_id(int trip_id) {
        this.trip_id = trip_id;
    }

    public String getTypeofexpense() {
        return typeofexpense;
    }

    public void setTypeofexpense(String typeofexpense) {
        this.typeofexpense = typeofexpense;
    }

    public String getAmountoftheexpense() {
        return amountoftheexpense;
    }

    public void setAmountoftheexpense(String amountoftheexpense) {
        this.amountoftheexpense = amountoftheexpense;
    }

    public String getTimeoftheexpense() {
        return timeoftheexpense;
    }

    public void setTimeoftheexpense(String timeoftheexpense) {
        this.timeoftheexpense = timeoftheexpense;
    }

    public String getAdditionalcomments() {
        return additionalcomments;
    }

    public void setAdditionalcomments(String additionalcomments) {
        this.additionalcomments = additionalcomments;
    }

    public Cost( int trip_id, String typeofexpense, String amountoftheexpense, String timeoftheexpense, String additionalcomments) {
        this.trip_id = trip_id;
        this.typeofexpense = typeofexpense;
        this.amountoftheexpense = amountoftheexpense;
        this.timeoftheexpense = timeoftheexpense;
        this.additionalcomments = additionalcomments;
    }
}
