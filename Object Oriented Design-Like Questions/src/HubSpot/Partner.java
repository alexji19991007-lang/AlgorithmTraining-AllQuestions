package HubSpot;

import java.util.ArrayList;
import java.util.List;

public class Partner {
    String firstName;
    String lastName;
    String email;
    String country;
    List<String> availableDates;

    public Partner(String firstName, String lastName, String email, String country, List<String> availableDates) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.country = country;
        this.availableDates = new ArrayList<>(availableDates);
    }
}
