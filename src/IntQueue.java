public class IntQueue {
    private int[] items;
    private int head;
    private int tail;
    private int size;

    public IntQueue() {
        items = new int[10];
        head = 0;
        tail = 0;
        size = 0;
    }

    public void add(int value) {
        if (size == items.length) {
            grow();
        }
        items[tail] = value;
        tail = (tail + 1) % items.length;
        size++;
    }

    public int poll() {
        if (size == 0) {
            return -1;
        }
        int value = items[head];
        head = (head + 1) % items.length;
        size--;
        return value;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    private void grow() {
        int[] bigger = new int[items.length * 2];
        for (int i = 0; i < size; i++) {
            bigger[i] = items[(head + i) % items.length];
        }
        items = bigger;
        head = 0;
        tail = size;
    }
}