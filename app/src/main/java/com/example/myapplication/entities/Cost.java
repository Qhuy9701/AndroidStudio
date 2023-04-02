package com.example.myapplication.entities;

public class Cost {
    private int costId;
    private int tripId;
    private String costType;
    private double costAmount;
    private String costTime;
    private String costComment;
    private double cost;

    public int getCostId() {
        return costId;
    }

    public void setCostId(int costId) {
        this.costId = costId;
    }

    public int getTripId() {
        return tripId;
    }

    public void setTripId(int tripId) {
        this.tripId = tripId;
    }

    public String getCostType() {
        return costType;
    }

    public void setCostType(String costType) {
        this.costType = costType;
    }

    public double getCostAmount() {
        return costAmount;
    }

    public void setCostAmount(double costAmount) {
        this.costAmount = costAmount;
    }

    public String getCostTime() {
        return costTime;
    }

    public void setCostTime(String costTime) {
        this.costTime = costTime;
    }

    public String getCostComment() {
        return costComment;
    }

    public void setCostComment(String costComment) {
        this.costComment = costComment;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public Cost(int costId, int tripId, String costType, double costAmount, String costTime, String costComment, double cost) {
        this.costId = costId;
        this.tripId = tripId;
        this.costType = costType;
        this.costAmount = costAmount;
        this.costTime = costTime;
        this.costComment = costComment;
        this.cost = cost;
    }

    public Cost(int tripId, String costType, double costAmount, String costTime, String costComment, double cost) {
        this.tripId = tripId;
        this.costType = costType;
        this.costAmount = costAmount;
        this.costTime = costTime;
        this.costComment = costComment;
        this.cost = cost;
    }

    public Cost() {
    }
}