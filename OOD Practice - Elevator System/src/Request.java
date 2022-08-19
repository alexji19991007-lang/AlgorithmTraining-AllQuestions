public class Request {
    int currentFloor;
    int targetFloor;
    boolean isFromInside;

    public Request(int currentFloor, int targetFloor, boolean isFromInside) {
        this.currentFloor = currentFloor;
        this.targetFloor = targetFloor;
        this.isFromInside = isFromInside;
    }
}
