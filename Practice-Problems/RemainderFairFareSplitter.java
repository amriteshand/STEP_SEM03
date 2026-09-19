import java.util.Arrays;

public class RemainderFairFareSplitter {

    static class FareSplitter {
        private String tripId;
        private double totalFare;
        private int passengerCount;

        public FareSplitter(String tripId, double totalFare, int passengerCount) {
            if (totalFare < 0) {
                throw new IllegalArgumentException("Fare cannot be negative");
            }

            if (passengerCount <= 0) {
                throw new IllegalArgumentException("Passenger count must be positive");
            }

            this.tripId = tripId;
            this.totalFare = totalFare;
            this.passengerCount = passengerCount;
        }

        public FareSplitter(String tripId, double totalFare) {
            this(tripId, totalFare, 2);
        }

        public FareSplitter(String tripId) {
            this(tripId, 0.0, 2);
        }

        double[] fareBreakdown() {
            long totalPaise = Math.round(totalFare * 100);

            long each = totalPaise / passengerCount;
            long remainder = totalPaise % passengerCount;

            double[] result = new double[passengerCount];

            for (int i = 0; i < passengerCount; i++) {
                long share = each;

                if (i == passengerCount - 1) {
                    share += remainder;
                }

                result[i] = share / 100.0;
            }

            return result;
        }

        boolean isConfirmationOverdue(int confirmed, int expected) {
            return confirmed < expected;
        }
    }

    public static void main(String[] args) {
        FareSplitter split1 =
                new FareSplitter("TRIP001", 100000, 3);

        FareSplitter split2 =
                new FareSplitter("TRIP003");

        System.out.println(Arrays.toString(split1.fareBreakdown()));
        System.out.println(Arrays.toString(split2.fareBreakdown()));

        System.out.println(split1.isConfirmationOverdue(2, 3));
    }
}