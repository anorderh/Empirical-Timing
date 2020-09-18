/**
 * (CS-310 / Program 1 - Empirical Timing) ArrayDriver Class
 * Class declaring and initializing several SortedArrayList objects for testing time complexity of internal functions,
 * depending on input size and order.
 * @author Anthony Norderhaug
 * @date 9/17/2020
 */

public class ArrayDriver {
    static Integer currInteger;
    static long prevTime;

    /**
     * main() includes several for loops for testing each internal function's time complexity a specific number of
     * iterations. Adjoined with each collection of results is a printed String identifying which function is being tested.
     *
     * @param args                                        N/A
     */
    public static void main(String[] args) {
        // base variables for adjusting SAL's input capacity and recording elapsed time
        int testSize;
        int startTestSize = 100;  // normal testSize value, some functions have more or less than this value
        long startTime;
        long endTime;

        // declaration of SALs associated with each test
        SortedArrayList<Integer> addCollection;
        SortedArrayList<Integer> addReverseCollection;
        SortedArrayList<Integer> removeCollection;
        SortedArrayList<Integer> clearCollection;


        System.out.println("\nTesting Add in order ...");
        // starting size of 80000 and 7 iterations to depict linear nature of "Add in Order"
        testSize = 80000;

        for (int i = 1; i <= 7; i++) {
            addCollection = new SortedArrayList<>(testSize);
            currInteger = 0; // Integer used for filling up associated SAL, will be constantly incremented

            startTime = System.currentTimeMillis(); // recording time before function's test
            testAdd(addCollection, false); // boolean "false" passed since data added is in order
            endTime = System.currentTimeMillis(); // recording time after function's test

            // Output statement printing result of printMessage() and doubling testSize for next iteration
            System.out.println(printMessage(testSize, startTime, endTime, i));
            testSize *= 2;
        }


        System.out.println("\nTesting Add in reverse...");
        // base starting size and 5 iterations to depict quadratic nature of "Add in Reverse Order"
        testSize = startTestSize;

        for (int i = 1; i <= 5; i++) {
            addReverseCollection = new SortedArrayList<>(testSize);
            currInteger = testSize - 1; // Integer used for filling up associated SAL, will be constantly decremented

            startTime = System.currentTimeMillis(); // recording time before function's test
            testAdd(addReverseCollection, true); // boolean "true" passed since data added is in reverse order
            endTime = System.currentTimeMillis(); // recording time after function's test

            // Output statement printing result of printMessage() and doubling testSize for next iteration
            System.out.println(printMessage(testSize, startTime, endTime, i));
            testSize *= 2;
        }


        System.out.println("\nTesting Remove...");
        // starting size of 1000 and 8 iterations to depict quadratic nature of "Remove"
        testSize = 1000;

        for (int i = 1; i <= 8; i++) {
            removeCollection = new SortedArrayList<>(testSize);
            currInteger = 0; // Integer used for filling up associated SAL, will be constantly incremented

            testAdd(removeCollection, false); // populating associated SAL with data in order

            startTime = System.currentTimeMillis(); // recording time before function's test
            testRemove(removeCollection);
            endTime = System.currentTimeMillis(); // recording time after function's test

            // Output statement printing result of printMessage() and doubling testSize for next iteration
            System.out.println(printMessage(testSize, startTime, endTime, i));
            testSize *= 2;
        }


        System.out.println("\nTesting Clear..");
        // starting size of 10000 and 5 iterations to depict constant nature of "Clear"
        testSize = 10000;

        for (int i = 1; i <= 5; i++) {
            clearCollection = new SortedArrayList<>(testSize);
            currInteger = 0; // Integer used for filling up associated SAL, will be constantly incremented

            testAdd(clearCollection, false); // populating associated SAL with data in order

            startTime = System.currentTimeMillis(); // recording time before function's test
            testClear(clearCollection);
            endTime = System.currentTimeMillis(); // recording time after function's test

            // Output statement printing result of printMessage() and doubling testSize for next iteration
            System.out.println(printMessage(testSize, startTime, endTime, i));
            testSize *= 2;
        }


    }

    /**
     * testAdd() takes in a SAL and boolean and constantly adds class element currInteger to the collection until
     * it's full. Additionally, the boolean is used specify whether or not the input data is to be in reverse (decreasing)
     * order, subsequently deciding whether currInteger is incremented or decremented each iteration.
     *
     * @param collection                                SortedArrayList being populated with currInteger
     * @param reverse                                   boolean specifying whether input is in reverse order
     */
    public static void testAdd(SortedArrayList collection, boolean reverse) {
        do {
            collection.add(currInteger);

            if (reverse) {
                currInteger--;
            } else {
                currInteger++;
            }
        } while (!(collection.isFull()));
    }

    /**
     * testRemove() takes in a SAL and constantly removes elements from its starting index until the collection is empty.
     *
     * @param collection                                SortedArrayList having elements removed at index 0
     */
    public static void testRemove(SortedArrayList collection) {
        do {
            collection.remove(0);
        } while (!(collection.isEmpty()));
    }

    /**
     * testClear() takes in a SAL and calls its associated clear() function.
     *
     * @param collection                                SortedArrayList having clear() function called
     */
    public static void testClear(SortedArrayList collection) {
        collection.clear();
    }

    /**
     * printMessage() takes in an int representing testSize, two longs representing start and ending times, and a second
     * int representing the current iteration. With the first 3 values, a String message is constructed specifying
     * the related collection's test size and the time elapsed executing the related function. If the function's test is
     * not on its 1st iteration, a growth factor of the time elapsed from the previous iteration is added on to help
     * identify complexity. Lastly, class element prevTime is initialized after String construction to keep record of
     * this iteration's time and the String message is returned.
     *
     * @param testSize                                  int representing collection's current size in function's test
     * @param start                                     long representing time right before function's test
     * @param end                                       long representing time right after function's test
     * @param iteration                                 int representing current iteration of function's test
     * @return                                          String specifying size, time, and if applicable, growth
     */
    public static String printMessage(int testSize, long start, long end, int iteration) {
        long elapsed = end - start;
        String message = "Test Size: " + testSize + " / Elapsed time: " + elapsed;

        if (iteration > 1) {
            message += " / Growth factor: " + (String.format("%.2f", (double)elapsed/prevTime));
        }
        prevTime = elapsed;

        return message;
    }

}
