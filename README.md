# CampusFinderApp_Group6

A text-only Java Campus Map Finder for DATSTAL (Group 6). The campus map is
modeled as an undirected graph using custom data structures built from scratch
(no `java.util` collection or sorting libraries), with Breadth-First Search
(BFS) to find the shortest route between places.

## Data structures and algorithms

- **Place & PlaceList:** Dynamic growable array (`Place[]`) storing campus
  places, supporting index lookup, linear search, duplicate rejection, and
  case-insensitive insertion sort on a copy (`sortedCopy`).
- **IntQueue:** Circular array queue (`int[]`) supporting FIFO `add`, `poll`,
  `isEmpty`, and automatic capacity doubling.
- **Graph & NeighborNode:** Adjacency list using singly linked lists of
  neighbor indices for two-way roads, supporting sorted place listing, BFS
  traversal, and shortest-path reconstruction.

## Project structure

```
src/
  Main.java          starts the program, loads the map, and prints the graph
  Place.java         represents a campus place (name and id)
  PlaceList.java     growable array list of places with insertion sort
  IntQueue.java      circular array queue of place indices (FIFO)
  NeighborNode.java  one node in a place's neighbor linked list
  Graph.java         adjacency list graph, sorting view, and BFS pathfinding
  MapReader.java     reads and validates the map file, builds the graph
  Week1Tests.java    automated unit tests for PlaceList and IntQueue
data/
  campus_map.txt     campus map: 12 places, 17 roads
  test_bad_lines.txt test file with bad lines (blank, 3 names, self-loop)
```

## Map file format

Each line is `Place1 Place2`, meaning there is a two-way road between them.
Place names are single words (e.g. `ScienceHall`). Missing files and invalid
lines (blank lines, fewer or more than 2 names, or a place connected to
itself) are handled safely with clear console messages instead of crashing.

## How to compile and run

Run these from the project folder (requires Java JDK, no external libraries):

```bash
javac -d out src/*.java
java -cp out Main
```

To test validation with a custom or malformed map file:

```bash
java -cp out Main data/test_bad_lines.txt
java -cp out Main missing.txt
```

To run the unit tests for `PlaceList`, sorting, and `IntQueue`:

```bash
java -cp out Week1Tests
```

## Contributions

- **Chase Carmelo B. Capilli (cccchase):** `Place` data model, `PlaceList`
  growable array with add, retrieve, and duplicate handling, and alphabetical
  insertion sort with edge cases (issues #2, #3, #4, #5).
- **Rone Jacob G. Medina (jacobbrx29):** `IntQueue` FIFO circular queue, BFS
  traversal, visited tracking, and shortest-path reconstruction (issues #6,
  #7, #8, #9).
- **Reymbert Belga (rymbrt):** `Graph` class with adjacency list
  (`NeighborNode`), `MapReader` file loader, file format validation, graph
  construction from map data, and unit tests (issues #10, #11, #12, #13).
