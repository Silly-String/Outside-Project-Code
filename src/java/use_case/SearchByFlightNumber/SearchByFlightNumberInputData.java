package use_case.SearchByFlightNumber;

/**
 * The input data for the SearchByFlightNumber Use Case.
 */
public class SearchByFlightNumberInputData {
    private final String flightNumber;  // (e.g., "187")

    // Constructor to initialize the fields
    public SearchByFlightNumberInputData(String flightNumber) {
        this.flightNumber = flightNumber;
    }

    // Getters
    public String getFlightNumber() {
        return flightNumber;
    }
}
