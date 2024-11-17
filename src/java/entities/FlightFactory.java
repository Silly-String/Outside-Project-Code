package java.entities;

/**
 * Factory for creating empty Flight objects.
 */
public class FlightFactory {

    public Flight create(String flightNumber, String flightDate, Airline airline, Airport departureAirport, Airport arrivalAirport,
                         String status, String scheduledArrivalTime, String scheduledDepartureTime,
                         String estimatedArrivalTime, String estimatedDepartureTime, double[] currentLocation) {

        Flight flight = new Flight();

        flight.setFlightNumber(flightNumber);
        flight.setFlightDate(flightDate);
        flight.setAirline(airline);
        flight.setDepartureAirport(departureAirport);
        flight.setArrivalAirport(arrivalAirport);
        flight.setStatus(status);
        flight.setScheduledArrivalTime(scheduledArrivalTime);
        flight.setScheduledDepartureTime(scheduledDepartureTime);
        flight.setEstimatedArrivalTime(estimatedArrivalTime);
        flight.setEstimatedDepartureTime(estimatedDepartureTime);
        flight.setCurrentLocation(currentLocation);

        return flight;
    }
}