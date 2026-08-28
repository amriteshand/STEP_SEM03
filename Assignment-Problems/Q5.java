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

    // Returns the basic salary of the employee
    double getSalary() {
        return salary;
    }
}


// ManagerEmployee inherits from Employee
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

    // Manager's effective pay = basic salary + team bonus
    double effectiveSalary() {
        return getSalary() + teamBonus;
    }
}


// Represents one parking slot
class ParkingSlot {

    String slotNo;
    int capacity;
    int occupiedCount;

    ParkingSlot(String slotNo, int capacity, int occupiedCount) {
        this.slotNo = slotNo;
        this.capacity = capacity;
        this.occupiedCount = occupiedCount;
    }

    // Allots one parking spot if space is available
    void allot(String vehicleNo) {

        if (occupiedCount < capacity) {
            occupiedCount++;

            System.out.println(
                vehicleNo + " allotted to slot " + slotNo
            );
        }
    }
}


// Combines employee information and parking assignment
class CompanyEmployeeRecord {

    String name;
    String empId;

    // An Employee object is itself a field
    // of CompanyEmployeeRecord.
    Employee employee;

    // A ParkingSlot object is also a field.
    ParkingSlot slot;

    // Shared counter for all employee records
    static int totalRecords = 0;


    CompanyEmployeeRecord(
            String name,
            String empId,
            Employee employee) {

        this.name = name;
        this.empId = empId;
        this.employee = employee;

        // Initially no parking is assigned
        this.slot = null;

        totalRecords++;
    }


    // Returns the complete profile of this employee
    String fullProfile() {

        String slotNumber;

        if (slot == null) {
            slotNumber = "no parking assigned";
        } else {
            slotNumber = slot.slotNo;
        }


        double effectivePay;

        // ManagerEmployee has an additional team bonus,
        // so use its effectiveSalary() method.
        if (employee instanceof ManagerEmployee) {

            ManagerEmployee manager =
                (ManagerEmployee) employee;

            effectivePay = manager.effectiveSalary();

        } else {

            effectivePay = employee.getSalary();
        }


        return name
            + " | Pay: Rs "
            + effectivePay
            + " | Slot: "
            + slotNumber;
    }
}


public class Q5 {

    // Finds the first parking slot having free capacity
    static ParkingSlot findAvailableSlot(ParkingSlot[] slots) {

        for (ParkingSlot slot : slots) {

            if (slot != null
                    && slot.occupiedCount < slot.capacity) {

                return slot;
            }
        }

        return null;
    }


    // Safely assigns a parking slot
    static ParkingSlot safeAllot(
            ParkingSlot[] slots,
            String vehicleNo) {

        ParkingSlot availableSlot =
            findAvailableSlot(slots);

        if (availableSlot != null) {

            availableSlot.allot(vehicleNo);

            return availableSlot;

        } else {

            System.out.println(
                "No slots available for " + vehicleNo
            );

            return null;
        }
    }


    public static void main(String[] args) {

        // ---------------------------------------------
        // PARKING SLOTS
        // ---------------------------------------------

        ParkingSlot[] slots = {

            new ParkingSlot("A1", 4, 3),

            new ParkingSlot("A2", 5, 4)
        };


        // ---------------------------------------------
        // THREE EMPLOYEE RECORDS
        // ---------------------------------------------

        CompanyEmployeeRecord divya =
            new CompanyEmployeeRecord(
                "Divya",
                "EMP101",
                new ManagerEmployee(
                    "EMP101",
                    "Divya",
                    70000,
                    8000
                )
            );


        CompanyEmployeeRecord karan =
            new CompanyEmployeeRecord(
                "Karan",
                "EMP102",
                new Employee(
                    "EMP102",
                    "Karan",
                    40000
                )
            );


        CompanyEmployeeRecord meera =
            new CompanyEmployeeRecord(
                "Meera",
                "EMP103",
                new Employee(
                    "EMP103",
                    "Meera",
                    10000
                )
            );


        // ---------------------------------------------
        // PARKING ALLOCATION
        // ---------------------------------------------

        // Allot parking only to first two employees
        divya.slot =
            safeAllot(slots, "TN09AB1001");

        karan.slot =
            safeAllot(slots, "TN09AB1002");

        // Meera intentionally remains unallotted
        // so that her slot reference stays null.


        // ---------------------------------------------
        // PRINT COMPLETE PROFILES
        // ---------------------------------------------

        System.out.println();
        System.out.println(divya.fullProfile());
        System.out.println(karan.fullProfile());
        System.out.println(meera.fullProfile());


        // ---------------------------------------------
        // TOTAL RECORDS
        // ---------------------------------------------

        System.out.println(
            "Total records: "
            + CompanyEmployeeRecord.totalRecords
        );
    }
}