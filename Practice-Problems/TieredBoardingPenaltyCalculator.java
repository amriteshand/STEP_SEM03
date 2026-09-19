public final class TieredBoardingPenaltyCalculator {

    private final double minimumPenaltyPercent;

    public TieredBoardingPenaltyCalculator(double minimumPenaltyPercent) {
        this.minimumPenaltyPercent = minimumPenaltyPercent;
    }

    public final double calculatePenalty(
            double ticketFare, int minutesLate) {

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
                ticketFare * minimumPenaltyPercent / 100.0;

        return Math.max(penalty, minimumPenalty);
    }

    public static void main(String[] args) {

        TieredBoardingPenaltyCalculator calculator =
                new TieredBoardingPenaltyCalculator(1.0);

        System.out.println(
                "Rs " + calculator.calculatePenalty(1000, 0));

        System.out.println(
                "Rs " + calculator.calculatePenalty(1000, 1));

        System.out.println(
                "Rs " + calculator.calculatePenalty(1000, 16));
    }
}