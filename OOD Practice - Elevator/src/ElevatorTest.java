import junit.framework.TestCase;

import java.util.Arrays;
import java.util.List;

public class ElevatorTest extends TestCase {
    private Elevator elevator;

    protected void setUp() throws Exception {
        this.elevator = new Elevator(0,4);
    }

    public void testElevator_PickUpAndGoUp() throws Exception {
        elevator.sendUpRequest(new Request(5, 10, Status.UP, false, 1));
        elevator.run();
        List<Integer> history = elevator.getHistory();
        List<Integer> expectedVisit = Arrays.asList(5, 10);
        assertEquals(expectedVisit, history);
    }

    public void testElevator_PickUpAndGoDown() throws Exception {
        elevator.sendDownRequest(new Request(3, 0, Status.DOWN, false, 1));
        elevator.run();
        List<Integer> history = elevator.getHistory();
        List<Integer> expectedVisit = Arrays.asList(3, 0);
        assertEquals(expectedVisit, history);
    }

    public void testElevator_MultipleRequests() throws Exception {
        elevator.sendDownRequest(new Request(2, 0, Status.DOWN, false, 1));
        elevator.sendDownRequest(new Request(1, 0, Status.DOWN, true, 1));
        elevator.sendDownRequest(new Request(3, 1, Status.DOWN, false, 1));
        elevator.run();
        elevator.sendUpRequest(new Request(elevator.getCurrentFloor(), 5, Status.UP, true, 1));
        elevator.sendUpRequest(new Request(elevator.getCurrentFloor(), 7, Status.UP, true, 1));
        elevator.sendUpRequest(new Request(4, 0, Status.DOWN, false, 1));
        elevator.sendUpRequest(new Request(6, 8, Status.UP, false, 1));
        elevator.run();
    }
}
