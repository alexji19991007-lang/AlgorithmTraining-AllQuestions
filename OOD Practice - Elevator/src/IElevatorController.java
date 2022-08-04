public interface IElevatorController {
    void status();

    Elevator getElevator(int id);

    void update(int elevatorId, int floor);

    void sendPickupRequest(Request request);

    void reset(int id, int floor);
}
