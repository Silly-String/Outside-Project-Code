package java.use_case.SearchByFlightNumber;

import java.entities.Flight;
import java.util.List;

/**
 * Output Data for the SearchByAirline Use Case.
 */
public class SearchByFlightNumberOutputData {

    private final List<Flight> filteredFlights;
    private final String errorMessage;

    // Constructor for success (with filtered flights)
    public SearchByFlightNumberOutputData(List<Flight> filteredFlights) {
        this.filteredFlights = filteredFlights;
        this.errorMessage = null;
    }

    // Constructor for failure (when no flights are found or an error occurs)
    public SearchByFlightNumberOutputData(String errorMessage) {
        this.filteredFlights = null;
        this.errorMessage = errorMessage;
    }

    public List<Flight> getFilteredFlights() {
        return filteredFlights;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public boolean isUseCaseFailed() {
        return errorMessage != null;
    }
}
