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
}
