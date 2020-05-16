import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Locker {
    // We use a queue to determine which spot will be used upon next call
    private Queue<LockerSpot> mLockerQueue;

    public Locker(int numLockers, BoxSize size) {
        List<LockerSpot> mLockerSpots = new ArrayList<>();
        for (int i = 0; i < numLockers; ++i) {
            mLockerSpots.add(new LockerSpot(size, i));
        }
        mLockerQueue = new LinkedList<>(mLockerSpots);
    }

    public boolean isSpotAvailable() {
        // if the there is no elements in the queue, then all spots have been used up
        return mLockerQueue.size() > 0;
    }

    public LockerSpot getSpot() {
        return mLockerQueue.poll();
    }

    public void addSpotToQueue(LockerSpot spot) {
        mLockerQueue.add(spot);
    }
}
