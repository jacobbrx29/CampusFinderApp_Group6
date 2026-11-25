import java.util.Scanner;

// Console entry point running the 4-choice Campus Map Finder menu.
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Graph graph = new Graph();
        if (args.length > 0) {
            MapReader.readMap(args[0], graph);
        }
        boolean running = true;
        while (running) {
            printMenu(graph);
            String choice = scanner.nextLine().trim();
            System.out.println();
            if (choice.equals("1")) {
                graph = handleLoadMap(scanner, graph);
            } else if (choice.equals("2")) {
                graph.printSortedPlaces();
            } else if (choice.equals("3")) {
                handleFindRoute(scanner, graph);
            } else if (choice.equals("4")) {
                System.out.println("Exiting program. Goodbye!");
                running = false;
            } else {
                System.out.println("Invalid choice. Enter 1, 2, 3, or 4.");
            }
            System.out.println();
        }
    }

    private static void printMenu(Graph graph) {
        if (isDlsuMap(graph)) {
            CampusMapView.printMap();
        }
        System.out.println("=== Campus Map Finder ===");
        System.out.println("1. Load campus map");
        System.out.println("2. View places (A-to-Z)");
        System.out.println("3. Find a path between 2 places");
        System.out.println("4. Exit");
        System.out.print("Enter choice (1-4): ");
    }

    // Shows the ASCII DLSU map only when the DLSU campus map is loaded.
    private static boolean isDlsuMap(Graph graph) {
        return graph.findPlace("StLaSalleHall") != -1
                && graph.findPlace("HenrySyHall") != -1
                && graph.findPlace("GokongweiHall") != -1;
    }

    // Loads a map file into a fresh graph and prints its adjacency list.
    private static Graph handleLoadMap(Scanner scanner, Graph current) {
        System.out.print("Enter map file (press Enter for default): ");
        String file = scanner.nextLine().trim();
        if (file.isEmpty()) {
            file = "data/campus_map.txt";
        }
        Graph nextGraph = new Graph();
        if (MapReader.readMap(file, nextGraph)) {
            nextGraph.printGraph();
            return nextGraph;
        }
        return current;
    }

    // Prompts for start and destination places and prints both routes.
    private static void handleFindRoute(Scanner scanner, Graph graph) {
        if (graph.isEmpty()) {
            System.out.println("No places loaded. Load a map first.");
            return;
        }
        System.out.print("Enter start place: ");
        String start = scanner.nextLine().trim();
        System.out.print("Enter destination place: ");
        String end = scanner.nextLine().trim();
        graph.lookPath(start, end);
    }
}
