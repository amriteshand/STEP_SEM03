import java.util.Arrays;

public class LateWithdrawalPenaltyAudit {

    static class RaceEntry {
        protected String bibNumber;
        protected double entryFee;
        protected double amountPaid;

        private double[] lateFeeHistory;
        private int historyCount;

        public RaceEntry(String bibNumber, double entryFee) {
            if (bibNumber == null || bibNumber.trim().isEmpty() || bibNumber.length() < 4) {
                throw new IllegalArgumentException("Invalid bib number");
            }

            this.bibNumber = bibNumber;
            this.entryFee = entryFee;
            this.lateFeeHistory = new double[10];
            this.historyCount = 0;
        }

        public void pay(double amount) {
            if (amount > 0) {
                amountPaid += amount;
            }
        }

        public double getBalanceDue() {
            return entryFee - amountPaid;
        }

        protected void applyLateFee(double amount) {
            amountPaid -= amount;

            if (historyCount < lateFeeHistory.length) {
                lateFeeHistory[historyCount] = amount;
                historyCount++;
            }
        }

        public double[] getLateFeeHistory() {
            return Arrays.copyOf(lateFeeHistory, historyCount);
        }
    }

    static class RunnerEntry extends RaceEntry {
        private String category;

        public RunnerEntry(String bibNumber, double entryFee, String category) {
            super(bibNumber, entryFee);
            this.category = category;
        }

        @Override
        protected void applyLateFee(double amount) {
            super.applyLateFee(amount * 2);
        }
    }

    public static void main(String[] args) {
        RunnerEntry r =
                new RunnerEntry("BIB2001", 80, "Open 10K");

        r.pay(30);
        r.applyLateFee(20);

        System.out.println(r.getBalanceDue());

        double[] history = r.getLateFeeHistory();
        System.out.println(Arrays.toString(history));

        history[0] = 999;

        System.out.println(Arrays.toString(r.getLateFeeHistory()));
    }
}