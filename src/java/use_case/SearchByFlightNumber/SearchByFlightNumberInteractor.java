package java.use_case.SearchByFlightNumber;

import java.entities.Flight;
import java.util.List;

/**
 * The SearchByFlightNumber Interactor.
 */
public class SearchByFlightNumberInteractor implements SearchByFlightNumberInputBoundary {

    private final SearchByFlightNumberDataAccessInterface flightDataAccessObject;
    private final SearchByFlightNumberOutputBoundary searchByFlightNumberPresenter;

    public SearchByFlightNumberInteractor(SearchByFlightNumberDataAccessInterface flightDataAccessObject,
                                          SearchByFlightNumberOutputBoundary flightPresenter) {
        this.flightDataAccessObject = flightDataAccessObject;
        this.searchByFlightNumberPresenter = flightPresenter;
    }

    @Override
    public void execute(SearchByFlightNumberInputData inputData) {
        List<Flight> flights = flightDataAccessObject.retrieveFlights();
        Flight foundFlight = inputData.SearchByFlightNumber(inputData.getFlightNumber());

        if (foundFlight != null) {
            SearchByFlightNumberOutputData outputData = new SearchByFlightNumberOutputData(foundFlight, true);
            searchByFlightNumberPresenter.presentSuccess(outputData);
        } else {
            SearchByFlightNumberOutputData outputData = new SearchByFlightNumberOutputData(null, false);
            searchByFlightNumberPresenter.presentFailure(outputData);
        }
    }
}
