package use_case.SearchByFlightNumber;

import entities.Flight;
import java.util.List;

/**
 * The input data for the SearchByFlightNumber Use Case.
 */
public class SearchByFlightNumberInputData {

    private List<Flight> flights;
    private String flightNumber;


    public void SearchFlightByNumberInputData(List<Flight> flights) {
        this.flights = flights;
    }

    public String getFlightNumber() {
        return flightNumber;
    }

    /**
     * Searches for a flight by flight number.
     * @param flightNumber the flight number to search for
     * @return the Flight object if found, otherwise null
     */
    public Flight SearchByFlightNumber(String flightNumber) {
        for (Flight flight : flights) {
            if (flight.getFlightNumber().equalsIgnoreCase(flightNumber)) {
                return flight;
            }
        }
        return null; // No matching flight found
    }
}
