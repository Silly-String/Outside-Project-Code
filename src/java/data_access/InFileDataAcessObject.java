package java.data_access;

import java.entities.Flight;
import java.use_case.SearchByAirlineID.SearchByAirlineIDDataAccessInterface;
import java.util.List;

public class InFileDataAcessObject implements SearchByAirlineIDDataAccessInterface {
    @Override
    public List<Flight> getFlightsByAirlineId(String airlineId) {
        return List.of();
    }
}
