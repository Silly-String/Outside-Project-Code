package java.use_case.SearchByAirlineID;

import java.entities.Flight;
import java.util.List;

/**
 * The Search by Airline ID Interactor.
 */
public class SearchByAirlineIDInteractor implements SearchByAirlineIDInputBoundary{

    private final SearchByAirlineIDDataAccessInterface flightDataAccessObject;
    private final SearchByAirlineIDOutputBoundary searchByAirlineIDPresenter;

    public SearchByAirlineIDInteractor(SearchByAirlineIDDataAccessInterface flightDataAccessObject,
                                          SearchByAirlineIDOutputBoundary searchByAirlineIDPresenter) {
        this.flightDataAccessObject = flightDataAccessObject;
        this.searchByAirlineIDPresenter = searchByAirlineIDPresenter;
    }

    @Override
    public void execute(SearchByAirlineIDInputData searchByAirlineIDInputData) {
        String code = searchByAirlineIDInputData.getAirlineIataCode();
        List<Flight> foundFlights = flightDataAccessObject.getFlightsByAirlineId(code);

        if (foundFlights != null) {
            SearchByAirlineIDOutputData outputData = new SearchByAirlineIDOutputData(foundFlights);
            searchByAirlineIDPresenter.prepareSuccessView(outputData);
        } else {
            SearchByAirlineIDOutputData outputData = new SearchByAirlineIDOutputData("No such flights located");
            searchByAirlineIDPresenter.prepareFailView(outputData);
        }
    }
}
