import java.util.*;

public class Elevator {
    private final int id;
    private final List<FloorButton> buttons;
    private ElevatorStatus elevatorStatus;
    private int curFloor;
    private final Queue<Request> upRequestQueue;
    private final Queue<Request> downRequestQueue;

    public Elevator(int id, int numFloors) {
        this.id = id;
        this.buttons = new ArrayList<>();
        for (int i = 0; i < numFloors; ++i) {
            buttons.add(new FloorButton(i));
        }
        this.elevatorStatus = ElevatorStatus.IDLE;
        this.curFloor = 0;
        // This is a min heap
        this.upRequestQueue = new PriorityQueue<>(Comparator.comparingInt(r -> r.targetFloor));
        // This is a max heap
        this.downRequestQueue = new PriorityQueue<>((r1, r2) -> Integer.compare(r2.targetFloor, r1.targetFloor));
    }

    public void pressFloorButton(int targetFloor) {
        if (targetFloor == curFloor) {
            return;
        }
        buttons.get(targetFloor).setStatus();
        Request request = new Request(curFloor, targetFloor, true);
        if (targetFloor < curFloor) {
            sendDownRequest(request);
        } else {
            sendUpRequest(request);
        }
    }

    public void run() {
        while (!upRequestQueue.isEmpty() || !downRequestQueue.isEmpty()) {
            processRequests();
        }
        System.out.println("All requests finished. Status is IDLE");
        this.elevatorStatus = ElevatorStatus.IDLE;
    }

    public void sendUpRequest(Request upRequest) {
        if (!isValidFloor(upRequest.targetFloor)) {
            throw new IllegalArgumentException("Illegal target floor");
        }
        // The request to pick the person up or to the person's target floor
        upRequestQueue.offer(upRequest);
        // If the request is from outside
        if (!upRequest.isFromInside) {
            System.out.println("Received pickup request (up) to floor " + upRequest.targetFloor);
        } else {
            System.out.println("Received up request to floor " + upRequest.targetFloor);
        }
    }

    public void sendDownRequest(Request downRequest) {
        if (!isValidFloor(downRequest.targetFloor)) {
            throw new IllegalArgumentException("Illegal target floor");
        }
        // The request to pick the person up or to the person's target floor
        downRequestQueue.offer(downRequest);
        // If the request is from outside, then we have to add two requests to the priority queue
        if (!downRequest.isFromInside) {
            System.out.println("Received pickup request (down) to floor " + downRequest.currentFloor);
        } else {
            System.out.println("Received down request to floor " + downRequest.targetFloor);
        }
    }

    public int getId() {
        return this.id;
    }

    public ElevatorStatus getElevatorStatus() {
        return this.elevatorStatus;
    }

    public int getCurFloor() {
        return this.curFloor;
    }

    private void processRequests() {
        if (this.elevatorStatus == ElevatorStatus.UP || this.elevatorStatus == ElevatorStatus.IDLE) {
            processUpRequest();
            processDownRequest();
        } else {
            processDownRequest();
            processUpRequest();
        }
    }

    private void processUpRequest() {
        while (!upRequestQueue.isEmpty()) {
            Request r = upRequestQueue.poll();
            if (this.curFloor == r.targetFloor) {
                continue;
            }
            this.curFloor = r.targetFloor;
            System.out.println("Going up and stopped at " + this.curFloor + " floor");
        }
        if (!downRequestQueue.isEmpty()) {
            this.elevatorStatus = ElevatorStatus.DOWN;
        } else {
            this.elevatorStatus = ElevatorStatus.IDLE;
        }
    }

    private void processDownRequest() {
        while (!downRequestQueue.isEmpty()) {
            Request r = downRequestQueue.poll();
            if (this.curFloor == r.targetFloor) {
                continue;
            }
            this.curFloor = r.targetFloor;
            System.out.println("Going down and stopped at " + this.curFloor + " floor");
        }
        if (!upRequestQueue.isEmpty()) {
            this.elevatorStatus = ElevatorStatus.UP;
        } else {
            this.elevatorStatus = ElevatorStatus.IDLE;
        }
    }

    private boolean isValidFloor(int floor) {
        return floor <= Constants.MAX_FLOOR && floor >= Constants.MIN_FLOOR;
    }
}
