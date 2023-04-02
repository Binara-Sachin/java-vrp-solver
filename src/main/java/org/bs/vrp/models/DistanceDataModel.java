package org.bs.vrp.models;

import org.bs.vrp.constants.SampleData;

public class DistanceDataModel {
    public long[][] distanceMatrix;
    public int vehicleNumber;
    public int depot;

    public DistanceDataModel(){
        distanceMatrix = SampleData.distanceMatrix;
        vehicleNumber = SampleData.vehicleNumber;
        depot = SampleData.depot;
    }
}