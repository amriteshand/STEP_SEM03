class SrmStudent {
    String name;
    String regNo;
    int attendance;

    // Constructor
    SrmStudent(String name, String regNo, int attendance) {
        this.name = name;
        this.regNo = regNo;
        this.attendance = attendance;
    }

    // Updates the student's attendance after re-check
    void addAttendanceUpdate(int newAttendance) {
        this.attendance = newAttendance;
    }

    // Instance method because eligibility depends on THIS student's attendance
    boolean isEligible() {
        return attendance >= 75;
    }

    /*
     * static because classAverage calculates a value for the whole
     * array of students, not for one particular student.
     */
    static double classAverage(SrmStudent[] students) {
        int total = 0;

        for (SrmStudent student : students) {
            total += student.attendance;
        }

        return (double) total / students.length;
    }
}

public class F1_AttendanceSystem {
    public static void main(String[] args) {

        SrmStudent[] students = {
            new SrmStudent("Ravi", "RA231100301001", 82),
            new SrmStudent("Anitha", "RA231100301002", 68),
            new SrmStudent("Karthik", "RA231100301003", 91),
            new SrmStudent("Meera", "RA231100301004", 74),
            new SrmStudent("Suresh", "RA231100301005", 60)
        };

        for (SrmStudent student : students) {
            String status = student.isEligible()
                    ? "Eligible"
                    : "Detained";

            System.out.println(
                student.name + " - " +
                student.attendance + "% - " +
                status
            );
        }

        double average = SrmStudent.classAverage(students);

        System.out.println("Class average: " + average + "%");
    }
}