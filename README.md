# CampusFinderApp_Group6

A text-only Campus Map Finder. The campus map is stored as a graph
(adjacency list), and later weeks add BFS to find the shortest route.

## Project structure

```
src/
  Main.java          starts the program and prints the graph
  Graph.java         graph of places (adjacency list)
  NeighborNode.java  one node in a place's neighbor linked list
  MapReader.java     reads and validates the map file, builds the graph
data/
  campus_map.txt     campus map: 12 places, 17 roads
  test_bad_lines.txt test file with bad lines (blank, 3 names, self-loop)
```

## Map file format

Each line is `Place1 Place2`, meaning there is a road between them.
Roads go both ways. Place names are single words (e.g. `ScienceHall`).
Bad lines are skipped with a message showing the line number and reason.

## How to compile and run

Run these from the project folder (requires Java):

```bash
javac -d out src/*.java
java -cp out Main
```

To load a different map file, add its name at the end:

```bash
java -cp out Main data/test_bad_lines.txt
```

## Contributions

- **Reymbert Belga (rymbrt):** Graph class with adjacency list, campus map
  file reader, file format validation, and graph construction from the
  map file (issues #10, #11, #12, #13).

## Help sources

- I (rymbrt) used an AI coding assistant to help draft the Graph and file
  reader code. I reviewed all of it and understand how every part works.