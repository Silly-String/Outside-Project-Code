package entities;
import java.util.Arrays;

/**
 * The representation of a flight in our program.
 */

public class Flight {
    private String flightNumber;
    private String airline;
    private String departureAirport;
    private String arrivalAirport;
    private String status;
    private String scheduledArrivalTime;
    private String scheduledDepartureTime;
    private String estimatedArrivalTime;
    private String estimatedDepartureTime;
    // ALL TIMES ARE GIVEN IN UTC
    private double[] currentLocation;

    // Constructor
    public Flight(String flightNumber, String airline, String departureAirport, String arrivalAirport,
                  String status, String scheduledArrivalTime, String scheduledDepartureTime,
                  String estimatedArrivalTime, String actualDepartureTime, double[] currentLocation) {
        this.flightNumber = flightNumber;
        this.airline = airline;
        this.departureAirport = departureAirport;
        this.arrivalAirport = arrivalAirport;
        this.status = status;
        this.scheduledArrivalTime = scheduledArrivalTime;
        this.scheduledDepartureTime = scheduledDepartureTime;
        this.estimatedArrivalTime = estimatedArrivalTime;
        this.estimatedDepartureTime = actualDepartureTime;
        this.currentLocation = currentLocation;
    }

    // Getters
    public String getFlightNumber() {
        return flightNumber;
    }

    public String getAirline() {
        return airline;
    }

    public String getDepartureAirport() {
        return departureAirport;
    }

    public String getArrivalAirport() {
        return arrivalAirport;
    }

    public String getStatus() {
        return status;
    }

    public String getScheduledDepartureTime() {
        return scheduledDepartureTime;
    }

    public String getEstimatedDepartureTime() {
        return estimatedDepartureTime;
    }

    public String getScheduledArrivalTime() {
        return scheduledArrivalTime;
    }

    public String getEstimatedArrivalTime() {
        return estimatedArrivalTime;
    }

    public String getCoordinates() {
        return Arrays.toString(currentLocation);
    }

    // Setters
    public void setCurrentLocation(double[] currentLocation) {
        this.currentLocation = currentLocation;
    }

    public void setArrivalAirport(String string) {
        this.arrivalAirport = string;
    }

    public void setStatus(String flightStatus) {
        this.status = flightStatus;
    }

    public void setEstimatedDepartureTime(String parse) {
        this.estimatedDepartureTime = parse;
    }

    public void setEstimatedArrivalTime(String parse) {
        this.estimatedArrivalTime = parse;
    }

    // toString method
    @Override
    public String toString() {
        return "Flight{" +
                "flightNumber='" + flightNumber + '\'' +
                ",\nairline='" + airline + '\'' +
                ",\ndepartureAirport='" + departureAirport + '\'' +
                ",\narrivalAirport='" + arrivalAirport + '\'' +
                ",\nstatus='" + status + '\'' +
                ",\nscheduledDepartureTime=" + scheduledDepartureTime + " UTC" +
                ",\nscheduledArrivalTime=" + scheduledArrivalTime + " UTC" +
                ",\nactualDepartureTime=" + estimatedDepartureTime + " UTC" +
                ",\nestimatedArrivalTime=" + estimatedArrivalTime + " UTC" +
                ",\ncurrentLocation='" + currentLocation + '\'' +
                '}';
    }
}
