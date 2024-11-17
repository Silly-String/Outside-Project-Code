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
    public void execute(SearchByFlightNumberInputData searchByFlightNumberInputData) {
        String flightnumber = searchByFlightNumberInputData.getFlightNumber();
        List<Flight> foundFlights = flightDataAccessObject.getFlightsByFlightNumber(flightnumber);

        if (foundFlights != null) {
            SearchByFlightNumberOutputData outputData = new SearchByFlightNumberOutputData(foundFlights);
            searchByFlightNumberPresenter.prepareSuccessView(outputData);
        } else {
            SearchByFlightNumberOutputData outputData = new SearchByFlightNumberOutputData("No such flights located");
            searchByFlightNumberPresenter.prepareFailView(outputData);
        }
    }
}
