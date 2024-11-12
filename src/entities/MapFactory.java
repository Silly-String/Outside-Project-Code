package entities;
import java.util.List;

/**
 * Factory for creating Map objects.
 */

public class MapFactory {

    public Map create(int mapID, List<Flight> flightList, int zoomLevel, String centreCoordinates) {
        return new Map(mapID, flightList, zoomLevel, centreCoordinates);
    }
}