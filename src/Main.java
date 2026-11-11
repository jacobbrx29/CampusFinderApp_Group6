import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        String fileName = "data/campus_map.txt";
        if (args.length > 0) {
            fileName = args[0];
        }
        System.out.println("");
        Graph graph = new Graph();
        boolean loaded = MapReader.readMap(fileName, graph);
        if (loaded) {
            graph.printGraph();
            graph.breadthFirst("MainGate Library");
            graph.lookPath("MainGate", "Library");
        }
    }
}