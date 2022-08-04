public class Request {
    int currentFloor;
    int targetFloor;
    Status status;
    boolean isFromInside;
    int numPassengers;

    public Request(int currentFloor, int targetFloor, Status status, boolean isFromInside, int numPassengers) {
        this.currentFloor = currentFloor;
        this.targetFloor = targetFloor;
        this.status = status;
        this.isFromInside = isFromInside;
        this.numPassengers = numPassengers;
    }

    public boolean isUpwardRequest() {
        return targetFloor - currentFloor > 0;
    }
}
