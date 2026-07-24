import java.util.*;

class UndergroundSystem {

    class CheckInData {
        String station;
        int time;

        CheckInData(String station, int time) {
            this.station = station;
            this.time = time;
        }
    }

    class TripData {
        int totalTime;
        int tripCount;

        TripData(int totalTime, int tripCount) {
            this.totalTime = totalTime;
            this.tripCount = tripCount;
        }
    }

    private Map<Integer, CheckInData> checkInMap;
    private Map<String, TripData> tripMap;

    public UndergroundSystem() {
        checkInMap = new HashMap<>();
        tripMap = new HashMap<>();
    }

    public void checkIn(int id, String stationName, int t) {
        checkInMap.put(id, new CheckInData(stationName, t));
    }

    public void checkOut(int id, String stationName, int t) {

        CheckInData checkIn = checkInMap.get(id);

        String key = checkIn.station + "->" + stationName;

        int travelTime = t - checkIn.time;

        TripData trip = tripMap.getOrDefault(key, new TripData(0, 0));

        trip.totalTime += travelTime;
        trip.tripCount++;

        tripMap.put(key, trip);

        checkInMap.remove(id);
    }

    public double getAverageTime(String startStation, String endStation) {

        String key = startStation + "->" + endStation;

        TripData trip = tripMap.get(key);

        return (double) trip.totalTime / trip.tripCount;
    }
}
