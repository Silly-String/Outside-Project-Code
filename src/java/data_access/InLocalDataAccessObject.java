package data_access;

import entities.Flight;
import entities.FlightFactory;
import entities.Airline;
import entities.Airport;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import use_case.SearchByAirlineID.SearchByAirlineIDDataAccessInterface;
import org.json.JSONArray;
import org.json.JSONObject;

import use_case.SearchByFlightNumber.SearchByFlightNumberDataAccessInterface;
import java.util.ArrayList;
import java.util.List;

/**
 * The DAO for testing with sample API calls.
 */
public class InLocalDataAccessObject implements SearchByAirlineIDDataAccessInterface,
        SearchByFlightNumberDataAccessInterface {

    // File path to the local JSON file
    private static final String LOCAL_FILE_PATH = "..."; // Use any local file path with a sample API call

    // Helper method to fetch and parse flight data from the local file
    //TODO: FIND ISSUE
    private List<JSONObject> fetchFlightsFromLocalFile() {
        List<JSONObject> flightDataList = new ArrayList<JSONObject>();

        try {
            // Read the local file
            BufferedReader reader = new BufferedReader(new FileReader(LOCAL_FILE_PATH));
            StringBuilder content = new StringBuilder();
            String line;

            while ((line = reader.readLine()) != null) {
                content.append(line);
            }
            reader.close();

            // Parse the response JSON from the file
            JSONObject jsonResponse = new JSONObject(content.toString());
            JSONArray data = jsonResponse.getJSONArray("data");

            // Add each flight data JSON object to the list
            for (int i = 0; i < data.length(); i++) {
                flightDataList.add(data.getJSONObject(i));
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return flightDataList;
    }

    // Helper method to parse flight data into a Flight object using the FlightFactory
    private Flight parseFlightData(JSONObject flightData) {
        // Create the necessary objects to pass into the FlightFactory
        Airline airline = new Airline(flightData.getJSONObject("airline").optString("iata", "N/A"),
                flightData.getJSONObject("airline").optString("name", "N/A"));
        Airport departureAirport = new Airport(flightData.getJSONObject("departure").optString("iata", "N/A"),
                flightData.getJSONObject("departure").optString("airport", "N/A"), null);
        Airport arrivalAirport = new Airport(flightData.getJSONObject("arrival").optString("iata", "N/A"),
                flightData.getJSONObject("arrival").optString("airport", "N/A"), null);

        // Time formatting and extraction
        String scheduledDepartureTime = flightData.getJSONObject("departure").optString("scheduled", "N/A");
        String estimatedDepartureTime = flightData.getJSONObject("departure").optString("estimated", "N/A");
        String scheduledArrivalTime = flightData.getJSONObject("arrival").optString("scheduled", "N/A");
        String estimatedArrivalTime = flightData.getJSONObject("arrival").optString("estimated", "N/A");

        // DateTimeFormatter for desired format
        String timeFormat = "yyyy/MM/dd HH:mm";  // Format pattern
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(timeFormat);

        // Parse the times using ISO_OFFSET_DATE_TIME and then format to the desired pattern
        LocalDateTime sourceDs = LocalDateTime.parse(scheduledDepartureTime, DateTimeFormatter.ISO_OFFSET_DATE_TIME);
        LocalDateTime sourceDe = LocalDateTime.parse(estimatedDepartureTime, DateTimeFormatter.ISO_OFFSET_DATE_TIME);
        LocalDateTime sourceAs = LocalDateTime.parse(scheduledArrivalTime, DateTimeFormatter.ISO_OFFSET_DATE_TIME);
        LocalDateTime sourceAe = LocalDateTime.parse(estimatedArrivalTime, DateTimeFormatter.ISO_OFFSET_DATE_TIME);

        // Format the times
        String formattedScheduledDepartureTime = sourceDs.format(formatter);
        String formattedEstimatedDepartureTime = sourceDe.format(formatter);
        String formattedScheduledArrivalTime = sourceAs.format(formatter);
        String formattedEstimatedArrivalTime = sourceAe.format(formatter);

        // Parse location if available
        double[] currentLocation = null;
        try {
            double lat = flightData.getJSONObject("live").getDouble("latitude");
            double lng = flightData.getJSONObject("live").getDouble("longitude");
            currentLocation = new double[]{lat, lng};
        } catch (Exception e) {
            // No live data, currentLocation remains null
        }

        // Use the FlightFactory to create and return the Flight object
        FlightFactory flightFactory = new FlightFactory();
        return flightFactory.create(
                flightData.getJSONObject("flight").optString("iata", "N/A"),
                flightData.optString("flight_date", "N/A"),
                airline,
                departureAirport,
                arrivalAirport,
                flightData.optString("flight_status", "N/A"),
                formattedScheduledArrivalTime,  // Updated formatted time
                formattedScheduledDepartureTime, // Updated formatted time
                formattedEstimatedArrivalTime,   // Updated formatted time
                formattedEstimatedDepartureTime, // Updated formatted time
                currentLocation
        );
    }

    public List<Flight> getFlights() {
        List<Flight> flights = new ArrayList<>();

        // Fetch the data from the API
        List<JSONObject> flightDataList = fetchFlightsFromLocalFile();

        // Iterate through the data
        for (JSONObject flightData : flightDataList) {
            Flight flight = parseFlightData(flightData);
            flights.add(flight);
        }

        return flights;
    }

    @Override
    //TODO: FIND ISSUE
    public List<Flight> getFlightsByAirlineId(String airlineId) {
        List<Flight> flights = new ArrayList<>();

        // Read the flight data from the local file
        List<JSONObject> flightDataList = fetchFlightsFromLocalFile();

        // Iterate through the data and filter by airlineId
        for (JSONObject flightData : flightDataList) {
            if (flightData.getJSONObject("airline").getString("iata").equals(airlineId)) {
                Flight flight = parseFlightData(flightData);
                flights.add(flight);
            }
        }

        return flights;
    }

    @Override
    //TODO: FIND ISSUE
    public List<Flight> getFlightsByFlightNumber(String flightNumber) {
        List<Flight> flights = new ArrayList<>();

        // Fetch the data from the API
        List<JSONObject> flightDataList = fetchFlightsFromLocalFile();

        // Iterate through the data and filter by flight number
        for (JSONObject flightData : flightDataList) {
            if (flightData.getJSONObject("flight").getString("iata").equals(flightNumber)) {
                Flight flight = parseFlightData(flightData);
                flights.add(flight);
            }
        }

        return flights;
    }

    /**
     * This is just a check that the flight caller works. Will be moved to test files at a later date.
     * Uncomment to run.
     */
    public static void main(String[] args) {
        // Create an instance of the DAO class
        APICallDataAccessObject dao = new APICallDataAccessObject();

        // Call the instance method using the object
        List<Flight> flights = dao.getFlights();

        // Iterate through the flights and print details
        for (Flight flight : flights) {
            System.out.println(flight.toString());
            System.out.println();  // This adds a blank line
        }
    }
}
