void main() throws IOException {
    Scanner s = new Scanner(System.in);
    System.out.println("Enter relative or full path (enter nothing for employeesWithoutRepeat.txt)");
    final var in = s.nextLine();
    final var path = !Objects.equals(in, "") ? in : "employeesWithoutRepeat.txt";

    var file = new File(path);

    System.out.println("Attempting to load employees from file: " + file);
    final var unsortedEmployees = Employee.loadFromFile(file);

//    System.out.println("Hourly rate and salary after deductions for first 5 employees");
//    for (int i = 0; i < 5; i++) {
//        var emp = unsortedEmployees.get(i);
//        System.out.printf("ID: %d\nName: %s%n", emp.getID(), emp.getName());
//        System.out.println("Before deductions: " + emp.getHourlyRate());
//        System.out.println("Salary after deductions: " + emp.calcHourlySalary());
//        System.out.println();
//    }

    var startTime = System.currentTimeMillis();
    final Comparator<Employee> byHourlyRate = Comparator.comparing(Employee::getHourlyRate);
    final var qSortEmployees = Utility.quickSort(unsortedEmployees, byHourlyRate);
    var endTime = System.currentTimeMillis();
    System.out.println("Quicksort took " + (endTime - startTime) + " ms");
    System.out.println("Saving to file 'sortedByRate.txt");
    Employee.saveToFile(qSortEmployees, "sortedByRate.txt");
    System.out.println("Printing first 5 employees sorted by hourlyRate using Utility.quickSort()");
    for (int i = 0; i < 5; i++) {
        var emp = qSortEmployees.get(i);
        System.out.println(emp);
    }
    System.out.println();
    startTime = System.currentTimeMillis();
    final Comparator<Employee> byName = Comparator.comparing(Employee::getName);
    final var selectionSortEmployees = Utility.selectionSort(unsortedEmployees, byName);
    endTime = System.currentTimeMillis();
    System.out.println("Selection sort took " + (endTime - startTime) + " ms");
    System.out.println("Saving to file 'sortedByName.txt");
    Employee.saveToFile(selectionSortEmployees, "sortedByName.txt");
    System.out.println("Printing first 5 employees sorted by name using Utility.selectionSort()");
    for (int i = 0; i < 5; i++) {
        var emp = selectionSortEmployees.get(i);
        System.out.println(emp);
    }
    System.out.println("Enter a name to search");

    final var searchName = s.nextLine();
    final var target = Utility.binarySearch(selectionSortEmployees, searchName, 0, selectionSortEmployees.size()-1);
    if (target != -1) {
        System.out.println("Target " + searchName + " was found at index " + target);
        System.out.println(selectionSortEmployees.get(target));
    } else {
        System.out.println("Target " + searchName + " was not found in the data");
    }
}
