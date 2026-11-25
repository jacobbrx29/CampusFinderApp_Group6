// Undirected campus graph with adjacency lists, BFS, and Dijkstra routing.
public class Graph {
    private static final int MAX_PLACES = 50;
    private static final int INF = 1000000000;

    private PlaceList places;
    private NeighborNode[] neighbors;

    public Graph() {
        places = new PlaceList();
        neighbors = new NeighborNode[MAX_PLACES];
    }

    public int findPlace(String name) {
        return places.indexOf(name);
    }

    // Adds a place if missing and returns its graph index.
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

    // Connects two existing places in both directions with a distance weight.
    public void addRoad(String place1, String place2, int distance) {
        int index1 = findPlace(place1);
        int index2 = findPlace(place2);
        if (index1 == -1 || index2 == -1) {
            System.out.println("Cannot add road, unknown place: "
                    + place1 + " or " + place2);
            return;
        }
        addNeighbor(index1, index2, distance);
        addNeighbor(index2, index1, distance);
    }

    private void addNeighbor(int from, int to, int distance) {
        NeighborNode newNode = new NeighborNode(to, distance);
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
        if (places.isEmpty()) {
            System.out.println("No places loaded. Load a map first.");
            return;
        }
        int count = places.size();
        System.out.println("Campus map (" + count + " places):");
        for (int index = 0; index < count; index++) {
            String line = places.get(index) + " -> ";
            NeighborNode current = neighbors[index];
            while (current != null) {
                line = line + places.get(current.placeIndex)
                        + " (" + current.distance + "m)";
                if (current.next != null) {
                    line = line + ", ";
                }
                current = current.next;
            }
            System.out.println(line);
        }
    }

    public boolean isEmpty() {
        return places.isEmpty();
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
        if (places.isEmpty()) {
            System.out.println("No places loaded. Load a map first.");
            return;
        }
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

    // Traverses reachable buildings level-by-level starting from one place.
    public void breadthFirst(String start) {
        if (places.isEmpty()) {
            System.out.println("No places loaded. Load a map first.");
            return;
        }
        int startIndex = findPlace(start);
        if (startIndex == -1) {
            System.out.println("Place not found: " + start);
            return;
        }

        boolean[] visited = new boolean[places.size()];
        IntQueue queue = new IntQueue();

        visited[startIndex] = true;
        queue.add(startIndex);

        System.out.println("BFS: start --> " + start);
        while (!queue.isEmpty()) {
            int current = queue.poll();
            System.out.println(places.get(current));

            NeighborNode neighbor = neighbors[current];
            while (neighbor != null) {
                int nextIndex = neighbor.placeIndex;
                if (!visited[nextIndex]) {
                    visited[nextIndex] = true;
                    queue.add(nextIndex);
                }
                neighbor = neighbor.next;
            }
        }
    }

    // Shows both fewest-stops (BFS) and shortest-distance (Dijkstra) routes.
    public void lookPath(String startPlace, String endPlace) {
        if (places.isEmpty()) {
            System.out.println("No places loaded. Load a map first.");
            return;
        }
        int startIndex = findPlace(startPlace);
        int endIndex = findPlace(endPlace);
        if (startIndex == -1 || endIndex == -1) {
            System.out.println("Place not found: " + startPlace
                    + " or " + endPlace);
            return;
        }
        boolean found = runBfsPath(startPlace, endPlace, startIndex, endIndex);
        if (found) {
            runDijkstra(startIndex, endIndex);
        }
    }

    // Finds the path with the fewest stops using our custom IntQueue.
    private boolean runBfsPath(String startPlace, String endPlace,
            int startIndex, int endIndex) {
        int count = places.size();
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
            visitUnseenNeighbors(current, visited, parent, queue);
        }
        if (!visited[endIndex]) {
            System.out.println("No path exists from " + startPlace
                    + " to " + endPlace);
            return false;
        }
        printRoute("Fewest-stops path (BFS)", parent, endIndex, -1);
        return true;
    }

    private void visitUnseenNeighbors(int current, boolean[] visited,
            int[] parent, IntQueue queue) {
        NeighborNode neighbor = neighbors[current];
        while (neighbor != null) {
            int nextIndex = neighbor.placeIndex;
            if (!visited[nextIndex]) {
                visited[nextIndex] = true;
                parent[nextIndex] = current;
                queue.add(nextIndex);
            }
            neighbor = neighbor.next;
        }
    }

    // Finds the shortest route by total meter distance using Dijkstra.
    private void runDijkstra(int startIndex, int endIndex) {
        int count = places.size();
        int[] dist = new int[count];
        int[] parent = new int[count];
        boolean[] visited = new boolean[count];
        for (int i = 0; i < count; i++) {
            dist[i] = INF;
            parent[i] = -1;
        }
        dist[startIndex] = 0;
        for (int step = 0; step < count; step++) {
            int current = findClosestUnvisited(dist, visited);
            if (current == -1 || current == endIndex) {
                break;
            }
            visited[current] = true;
            relaxNeighbors(current, dist, parent, visited);
        }
        printRoute("Shortest-distance path (Dijkstra)",
                parent, endIndex, dist[endIndex]);
    }

    private int findClosestUnvisited(int[] dist, boolean[] visited) {
        int bestIndex = -1;
        int bestDist = INF;
        for (int i = 0; i < dist.length; i++) {
            if (!visited[i] && dist[i] < bestDist) {
                bestDist = dist[i];
                bestIndex = i;
            }
        }
        return bestIndex;
    }

    private void relaxNeighbors(int current, int[] dist, int[] parent,
            boolean[] visited) {
        NeighborNode neighbor = neighbors[current];
        while (neighbor != null) {
            int nextIndex = neighbor.placeIndex;
            int newDist = dist[current] + neighbor.distance;
            if (!visited[nextIndex] && newDist < dist[nextIndex]) {
                dist[nextIndex] = newDist;
                parent[nextIndex] = current;
            }
            neighbor = neighbor.next;
        }
    }

    // Rebuilds a route from parent[] breadcrumbs and prints stops and meters.
    private void printRoute(String label, int[] parent,
            int endIndex, int knownDist) {
        int[] path = new int[places.size()];
        int length = 0;
        int step = endIndex;
        while (step != -1) {
            path[length] = step;
            length++;
            step = parent[step];
        }
        int totalDist = knownDist;
        if (totalDist < 0) {
            totalDist = computePathDistance(path, length);
        }
        System.out.print(label + ": ");
        for (int i = length - 1; i >= 0; i--) {
            System.out.print(places.get(path[i]));
            if (i > 0) {
                System.out.print(" -> ");
            }
        }
        System.out.println();
        System.out.println("  Stops: " + (length - 1)
                + " | Distance: " + totalDist + " meters");
    }

    private int computePathDistance(int[] path, int length) {
        int total = 0;
        for (int i = length - 1; i > 0; i--) {
            total = total + getRoadDistance(path[i], path[i - 1]);
        }
        return total;
    }

    private int getRoadDistance(int from, int to) {
        NeighborNode current = neighbors[from];
        while (current != null) {
            if (current.placeIndex == to) {
                return current.distance;
            }
            current = current.next;
        }
        return 0;
    }
}
