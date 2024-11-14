package java.use_case.SearchByFlightNumber;

/**
 * The output boundary for the SearchByFlightNumber Use Case.
 */
public interface SearchByFlightNumberOutputBoundary {
    /**
     * Prepares the success view for the Change Password Use Case.
     * @param outputData the output data
     */
    void prepareSuccessView(SearchByFlightNumberOutputData outputData);

    /**
     * Prepares the failure view for the Change Password Use Case.
     * @param errorMessage the explanation of the failure
     */
    void prepareFailView(String errorMessage);
    void presentSuccess(SearchByFlightNumberOutputData outputData);
    void presentFailure(SearchByFlightNumberOutputData outputData);
}
