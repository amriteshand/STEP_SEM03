class HostelRoom {

    String roomNo;
    int beds;
    int occupied;

    // Constructor
    HostelRoom(String roomNo, int beds, int occupied) {
        this.roomNo = roomNo;
        this.beds = beds;
        this.occupied = occupied;
    }

    // Allots one bed if a bed is available
    void allot(String name) {
        if (occupied < beds) {
            occupied++;
            System.out.println(name + " allotted to room " + roomNo);
        }
    }
}


public class Q3 {

    // Finds and returns the first room having an available bed.
    // If all rooms are full, it returns null.
    static HostelRoom findAvailableRoom(HostelRoom[] rooms) {

        for (HostelRoom room : rooms) {

            if (room != null && room.occupied < room.beds) {
                return room;
            }
        }

        return null;
    }


    // Safely allots a student to an available room.
    // The array contains references to HostelRoom objects.
    // Passing the array does not create copies of the HostelRoom objects.
    // Therefore, changes made through a reference modify the original room object.
    static void safeAllot(HostelRoom[] rooms, String studentName) {

        HostelRoom availableRoom = findAvailableRoom(rooms);

        if (availableRoom != null) {
            availableRoom.allot(studentName);
        } else {
            System.out.println(
                "No rooms available for " + studentName
            );
        }
    }


    public static void main(String[] args) {

        // Case 1: An available room exists
        HostelRoom[] roomsWithAvailability = {
            new HostelRoom("C-214", 3, 2),
            new HostelRoom("C-507", 2, 2)
        };

        System.out.println("Case 1: Available room");
        safeAllot(roomsWithAvailability, "Divya");

        System.out.println(
            "C-214: " +
            roomsWithAvailability[0].occupied +
            "/" +
            roomsWithAvailability[0].beds
        );

        System.out.println(
            "C-507: " +
            roomsWithAvailability[1].occupied +
            "/" +
            roomsWithAvailability[1].beds
        );


        // Case 2: Every room is already full
        HostelRoom[] fullRooms = {
            new HostelRoom("C-214", 3, 3),
            new HostelRoom("C-507", 2, 2)
        };

        System.out.println("\nCase 2: All rooms full");
        safeAllot(fullRooms, "Divya");
    }
}