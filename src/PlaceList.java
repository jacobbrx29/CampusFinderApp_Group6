public class PlaceList {
    private Place[] items;
    private int count;

    public PlaceList() {
        items = new Place[10];
        count = 0;
    }

    public boolean add(Place place) {
        if (place == null) {
            return false;
        }
        if (indexOf(place.getName()) != -1) {
            return false;
        }
        if (count == items.length) {
            grow();
        }
        items[count] = place;
        count++;
        return true;
    }

    public Place get(int index) {
        if (index < 0 || index >= count) {
            return null;
        }
        return items[index];
    }

    public int size() {
        return count;
    }

    public boolean isEmpty() {
        return count == 0;
    }

    public int indexOf(String name) {
        for (int i = 0; i < count; i++) {
            if (items[i].getName().equals(name)) {
                return i;
            }
        }
        return -1;
    }

    public PlaceList sortedCopy() {
        PlaceList copy = new PlaceList();
        for (int i = 0; i < count; i++) {
            copy.add(items[i]);
        }
        copy.insertionSort();
        return copy;
    }

    private void insertionSort() {
        for (int i = 1; i < count; i++) {
            Place current = items[i];
            int j = i - 1;
            while (j >= 0 && comesAfter(items[j], current)) {
                items[j + 1] = items[j];
                j--;
            }
            items[j + 1] = current;
        }
    }

    private boolean comesAfter(Place first, Place second) {
        String a = first.getName();
        String b = second.getName();
        return a.compareToIgnoreCase(b) > 0;
    }

    private void grow() {
        Place[] bigger = new Place[items.length * 2];
        for (int i = 0; i < count; i++) {
            bigger[i] = items[i];
        }
        items = bigger;
    }
}
