package java.entities;

/**
 * Represents an airline with relevant details.
 */
public class Airline {
    private String airlineCode;      // e.g., "AA" for American Airlines (the iata code)
    private String name;             // Full name of the airline

    // Constructors
    public Airline() {}

    public Airline(String airlineCode, String name) {
        this.airlineCode = airlineCode;
        this.name = name;
    }

    // Getters
    public String getAirlineCode() {
        return airlineCode;
    }

    public String getName() {
        return name;
    }

    // Setters
    public void setAirlineCode(String airlineCode) {
        this.airlineCode = airlineCode;
    }

    public void setName(String name) {
        this.name = name;
    }

    // string method
    @Override
    public String toString() {
        return airlineCode + "{" +
                ", name='" + name + '\'' +
                '}';
    }
}
