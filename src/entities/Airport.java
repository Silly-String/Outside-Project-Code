package entities;

/**
 * The representation of an airport in our program.
 * Class created in the even we decide to use this instead of a string.
 */

public class Airport {

    private String name;
    private String location;

    // Constructor
    public Airport(String name, String location) {
        this.location = location;
        this.name = name;
    }

    // Getters
    public String getLocation() {
        return location;
    }

    public String getName() {
        return name;
    }

    // Setters
    public void setLocation(String location) {
        this.location = location;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Airport{" +
                "location='" + location + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
