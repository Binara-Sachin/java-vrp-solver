package org.bs.vrp;

import com.google.ortools.Loader;
import com.google.ortools.constraintsolver.*;

import org.bs.vrp.models.DistanceDataModel;

public class Solver {
    public static void main(String[] args) {
        System.out.println("---Start---");
        final DistanceDataModel data = new DistanceDataModel();

        Loader.loadNativeLibraries();
        RoutingIndexManager manager = new RoutingIndexManager(data.distanceMatrix.length, data.vehicleNumber, data.depot);
        RoutingModel routing = new RoutingModel(manager);

        final int transitCallbackIndex =
                routing.registerTransitCallback((long fromIndex, long toIndex) -> {
                    // Convert from routing variable Index to user NodeIndex.
                    int fromNode = manager.indexToNode(fromIndex);
                    int toNode = manager.indexToNode(toIndex);
                    return data.distanceMatrix[fromNode][toNode];
                });

        routing.setArcCostEvaluatorOfAllVehicles(transitCallbackIndex);

        routing.addDimension(transitCallbackIndex, 0, 3000, true, "Distance");
        RoutingDimension distanceDimension = routing.getMutableDimension("Distance");
        distanceDimension.setGlobalSpanCostCoefficient(100);

        RoutingSearchParameters searchParameters =
                main.defaultRoutingSearchParameters()
                        .toBuilder()
                        .setFirstSolutionStrategy(FirstSolutionStrategy.Value.PATH_CHEAPEST_ARC)
                        .build();


        Assignment solution = routing.solveWithParameters(searchParameters);
        printSolution(data, routing, manager, solution);
        System.out.println("---Complete---");
    }

    /// @brief Print the solution.
    static void printSolution(DistanceDataModel data, RoutingModel routing, RoutingIndexManager manager, Assignment solution) {
        System.out.println("Objective : " + solution.objectiveValue());
        long maxRouteDistance = 0;
        for (int i = 0; i < data.vehicleNumber; ++i) {
            long index = routing.start(i);
            System.out.println("Route for Vehicle " + (i + 1) + ":");
            long routeDistance = 0;
            String route = "\t";
            while (!routing.isEnd(index)) {
                route += manager.indexToNode(index) + " -> ";
                long previousIndex = index;
                index = solution.value(routing.nextVar(index));
                routeDistance += routing.getArcCostForVehicle(previousIndex, index, i);
            }
            System.out.println(route + manager.indexToNode(index));
            System.out.println("\tDistance of the route: " + routeDistance + "m");
            maxRouteDistance = Math.max(routeDistance, maxRouteDistance);
        }
        System.out.println("Maximum of the route distances: " + maxRouteDistance + "m");
    }
}