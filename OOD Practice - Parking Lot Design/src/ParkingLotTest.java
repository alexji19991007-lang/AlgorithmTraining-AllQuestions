import java.util.*;

public class ParkingLotTest {
    public static void main(String[] args) {
        ParkingLot myLot = new ParkingLot(4, 10);
        List<Vehicle> carList = new ArrayList<>();
        for (int i = 0; i < 50; ++i) {
            final Vehicle v = i % 2 == 0 ? new Car() : new Truck();
            carList.add(v);
            boolean hasSpot = myLot.hasSpot(v);
            if (i < 40) {
                assert hasSpot;
                assert myLot.park(v);
            } else {
                assert !hasSpot;
                assert !myLot.park(v);
            }
        }
        assert carList.size() == 50;
        int i = 0;
        for (Vehicle v : carList) {
            assert i >= 40 || myLot.leave(v);
            ++i;
        }
        System.out.println("Test All Passed.");
    }
}
