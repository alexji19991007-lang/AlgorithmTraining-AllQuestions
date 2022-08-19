public class UpDownButton {
    private final int floor;
    private UpDownButtonStatus status;

    public UpDownButton(int floor) {
        this.floor = floor;
        this.status = UpDownButtonStatus.None;
    }

    public void setStatus(UpDownButtonStatus status) {
        this.status = status;
    }

    public int getFloor() {
        return this.floor;
    }

    public UpDownButtonStatus getStatus() {
        return this.status;
    }
}
