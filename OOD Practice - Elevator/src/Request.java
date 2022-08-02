public class Request {
    int currentFloor;
    int targetFloor;
    Status status;
    boolean isFromInside;

    public Request(int currentFloor, int targetFloor, Status status, boolean isFromInside) {
        this.currentFloor = currentFloor;
        this.targetFloor = targetFloor;
        this.status = status;
        this.isFromInside = isFromInside;
    }
}
