package java.use_case.SearchByFlightNumber;

/**
 * Input Boundary for actions which are related to searching by Flight Number.
 */
public interface SearchByFlightNumberInputBoundary {

    /**
     * Execute the SearchByFlightNumber Use Case.
     * @param SearchByFlightNumberInputData the input data for this use case
     */
    void execute(SearchByFlightNumberInputData SearchByFlightNumberInputData);
}
