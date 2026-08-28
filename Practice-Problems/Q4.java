// Broken version:
// name, regNo and attendance are incorrectly declared static.
// This means these variables belong to the class, not to each student.
// So when a second student is created, the first student's data gets overwritten.

class BrokenSrmStudent {

    static String name;
    static String regNo;
    static int attendance;

    BrokenSrmStudent(String name, String regNo, int attendance) {
        BrokenSrmStudent.name = name;
        BrokenSrmStudent.regNo = regNo;
        BrokenSrmStudent.attendance = attendance;
    }
}


// Corrected version
class SrmStudent {

    // These belong to each individual student.
    String name;
    String regNo;
    int attendance;

    // These belong to the whole university/system.
    static String university = "SRM Institute of Science and Technology";
    static int admissionCount = 0;

    // Constructor automatically generates the registration number
    // using the shared admissionCount.
    SrmStudent(String name, int attendance) {

        this.name = name;
        this.attendance = attendance;

        admissionCount++;

        this.regNo = "RA2511026" + String.format("%03d", admissionCount);
    }

    // Instance method because it prints information
    // belonging to one particular student.
    void printIdCard() {

        System.out.println("Name: " + name);
        System.out.println("Reg No: " + regNo);
        System.out.println("Attendance: " + attendance + "%");
        System.out.println("University: " + university);
        System.out.println();
    }

    // Static method because admissionCount belongs
    // to the whole class, not to one student.
    static void printTotalAdmissions() {

        System.out.println(
            "Total Admissions: " + admissionCount
        );
    }
}


public class Q4 {

    public static void main(String[] args) {

        // ------------------------------------------------
        // BROKEN VERSION
        // ------------------------------------------------

        System.out.println("Broken version:");

        BrokenSrmStudent student1 =
            new BrokenSrmStudent(
                "Ravi",
                "RA2511026001",
                82
            );

        BrokenSrmStudent student2 =
            new BrokenSrmStudent(
                "Meera",
                "RA2511026002",
                74
            );

        // Because all three fields are static,
        // creating student2 overwrites student1's data.
        System.out.println(
            "Student 1 name: " + student1.name
        );

        System.out.println(
            "Student 2 name: " + student2.name
        );

        System.out.println(
            "(Ravi's data was overwritten because name is static.)"
        );


        // ------------------------------------------------
        // CORRECTED VERSION
        // ------------------------------------------------

        System.out.println("\nCorrected version:");

        SrmStudent student3 =
            new SrmStudent(
                "Ravi",
                82
            );

        SrmStudent student4 =
            new SrmStudent(
                "Meera",
                74
            );

        // Each object now maintains its own data.
        student3.printIdCard();
        student4.printIdCard();

        // admissionCount is shared by the whole class,
        // so this is called using the class name.
        SrmStudent.printTotalAdmissions();
    }
}