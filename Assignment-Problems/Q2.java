class Employee {

    private String empId;
    private String empName;
    private double salary;

    // Constructor
    Employee(String empId, String empName, double salary) {
        this.empId = empId;
        this.empName = empName;
        this.salary = salary;
    }

    // Returns the employee's basic salary
    double getSalary() {
        return salary;
    }
}


// ManagerEmployee extends Employee
class ManagerEmployee extends Employee {

    private double teamBonus;

    ManagerEmployee(
            String empId,
            String empName,
            double salary,
            double teamBonus) {

        super(empId, empName, salary);
        this.teamBonus = teamBonus;
    }

    // Manager gets basic salary + team bonus
    double effectiveSalary() {
        return getSalary() + teamBonus;
    }
}


// InternEmployee extends Employee
class InternEmployee extends Employee {

    private double stipendCap;

    InternEmployee(
            String empId,
            String empName,
            double salary,
            double stipendCap) {

        super(empId, empName, salary);
        this.stipendCap = stipendCap;
    }

    // Intern receives whichever is smaller:
    // basic salary or stipend cap
    double effectiveSalary() {

        if (getSalary() < stipendCap) {
            return getSalary();
        }

        return stipendCap;
    }
}


public class Q2 {

    public static void main(String[] args) {

        // Create one object of each employee type

        Employee plainEmployee =
            new Employee(
                "EMP101",
                "Ravi",
                40000
            );


        ManagerEmployee manager =
            new ManagerEmployee(
                "EMP102",
                "Anitha",
                70000,
                8000
            );


        InternEmployee intern =
            new InternEmployee(
                "EMP103",
                "Karthik",
                12000,
                10000
            );


        // Store all three using parent-class references
        Employee[] employees = {
            plainEmployee,
            manager,
            intern
        };


        // Use instanceof to identify the actual object type
        for (Employee employee : employees) {

            if (employee instanceof ManagerEmployee) {

                ManagerEmployee managerEmployee =
                    (ManagerEmployee) employee;

                System.out.println(
                    "Manager effective pay: Rs "
                    + managerEmployee.effectiveSalary()
                );

            } else if (employee instanceof InternEmployee) {

                InternEmployee internEmployee =
                    (InternEmployee) employee;

                System.out.println(
                    "Intern effective pay: Rs "
                    + internEmployee.effectiveSalary()
                );

            } else {

                System.out.println(
                    "Plain employee pay: Rs "
                    + employee.getSalary()
                );
            }
        }
    }
}