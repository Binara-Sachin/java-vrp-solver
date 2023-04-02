package org.bs.vrp.utils;

import com.google.ortools.Loader;
import com.google.ortools.constraintsolver.*;
import org.bs.vrp.models.DistanceDataModel;

public class ORRoutingManager {
    private static RoutingIndexManager manager;
    private static RoutingModel routing;

    public static Assignment solveRoutingProblem(DistanceDataModel data){
        Loader.loadNativeLibraries();
        manager = new RoutingIndexManager(data.distanceMatrix.length, data.vehicleNumber, data.depot);
        routing = new RoutingModel(manager);

        final int transitCallbackIndex =
                routing.registerTransitCallback((long fromIndex, long toIndex) -> {
                    // Convert from routing variable Index to user NodeIndex.
                    int fromNode = manager.indexToNode(fromIndex);
                    int toNode = manager.indexToNode(toIndex);
                    return data.distanceMatrix[fromNode][toNode];
                });

        routing.setArcCostEvaluatorOfAllVehicles(transitCallbackIndex);

        routing.addDimension(transitCallbackIndex, 0, data.getMaxDistance(), true, "Distance");
        RoutingDimension distanceDimension = routing.getMutableDimension("Distance");
        distanceDimension.setGlobalSpanCostCoefficient(100);

        RoutingSearchParameters searchParameters =
                main.defaultRoutingSearchParameters()
                        .toBuilder()
                        .setFirstSolutionStrategy(FirstSolutionStrategy.Value.PATH_CHEAPEST_ARC)
                        .build();


        return routing.solveWithParameters(searchParameters);
    }

    /// @brief Print the solution.
    public static void printSolution(DistanceDataModel data, Assignment solution) {
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
