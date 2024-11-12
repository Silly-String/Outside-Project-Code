package use_case.SearchByFlightNumber;

import entities.Flight;
import java.util.List;

/**
 * The interface of the DAO for the SearchByFlightNumber Use Case.
 */
public interface SearchByFlightNumberUserDataAccessInterface {

    /**
     * Retrieves a list of flights from the system.
     * @return a list of flights
     */
    List<Flight> retrieveFlights();
}
