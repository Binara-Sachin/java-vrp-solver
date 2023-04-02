package org.bs.vrp.models;

import org.bs.vrp.constants.SampleData;

public class LocationDataModel {
    public double[][] locationMatrix;
    public int vehicleNumber;
    public int depot;

    public LocationDataModel(){
        locationMatrix = SampleData.locationMatrix;
        vehicleNumber = SampleData.vehicleNumber;
        depot = SampleData.depot;
    }

    public LocationDataModel(double[][] inputLocationMatrix, int inputVehicleNumber, int inputDepot){
        locationMatrix = inputLocationMatrix;
        vehicleNumber = inputVehicleNumber;
        depot = inputDepot;
    }
}
