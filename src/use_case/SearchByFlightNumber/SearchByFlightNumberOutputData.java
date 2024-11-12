package use_case.SearchByFlightNumber;

import entities.Flight;

/**
 * Output Data for the SearchByFlightNumber Use Case.
 */
public class SearchByFlightNumberOutputData {

    private final Flight flight;

    private final boolean searchSuccess;

    public SearchByFlightNumberOutputData(Flight flight, boolean searchSuccess) {
        this.flight = flight;
        this.searchSuccess = searchSuccess;
    }

    public Flight getFlight() {
        return flight;
    }

    public boolean isSearchSuccess() {
        return searchSuccess;
    }
}
