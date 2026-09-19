public class BookCopyCirculationGuard {

    static class BookInventory {

        private int copiesTotal;
        private int copiesAvailable;

        public BookInventory(int copiesTotal) {

            if (copiesTotal <= 0) {
                throw new IllegalArgumentException(
                        "Total copies must be positive");
            }

            this.copiesTotal = copiesTotal;
            this.copiesAvailable = copiesTotal;
        }

        void checkOut() {

            if (copiesAvailable > 0) {
                copiesAvailable--;
            }
        }

        void checkIn() {

            if (copiesAvailable < copiesTotal) {
                copiesAvailable++;
            }
        }

        int getCopiesAvailable() {
            return copiesAvailable;
        }
    }

    public static void main(String[] args) {

        BookInventory book = new BookInventory(3);

        book.checkOut();
        book.checkOut();
        book.checkOut();
        book.checkOut();

        System.out.println(book.getCopiesAvailable());

        book.checkIn();
        book.checkIn();
        book.checkIn();
        book.checkIn();

        System.out.println(book.getCopiesAvailable());
    }
}