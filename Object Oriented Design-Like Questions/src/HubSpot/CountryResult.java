package HubSpot;

import java.util.ArrayList;
import java.util.List;

public class CountryResult {
    int attendeeCount;
    List<String> attendees;
    String name;
    String startDate;

    public CountryResult(int attendeeCount, List<String> attendees, String name, String startDate) {
        this.attendeeCount = attendeeCount;
        this.attendees = new ArrayList<>(attendees);
        this.name = name;
        this.startDate = startDate;
    }


}
