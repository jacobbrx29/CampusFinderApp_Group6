// Linked list node storing a neighbor's index and road distance in meters.
public class NeighborNode {
    int placeIndex;
    int distance;
    NeighborNode next;

    // Creates a neighbor edge pointing to placeIndex with given meters.
    public NeighborNode(int placeIndex, int distance) {
        this.placeIndex = placeIndex;
        this.distance = distance;
        this.next = null;
    }
}
