import java.util.HashMap;
import java.util.Map;

public class LockerManager {
    // We have ten locker spots for each size
    private static final int MAX_SMALL = 10;
    private static final int MAX_MEDIUM = 10;
    private static final int MAX_LARGE = 10;
    // The Locker is represented as an array of size 3.
    private Locker[] mLockers;
    // Pair each box with their spot
    private Map<Box, LockerSpot> boxLocation;

    public LockerManager() {
        mLockers = new Locker[3];
        mLockers[0] = new Locker(MAX_SMALL, BoxSize.SMALL);
        mLockers[1] = new Locker(MAX_MEDIUM, BoxSize.MEDIUM);
        mLockers[2] = new Locker(MAX_LARGE, BoxSize.LARGE);
        boxLocation = new HashMap<>();
    }

    public void addBox(Box box) {
        boolean isSpotFound = false;
        // if the box size is small, start from 0; if it's medium, starts from 1, .etc.
        for (int i = box.getSize().ordinal(); i <= BoxSize.LARGE.ordinal(); ++i) {
            if (mLockers[i].isSpotAvailable()) {
                LockerSpot newSpot = mLockers[1].getSpot();
                newSpot.putInBox(box);
                boxLocation.put(box, newSpot);
                isSpotFound = true;
                break;
            }
        }
        if (!isSpotFound) {
            System.out.println("wait");
        }
    }

    public void removeBox(Box box) {
        if (boxLocation.containsKey(box)) {
            LockerSpot lockerSpot = boxLocation.get(box);
            lockerSpot.setStatus(LockerStatus.UNOCCUPIED);
            boxLocation.remove(box);
            mLockers[box.getLockerLocation()].addSpotToQueue(lockerSpot);
        }
    }

    public String lookForBox(Box box) {
        if (boxLocation.containsKey(box)) {
            LockerSpot lockerSpot = boxLocation.get(box);
            int lockerID = lockerSpot.getLockerID();
            BoxSize size = lockerSpot.getSize();
            return "" + lockerID + size;
        }
        return "Your box is not found";
    }
}
