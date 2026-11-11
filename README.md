# Campus Map Finder (Group 6)

A text-based Java console application for DATSTAL (Term 4). The program models a
campus map as an undirected graph, sorts locations alphabetically, and uses
Breadth-First Search (BFS) to find the shortest path between places.

All data structures are built from scratch without using Java collection or
sorting libraries (no `java.util.ArrayList`, `LinkedList`, `HashMap`, etc.).

## Menu Options

When you run the program, a 4-choice menu appears:

1. **Load campus map:** Reads the map file and builds the graph. Press Enter to
   load the default campus map (`data/campus_map.txt`) or type a custom file.
2. **View places (A-to-Z):** Displays all campus places in alphabetical order
   using insertion sort.
3. **Find shortest route (BFS):** Prompts for a start and destination, then
   prints the shortest route and the number of stops.
4. **Exit:** Quits the program.

## Project Structure

```
src/
  Main.java          Interactive 4-choice menu and program entry point
  Graph.java         Adjacency list graph, BFS traversal, and pathfinding
  NeighborNode.java  Linked list node for neighbor places
  MapReader.java     Reads and validates map data files
  Place.java         Place model (name and index id)
  PlaceList.java     Custom growable array of places with insertion sort
  IntQueue.java      Custom circular array queue for BFS
  Week1Tests.java    Unit tests for PlaceList and IntQueue
data/
  campus_map.txt     Campus map (12 places, 17 roads)
  test_bad_lines.txt Test file with blank lines, bad tokens, and self-loops
```

## How to Compile and Run

Requires Java (JDK):

```bash
javac -d out src/*.java
java -cp out Main
```

To run the unit tests:

```bash
java -cp out Week1Tests
```

## Map File Format

- Each line represents a two-way road between two places: `Place1 Place2`
- Names must be single words without spaces.
- Blank lines, lines with fewer or more than 2 names, and self-loops are
  skipped with a descriptive error message instead of crashing.

## Team Contributions

- **Chase Carmelo B. Capilli (cccchase):**
  Place model, PlaceList growable array, duplicate rejection, and alphabetical
  insertion sort (issues #2, #3, #4, #5).
- **Rone Jacob G. Medina (jacobbrx29):**
  IntQueue circular queue, BFS traversal, visited tracking, and shortest-path
  reconstruction (issues #6, #7, #8, #9).
- **Reymbert Belga (rymbrt):**
  Graph adjacency list, MapReader file loader, file validation, graph
  construction, and unit tests (issues #10, #11, #12, #13).
