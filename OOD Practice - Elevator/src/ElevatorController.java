import java.util.NoSuchElementException;

public class ElevatorController implements IElevatorController{
    private Elevator[] elevators;
    private int numElevators;

    public ElevatorController(int numElevators) {
        initElevators(numElevators);
    }

    private void initElevators(int numElevators) {
        if (numElevators <= 0) {
            throw new IllegalArgumentException("No elevators");
        }
        this.numElevators = numElevators;
        this.elevators = new Elevator[numElevators];
        for (int i = 0; i < elevators.length; ++i) {
            elevators[i] = new Elevator(i, 0);
        }
    }

    @Override
    public void status() {

    }

    @Override
    public Elevator getElevator(int id) {
        return null;
    }

    @Override
    public void update(int elevatorId, int floor) {

    }

    @Override
    public void sendPickupRequest(Request request) {
        if (request.currentFloor == request.targetFloor) {
            return;
        }
        boolean isUpward = request.isUpwardRequest();
        Elevator targetElevator = isUpward ? selectUpwardElevator(request.currentFloor) : selectDownwardElevator(request.currentFloor);
        if (isUpward) {
            targetElevator.sendUpRequest(request);
        } else {
            targetElevator.sendDownRequest(request);
        }
    }

    @Override
    public void reset(int id, int floor) {
        if (id < 0 || id >= numElevators) {
            throw new NoSuchElementException("Invalid elevator id");
        }
        elevators[id].reset(floor);
    }

    private Elevator selectUpwardElevator(int targetFloor) {
        Elevator targetElevator = null;
        int curMinFloor = Integer.MAX_VALUE;
        for (Elevator el : elevators) {
            if (el.getStatus() == Status.DOWN) {
                continue;
            }
            int curFloor = el.getCurrentFloor();
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
            if (el.getStatus() == Status.UP) {
                continue;
            }
            int curFloor = el.getCurrentFloor();
            if (curFloor <= targetFloor && curFloor < curMinFloor) {
                targetElevator = el;
            }
        }
        return targetElevator;
    }

    private int calculateRoute(int curFloor, int targetFloor) {
        return targetFloor - curFloor;
    }
}
