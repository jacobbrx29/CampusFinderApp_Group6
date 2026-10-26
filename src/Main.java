public class Main {
    public static void main(String[] args) {
        Graph graph = new Graph();
        graph.addPlace("Library");
        graph.addPlace("Cafeteria");
        graph.addPlace("Gym");
        graph.addPlace("Library");
        graph.addRoad("Library", "Cafeteria");
        graph.addRoad("Cafeteria", "Gym");
        graph.printGraph();
    }
}
