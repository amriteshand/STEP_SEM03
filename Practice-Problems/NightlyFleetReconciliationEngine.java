public class NightlyFleetReconciliationEngine {

    static class BusTicketAccount {
        protected String bookingId;
        protected double ticketFare;

        static {
            System.out.println("Fleet reconciliation system initialized.");
        }

        public BusTicketAccount(String bookingId, double ticketFare) {
            this.bookingId = bookingId;
            this.ticketFare = ticketFare;
        }

        public BusTicketAccount(String bookingId) {
            this(bookingId, 0.0);
        }

        final double calculatePenalty(int minutesLate) {

            if (ticketFare < 0 || minutesLate < 0) {
                throw new IllegalArgumentException(
                        "Invalid fare or delay");
            }

            if (minutesLate == 0) {
                return 0.0;
            }

            double penalty = 0.0;

            int firstTier = Math.min(minutesLate, 5);
            penalty += firstTier * ticketFare * 0.005;

            if (minutesLate > 5) {
                int secondTier =
                        Math.min(minutesLate - 5, 10);

                penalty += secondTier * ticketFare * 0.01;
            }

            if (minutesLate > 15) {
                int thirdTier = minutesLate - 15;

                penalty += thirdTier * ticketFare * 0.02;
            }

            double minimumPenalty =
                    ticketFare * 1.0 / 100.0;

            return Math.max(penalty, minimumPenalty);
        }

        void processAccount(
                BusTicketAccount account,
                double amount,
                int minutesLate) {

            if (account == null) {
                return;
            }

            double penalty =
                    account.calculatePenalty(minutesLate);

            if (account instanceof Sleeper) {
                penalty *= 0.5;

                System.out.println(
                        "Sleeper: " + account.bookingId
                        + " | Amount: " + amount
                        + " | Penalty: " + penalty);

            } else {

                System.out.println(
                        "Regular: " + account.bookingId
                        + " | Amount: " + amount
                        + " | Penalty: " + penalty);
            }
        }

        static void processBatch(
                BusTicketAccount[] accounts,
                double[] amounts,
                int[] minutesLateArray) {

            int processed = 0;
            int nullSkipped = 0;
            int sleeperCount = 0;
            int regularCount = 0;

            double grandTotal = 0.0;

            int size = Math.min(
                    accounts.length,
                    Math.min(
                            amounts.length,
                            minutesLateArray.length));

            BusTicketAccount processor =
                    new BusTicketAccount("SYSTEM", 0);

            for (int i = 0; i < size; i++) {

                BusTicketAccount account = accounts[i];

                if (account == null) {
                    nullSkipped++;
                    continue;
                }

                try {
                    double penalty =
                            account.calculatePenalty(
                                    minutesLateArray[i]);

                    if (account instanceof Sleeper) {
                        penalty *= 0.5;
                        sleeperCount++;
                    } else {
                        regularCount++;
                    }

                    processor.processAccount(
                            account,
                            amounts[i],
                            minutesLateArray[i]);

                    grandTotal += penalty;
                    processed++;

                } catch (Exception e) {
                    System.out.println(
                            "Skipped invalid account at index " + i);
                }
            }

            System.out.println();

            System.out.println(
                    processed + " processed | "
                    + nullSkipped + " null skipped | "
                    + sleeperCount + " sleeper | "
                    + regularCount + " regular");

            System.out.println(
                    "Grand total penalties = "
                    + grandTotal);
        }
    }

    static class Sleeper extends BusTicketAccount {

        public Sleeper(
                String bookingId,
                double ticketFare) {

            super(bookingId, ticketFare);
        }

        public Sleeper(String bookingId) {
            super(bookingId);
        }
    }

    public static void main(String[] args) {

        BusTicketAccount[] accounts = {
            new Sleeper("BK001", 2000),
            null,
            new BusTicketAccount("BK002", 1200)
        };

        double[] amounts = {
            1200, 900, 700
        };

        int[] minutesLateArray = {
            10, 5, 0
        };

        BusTicketAccount.processBatch(
                accounts,
                amounts,
                minutesLateArray);
    }
}