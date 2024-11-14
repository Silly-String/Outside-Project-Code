package java.entities;

/**
 * The representation of an airport in our program.
 * Class created in the even we decide to use this instead of a string.
 */
public class Airport {

    private String airportCode; // eg: "MNL" for Ninoy Aquino International(the iata code)
    private String name;
    private double[] location;

    // Constructors
    public Airport() {}

    public Airport(String airportCode, String name, double[] location) {
        this.airportCode = airportCode;
        this.name = name;
        this.location = location;
    }

    // Getters
    public String getairportCode() {
        return airportCode;
    }

    public String getName() {
        return name;
    }

    public double[] getLocation() {
        return location;
    }

    // Setters
    public void setairportCode(String airportCode) {
        this.name = airportCode;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setLocation(double[] location) {
        this.location = location;
    }

    // string method
    @Override
    public String toString() {
        return "Airport{" +
                "airportCode='" + airportCode + '\'' +
                "name='" + name + '\'' +
                ", location='" + location + '\'' +
                '}';
    }
}
