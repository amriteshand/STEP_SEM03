// ---------------------------------------------------------
// BROKEN VERSION
// ---------------------------------------------------------

class BrokenLibraryMember {

    /*
     * These fields are incorrectly static.
     *
     * name:
     * Every member should have their own name,
     * so it must not be shared.
     *
     * memberId:
     * Every member must have a different ID,
     * so it must be an instance field.
     *
     * booksIssued:
     * Each member can have a different number of books,
     * so it must also belong to the individual object.
     */
    static String name;
    static String memberId;
    static int booksIssued;


    BrokenLibraryMember(
            String name,
            String memberId,
            int booksIssued) {

        BrokenLibraryMember.name = name;
        BrokenLibraryMember.memberId = memberId;
        BrokenLibraryMember.booksIssued = booksIssued;
    }
}


// ---------------------------------------------------------
// CORRECTED VERSION
// ---------------------------------------------------------

class LibraryMember {

    // These belong to each individual member
    String name;
    String memberId;
    int booksIssued;


    // These are shared by the whole library
    static String libraryName =
        "SRM Central Library";

    static int memberCount = 0;


    /*
     * memberId is automatically generated using
     * the shared memberCount.
     */
    LibraryMember(
            String name,
            int booksIssued) {

        this.name = name;
        this.booksIssued = booksIssued;

        memberCount++;

        this.memberId =
            "LM-"
            + String.format(
                "%04d",
                memberCount
            );
    }


    // Instance method because it displays
    // information belonging to one member.
    void printMemberCard() {

        System.out.println(
            name
            + " | "
            + memberId
        );
    }


    // Static method because memberCount belongs
    // to the library as a whole.
    static void printTotalMembers() {

        System.out.println(
            "Total members: "
            + memberCount
        );
    }
}


public class Q4 {

    public static void main(String[] args) {

        // -------------------------------------------------
        // BROKEN VERSION
        // -------------------------------------------------

        System.out.println("Broken version:");

        BrokenLibraryMember member1 =
            new BrokenLibraryMember(
                "Aditi",
                "LM-1001",
                2
            );


        BrokenLibraryMember member2 =
            new BrokenLibraryMember(
                "Rohan",
                "LM-1002",
                3
            );


        /*
         * Since name is static, member1.name and member2.name
         * refer to the same shared variable.
         *
         * Creating member2 overwrote Aditi's name.
         */
        System.out.println(
            member1.name
        );

        System.out.println(
            member2.name
        );

        System.out.println(
            "(Aditi's data was overwritten — "
            + "both members now show Rohan)"
        );


        // -------------------------------------------------
        // FIXED VERSION
        // -------------------------------------------------

        System.out.println("\nFixed version:");

        LibraryMember member3 =
            new LibraryMember(
                "Aditi",
                2
            );


        LibraryMember member4 =
            new LibraryMember(
                "Rohan",
                3
            );


        // Each object now has its own data
        member3.printMemberCard();
        member4.printMemberCard();


        // Shared library-wide count
        LibraryMember.printTotalMembers();
    }
}