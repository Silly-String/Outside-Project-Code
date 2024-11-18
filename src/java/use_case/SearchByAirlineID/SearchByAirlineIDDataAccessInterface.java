package use_case.SearchByAirlineID;

import entities.Flight;
import java.util.List;

/**
 * Data Access Interface for the Search By Airline ID Use Case.
 */
public interface SearchByAirlineIDDataAccessInterface {

    /**
     * Retrieves all flights for the given airline ID.
     *
     * @param airlineId the (IATA) code of the airline
     * @return a list of flights for the given airline ID
     */
    List<Flight> getFlightsByAirlineId(String airlineId);
}