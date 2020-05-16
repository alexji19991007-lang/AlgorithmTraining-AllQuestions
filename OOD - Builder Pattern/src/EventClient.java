import java.util.Date;

public class EventClient {
    Event careerFair = new Event.EventBuilder("Vanderbilt Career Fair",
            "SLC Ballroom", new Date(2020))
            .setAdmissionFee(0.00)
            .setOrganizer("Vanderbilt Career Center")
            .setPhoneNumber("615-415-5656")
            .setStartTime("4:00 P.M.")
            .build();
}
