class NightlyMultiKitchenReconciliation {

    static class DeliveryAccount {
        protected String studentId;
        protected double orderValue;

        static {
            System.out.println("Delivery account system initialized.");
        }

        public DeliveryAccount(String studentId, double orderValue) {
            this.studentId = studentId;
            this.orderValue = orderValue;
        }

        public DeliveryAccount(String studentId) {
            this(studentId, 0.0);
        }

        final double calculateSurgeFee(int delayMinutes) {
            if (delayMinutes < 0 || orderValue < 0) {
                throw new IllegalArgumentException("Invalid order value or delay");
            }

            return orderValue * 0.01 * delayMinutes;
        }

        void processAccount(DeliveryAccount account, double amount, int delayMinutes) {
            if (account == null) {
                return;
            }

            double fee = account.calculateSurgeFee(delayMinutes);

            if (account instanceof Premium) {
                fee *= 0.5;
                System.out.println("Premium: " + account.studentId
                        + " | Amount: " + amount
                        + " | Surge Fee: " + fee);
            } else {
                System.out.println("Regular: " + account.studentId
                        + " | Amount: " + amount
                        + " | Surge Fee: " + fee);
            }
        }

        static void processBatch(DeliveryAccount[] accounts,
                                 double[] amounts,
                                 int[] delayMinutesArray) {

            int processed = 0;
            int nullSkipped = 0;
            int premiumCount = 0;
            int regularCount = 0;
            double grandTotal = 0.0;

            int size = Math.min(accounts.length,
                    Math.min(amounts.length, delayMinutesArray.length));

            DeliveryAccount processor =
                    new DeliveryAccount("SYSTEM", 0);

            for (int i = 0; i < size; i++) {
                DeliveryAccount account = accounts[i];

                if (account == null) {
                    nullSkipped++;
                    continue;
                }

                try {
                    double fee = account.calculateSurgeFee(
                            delayMinutesArray[i]);

                    if (account instanceof Premium) {
                        fee *= 0.5;
                        premiumCount++;
                    } else {
                        regularCount++;
                    }

                    processor.processAccount(
                            account,
                            amounts[i],
                            delayMinutesArray[i]);

                    grandTotal += fee;
                    processed++;

                } catch (Exception e) {
                    System.out.println(
                            "Skipped invalid account at index " + i);
                }
            }

            System.out.println(
                    processed + " processed | "
                    + nullSkipped + " null skipped | "
                    + premiumCount + " premium | "
                    + regularCount + " regular");

            System.out.println(
                    "Grand total surge fees = " + grandTotal);
        }
    }

    static class Premium extends DeliveryAccount {

        public Premium(String studentId, double orderValue) {
            super(studentId, orderValue);
        }

        public Premium(String studentId) {
            super(studentId);
        }
    }

    public static void main(String[] args) {

        DeliveryAccount[] accounts = {
            new Premium("STU001", 500),
            null,
            new DeliveryAccount("STU002", 300)
        };

        double[] amounts = {500, 400, 300};
        int[] delayMinutesArray = {10, 5, 0};

        DeliveryAccount.processBatch(
                accounts,
                amounts,
                delayMinutesArray);
    }
}