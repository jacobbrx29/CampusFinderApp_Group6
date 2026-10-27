import java.util.Queue;
import java.util.LinkedList;


public class Graph {
    private static final int MAX_PLACES = 50;

    private String[] places;
    private NeighborNode[] neighbors;
    private int count;

    public Graph() {
        places = new String[MAX_PLACES];
        neighbors = new NeighborNode[MAX_PLACES];
        count = 0;
    }

    public int findPlace(String name) {
        for (int index = 0; index < count; index++) {
            if (places[index].equals(name)) {
                return index;
            }
        }
        return -1;
    }

    public int addPlace(String name) {
        int existingIndex = findPlace(name);
        if (existingIndex != -1) {
            return existingIndex;
        }
        if (count == MAX_PLACES) {
            System.out.println("Graph is full, cannot add " + name);
            return -1;
        }
        places[count] = name;
        neighbors[count] = null;
        count++;
        return count - 1;
    }

    public void addRoad(String place1, String place2) {
        int index1 = findPlace(place1);
        int index2 = findPlace(place2);
        if (index1 == -1 || index2 == -1) {
            System.out.println("Cannot add road, unknown place: "
                    + place1 + " or " + place2);
            return;
        }
        addNeighbor(index1, index2);
        addNeighbor(index2, index1);
    }

    private void addNeighbor(int from, int to) {
        NeighborNode newNode = new NeighborNode(to);
        if (neighbors[from] == null) {
            neighbors[from] = newNode;
            return;
        }
        NeighborNode current = neighbors[from];
        while (current.next != null) {
            current = current.next;
        }
        current.next = newNode;
    }

    public void printGraph() {
        System.out.println("Campus map (" + count + " places):");
        for (int index = 0; index < count; index++) {
            String line = places[index] + " -> ";
            NeighborNode current = neighbors[index];
            while (current != null) {
                line = line + places[current.placeIndex];
                if (current.next != null) {
                    line = line + ", ";
                }
                current = current.next;
            }
            System.out.println(line);
        }
    }

    public void queueStruct(String startPlace){
        int startIndex = findPlace(startPlace);
        if (startIndex == -1) {
            System.out.println("Place not found"+ startPlace);
            return;
        }
        boolean[] visited = new boolean[count];

        Queue<Integer> queue= new LinkedList<>();

        visited[startIndex] = true;

        while (!queue.isEmpty()){
            int current = queue.poll();
            System.out.println(places[current]); // extracts the place from the user that chooses until the queue is empty

            NeighborNode neighbor = neighbors[current];
            while (neighbor != null){ //
                if (visited[neighbor.placeIndex]){
                    visited[neighbor.placeIndex] = true;
                    queue.add(neighbor.placeIndex);
                }

            }
            neighbor = neighbor.next; //finds an anoteher neigbor of the chosen starting point
        }
    }
    public void findPath(String startPlace, String endPlace) {
        int startIndex = findPlace(startPlace);
        int endIndex = findPlace(endPlace);

        if (startIndex == -1 || endIndex == -1) {
            System.out.println("Place not found: " + startPlace + " or " + endPlace);
            return;
        }

        boolean[] visited = new boolean[count];
        int[] parent = new int[count];
        for (int i = 0; i < count; i++) {
            parent[i] = -1;
        }

        Queue<Integer> queue = new LinkedList<>();
        visited[startIndex] = true;
        queue.add(startIndex);

        while (!queue.isEmpty()) {
            int current = queue.poll();

            if (current == endIndex) {
                break;
            }

            NeighborNode neighbor = neighbors[current];
            while (neighbor != null) {
                if (!visited[neighbor.placeIndex]) {
                    visited[neighbor.placeIndex] = true;
                    parent[neighbor.placeIndex] = current;
                    queue.add(neighbor.placeIndex);
                }
                neighbor = neighbor.next;
            }
        }

        if (!visited[endIndex]) {
            System.out.println("No path exists from " + startPlace + " to " + endPlace);
            return;
        }

        int[] pathBuffer = new int[count];
        int pathLength = 0;
        int step = endIndex;
        while (step != -1) {
            pathBuffer[pathLength] = step;
            pathLength++;
            step = parent[step];
        }

        System.out.print("Path: ");
        for (int i = pathLength - 1; i >= 0; i--) {
            System.out.print(places[pathBuffer[i]]);
            if (i > 0) {
                System.out.print(" -> ");
            }
        }
        System.out.println();
        System.out.println("Number of stops: " + (pathLength - 1));
    }



}
