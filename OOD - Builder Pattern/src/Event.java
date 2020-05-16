import java.util.Date;

public class Event {
    private final String eventName;
    private final String eventLocation;
    private final Date eventDate;
    private String organizer;
    private double admissionFee;
    private String phoneNumber;
    private String startTime;

    private Event(EventBuilder builder) {
        this.eventName = builder.eventName;
        this.eventLocation = builder.eventLocation;
        this.eventDate = builder.eventDate;
        this.organizer = builder.organizer;
        this.admissionFee = builder.admissionFee;
        this.phoneNumber = builder.phoneNumber;
        this.startTime = builder.startTime;
    }

    public String getEventName() {
        return eventName;
    }

    public String getEventLocation() {
        return eventLocation;
    }

    public Date getEventDate() {
        return eventDate;
    }

    public String getOrganizer() {
        return organizer;
    }

    public double getAdmissionFee() {
        return admissionFee;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getStartTime() {
        return startTime;
    }


    public static class EventBuilder {
        private final String eventName;
        private final String eventLocation;
        private final Date eventDate;
        private String organizer = "";
        private double admissionFee = 0.01;
        private String phoneNumber = "012-345-6789";
        private String startTime = "0:00 A.M.";

        public EventBuilder(String eventName, String eventLocation, Date eventDate) {
            this.eventName = eventName;
            this.eventLocation = eventLocation;
            this.eventDate = eventDate;
        }

        public EventBuilder setOrganizer(String organizer) {
            this.organizer = organizer;
            return this;
        }

        public EventBuilder setAdmissionFee(double admissionFee) {
            this.admissionFee = admissionFee;
            return this;
        }

        public EventBuilder setPhoneNumber(String phoneNumber) {
            this.phoneNumber = phoneNumber;
            return this;
        }

        public EventBuilder setStartTime(String startTime) {
            this.startTime = startTime;
            return this;
        }

        public Event build() {
            return new Event(this);
        }
    }
}
