package HackerRank;

import java.util.*;

public class TrafficNetworkMonitor {
    public static void main(String[] args) {
        TrafficNetworkMonitor test = new TrafficNetworkMonitor();
        String i1 = "OPEN s1 c1";
        String i2 = "READ s1 1 256";
        String i3 = "WRITE s1 1 512";
        String i4 = "OPEN s1 c1";
        String i5 = "OPEN s1 c2";
        String i6 = "OPEN s2 c2";
        String i7 = "WRITE s1 3 1025";
        String i8 = "READ s1 2 64";
        List<String> input = Arrays.asList(i1, i2, i3, i4, i5, i6, i7, i8);
        System.out.println(test.monitor(input));
    }

    public List<String> monitor(List<String> input) {
        if (input == null || input.size() == 0) {
            return new ArrayList<>();
        }
        Map<String, Server> nameToServer = new HashMap<>();
        for (String s : input) {
            String[] array = s.split(" ");
            if (array.length == 3) {
                // This is an OPEN operation
                String serverName = array[1], clientName = array[2];
                Server server = nameToServer.getOrDefault(serverName, null);
                if (server == null) {
                    nameToServer.put(serverName, new Server(serverName, clientName));
                } else {
                    server.establishConnection(clientName);
                    nameToServer.put(serverName, server);
                }
            } else {
                // This is a READ or WRITE operation
                String serverName = array[1];
                int clientId = Integer.parseInt(array[2]);
                long bytes = Long.parseLong(array[3]);
                Server server = nameToServer.get(serverName);
                server.dataTransfer(clientId, bytes);
            }
        }
        List<String> res = new ArrayList<>();
        for (Map.Entry<String, Server> entry : nameToServer.entrySet()) {
            res.add(entry.getKey() + " " + entry.getValue().maxTransferClient);
        }
        return res;
    }

    static class Server {
        String server;
        int id;
        String maxTransferClient;
        Map<Integer, String> idToClient;
        Map<String, Long> connections;

        public Server(String server, String client) {
            this.server = server;
            this.id = 1;
            this.maxTransferClient = client;
            this.idToClient = new HashMap<>();
            idToClient.put(id++, client);
            this.connections = new HashMap<>();
            connections.put(client, (long) 128);
        }

        public void establishConnection(String client) {
            idToClient.put(id++, client);
            long newTotalSize = connections.getOrDefault(client, (long) 0) + 128;
            if (connections.get(maxTransferClient) < newTotalSize) {
                maxTransferClient = client;
            }
            connections.put(client, newTotalSize);
        }

        public void dataTransfer(int id, long size) {
            String client = idToClient.get(id);
            long newTotalSize = connections.get(client) + size;
            if (connections.get(maxTransferClient) < newTotalSize) {
                maxTransferClient = client;
            }
            connections.put(client, newTotalSize);
        }
    }
}
