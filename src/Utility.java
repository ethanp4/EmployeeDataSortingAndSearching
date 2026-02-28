import java.util.ArrayList;
import java.util.Comparator;

public class Utility {
    //make the constructor private so that Utility cant be instantiated
    private Utility() {}

    //compare using a custom comparator
    public static <T> ArrayList<T> quickSort(ArrayList<T> input, Comparator<T> comparator) {
        if (input == null || input.size() <= 1) return input;
        quickSort(input, 0, input.size() - 1, comparator);
        return input;
    }

    private static <T> void quickSort(ArrayList<T> list, int low, int high, Comparator<T> comparator) {
        if (low < high) {
            int pivot = partition(list, low, high, comparator);
            quickSort(list, low, pivot - 1, comparator);
            quickSort(list, pivot + 1, high, comparator);
        }
    }

    // pivot = list[low]
    // left = low+1, right = high
    // move left while <= pivot, move right while > pivot
    // swap left/right until crossed
    // swap low/right at end
    private static <T> int partition(ArrayList<T> list, int low, int high, Comparator<T> comparator) {
        // Choose middle element as pivot
        int mid = low + (high - low) / 2;
        swap(list, low, mid); // move pivot to low

        T pivotValue = list.get(low);
        int left = low + 1;
        int right = high;

        while (true) {
            while (left <= right && comparator.compare(list.get(left), pivotValue) <= 0) {
                left++;
            }

            while (left <= right && comparator.compare(list.get(right), pivotValue) > 0) {
                right--;
            }

            if (left > right) {
                break;
            }

            swap(list, left, right);
            left++;
            right--;
        }

        swap(list, low, right);
        return right;
    }

    private static <T> void swap(ArrayList<T> list, int i, int j) {
        if (i == j) return;
        T temp = list.get(i);
        list.set(i, list.get(j));
        list.set(j, temp);
    }

    //compare using the default Employee.compareTo
    // we declare a generic type 'T' that extends Comparable, ensuring compareTo exists for the compared types.
    public static <T> ArrayList<T> selectionSort(ArrayList<T> input, Comparator<T> comp) {
        int length = input.size();
        //outer loop
        for (int i = 0; i < length - 1; i++) {
            int indexOfMin = i;
            for (int j = i + 1; j < length; j++) {
                if (comp.compare(input.get(j), input.get(indexOfMin)) < 0) {
                    indexOfMin = j;
                }
            }
            //Swap elements
            T temp = input.get(i);
            input.set(i, input.get(indexOfMin));
            input.set(indexOfMin, temp);
        }
        return input;
    }
}
