import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Graph graph = new Graph();
        if (args.length > 0) {
            MapReader.readMap(args[0], graph);
        }
        boolean running = true;
        while (running) {
            printMenu();
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

    private static void printMenu() {
        System.out.println("=== Campus Map Finder ===");
        System.out.println("1. Load campus map");
        System.out.println("2. View places (A-to-Z)");
        System.out.println("3. Find shortest route (BFS)");
        System.out.println("4. Exit");
        System.out.print("Enter choice (1-4): ");
    }

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
