// Displays the scaled ASCII DLSU campus map graph and building legend.
public class CampusMapView {
    private static final String[] MAP_LINES = {
        "+----------------------------------------------------------------+",
        "|           DE LA SALLE UNIVERSITY - CAMPUS MAP GRAPH            |",
        "|  NORTH ->       Scale: ---- = 30m (1 col ~ 7.5m, 1 row ~ 15m)  |",
        "+----------------------------------------------------------------+",
        "|                        [FC]-----[W]                            |",
        "|                          |     / |                             |",
        "|                  +------[==SJ==] |       [MM]                  |",
        "|                 /        |     \\ |      /                      |",
        "|   +-----[SPS]--[Y]------[H]-----[B]--[M]-      [STRC]    [E]   |",
        "|   |        |    |      / | \\    |   / |  \\        |       |    |",
        "|  [J]       |    |     /  |  \\   |  / [AG]-:-------+-------+    |",
        "|   |        |    |    /   |   \\  | /  / \\   \\              |    |",
        "|   |        |    |   /    |    \\ |/  /   \\   \\             |    |",
        "|   |        |    |  /     |     \\|/ /     \\  |             |    |",
        "|   +-------[===LS===]-----:-----[V]-       -[G]           [A]   |",
        "|      +-----+         \\   |    /             |             |    |",
        "|      |                \\  | /--+             |             |    |",
        "|  ==[G1]================[G2]===============[G4A]=========[G5A]= |",
        "+----------------------------------------------------------------+",
        "| Legend (use exact names in option 3):                          |",
        "|  LS=StLaSalleHall   FC=FacultyCenter    M=StMiguelHall         |",
        "|  J=JohnHall         W=WilliamHall       MM=MutienMarieHall     |",
        "|  SPS=ConnonHall     SJ=StJosephHall     STRC=STRC              |",
        "|  Y=YuchengcoHall    H=HenrySyHall       E=RazonSportsCenter    |",
        "|  V=VelascoHall      B=BloemenHall       G=GokongweiHall        |",
        "|  A=AndrewGonzalezHall  G1=Gate1  G2=Gate2  AG=AgnoGate         |",
        "|  G4A=Gate4A  G5A=Gate5A                                        |",
        "+----------------------------------------------------------------+"
    };

    // Prints the ASCII campus map and building legend above the menu.
    public static void printMap() {
        for (int i = 0; i < MAP_LINES.length; i++) {
            System.out.println(MAP_LINES[i]);
        }
    }
}
