void main() throws IOException {
    Scanner s = new Scanner(System.in);
    System.out.println("Enter relative or full path (enter nothing for employeesWithoutRepeat.txt)");
    final var in = s.nextLine();
    final var path = !Objects.equals(in, "") ? in : "employeesWithoutRepeat.txt";

    var file = new File(path);

    System.out.println("Attempting to load employees from file: " + file);
    final var unsortedEmployees = Employee.loadFromFile(file);

    System.out.println("Hourly rate and salary after deductions for first 5 employees");
    for (int i = 0; i < 5; i++) {
        var emp = unsortedEmployees.get(i);
        System.out.printf("ID: %d\nName: %s%n", emp.getID(), emp.getName());
        System.out.println("Before deductions: " + emp.getHourlyRate());
        System.out.println("Salary after deductions: " + emp.calcHourlySalary());
        System.out.println();
    }

    final Comparator<Employee> byHourlyRate = Comparator.comparing(Employee::getHourlyRate);
    final var qSortEmployees = Utility.quickSort(unsortedEmployees, byHourlyRate);
    System.out.println("Printing first 5 employees sorted by Utility.quickSort()");
    for (int i = 0; i < 5; i++) {
        var emp = qSortEmployees.get(i);
        System.out.println(emp);
        System.out.println();
    }

    final var selectionSortEmployees = Utility.selectionSort(unsortedEmployees);
    System.out.println("Printing first 5 employees sorted by Utility.selectionSort()");
    for (int i = 0; i < 5; i++) {
        var emp = selectionSortEmployees.get(i);
        System.out.println(emp);
        System.out.println();
    }


}
