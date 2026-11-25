# Campus Map Finder (Group 6)

A text-based Java console application for DATSTAL (Term 4). The program models
the DLSU Manila campus map as an undirected graph, sorts locations A-to-Z, and
finds paths between places using Breadth-First Search (BFS) for fewest stops
and Dijkstra's algorithm for shortest distance in meters.

All data structures and algorithms are built from scratch without using Java
collection or sorting libraries (no `ArrayList`, `LinkedList`, `HashMap`,
`Queue`, or library sort/search).

## Menu Options

When you run the program, a 4-choice menu appears (the ASCII DLSU map graph
displays automatically once the DLSU campus map is loaded):

1. **Load the map file:** Reads a map file and builds the graph. Press Enter
   to load `data/campus_map.txt` (21 DLSU locations, 40 roads) or type a custom
   file path.
2. **Show all places in A-to-Z order:** Displays all loaded places sorted
   alphabetically using insertion sort on a copy.
3. **Find a path between 2 places:** Prompts for a start and destination, then
   prints both the fewest-stops path (BFS) and shortest-distance path
   (Dijkstra, with total meters).
4. **Quit:** Exits the program.

## Project Structure

```
src/
  Main.java           4-choice interactive menu and program entry point
  CampusMapView.java  ASCII DLSU campus map graph and building legend
  Graph.java          Weighted adjacency list graph, BFS, and Dijkstra search
  NeighborNode.java   Linked list node for neighbor index and distance (meters)
  MapReader.java      Reads and validates 2-word or 3-word map lines
  Place.java          Place model (name and index id)
  PlaceList.java      Custom growable array of places with insertion sort
  IntQueue.java       Custom circular array queue for BFS
  Week1Tests.java     Unit tests for PlaceList and IntQueue
data/
  campus_map.txt      DLSU campus map (21 locations, 40 two-way roads)
  test_bad_lines.txt  Validation test file (blank, bad tokens, self-loop, etc.)
  sample_output.txt   Example console run of all 4 menu options
```

## How to Compile and Run

Requires Java (JDK):

```bash
javac -d out src/*.java
java -cp out Main
```

To run the unit tests for `PlaceList` and `IntQueue`:

```bash
java -cp out Week1Tests
```

## Map File Format

- Supports both `Place1 Place2` (default distance `1`) and weighted
  `Place1 Place2 Distance` lines (roads go both ways).
- Place names must be single words without spaces.
- Missing files, blank lines, invalid token counts, self-loops, and invalid
  distances are reported safely without crashing.

## Distance Measurement and Map Scaling

- **Scalar edge weights:** Each road stores an undirected distance in whole
  meters (`int distance`), identical in both directions.
- **GPS measurement:** Distances were computed center-to-center using the
  Haversine formula from surveyed DLSU Manila building polygon centroids and
  gate GPS coordinates (Google Maps / OpenStreetMap). Because values are
  center-to-center rather than door-to-door, spans include building footprints
  (for example, `StLaSalleHall` to `HenrySyHall` = 100m, `Gate1` to
  `Gate2` = 143m).
- **ASCII map scaling:** Surveyed GPS coordinates are projected along the
  Taft Avenue axis into clean horizontal campus bands on a 66-column grid at
  `1 col ~ 7.5m` and `1 row ~ 15.0m` (`---- = 30m`), compensating for the ~2:1
  height-to-width monospace ratio (`Gate1`-`Gate2` 143m ~ `Gate2`-`Gate4A`
  146m > `Gate4A`-`Gate5A` 102m; `AgnoGate` opens onto one straight Agno way
  entering `STRC`, `RazonSportsCenter`, and `AndrewGonzalezHall` directly,
  plus `VelascoHall`-`AgnoGate` 48m and `StMiguelHall`-`GokongweiHall` 73m).
  Exact route totals use the integer meters in `data/campus_map.txt`.

## Team Contributions

- **Chase Carmelo B. Capilli (cccchase):**
  Place model, PlaceList growable array, duplicate rejection, and alphabetical
  insertion sort (issues #2, #3, #4, #5).
- **Rone Jacob G. Medina (jacobbrx29):**
  IntQueue circular queue, BFS traversal, visited tracking, and shortest-path
  reconstruction (issues #6, #7, #8, #9).
- **Reymbert Belga (rymbrt):**
  Graph adjacency list, MapReader loader and validation, Dijkstra distance
  routing, interactive menu, ASCII map, and unit tests (issues #10–#13).

## Help Sources

- DATSTAL class lecture notes and textbook materials on arrays, queues,
  insertion sort, adjacency list graphs, BFS, and Dijkstra's algorithm.
- Official Java SE documentation for `Scanner` and `FileNotFoundException`.
- De La Salle University Manila campus layout map and Google Maps /
  OpenStreetMap GPS coordinates for exact building-to-building distances.
