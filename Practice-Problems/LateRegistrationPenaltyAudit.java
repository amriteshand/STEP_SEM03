import java.util.Arrays;

public class LateRegistrationPenaltyAudit {

    static class EventTicket {
        protected double basePrice;
        protected double amountPaid;

        private double[] lateFeeHistory;
        private int historyCount;

        public EventTicket(double basePrice) {
            this.basePrice = basePrice;
            this.lateFeeHistory = new double[10];
            this.historyCount = 0;
        }

        public void pay(double amount) {
            if (amount > 0) {
                amountPaid += amount;
            }
        }

        public double getBalanceDue() {
            return basePrice - amountPaid;
        }

        protected void applyLateFee(double amount) {
            amountPaid -= amount;

            if (historyCount < lateFeeHistory.length) {
                lateFeeHistory[historyCount] = amount;
                historyCount++;
            }
        }

        public double[] getLateFeeHistory() {
            return Arrays.copyOf(
                    lateFeeHistory,
                    historyCount
            );
        }
    }

    static class WorkshopTicket extends EventTicket {

        public WorkshopTicket(double basePrice) {
            super(basePrice);
        }

        @Override
        protected void applyLateFee(double amount) {
            super.applyLateFee(amount * 2);
        }
    }

    public static void main(String[] args) {

        WorkshopTicket w =
                new WorkshopTicket(1200);

        w.pay(1200);

        w.applyLateFee(100);

        System.out.println(
                w.getBalanceDue());

        double[] history =
                w.getLateFeeHistory();

        System.out.println(
                Arrays.toString(history));

        history[0] = 999;

        System.out.println(
                Arrays.toString(
                        w.getLateFeeHistory()));
    }
}