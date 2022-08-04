import java.util.*;

public class Elevator {
    private int id;
    private int currentFloor;
    private Status status;
    private final Queue<Request> upRequestQueue;
    private final Queue<Request> downRequestQueue;
    int numPassengers;
    private final List<Integer> history;
    private ElevatorEventListener elEventListener;

    public Elevator(int id, int currentFloor) {
        if (!isValidFloor(currentFloor)) {
            throw new IllegalArgumentException("Illegal starting floor");
        }
        this.id = id;
        this.currentFloor = currentFloor;
        this.status = Status.IDLE;
        // This is a min heap
        this.upRequestQueue = new PriorityQueue<>(Comparator.comparingInt(r -> r.targetFloor));
        // This is a max heap
        this.downRequestQueue = new PriorityQueue<>((r1, r2) -> Integer.compare(r2.targetFloor, r1.targetFloor));
        this.numPassengers = 0;
        this.history = new ArrayList<>();
    }

    public void sendUpRequest(Request upRequest) {
        if (!isValidFloor(upRequest.targetFloor)) {
            throw new IllegalArgumentException("Illegal target floor");
        }
        // If the request is from outside, then we have to add two requests to the priority queue
        if (!upRequest.isFromInside) {
            // 1. The request to pick the person up
            upRequestQueue.offer(new Request(upRequest.currentFloor, upRequest.currentFloor, Status.UP, false, upRequest.numPassengers));
            System.out.println("Received pickup request (up) to floor " + upRequest.currentFloor);
        }
        // 2. The request to go to the person's target floor
        upRequestQueue.offer(upRequest);
        System.out.println("Received up request to floor " + upRequest.targetFloor);
    }

    public void sendDownRequest(Request downRequest) {
        if (!isValidFloor(downRequest.targetFloor)) {
            throw new IllegalArgumentException("Illegal target floor");
        }
        // If the request is from outside, then we have to add two requests to the priority queue
        if (!downRequest.isFromInside) {
            // 1. The request to pick the person up
            downRequestQueue.offer(new Request(downRequest.currentFloor, downRequest.currentFloor, Status.DOWN, false, downRequest.numPassengers));
            System.out.println("Received pickup request (down) to floor " + downRequest.currentFloor);
        }
        // 2. The request to go to the person's target floor
        downRequestQueue.offer(downRequest);
        System.out.println("Received down request to floor " + downRequest.targetFloor);
    }

    public void run() {
        while (!upRequestQueue.isEmpty() || !downRequestQueue.isEmpty()) {
            if (numPassengers > Constants.MAX_CAPACITY) {
                this.status = Status.OVERLOADED;
                System.out.println("The elevator is overloaded");
                return;
            }
            processRequests();
        }
        System.out.println("All requests finished. Status is IDLE");
        this.status = Status.IDLE;
    }

    public void reset(int floor) {
        this.currentFloor = floor;
        this.numPassengers = 0;
        this.upRequestQueue.clear();
        this.downRequestQueue.clear();
        this.status = Status.IDLE;
    }

    public void setElEventListener(ElevatorEventListener elEventListener) {
        this.elEventListener = elEventListener;
    }

    public int getCurrentFloor(){
        return this.currentFloor;
    }

    public Status getStatus() {
        return this.status;
    }

    public List<Integer> getHistory() {
        return history;
    }

    private void processRequests() {
        if (this.status == Status.UP || this.status == Status.IDLE) {
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
            if (this.currentFloor == r.targetFloor) {
                continue;
            }
            this.currentFloor = r.targetFloor;
            history.add(r.targetFloor);
            if (elEventListener != null) {
                elEventListener.onStopped(this);
            }
            System.out.println("Going up and stopped at " + this.currentFloor + " floor");
        }
        if (!downRequestQueue.isEmpty()) {
            this.status = Status.DOWN;
        } else {
            this.status = Status.IDLE;
        }
    }

    private void processDownRequest() {
        while (!downRequestQueue.isEmpty()) {
            Request r = downRequestQueue.poll();
            if (this.currentFloor == r.targetFloor) {
                continue;
            }
            this.currentFloor = r.targetFloor;
            history.add(r.targetFloor);
            if (elEventListener != null) {
                elEventListener.onStopped(this);
            }
            System.out.println("Going down and stopped at " + this.currentFloor + " floor");
        }
        if (!upRequestQueue.isEmpty()) {
            this.status = Status.UP;
        } else {
            this.status = Status.IDLE;
        }
    }

    private boolean isValidFloor(int floor) {
        return floor <= Constants.MAX_FLOOR && floor >= Constants.MIN_FLOOR;
    }
}
