package java.entities;

/**
 * The representation of a flight in our program.
 */
public class Flight {
    private String flightNumber;
    private String flightDate;
    private Airline airline;
    private Airport departureAirport;
    private Airport arrivalAirport;
    private String status;
    private String scheduledDepartureTime;
    private String scheduledArrivalTime;
    private String estimatedDepartureTime;
    private String estimatedArrivalTime;
    // ALL TIMES ARE GIVEN IN UTC
    private double[] currentLocation;

    // No-argument constructor
    public Flight() {
    }

    // Getters
    public String getFlightNumber() {
        return flightNumber;
    }

    public String getFlightDate() {
        return flightDate;
    }

    public Airline getAirline() {
        return airline;
    }

    public Airport getDepartureAirport() {
        return departureAirport;
    }

    public Airport getArrivalAirport() {
        return arrivalAirport;
    }

    public String getStatus() {
        return status;
    }

    public String getScheduledDepartureTime() {
        return scheduledDepartureTime;
    }

    public String getScheduledArrivalTime() {
        return scheduledArrivalTime;
    }

    public String getEstimatedDepartureTime() {
        return estimatedDepartureTime;
    }

    public String getEstimatedArrivalTime() {
        return estimatedArrivalTime;
    }

    public double[] getCurrentLocation() {
        return currentLocation;
    }

    // Setters
    public void setFlightNumber(String flightNumber) {
        this.flightNumber = flightNumber;
    }

    public void setFlightDate(String flightDate) {
        this.flightDate = flightDate;
    }

    public void setAirline(Airline airline) {
        this.airline = airline;
    }

    public void setDepartureAirport(Airport departureAirport) {
        this.departureAirport = departureAirport;
    }

    public void setArrivalAirport(Airport arrivalAirport) {
        this.arrivalAirport = arrivalAirport;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setScheduledDepartureTime(String scheduledDepartureTime) {
        this.scheduledDepartureTime = scheduledDepartureTime;
    }

    public void setScheduledArrivalTime(String scheduledArrivalTime) {
        this.scheduledArrivalTime = scheduledArrivalTime;
    }

    public void setEstimatedDepartureTime(String estimatedDepartureTime) {
        this.estimatedDepartureTime = estimatedDepartureTime;
    }

    public void setEstimatedArrivalTime(String estimatedArrivalTime) {
        this.estimatedArrivalTime = estimatedArrivalTime;
    }

    public void setCurrentLocation(double[] currentLocation) {
        this.currentLocation = currentLocation;
    }

    // string method
    @Override
    public String toString() {
        return "Flight {\n" +
                "  flightNumber='" + flightNumber + "',\n" +
                "  flightDate='" + flightDate + "',\n" +
                "  airline=" + (airline != null ? airline.getName() : "N/A") + ",\n" +
                "  departureAirport=" + (departureAirport != null ? departureAirport.getName() : "N/A") + ",\n" +
                "  arrivalAirport=" + (arrivalAirport != null ? arrivalAirport.getName() : "N/A") + ",\n" +
                "  status='" + status + "',\n" +
                "  scheduledDepartureTime='" + scheduledDepartureTime + "',\n" +
                "  scheduledArrivalTime='" + scheduledArrivalTime + "',\n" +
                "  estimatedDepartureTime='" + estimatedDepartureTime + "',\n" +
                "  estimatedArrivalTime='" + estimatedArrivalTime + "',\n" +
                "  currentLocation=" + (currentLocation != null && currentLocation.length >= 2
                ? "[" + currentLocation[0] + ", " + currentLocation[1] + "]"
                : "N/A") + "\n" +
                '}';
    }
}