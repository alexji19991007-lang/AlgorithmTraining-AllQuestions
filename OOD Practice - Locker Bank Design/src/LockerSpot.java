public class LockerSpot {
    private BoxSize size;
    private LockerStatus status;
    private Box box;
    private int lockerID;

    public LockerSpot(BoxSize size, int lockerID) {
        this.size = size;
        this.status = LockerStatus.UNOCCUPIED;
        this.lockerID = lockerID;
    }

    public void putInBox(Box box) {
        setBox(box);
        setStatus(LockerStatus.OCCUPIED);
        this.box.setLockerLocation(this.lockerID);
    }

    public void setBox(Box box) {
        this.box = box;
    }

    public void setStatus(LockerStatus status) {
        this.status = status;
    }

    public int getLockerID() {
        return lockerID;
    }

    public BoxSize getSize() {
        return size;
    }
}
