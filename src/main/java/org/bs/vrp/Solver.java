package org.bs.vrp;

import com.google.ortools.constraintsolver.*;

import org.bs.vrp.models.DistanceDataModel;
import org.bs.vrp.models.LocationDataModel;
import org.bs.vrp.utils.CSVReader;
import org.bs.vrp.utils.ORRoutingManager;

public class Solver {
    public static void main(String[] args) {
        System.out.println("---Start---");

        double[][] readData = CSVReader.readCSV("sampleData.csv");
        final LocationDataModel locationDataModel = new LocationDataModel(readData, 2, 0);
        final DistanceDataModel distanceDataModel = new DistanceDataModel(locationDataModel);

        Assignment solution = ORRoutingManager.solveRoutingProblem(distanceDataModel);
        ORRoutingManager.printSolution(distanceDataModel, solution);

        System.out.println("---Complete---");
    }
}