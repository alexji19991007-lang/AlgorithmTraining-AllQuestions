import java.util.*;

public class Elevator {
    int currentFloor;
    Status status;
    Queue<Request> upRequestQueue;
    Queue<Request> downRequestQueue;
    private List<Integer> history;

    public Elevator(int currentFloor) {
        this.currentFloor = currentFloor;
        this.status = Status.IDLE;
        // This is a min heap
        upRequestQueue = new PriorityQueue<>(Comparator.comparingInt(r -> r.targetFloor));
        // This is a max heap
        downRequestQueue = new PriorityQueue<>((r1, r2) -> Integer.compare(r2.targetFloor, r1.targetFloor));
        history = new ArrayList<>();
    }

    public void sendUpRequest(Request upRequest) {
        // If the request is from outside, then we have to add two requests to the priority queue
        if (!upRequest.isFromInside) {
            // 1. The request to pick the person up
            upRequestQueue.offer(new Request(upRequest.currentFloor, upRequest.currentFloor, Status.UP, false));
            System.out.println("Received pickup request (up) to floor " + upRequest.currentFloor);
        }
        // 2. The request to go to the person's target floor
        upRequestQueue.offer(upRequest);
        System.out.println("Received up request to floor " + upRequest.targetFloor);
    }

    public void sendDownRequest(Request downRequest) {
        // If the request is from outside, then we have to add two requests to the priority queue
        if (!downRequest.isFromInside) {
            // 1. The request to pick the person up
            downRequestQueue.offer(new Request(downRequest.currentFloor, downRequest.currentFloor, Status.DOWN, false));
            System.out.println("Received pickup request (down) to floor " + downRequest.currentFloor);
        }
        // 2. The request to go to the person's target floor
        downRequestQueue.offer(downRequest);
        System.out.println("Received down request to floor " + downRequest.targetFloor);
    }

    public void run() {
        while (!upRequestQueue.isEmpty() || !downRequestQueue.isEmpty()) {
            processRequests();
        }
        System.out.println("All requests finished. Status is IDLE");
        this.status = Status.IDLE;
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
            System.out.println("Going down and stopped at " + this.currentFloor + " floor");
        }
        if (!upRequestQueue.isEmpty()) {
            this.status = Status.UP;
        } else {
            this.status = Status.IDLE;
        }
    }
}
