/**
 * (CS-310 / Program 1 - Empirical Timing) SortedArrayList Class
 * Class containing implementation of data structure SortedArrayList
 * @author Anthony Norderhaug
 * @date 9/17/2020
 */
import java.util.AbstractList;


public class SortedArrayList<E extends Comparable<E>> extends AbstractList {
    E[] baseArray; // array of type parameter E to act as SAL's base
    int size;
    int capacity;
    int initialCapacity; // stores initial capacity, ensures array shrinking does not go below this value

    /**
     * Constructor with no capacity specified. Initializes baseArray with Comparable[] of capacity 10 cast to
     * type parameter E. Also initializes related class members with associated values.
     */
    public SortedArrayList() {
        baseArray = (E[]) new Comparable[10];
        // array of Comparable

        initialCapacity = 10;
        capacity = 10;
        size = 0;
    }

    /**
     * Constructor with capacity specified. Initializes baseArray with Comparable[] of capacity specified cast to
     * type parameter E. Also initializes related class members with associated values.
     *
     * @param inputCapacity                                 int representing desired SAL capacity
     */
    public SortedArrayList(int inputCapacity) {
        baseArray = (E[]) new Comparable[inputCapacity];

        initialCapacity = inputCapacity;
        capacity = inputCapacity;
        size = 0;
    }

    /**
     * clear() reverts the SAL and related members back to their original state, initializing its baseArray with
     * Comparable[] of its initial capacity cast to type parameter E. By changing baseArray's reference from the original
     * array to a new one, Java's built-in "garbage collector" removes the original from memory.
     */
    @Override
    public void clear() {
        baseArray = (E[]) new Comparable[initialCapacity];

        capacity = initialCapacity;
        size = 0;
    }

    /**
     * size() returns the baseArray's size, stored in its own class member "size".
     *
     * @return                                              int representing baseArray's size
     */
    @Override
    public int size() {
        return size;
    }

    /**
     * capacity() returns the baseArray's capacity, stored in its own class member "capacity".
     *
     * @return                                              int representing baseArray's capacity
     */
    public int capacity() { return capacity; }

    /**
     * isEmpty() returns a boolean dependent on whether or not class member "size" equals 0.
     *
     * @return                                              boolean specifying if baseArray is empty
     */
    @Override
    public boolean isEmpty() {
        if (size == 0) {
            return true;
        }
        return false;
    }

    /**
     * isEmpty() returns a boolean dependent on whether or not class member "size" equals class member "capacity".
     *
     * @return                                              boolean specifying if baseArray is full
     */
    public boolean isFull() {
        if (size == capacity) {
            return true;
        }
        return false;
    }

    /**
     * add() takes in type parameter E and decides, according to its relationship with baseArray's contents, where to
     * place it. Before doing so, the method checks if baseArray needs expansion with isFull() and if there any contents
     * in baseArray with isEmpty(). Afterwards, a for loop iterates from baseArray's last index to its first checking if
     * it is greater than selected element. If so, input E is placed in an index after the selected and breaks the loop.
     * If not, the selected is copied into the index after itself and the for loop continues. If input E reaches iteration
     * i == 0 with no allocation, it is the smallest element and placed at baseArray's index 0. Size is incremented.
     *
     * @param item                                          input E to be added into SAL
     * @return                                              boolean specifying if execution is successful
     */
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

                // final index reached with no allocation, E placed into index 0
                if (i == 0) {
                    baseArray[i] = item;
                }
            }
        }
        size++;

        return true;
    }

    /**
     * expandArray() takes in an array of E (typically own baseArray) and initializes baseArray with Comparable[] of
     * double capacity cast to E. Related class members changed accordingly and the original baseArray's contents
     * (stored in inputArray) are copied into the new array referenced by baseArray.
     *
     * @param inputArray                                    array of E being expanded
     */
    public void expandArray(E[] inputArray) {
        baseArray = (E[]) new Comparable[capacity*2];
        capacity = capacity*2;

        System.arraycopy(inputArray, 0, baseArray,
                0, size);
    }

    /**
     * expandArray() takes in an array of E (typically own baseArray) and initializes baseArray with Comparable[] of
     * half capacity cast to E. Related class members changed accordingly and the original baseArray's contents
     * (stored in inputArray) are copied into the new array referenced by baseArray.
     *
     * @param inputArray                                    array of E being shrunk
     */
    public void shrinkArray(E[] inputArray) {
        baseArray = (E[]) new Comparable[capacity/2];
        capacity = capacity/2;

        System.arraycopy(inputArray, 0, baseArray,
                0, size);
    }

    /**
     * get() takes in an int representing a desired element's index in baseArray and returns element E found at that
     * index.
     *
     * @param index                                         int representing desired E
     * @return                                              E retrieved from specified index
     */
    @Override
    public E get(int index) {
        return baseArray[index];
    }

    /**
     * remove() takes in an int representing index of element E to be removed and after execution, returns it. Execution
     * includes a for loop iterating from the specified index to its end index, initializing each element with the element
     * held after it. By starting iteration at the index of E to be removed, this deletes it from the baseArray while
     * also properly shifting all latter elements. When the last index is reached, this is initialized with null and size
     * is decremented. If size is less than half of capacity and the capacity is greater than its initial, shrinkArray()
     * is called.
     *
     * @param index                                         int representing index of desired E to be removed
     * @return                                              E removed from baseArray
     */
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
