import java.util.AbstractList;


public class SortedArrayList<E extends Comparable<E>> extends AbstractList {
    E[] baseArray;
    int size;
    int capacity;
    int initialCapacity;

    public SortedArrayList() {
        baseArray = (E[]) new Comparable[10];

        initialCapacity = 10;
        capacity = 10;
        size = 0;
    }

    public SortedArrayList(int inputSize) {
        baseArray = (E[]) new Comparable[inputSize];

        initialCapacity = inputSize;
        capacity = inputSize;
        size = 0;
    }

    @Override
    public void clear() {
        baseArray = (E[]) new Comparable[initialCapacity];

        capacity = initialCapacity;
        size = 0;
    }

    @Override
    public int size() {
        return size;
    }

    public int capacity() { return capacity; }

    @Override
    public boolean isEmpty() {
        if (size == 0) {
            return true;
        }
        return false;
    }

    public boolean isFull() {
        if (size == capacity) {
            return true;
        }
        return false;
    }

    public boolean add(E item) {
        if (isFull()) {
            expandArray(baseArray);
        }

        if (isEmpty()) {
            baseArray[size] = item;
        } else {
            for (int i = size - 1; i >= 0; i--) {

                if (item.compareTo(baseArray[i]) > 0) {
                    baseArray[i + 1] = item;
                    break;
                } else {
                    baseArray[i + 1] = baseArray[i];
                }

                if (i == 0) {
                    baseArray[i] = item;
                }
            }
        }
        size++;

        return true;
    }

    public void expandArray(E[] inputArray) {
        baseArray = (E[]) new Comparable[capacity*2];
        capacity = capacity*2;

        System.arraycopy(inputArray, 0, baseArray,
                0, size);
    }

    public void shrinkArray(E[] inputArray) {
        baseArray = (E[]) new Comparable[capacity/2];
        capacity = capacity/2;

        System.arraycopy(inputArray, 0, baseArray,
                0, size);
    }

    @Override
    public E get(int index) {
        return baseArray[index];
    }

    public E remove(int index) {
        E removedValue = get(index);

        for (int i = index; i < size; i++) {
            if (i == size - 1) {
                baseArray[i] = null;
            }
            else {
                baseArray[i] = baseArray[i+1];
            }
        }
        size--;

        if ((size == capacity/2) && (capacity > initialCapacity)) {
            shrinkArray(baseArray);
        }

        return removedValue;
    }
}
