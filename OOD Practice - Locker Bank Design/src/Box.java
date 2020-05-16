public class Box {
    private int id;
    private BoxSize size;
    private int lockerLocation;

    public Box(int id, BoxSize size) {
        this.id = id;
        this.size = size;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setLockerLocation(int lockerLocation) {
        this.lockerLocation = lockerLocation;
    }

    public int getId() {
        return id;
    }

    public BoxSize getSize() {
        return size;
    }

    public int getLockerLocation() {
        return lockerLocation;
    }
}
