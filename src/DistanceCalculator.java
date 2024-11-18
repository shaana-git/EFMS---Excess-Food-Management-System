package src.src;

public class DistanceCalculator {

    private static final double EARTH_RADIUS_KM = 6371.0; // Earth radius in kilometers

    public static double haversineDistance(double lat1, double lon1, double lat2, double lon2) {
        // Convert latitude and longitude from degrees to radians
        double lat1Rad = Math.toRadians(lat1);
        double lon1Rad = Math.toRadians(lon1);
        double lat2Rad = Math.toRadians(lat2);
        double lon2Rad = Math.toRadians(lon2);

        // Calculate the differences
        double deltaLat = lat2Rad - lat1Rad;
        double deltaLon = lon2Rad - lon1Rad;

        // Apply Haversine formula
        double a = Math.sin(deltaLat / 2) * Math.sin(deltaLat / 2) +
                Math.cos(lat1Rad) * Math.cos(lat2Rad) *
                        Math.sin(deltaLon / 2) * Math.sin(deltaLon / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));

        // Distance in kilometers
        return EARTH_RADIUS_KM * c;
    }

    public static void main(String[] args) {
        // Example coordinates
        double lat1 = 8.72104; // Latitude of location 1 (New York)
        double lon1 = 77.67509; // Longitude of location 1 (New York)
        double lat2 = 8.72799; // Latitude of location 2 (Los Angeles)
        double lon2 = 77.69366; // Longitude of location 2 (Los Angeles)

        double distance = haversineDistance(lat1, lon1, lat2, lon2);
        System.out.println("Distance between locations: " + distance + " km");
    }
}
