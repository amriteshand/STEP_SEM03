public class MembershipFieldReachChecker {

    static class LibraryMember {

        private String membershipId;
        String branchCode;
        protected double finesOwed;
        public String displayName;

        public LibraryMember(String membershipId, String branchCode,
                             double finesOwed, String displayName) {

            String id = membershipId == null
                    ? ""
                    : membershipId.trim();

            if (id.length() < 4) {
                throw new IllegalArgumentException(
                        "Invalid membership ID");
            }

            this.membershipId = id;
            this.branchCode = branchCode;
            this.finesOwed = finesOwed;
            this.displayName = displayName;
        }
    }

    static String classifyAccess(
            String fieldModifier,
            String accessorContext) {

        if (fieldModifier.equals("private")) {
            return accessorContext.equals("SAME_CLASS")
                    ? "ALLOWED" : "DENIED";
        }

        if (fieldModifier.equals("default")) {
            return accessorContext.equals("SAME_CLASS")
                    || accessorContext.equals("SAME_PACKAGE")
                    ? "ALLOWED" : "DENIED";
        }

        if (fieldModifier.equals("protected")) {
            return accessorContext.equals("SAME_CLASS")
                    || accessorContext.equals("SAME_PACKAGE")
                    ? "ALLOWED" : "DENIED";
        }

        if (fieldModifier.equals("public")) {
            return "ALLOWED";
        }

        return "DENIED";
    }

    static String summarizeByModifier(String[][] attempts) {

        String[] modifiers = {
                "private", "default", "protected", "public"
        };

        StringBuilder result = new StringBuilder();

        for (int i = 0; i < modifiers.length; i++) {

            int allowed = 0;
            int denied = 0;

            for (String[] attempt : attempts) {

                if (attempt[0].equals(modifiers[i])) {

                    if (classifyAccess(
                            attempt[0],
                            attempt[1]).equals("ALLOWED")) {
                        allowed++;
                    } else {
                        denied++;
                    }
                }
            }

            if (i > 0) {
                result.append(" | ");
            }

            result.append(modifiers[i])
                    .append(": ")
                    .append(allowed)
                    .append(" allowed / ")
                    .append(denied)
                    .append(" denied");
        }

        return result.toString();
    }

    public static void main(String[] args) {

        String[][] attempts = {
                {"private", "SAME_CLASS"},
                {"private", "SAME_PACKAGE"},
                {"default", "SAME_PACKAGE"},
                {"default", "DIFFERENT_PACKAGE"},
                {"protected", "SAME_PACKAGE"},
                {"protected", "SAME_CLASS"},
                {"public", "DIFFERENT_PACKAGE"}
        };

        System.out.println(
                classifyAccess("private", "SAME_CLASS"));

        System.out.println(
                classifyAccess("protected",
                        "DIFFERENT_PACKAGE"));

        System.out.println(
                summarizeByModifier(attempts));

        LibraryMember member =
                new LibraryMember(
                        "LB94",
                        "BR1",
                        0,
                        "Priya Nair");

        System.out.println(member.displayName);
    }
}