package com.example.myapplication.entities;

import androidx.annotation.NonNull;

public class Trip {
    private int tripId;
    private String name;
    private String transportation;
    private String destination;
    private String dateOfTheTrip;
    private String requiresRiskAssessment;
    private String description;
    private String otherServices;

    public int getTripId() {
        return tripId;
    }

    public void setTripId(int tripId) {
        this.tripId = tripId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTransportation() {
        return transportation;
    }

    public void setTransportation(String transportation) {
        this.transportation = transportation;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public String getDateOfTheTrip() {
        return dateOfTheTrip;
    }

    public void setDateOfTheTrip(String dateOfTheTrip) {
        this.dateOfTheTrip = dateOfTheTrip;
    }

    public String getRequiresRiskAssessment() {
        return requiresRiskAssessment;
    }

    public void setRequiresRiskAssessment(String requiresRiskAssessment) {
        this.requiresRiskAssessment = requiresRiskAssessment;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getOtherServices() {
        return otherServices;
    }

    public void setOtherServices(String otherServices) {
        this.otherServices = otherServices;
    }
    public String getTripDate() {
        return dateOfTheTrip;
    }

    @NonNull
    @Override
    public String toString() {
        return "TRIP:\n" +
                "\tName: '" + name + '\'' + "\n" +
                "\tTransportation: '" + transportation + '\'' + "\n" +
                "\tDestination: '" + destination + '\'' + "\n" +
                "\tDateOfTheTrip: '" + dateOfTheTrip + '\'' + "\n" +
                "\tRequiresRiskAssessment: '" + requiresRiskAssessment + '\'' + "\n" +
                "\tDescription: '" + description + '\'' + "\n" +
                "\tOtherServices: '" + otherServices + '\'' + "\n" +
                '.';
    }
}
