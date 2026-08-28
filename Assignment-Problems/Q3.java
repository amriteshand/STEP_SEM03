class ParkingSlot {

    String slotNo;
    int capacity;
    int occupiedCount;

    // Constructor
    ParkingSlot(
            String slotNo,
            int capacity,
            int occupiedCount) {

        this.slotNo = slotNo;
        this.capacity = capacity;
        this.occupiedCount = occupiedCount;
    }


    // Allots one vehicle if a spot is available
    void allot(String vehicleNo) {

        if (occupiedCount < capacity) {

            occupiedCount++;

            System.out.println(
                vehicleNo
                + " allotted to slot "
                + slotNo
            );
        }
    }
}


public class Q3 {

    // Returns the first parking slot having
    // at least one free spot.
    // Returns null if every slot is full.
    static ParkingSlot findAvailableSlot(
            ParkingSlot[] slots) {

        for (ParkingSlot slot : slots) {

            if (slot != null
                    && slot.occupiedCount < slot.capacity) {

                return slot;
            }
        }

        return null;
    }


    /*
     * The ParkingSlot[] contains references to ParkingSlot
     * objects. Passing the array to this method does not
     * create copies of those objects.
     *
     * Therefore, when allot() changes occupiedCount,
     * the original ParkingSlot object is modified.
     */
    static void safeAllot(
            ParkingSlot[] slots,
            String vehicleNo) {

        ParkingSlot availableSlot =
            findAvailableSlot(slots);


        // Check for null BEFORE accessing the object.
        if (availableSlot != null) {

            availableSlot.allot(vehicleNo);

        } else {

            System.out.println(
                "No slots available for "
                + vehicleNo
            );
        }
    }


    public static void main(String[] args) {

        // ---------------------------------------------
        // CASE 1: An available slot exists
        // ---------------------------------------------

        ParkingSlot[] availableSlots = {

            new ParkingSlot("A1", 4, 3),

            new ParkingSlot("A2", 5, 5)
        };


        System.out.println(
            "Before allotment:"
        );

        System.out.println(
            "A1: "
            + availableSlots[0].occupiedCount
            + "/"
            + availableSlots[0].capacity
        );

        System.out.println(
            "A2: "
            + availableSlots[1].occupiedCount
            + "/"
            + availableSlots[1].capacity
        );


        safeAllot(
            availableSlots,
            "TN09AB1234"
        );


        System.out.println(
            "After allotment:"
        );

        System.out.println(
            "A1: "
            + availableSlots[0].occupiedCount
            + "/"
            + availableSlots[0].capacity
        );

        System.out.println(
            "A2: "
            + availableSlots[1].occupiedCount
            + "/"
            + availableSlots[1].capacity
        );


        // ---------------------------------------------
        // CASE 2: All slots are full
        // ---------------------------------------------

        ParkingSlot[] fullSlots = {

            new ParkingSlot("A1", 4, 4),

            new ParkingSlot("A2", 5, 5)
        };


        System.out.println(
            "\nAll slots full:"
        );

        safeAllot(
            fullSlots,
            "TN09AB1234"
        );
    }
}