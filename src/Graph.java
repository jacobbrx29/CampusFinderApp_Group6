public class Graph {
    private static final int MAX_PLACES = 50;

    private PlaceList places;
    private NeighborNode[] neighbors;

    public Graph() {
        places = new PlaceList();
        neighbors = new NeighborNode[MAX_PLACES];
    }

    public int findPlace(String name) {
        return places.indexOf(name);
    }

    public int addPlace(String name) {
        int existingIndex = findPlace(name);
        if (existingIndex != -1) {
            return existingIndex;
        }
        if (places.size() == MAX_PLACES) {
            System.out.println("Graph is full, cannot add " + name);
            return -1;
        }
        int newIndex = places.size();
        places.add(new Place(name, newIndex));
        neighbors[newIndex] = null;
        return newIndex;
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
        int count = places.size();
        System.out.println("Campus map (" + count + " places):");
        for (int index = 0; index < count; index++) {
            String line = places.get(index) + " -> ";
            NeighborNode current = neighbors[index];
            while (current != null) {
                line = line + places.get(current.placeIndex);
                if (current.next != null) {
                    line = line + ", ";
                }
                current = current.next;
            }
            System.out.println(line);
        }
    }

    public void printSortedPlaces() {
        if (places.isEmpty()) {
            System.out.println("No places loaded. Load a map first.");
            return;
        }
        PlaceList sorted = places.sortedCopy();
        System.out.println("Places in A-to-Z order:");
        for (int i = 0; i < sorted.size(); i++) {
            System.out.println((i + 1) + ". " + sorted.get(i));
        }
    }

    public void queueStruct(String startPlace) {
        int startIndex = findPlace(startPlace);
        if (startIndex == -1) {
            System.out.println("Place not found: " + startPlace);
            return;
        }
        boolean[] visited = new boolean[places.size()];
        IntQueue queue = new IntQueue();
        visited[startIndex] = true;
        queue.add(startIndex);

        while (!queue.isEmpty()) {
            int current = queue.poll();
            System.out.println(places.get(current));

            NeighborNode neighbor = neighbors[current];
            while (neighbor != null) {
                if (!visited[neighbor.placeIndex]) {
                    visited[neighbor.placeIndex] = true;
                    queue.add(neighbor.placeIndex);
                }
                neighbor = neighbor.next;
            }
        }
    }

    public void findPath(String startPlace, String endPlace) {
        int count = places.size();
        int startIndex = findPlace(startPlace);
        int endIndex = findPlace(endPlace);

        if (startIndex == -1 || endIndex == -1) {
            System.out.println("Place not found: " + startPlace
                    + " or " + endPlace);
            return;
        }

        boolean[] visited = new boolean[count];
        int[] parent = new int[count];
        for (int i = 0; i < count; i++) {
            parent[i] = -1;
        }

        IntQueue queue = new IntQueue();
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
            System.out.println("No path exists from " + startPlace
                    + " to " + endPlace);
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
            System.out.print(places.get(pathBuffer[i]));
            if (i > 0) {
                System.out.print(" -> ");
            }
        }
        System.out.println();
        System.out.println("Number of stops: " + (pathLength - 1));
    }
}