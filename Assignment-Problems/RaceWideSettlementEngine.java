public class RaceWideSettlementEngine {

    static class SettlementRaceEntry {
        protected String bibNumber;
        protected double entryFee;
        protected double amountPaid;

        private final int entryCode;
        private static int bibCounter = 0;

        public SettlementRaceEntry(String bibNumber, double entryFee) {
            if (bibNumber == null || bibNumber.trim().isEmpty()
                    || bibNumber.length() < 4) {
                throw new IllegalArgumentException("Invalid bib number");
            }

            this.bibNumber = bibNumber;
            this.entryFee = entryFee;

            bibCounter++;
            entryCode = bibCounter;
        }

        public void pay(double amount) {
            if (amount > 0) {
                amountPaid += amount;
            }
        }

        public void pay(double amount, String mode) {
            pay(amount);
            System.out.println("Paying via " + mode);
        }

        public double getBalanceDue() {
            return entryFee - amountPaid;
        }

        public int getEntryCode() {
            return entryCode;
        }

        public static boolean isValidDiscountCode(String code) {
            if (code == null || code.length() != 5) {
                return false;
            }

            if (code.charAt(0) != 'M') {
                return false;
            }

            if (!Character.isDigit(code.charAt(1))) {
                return false;
            }

            if (!Character.isDigit(code.charAt(2))) {
                return false;
            }

            if (!Character.isDigit(code.charAt(3))) {
                return false;
            }

            if (!Character.isUpperCase(code.charAt(4))) {
                return false;
            }

            return true;
        }

        public static int getBibCounter() {
            return bibCounter;
        }
    }

    static class SettlementRunnerEntry extends SettlementRaceEntry {
        private String category;

        public SettlementRunnerEntry(String bibNumber, double entryFee,
                                     String category) {
            super(bibNumber, entryFee);
            this.category = category;
        }
    }

    static class SettlementEliteRunnerEntry extends SettlementRunnerEntry {
        private double sponsorBonus;

        public SettlementEliteRunnerEntry(String bibNumber, double entryFee,
                                          String category,
                                          double sponsorBonus) {
            super(bibNumber, entryFee, category);
            this.sponsorBonus = sponsorBonus;
        }
    }

    static class SettlementRelayTeamEntry extends SettlementRaceEntry {
        private int teamSize;

        public SettlementRelayTeamEntry(String bibNumber, double entryFee,
                                        int teamSize) {
            super(bibNumber, entryFee);
            this.teamSize = teamSize;
        }

        public int getTeamSize() {
            return teamSize;
        }
    }

    static String settleNight(SettlementRaceEntry[] entries) {
        int processed = 0;
        int nullSkipped = 0;
        int relay = 0;
        int individual = 0;

        if (entries == null) {
            return "0 processed | 0 null skipped | 0 relay | 0 individual";
        }

        for (SettlementRaceEntry entry : entries) {
            if (entry == null) {
                nullSkipped++;
                continue;
            }

            processed++;

            if (entry instanceof SettlementRelayTeamEntry) {
                relay++;
            } else {
                individual++;
            }
        }

        return processed + " processed | "
                + nullSkipped + " null skipped | "
                + relay + " relay | "
                + individual + " individual";
    }

    static boolean isValidDiscountCode(String code) {
        return SettlementRaceEntry.isValidDiscountCode(code);
    }

    static int getBibCounter() {
        return SettlementRaceEntry.getBibCounter();
    }

    public static void main(String[] args) {

        System.out.println(
                SettlementRaceEntry.isValidDiscountCode("M123A"));

        System.out.println(
                SettlementRaceEntry.isValidDiscountCode("M12A"));

        System.out.println(
                SettlementRaceEntry.isValidDiscountCode("X123A"));

        SettlementEliteRunnerEntry elite =
                new SettlementEliteRunnerEntry(
                        "BIB3001",
                        150,
                        "Elite Full Marathon",
                        500);

        SettlementRelayTeamEntry relay =
                new SettlementRelayTeamEntry(
                        "BIB4001",
                        300,
                        4);

        elite.pay(10, "UPI");

        SettlementRaceEntry[] entries = {
            elite,
            null,
            relay
        };

        System.out.println(settleNight(entries));

        System.out.println(getBibCounter());
    }
}