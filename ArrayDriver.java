

public class ArrayDriver {
    static Integer currInteger;


    public static void main(String[] args) {
        int startTestSize = 100;
        int testSize;
        long startTime;
        long endTime;
        SortedArrayList<Integer> addCollection;
        SortedArrayList<Integer> addReverseCollection;
        SortedArrayList<Integer> removeCollection;
        SortedArrayList<Integer> clearCollection;


        System.out.println("Testing Add in order ...");
        testSize = 80000;

        for (int i = 1; i <= 7; i++) {
            addCollection = new SortedArrayList<>(testSize);
            currInteger = 0;

            startTime = System.currentTimeMillis();
            testAdd(addCollection, false);
            endTime = System.currentTimeMillis();

            System.out.println(printMessage(testSize, startTime, endTime));
            testSize *= 2;
        }

        System.out.println("Testing Add in reverse...");
        testSize = startTestSize;

        for (int i = 1; i <= 5; i++) {
            addReverseCollection = new SortedArrayList<>(testSize);
            currInteger = testSize - 1;

            startTime = System.currentTimeMillis();
            testAdd(addReverseCollection, true);
            endTime = System.currentTimeMillis();

            System.out.println(printMessage(testSize, startTime, endTime));
            testSize *= 2;
        }

        System.out.println("Testing Remove...");
        testSize = 1000;

        for (int i = 1; i <= 8; i++) {
            removeCollection = new SortedArrayList<>(testSize);
            currInteger = 0;

            testAdd(removeCollection, false);

            startTime = System.currentTimeMillis();
            testRemove(removeCollection);
            endTime = System.currentTimeMillis();

            System.out.println(printMessage(testSize, startTime, endTime));
            testSize *= 2;
        }

        System.out.println("Testing Clear..");
        testSize = 10000;

        for (int i = 1; i <= 5; i++) {
            clearCollection = new SortedArrayList<>(testSize);
            currInteger = 0;

            testAdd(clearCollection, false);

            startTime = System.currentTimeMillis();
            testClear(clearCollection);
            endTime = System.currentTimeMillis();

            System.out.println(printMessage(testSize, startTime, endTime));
            testSize *= 2;
        }

    }

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

    public static void testRemove(SortedArrayList collection) {
        do {
            collection.remove(0);
        } while (!(collection.isEmpty()));
    }

    public static String printMessage(int testSize, long start, long end) {
        long elapsed = end - start;

        return "Test Size: " + testSize + " / Elapsed time: " + elapsed;
    }

    public static void testClear(SortedArrayList collection) {
        collection.clear();
    }


}
