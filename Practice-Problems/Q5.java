class FeeAccount {

    private String regNo;
    private double totalFee;
    private double amountPaid;

    FeeAccount(String regNo, double totalFee) {
        this.regNo = regNo;
        this.totalFee = totalFee;
        this.amountPaid = 0;
    }

    // Accepts only positive payments
    void pay(double amount) {

        if (amount <= 0) {
            System.out.println(
                "Payment rejected for " + regNo +
                ": amount must be positive."
            );
            return;
        }

        amountPaid += amount;

        // Payment cannot exceed the total fee
        if (amountPaid > totalFee) {
            amountPaid = totalFee;
        }
    }

    double getDue() {
        return totalFee - amountPaid;
    }
}


// Hostel fee account inherits from FeeAccount
class HostelFeeAccount extends FeeAccount {

    HostelFeeAccount(String regNo, double totalFee) {
        super(regNo, totalFee);
    }

    // Splits the given payment into two equal payments
    void payInTwoInstallments(double amount) {

        if (amount <= 0) {
            System.out.println(
                "Payment rejected: amount must be positive."
            );
            return;
        }

        pay(amount / 2);
        pay(amount / 2);
    }
}


// Represents a hostel room
class HostelRoom {

    String roomNo;
    int beds;
    int occupied;

    HostelRoom(String roomNo, int beds, int occupied) {
        this.roomNo = roomNo;
        this.beds = beds;
        this.occupied = occupied;
    }

    // Allots a bed if one is available
    void allot(String name) {

        if (occupied < beds) {
            occupied++;

            System.out.println(
                name + " allotted to room " + roomNo
            );
        }
    }
}


// Represents one complete student record
class SrmStudent {

    String name;
    String regNo;

    // An object as a field of another object
    HostelFeeAccount feeAccount;
    HostelRoom room;

    // Shared by all SrmStudent objects
    static int totalStudents = 0;

    SrmStudent(
        String name,
        String regNo,
        HostelFeeAccount feeAccount
    ) {

        this.name = name;
        this.regNo = regNo;
        this.feeAccount = feeAccount;

        // Initially no room is assigned
        this.room = null;

        totalStudents++;
    }


    // Prints the student's complete status
    String fullStatus() {

        String roomNumber;

        if (room == null) {
            roomNumber = "unallotted";
        } else {
            roomNumber = room.roomNo;
        }

        return name
            + " | Due: Rs "
            + feeAccount.getDue()
            + " | Room: "
            + roomNumber;
    }
}


public class Q5 {

    // Finds the first available room
    static HostelRoom findAvailableRoom(HostelRoom[] rooms) {

        for (HostelRoom room : rooms) {

            if (room != null && room.occupied < room.beds) {
                return room;
            }
        }

        return null;
    }


    // Safely allots a room to a student
    static HostelRoom safeAllot(
        HostelRoom[] rooms,
        String studentName
    ) {

        HostelRoom availableRoom =
            findAvailableRoom(rooms);

        if (availableRoom != null) {

            availableRoom.allot(studentName);

            return availableRoom;

        } else {

            System.out.println(
                "No rooms available for " + studentName
            );

            return null;
        }
    }


    public static void main(String[] args) {

        // ---------------------------------------------
        // HOSTEL ROOMS
        // ---------------------------------------------

        HostelRoom[] rooms = {

            new HostelRoom("C-214", 3, 2),

            new HostelRoom("C-507", 2, 1)
        };


        // ---------------------------------------------
        // THREE STUDENTS
        // ---------------------------------------------

        SrmStudent ravi =
            new SrmStudent(
                "Ravi",
                "RA231100301011",
                new HostelFeeAccount(
                    "RA231100301011",
                    200000
                )
            );


        SrmStudent anitha =
            new SrmStudent(
                "Anitha",
                "RA231100301012",
                new HostelFeeAccount(
                    "RA231100301012",
                    180000
                )
            );


        SrmStudent karthik =
            new SrmStudent(
                "Karthik",
                "RA231100301013",
                new HostelFeeAccount(
                    "RA231100301013",
                    200000
                )
            );


        // ---------------------------------------------
        // ROOM ALLOTMENT
        // ---------------------------------------------

        // Allot rooms only to Ravi and Anitha
        ravi.room =
            safeAllot(rooms, ravi.name);

        anitha.room =
            safeAllot(rooms, anitha.name);

        // Karthik intentionally remains unallotted


        // ---------------------------------------------
        // PAYMENTS
        // ---------------------------------------------

        // Valid payment of Rs 60,000 for Ravi
        ravi.feeAccount.pay(60000);

        // Valid payment of Rs 0 is rejected
        anitha.feeAccount.pay(0);

        // Negative payment is rejected
        karthik.feeAccount.pay(-5000);


        // ---------------------------------------------
        // FINAL STATUS
        // ---------------------------------------------

        System.out.println();
        System.out.println(ravi.fullStatus());
        System.out.println(anitha.fullStatus());
        System.out.println(karthik.fullStatus());

        System.out.println(
            "Total students: " + SrmStudent.totalStudents
        );
    }
}