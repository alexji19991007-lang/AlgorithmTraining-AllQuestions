import java.util.ArrayList;
import java.util.List;

public class ElevatorSystem {
    private List<UpDownButton> floors;
    private List<Elevator> elevators;

    public ElevatorSystem(int numFloors, int numElevators) {
        this.floors = new ArrayList<>();
        this.elevators = new ArrayList<>();
        for (int i = 0; i < numFloors; ++i) {
            floors.add(new UpDownButton(i));
        }
        for (int i = 0; i < numElevators; ++i) {
            elevators.add(new Elevator(i, numFloors));
        }
    }

    public void pressUpDownButton(int floor, UpDownButtonStatus status) {
        floors.get(floor).setStatus(status);
        sendPickUpRequest(floors.get(floor));
    }

    private void sendPickUpRequest(UpDownButton button) {
        boolean isUpward = button.getStatus() == UpDownButtonStatus.Up;
        Request pickupRequest = new Request(-1, button.getFloor(), false);
        Elevator targetElevator = isUpward ? selectUpwardElevator(pickupRequest.targetFloor) : selectDownwardElevator(pickupRequest.targetFloor);
        pickupRequest.currentFloor = targetElevator.getCurFloor();
        if (pickupRequest.targetFloor > pickupRequest.currentFloor) {
            targetElevator.sendUpRequest(pickupRequest);
        } else {
            targetElevator.sendDownRequest(pickupRequest);
        }
    }

    private Elevator selectUpwardElevator(int targetFloor) {
        Elevator targetElevator = null;
        int curMinFloor = Integer.MAX_VALUE;
        for (Elevator el : elevators) {
            if (el.getElevatorStatus() == ElevatorStatus.DOWN) {
                continue;
            }
            int curFloor = el.getCurFloor();
            if (curFloor <= targetFloor && curFloor < curMinFloor) {
                targetElevator = el;
            }
        }
        return targetElevator;
    }

    private Elevator selectDownwardElevator(int targetFloor) {
        Elevator targetElevator = null;
        int curMinFloor = Integer.MAX_VALUE;
        for (Elevator el : elevators) {
            if (el.getElevatorStatus() == ElevatorStatus.UP) {
                continue;
            }
            int curFloor = el.getCurFloor();
            if (curFloor <= targetFloor && curFloor < curMinFloor) {
                targetElevator = el;
            }
        }
        return targetElevator;
    }
}
