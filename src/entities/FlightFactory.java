package entities;
import java.time.LocalDateTime;

/**
 * Factory for creating Flight objects.
 */

public class FlightFactory {

    public Flight create(String flightNumber, String airline, String departureAirport, String arrivalAirport,
                         String status, String scheduledArrivalTime, String scheduledDepartureTime,
                         String estimatedArrivalTime, String actualDepartureTime, double[] currentLocation){
        return new Flight(flightNumber, airline, departureAirport, arrivalAirport, status, scheduledArrivalTime,
                scheduledDepartureTime, estimatedArrivalTime, actualDepartureTime, currentLocation);
    }
}