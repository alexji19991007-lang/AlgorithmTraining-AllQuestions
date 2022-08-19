public class FloorButton {
    private int floor;

    private FloorButtonStatus status;

    public FloorButton(int floor) {
        this.floor = floor;
        this.status = FloorButtonStatus.Unlighted;
    }

    public int getFloor() {
        return this.floor;
    }

    public void setStatus() {
        status = FloorButtonStatus.Lighted;
    }

    public FloorButtonStatus getStatus() {
        return this.status;
    }
}
