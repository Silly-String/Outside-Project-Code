package java.use_case.SearchByFlightNumber;

import java.entities.Flight;
import java.util.List;

/**
 * The SearchByFlightNumber Interactor.
 */
public class SearchByFlightNumberInteractor implements SearchByFlightNumberInputBoundary {

    private final SearchByFlightNumberUserDataAccessInterface flightDataAccessObject;
    private final SearchByFlightNumberOutputBoundary flightPresenter;

    public SearchByFlightNumberInteractor(SearchByFlightNumberUserDataAccessInterface flightDataAccessObject,
                                          SearchByFlightNumberOutputBoundary flightPresenter) {
        this.flightDataAccessObject = flightDataAccessObject;
        this.flightPresenter = flightPresenter;
    }

    @Override
    public void execute(SearchByFlightNumberInputData inputData) {
        List<Flight> flights = flightDataAccessObject.retrieveFlights();
        Flight foundFlight = inputData.SearchByFlightNumber(inputData.getFlightNumber());

        if (foundFlight != null) {
            SearchByFlightNumberOutputData outputData = new SearchByFlightNumberOutputData(foundFlight, true);
            flightPresenter.presentSuccess(outputData);
        } else {
            SearchByFlightNumberOutputData outputData = new SearchByFlightNumberOutputData(null, false);
            flightPresenter.presentFailure(outputData);
        }
    }
}
