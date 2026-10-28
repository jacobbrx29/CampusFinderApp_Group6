public class Week1Tests {
    private static int passed = 0;
    private static int total = 0;

    public static void main(String[] args) {
        testEmptyList();
        testAddAndGet();
        testDuplicateRejected();
        testIndexOf();
        testListGrows();
        testGetOutOfRange();
        testSortedOrder();
        testOriginalUnchanged();
        testSortEmptyList();
        testQueueFifo();
        testQueueWrapAround();
        testQueueEmpty();
        System.out.println(passed + " out of " + total + " tests passed");
    }

    private static void check(String name, boolean result) {
        total++;
        if (result) {
            passed++;
            System.out.println("PASS: " + name);
        } else {
            System.out.println("FAIL: " + name);
        }
    }

    private static void testEmptyList() {
        PlaceList list = new PlaceList();
        check("new list is empty", list.isEmpty() && list.size() == 0);
    }

    private static void testAddAndGet() {
        PlaceList list = new PlaceList();
        list.add(new Place("Library", 0));
        list.add(new Place("Gym", 1));
        boolean ok = list.size() == 2;
        ok = ok && list.get(0).getName().equals("Library");
        ok = ok && list.get(1).getName().equals("Gym");
        check("add and get places", ok);
    }

    private static void testDuplicateRejected() {
        PlaceList list = new PlaceList();
        list.add(new Place("Library", 0));
        boolean added = list.add(new Place("Library", 1));
        check("duplicate name rejected", !added && list.size() == 1);
    }

    private static void testIndexOf() {
        PlaceList list = new PlaceList();
        list.add(new Place("Library", 0));
        list.add(new Place("Gym", 1));
        boolean ok = list.indexOf("Gym") == 1;
        ok = ok && list.indexOf("Cafeteria") == -1;
        check("indexOf found and missing", ok);
    }

    private static void testListGrows() {
        PlaceList list = new PlaceList();
        for (int i = 0; i < 25; i++) {
            list.add(new Place("Place" + i, i));
        }
        boolean ok = list.size() == 25;
        ok = ok && list.get(24).getName().equals("Place24");
        check("list grows past capacity", ok);
    }

    private static void testGetOutOfRange() {
        PlaceList list = new PlaceList();
        list.add(new Place("Library", 0));
        boolean ok = list.get(5) == null && list.get(-1) == null;
        check("get out of range returns null", ok);
    }

    private static void testSortedOrder() {
        PlaceList list = new PlaceList();
        list.add(new Place("Library", 0));
        list.add(new Place("cafeteria", 1));
        list.add(new Place("Gym", 2));
        PlaceList sorted = list.sortedCopy();
        boolean ok = sorted.get(0).getName().equals("cafeteria");
        ok = ok && sorted.get(1).getName().equals("Gym");
        ok = ok && sorted.get(2).getName().equals("Library");
        check("sortedCopy is A to Z", ok);
    }

    private static void testOriginalUnchanged() {
        PlaceList list = new PlaceList();
        list.add(new Place("Library", 0));
        list.add(new Place("Gym", 1));
        list.sortedCopy();
        boolean ok = list.get(0).getName().equals("Library");
        check("sortedCopy keeps original order", ok);
    }

    private static void testSortEmptyList() {
        PlaceList list = new PlaceList();
        PlaceList sorted = list.sortedCopy();
        check("sortedCopy of empty list", sorted.isEmpty());
    }

    private static void testQueueFifo() {
        IntQueue queue = new IntQueue();
        queue.add(1);
        queue.add(2);
        queue.add(3);
        boolean ok = queue.poll() == 1;
        ok = ok && queue.poll() == 2;
        ok = ok && queue.poll() == 3;
        check("queue is first in first out", ok);
    }

    private static void testQueueWrapAround() {
        IntQueue queue = new IntQueue();
        for (int i = 0; i < 8; i++) {
            queue.add(i);
        }
        for (int i = 0; i < 6; i++) {
            queue.poll();
        }
        for (int i = 8; i < 20; i++) {
            queue.add(i);
        }
        boolean ok = true;
        for (int i = 6; i < 20; i++) {
            if (queue.poll() != i) {
                ok = false;
            }
        }
        check("queue wraps around and grows", ok && queue.isEmpty());
    }

    private static void testQueueEmpty() {
        IntQueue queue = new IntQueue();
        boolean ok = queue.isEmpty() && queue.poll() == -1;
        check("empty queue poll returns -1", ok);
    }
}
