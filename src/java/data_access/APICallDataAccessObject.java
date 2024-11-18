package data_access;

import entities.Flight;
import entities.FlightFactory;
import entities.Airline;
import entities.Airport;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import use_case.SearchByAirlineID.SearchByAirlineIDDataAccessInterface;
import org.json.JSONArray;
import org.json.JSONObject;

import use_case.SearchByFlightNumber.SearchByFlightNumberDataAccessInterface;
import java.util.ArrayList;
import java.util.List;


/**
 * The DAO for using live API calls.
 */
public class APICallDataAccessObject implements SearchByAirlineIDDataAccessInterface,
        SearchByFlightNumberDataAccessInterface {

    private static final String ACCESS_KEY = "..."; // Replace with your own access key
    // (eg: "f3b8e30f646315a2874f86284f52d5b9")

    // Helper method to fetch and parse flight data from the API
    private List<JSONObject> fetchFlightsFromApi() {
        List<JSONObject> flightDataList = new ArrayList<JSONObject>();
        String apiUrl = "https://api.aviationstack.com/v1/flights?access_key=" + ACCESS_KEY;

        try {
            // Create the connection and make the GET request to the API
            URL url = new URL(apiUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            int responseCode = connection.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                StringBuilder content = new StringBuilder();
                String inputLine;

                while ((inputLine = in.readLine()) != null) {
                    content.append(inputLine);
                }

                in.close();
                connection.disconnect();

                // Parse the response JSON
                JSONObject jsonResponse = new JSONObject(content.toString());
                JSONArray data = jsonResponse.getJSONArray("data");

                // Add each flight data JSON object to the list
                for (int i = 0; i < data.length(); i++) {
                    flightDataList.add(data.getJSONObject(i));
                }
            } else {
                System.out.println("GET request failed. Response Code: " + responseCode);
            }

        } catch (Exception e) {
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
        List<JSONObject> flightDataList = fetchFlightsFromApi();

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

        // Fetch the data from the API
        List<JSONObject> flightDataList = fetchFlightsFromApi();

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
        List<JSONObject> flightDataList = fetchFlightsFromApi();

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
