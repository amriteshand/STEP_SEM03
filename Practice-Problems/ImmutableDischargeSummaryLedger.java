import java.util.regex.Pattern;

public class ImmutableDischargeSummaryLedger {

    static class DischargeSummary {

        private final String patientId;
        private final String[] medicationCodes;

        static {
            System.out.println(
                    "Discharge summary system initialized.");
        }

        public DischargeSummary(
                String patientId,
                String[] medicationCodes) {

            if (patientId == null
                    || patientId.trim().isEmpty()) {
                throw new IllegalArgumentException(
                        "Invalid patient ID");
            }

            if (medicationCodes == null) {
                throw new IllegalArgumentException(
                        "Medication codes cannot be null");
            }

            Pattern pattern =
                    Pattern.compile("MED-[A-Z]");

            String[] copy =
                    new String[medicationCodes.length];

            for (int i = 0; i < medicationCodes.length; i++) {

                String code = medicationCodes[i];

                if (code == null
                        || !pattern.matcher(code).matches()) {
                    throw new IllegalArgumentException(
                            "Invalid medication code");
                }

                copy[i] = code;
            }

            this.patientId = patientId;
            this.medicationCodes = copy;
        }

        public String getPatientId() {
            return patientId;
        }

        public String[] getMedicationCodes() {
            return medicationCodes.clone();
        }

        public DischargeSummary withCorrectedMedication(
                int index,
                String newCode) {

            if (index < 0
                    || index >= medicationCodes.length) {
                throw new IndexOutOfBoundsException();
            }

            if (newCode == null
                    || !newCode.matches("MED-[A-Z]")) {
                throw new IllegalArgumentException(
                        "Invalid medication code");
            }

            String[] corrected =
                    medicationCodes.clone();

            corrected[index] = newCode;

            return new DischargeSummary(
                    patientId,
                    corrected);
        }
    }

    static class CriticalCareDischargeSummary
            extends DischargeSummary {

        private final int icuDays;

        public CriticalCareDischargeSummary(
                String patientId,
                String[] medicationCodes,
                int icuDays) {

            super(patientId, medicationCodes);
            this.icuDays = icuDays;
        }

        public int getIcuDays() {
            return icuDays;
        }
    }

    static String processNightlyBatch(
            DischargeSummary[] summaries) {

        int processed = 0;
        int nullSkipped = 0;
        int criticalCare = 0;
        int routine = 0;

        if (summaries == null) {
            return "0 processed | 0 null skipped | "
                    + "0 critical-care | 0 routine";
        }

        for (DischargeSummary summary : summaries) {

            if (summary == null) {
                nullSkipped++;
                continue;
            }

            processed++;

            if (summary instanceof CriticalCareDischargeSummary) {
                criticalCare++;
            } else {
                routine++;
            }
        }

        return processed
                + " processed | "
                + nullSkipped
                + " null skipped | "
                + criticalCare
                + " critical-care | "
                + routine
                + " routine";
    }

    public static void main(String[] args) {

        try {
            new DischargeSummary(
                    "MT2026-0142",
                    new String[]{"MED-A", "bad"});
        } catch (IllegalArgumentException e) {
            System.out.println(
                    "construction rejected");
        }

        DischargeSummary d =
                new DischargeSummary(
                        "MT2026-0142",
                        new String[]{"MED-A", "MED-B"});

        String[] codes =
                d.getMedicationCodes();

        codes[0] = "TAMPERED";

        System.out.println(
                d.getMedicationCodes()[0]);

        DischargeSummary corrected =
                d.withCorrectedMedication(
                        0,
                        "MED-C");

        System.out.println(
                corrected.getMedicationCodes()[0]);

        DischargeSummary[] batch = {
                new CriticalCareDischargeSummary(
                        "MT001",
                        new String[]{"MED-X"},
                        4),
                null,
                new DischargeSummary(
                        "MT002",
                        new String[]{"MED-Y"})
        };

        System.out.println(
                processNightlyBatch(batch));
    }
}