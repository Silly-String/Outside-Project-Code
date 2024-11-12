package entities;

/**
 * Factory for creating Airport objects.
 * Class created in the even we decide to use this instead of a string.
 */

public class AirportFactory {

    public Airport create(String name, String location) {
        return new Airport(name,location);
    }
}