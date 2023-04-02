package org.bs.vrp.utils;

public class DistanceMatrixGenerator {
    private static final double EARTH_RADIUS = 6371.0; // in kilometers

    public static long[][] generateDistanceMatrix(double[][] locationMatrix) {
        int numLocations = locationMatrix.length;
        long[][] distanceMatrix = new long[numLocations][numLocations];

        for (int i = 0; i < numLocations; i++) {
            for (int j = i; j < numLocations; j++) {
                if (i == j) {
                    distanceMatrix[i][j] = 0;
                } else {
                    double lat1 = locationMatrix[i][0];
                    double lng1 = locationMatrix[i][1];
                    double lat2 = locationMatrix[j][0];
                    double lng2 = locationMatrix[j][1];

                    double dLat = Math.toRadians(lat2 - lat1);
                    double dLng = Math.toRadians(lng2 - lng1);

                    double a = Math.sin(dLat / 2) * Math.sin(dLat / 2) +
                            Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2)) *
                                    Math.sin(dLng / 2) * Math.sin(dLng / 2);

                    double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));

                    long dist = (long) (EARTH_RADIUS * c * 1000);
                    distanceMatrix[i][j] = dist;
                    distanceMatrix[j][i] = dist;
                }
            }
        }

        return distanceMatrix;
    }

}

