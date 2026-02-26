import java.io.*;
import java.util.ArrayList;

public class Employee implements Comparable<Employee> {
    private int ID;
    private String name;
    private double hoursWorked;
    private double hourlyRate;
    private double deductionProvince;
    private double deductionFederal;
    private double educationAllowance;

    public Employee(int ID, String name, double hoursWorked, double hourlyRate, double deductionProvince, double deductionFederal, double educationAllowance) {
        this.ID = ID;
        this.name = name;
        this.hoursWorked = hoursWorked;
        this.hourlyRate = hourlyRate;
        this.deductionProvince = deductionProvince;
        this.deductionFederal = deductionFederal;
        this.educationAllowance = educationAllowance;
    }

    //static function to load a list of employees from a file and return an array
    public static ArrayList<Employee> loadFromFile(File file) throws IOException {
        var ret = new ArrayList<Employee>();
        try(var br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] val = line.split(",");
                var id = Integer.parseInt(val[0]);
                var newEmployee = employeeFromString(val, id);
                ret.add(newEmployee);
            }
        }
        return ret;
    }

    //turn csv row into employee object (used while loading from file)
    private static Employee employeeFromString(String[] val, int id) {
        var name = val[1];
        var hoursWorked = Double.parseDouble(val[2]);
        var hourlyRate = Double.parseDouble(val[3]);
        var deductionProvince = Double.parseDouble(val[4]);
        var deductionFederal = Double.parseDouble(val[5]);
        var educationAllowance = Double.parseDouble(val[6]);

        return new Employee(id, name, hoursWorked, hourlyRate, deductionProvince, deductionFederal, educationAllowance);
    }

    //calculate what the employee is being paid per hour minus the deductions
    //the deductions seem to be from the grossPay so we calculate that first then continue
    public double calcHourlySalary() {
        double grossPay = hoursWorked * hourlyRate;
        double grossPayAfterDeductions = grossPay - deductionProvince - deductionFederal + educationAllowance;
        return grossPayAfterDeductions / hoursWorked;
    }

    //tostring override
    @Override
    public String toString() {
        return String.format("ID: %d\nName: %s\nHours worked: %f\nHourly rate: %f\nDeduction province: %f\nDeduction federal: %f\nEducation allowance: %f",
                getID(), getName(), getHoursWorked(), getHourlyRate(), getDeductionProvince(), getDeductionFederal(), getEducationAllowance());
    }

    @Override
    public int compareTo(Employee other) {
        return this.name.compareTo(other.getName());
    }

    //getters and setters below here
    public double getHoursWorked() {
        return hoursWorked;
    }

    public void setHoursWorked(int hoursWorked) {
        this.hoursWorked = hoursWorked;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getHourlyRate() {
        return hourlyRate;
    }

    public void setHourlyRate(double hourlyRate) {
        this.hourlyRate = hourlyRate;
    }

    public double getDeductionProvince() {
        return deductionProvince;
    }

    public void setDeductionProvince(double deductionProvince) {
        this.deductionProvince = deductionProvince;
    }

    public double getDeductionFederal() {
        return deductionFederal;
    }

    public void setDeductionFederal(double deductionFederal) {
        this.deductionFederal = deductionFederal;
    }

    public double getEducationAllowance() {
        return educationAllowance;
    }

    public void setEducationAllowance(double educationAllowance) {
        this.educationAllowance = educationAllowance;
    }


}
