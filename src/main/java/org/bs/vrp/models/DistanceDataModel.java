package org.bs.vrp.models;

import org.bs.vrp.constants.SampleData;
import org.bs.vrp.utils.DistanceMatrixGenerator;

public class DistanceDataModel {
    public long[][] distanceMatrix;
    public int vehicleNumber;
    public int depot;

    public DistanceDataModel(){
        distanceMatrix = SampleData.distanceMatrix;
        vehicleNumber = SampleData.vehicleNumber;
        depot = SampleData.depot;
    }

    public DistanceDataModel(LocationDataModel locationDataModel){
        distanceMatrix = DistanceMatrixGenerator.generateDistanceMatrix(locationDataModel.locationMatrix);
        vehicleNumber = locationDataModel.vehicleNumber;
        depot = locationDataModel.depot;
    }

    public int getMaxDistance(){
        int maxDistance = 0;
        for (int i = 0; i < distanceMatrix.length; i++) {
            maxDistance += distanceMatrix[0][i];
        }

        return maxDistance;
    }
}