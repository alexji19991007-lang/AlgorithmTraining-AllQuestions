package HackerRank;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TruckPositions {
    static class Position {
        int x;
        int y;

        public Position(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public void updatePosition(int deltaX, int deltaY) {
            this.x += deltaX;
            this.y += deltaY;
        }

        @Override
        public String toString() {
            return x + " " + y;
        }
    }

    static class Truck {
        int truckId;
        Position p;
        List<Client> clientList;

        public Truck(int truckId, int x, int y) {
            this.truckId = truckId;
            this.p = new Position(x, y);
            clientList = new ArrayList<>();
        }

        public void updatePosition(int deltaX, int deltaY) {
            this.p.updatePosition(deltaX, deltaY);
            for (Client c : clientList) {
                c.truckPositionUpdates.add(new Position(deltaX, deltaY));
            }
        }
    }

    static class Client {
        int clientId;
        List<Position> truckPositionUpdates;

        public Client(int clientId) {
            this.clientId = clientId;
            this.truckPositionUpdates = new ArrayList<>();
        }

        public List<Position> requestUpdate() {
            List<Position> result = new ArrayList<>(truckPositionUpdates);
            this.truckPositionUpdates.clear();
            return result;
        }
    }

    Map<Integer, Truck> truckIdToTruckMap;
    Map<Integer, Client> clientIdToClientMap;

    public TruckPositions() {
        this.truckIdToTruckMap = new HashMap<>();
        this.clientIdToClientMap = new HashMap<>();
    }

    public void addTruck(int id, int x, int y) {
        truckIdToTruckMap.put(id, new Truck(id, x, y));
    }

    public String subscribeToTruck(int clientId, int truckId) {
        clientIdToClientMap.put(clientId, new Client(clientId));
        Truck truck = truckIdToTruckMap.get(truckId);
        return "S " + clientId + truckId + truck.p.toString();
    }

}
