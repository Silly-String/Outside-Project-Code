package java.entities;
import java.util.List;

/**
 * The representation of a map in our program.
 */
public class Map {
    private int mapID;
    private List<Flight> flightList;
    private int zoomLevel;
    private double[] centreCoordinates;

    // Constructor
    public Map(){}

    public Map(int mapID, List<Flight> flightList, int zoomLevel, double[] centreCoordinates) {
        this.mapID = mapID;
        this.flightList = flightList;
        this.zoomLevel = zoomLevel;
        this.centreCoordinates = centreCoordinates;
    }

    // Getters
    public int getMapID() {
        return mapID;
    }

    public List<Flight> getFlightList() {
        return flightList;
    }

    public int getZoomLevel() {
        return zoomLevel;
    }

    public double[] getCentreCoordinates() {
        return centreCoordinates;
    }

    // Setters
    public void addFlight(Flight flight) {
        if (flightList != null && flight != null) {
            for (Flight existingFlight : flightList) {
                if (existingFlight.getFlightNumber().equalsIgnoreCase(flight.getFlightNumber())) {
                    return; // Flight is already in the list, so we skip adding
                }
            }
            flightList.add(flight); // No duplicate found, so add the flight
        }
    }

    public void updateCentreCoordinates(double[] newCoordinates) {
        this.centreCoordinates = newCoordinates;
    }

    public void updateZoomLevel(int zoomLevel) {
        this.zoomLevel = zoomLevel;
    }
}
