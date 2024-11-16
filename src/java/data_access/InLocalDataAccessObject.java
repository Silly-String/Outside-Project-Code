package java.data_access;

import java.entities.Flight;
import java.entities.FlightFactory;
import java.entities.Airline;
import java.entities.Airport;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.use_case.SearchByAirlineID.SearchByAirlineIDDataAccessInterface;
import org.json.JSONArray;
import org.json.JSONObject;

import java.use_case.SearchByFlightNumber.SearchByFlightNumberDataAccessInterface;
import java.util.ArrayList;
import java.util.List;

/**
 * The DAO for testing with sample API calls.
 */
public class InLocalDataAccessObject implements SearchByAirlineIDDataAccessInterface,
        SearchByFlightNumberDataAccessInterface {

    // File path to the local JSON file
    private static final String LOCAL_FILE_PATH = "..."; // Use any local file path with a sample API call

    // Helper method to parse flight data into a Flight object using the FlightFactory
    private Flight parseFlightData(JSONObject flightData) {
        // Create the necessary objects to pass into the FlightFactory
        Airline airline = new Airline(flightData.getJSONObject("airline").getString("iata"),
                flightData.getJSONObject("airline").getString("name"));
        Airport departureAirport = new Airport(flightData.getJSONObject("departure").getString("iata"),
                flightData.getJSONObject("departure").getString("airport"), null);
        Airport arrivalAirport = new Airport(flightData.getJSONObject("arrival").getString("iata"),
                flightData.getJSONObject("arrival").getString("airport"), null);

        // Time formatting and extraction
        String scheduledDepartureTime = flightData.getJSONObject("departure").getString("scheduled");
        String estimatedDepartureTime = flightData.getJSONObject("departure").getString("estimated");
        String scheduledArrivalTime = flightData.getJSONObject("arrival").getString("scheduled");
        String estimatedArrivalTime = flightData.getJSONObject("arrival").getString("estimated");

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
                flightData.getJSONObject("flight").getString("iata"),
                flightData.getString("flight_date"),
                airline,
                departureAirport,
                arrivalAirport,
                flightData.getString("flight_status"),
                formattedScheduledArrivalTime,  // Updated formatted time
                formattedScheduledDepartureTime, // Updated formatted time
                formattedEstimatedArrivalTime,   // Updated formatted time
                formattedEstimatedDepartureTime, // Updated formatted time
                currentLocation
        );
    }

    @Override
    public List<Flight> getFlightsByAirlineId(String airlineId) {
        List<Flight> flights = new ArrayList<>();

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

            // Iterate through the data and filter by airlineId
            for (int i = 0; i < data.length(); i++) {
                JSONObject flightData = data.getJSONObject(i);

                // Check if the airline matches the provided airlineId
                if (flightData.getJSONObject("airline").getString("iata").equals(airlineId)) {
                    Flight flight = parseFlightData(flightData);
                    flights.add(flight);
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return flights;
    }

    @Override
    public List<Flight> getFlightsByFlightNumber(String flightNumber) {
        List<Flight> flights = new ArrayList<>();

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

            // Iterate through the data and filter by airlineId
            for (int i = 0; i < data.length(); i++) {
                JSONObject flightData = data.getJSONObject(i);

                // Check if the airline matches the provided airlineId
                if (flightData.getJSONObject("airline").getString("iata").equals(flightNumber)) {
                    Flight flight = parseFlightData(flightData);
                    flights.add(flight);
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return flights;
    }
}
