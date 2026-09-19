import java.util.regex.Pattern;

public class ImmutableLoanReceiptLedger {

    static class LoanReceipt {

        private final String memberId;
        private final String[] bookIds;

        static {
            System.out.println(
                    "Loan receipt system initialized.");
        }

        public LoanReceipt(
                String memberId,
                String[] bookIds) {

            if (memberId == null
                    || memberId.trim().isEmpty()) {
                throw new IllegalArgumentException(
                        "Invalid member ID");
            }

            if (bookIds == null) {
                throw new IllegalArgumentException(
                        "Book IDs cannot be null");
            }

            String[] copy =
                    new String[bookIds.length];

            Pattern pattern =
                    Pattern.compile("BK-\\d{3}");

            for (int i = 0; i < bookIds.length; i++) {

                if (bookIds[i] == null
                        || !pattern.matcher(bookIds[i])
                        .matches()) {

                    throw new IllegalArgumentException(
                            "Invalid book ID");
                }

                copy[i] = bookIds[i];
            }

            this.memberId = memberId;
            this.bookIds = copy;
        }

        public String getMemberId() {
            return memberId;
        }

        public String[] getBookIds() {
            return bookIds.clone();
        }

        public LoanReceipt withCorrectedBookId(
                int index,
                String newId) {

            if (index < 0
                    || index >= bookIds.length) {
                throw new IndexOutOfBoundsException();
            }

            Pattern pattern =
                    Pattern.compile("BK-\\d{3}");

            if (newId == null
                    || !pattern.matcher(newId).matches()) {
                throw new IllegalArgumentException(
                        "Invalid book ID");
            }

            String[] corrected =
                    bookIds.clone();

            corrected[index] = newId;

            return new LoanReceipt(
                    memberId,
                    corrected);
        }
    }

    static class ReferenceOnlyLoanReceipt
            extends LoanReceipt {

        private final String roomNumber;

        public ReferenceOnlyLoanReceipt(
                String memberId,
                String[] bookIds,
                String roomNumber) {

            super(memberId, bookIds);
            this.roomNumber = roomNumber;
        }

        public String getRoomNumber() {
            return roomNumber;
        }
    }

    static String processNightlyCirculation(
            LoanReceipt[] receipts) {

        int processed = 0;
        int nullSkipped = 0;
        int referenceOnly = 0;
        int regular = 0;

        if (receipts == null) {
            return "0 processed | 0 null skipped | "
                    + "0 reference-only | 0 regular";
        }

        for (LoanReceipt receipt : receipts) {

            if (receipt == null) {
                nullSkipped++;
                continue;
            }

            processed++;

            if (receipt
                    instanceof ReferenceOnlyLoanReceipt) {
                referenceOnly++;
            } else {
                regular++;
            }
        }

        return processed
                + " processed | "
                + nullSkipped
                + " null skipped | "
                + referenceOnly
                + " reference-only | "
                + regular
                + " regular";
    }

    public static void main(String[] args) {

        LoanReceipt[] receipts = {

            new ReferenceOnlyLoanReceipt(
                    "LIB-001",
                    new String[]{"BK-200"},
                    "Reading Room 3"),

            null,

            new LoanReceipt(
                    "LIB-002",
                    new String[]{"BK-201"})
        };

        System.out.println(
                processNightlyCirculation(receipts));

        LoanReceipt receipt =
                new LoanReceipt(
                        "LIB-8841",
                        new String[]{
                                "BK-100",
                                "BK-101"
                        });

        String[] ids = receipt.getBookIds();

        ids[0] = "HACKED";

        System.out.println(
                receipt.getBookIds()[0]);

        LoanReceipt corrected =
                receipt.withCorrectedBookId(
                        0,
                        "BK-999");

        System.out.println(
                corrected.getBookIds()[0]);
    }
}