import java.util.ArrayList;
import java.util.Comparator;

public class Utility {
    //make the constructor private so that Utility cant be instantiated
    private Utility() {}

    //compare using a custom comparator
    public static <T> ArrayList<T> quickSort(ArrayList<T> input, Comparator<T> comparator) {

        return input;
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
