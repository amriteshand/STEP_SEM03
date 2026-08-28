class BookIssue {

    String title;
    String borrowerName;
    int daysOverdue;

    // Constructor
    BookIssue(String title, String borrowerName, int daysOverdue) {
        this.title = title;
        this.borrowerName = borrowerName;
        this.daysOverdue = daysOverdue;
    }

    // Calculates fine for this particular book.
    // Fine is Rs 5 per overdue day.
    double fineAmount() {

        if (daysOverdue > 0) {
            return daysOverdue * 5;
        }

        return 0;
    }

    // Checks whether this particular book is
    // overdue by more than 14 days.
    boolean isSeverelyOverdue() {
        return daysOverdue > 14;
    }

    /*
     * fineAmount() is an instance method because it
     * depends on the data of one particular BookIssue.
     *
     * totalFineCollected() is static because it works
     * on the complete array of BookIssue objects and
     * calculates a value for the collection as a whole.
     */
    static double totalFineCollected(BookIssue[] issues) {

        double total = 0;

        for (BookIssue issue : issues) {
            total += issue.fineAmount();
        }

        return total;
    }
}


public class Q1 {

    public static void main(String[] args) {

        // Create five book issue objects
        BookIssue[] issues = {

            new BookIssue(
                "Clean Code",
                "Ravi",
                18
            ),

            new BookIssue(
                "Effective Java",
                "Anitha",
                5
            ),

            new BookIssue(
                "Refactoring",
                "Karthik",
                0
            ),

            new BookIssue(
                "DSA Handbook",
                "Meera",
                21
            ),

            new BookIssue(
                "Design Patterns",
                "Suresh",
                9
            )
        };


        // Print each book's overdue status
        for (BookIssue issue : issues) {

            String status;

            if (issue.isSeverelyOverdue()) {
                status = "Severely overdue";
            } else {
                status = "OK";
            }

            System.out.println(
                issue.title
                + " - "
                + issue.daysOverdue
                + " days - "
                + status
            );
        }


        // Calculate total fine for all books
        System.out.println(
            "Total fine collected: Rs "
            + BookIssue.totalFineCollected(issues)
        );
    }
}