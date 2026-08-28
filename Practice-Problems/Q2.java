class FeeAccount {

    private String regNo;
    private double totalFee;
    private double amountPaid;

    // Constructor
    FeeAccount(String regNo, double totalFee) {
        this.regNo = regNo;
        this.totalFee = totalFee;
        this.amountPaid = 0;
    }

    // Accepts only positive payments
    void pay(double amount) {
        if (amount <= 0) {
            System.out.println("Payment rejected: amount must be positive.");
            return;
        }

        amountPaid += amount;

        // Prevent payment from exceeding total fee
        if (amountPaid > totalFee) {
            amountPaid = totalFee;
        }
    }

    // Returns remaining fee
    double getDue() {
        return totalFee - amountPaid;
    }
}


// HostelFeeAccount inherits from FeeAccount
class HostelFeeAccount extends FeeAccount {

    HostelFeeAccount(String regNo, double totalFee) {
        super(regNo, totalFee);
    }

    // Splits the payment into two equal installments
    void payInTwoInstallments(double amount) {
        if (amount <= 0) {
            System.out.println("Payment rejected: amount must be positive.");
            return;
        }

        pay(amount / 2);
        pay(amount / 2);
    }
}


// ScholarshipFeeAccount inherits from FeeAccount
class ScholarshipFeeAccount extends FeeAccount {

    private double scholarshipPercent;

    ScholarshipFeeAccount(
            String regNo,
            double totalFee,
            double scholarshipPercent) {

        super(regNo, totalFee);

        if (scholarshipPercent < 0 || scholarshipPercent > 100) {
            throw new IllegalArgumentException(
                "Scholarship percentage must be between 0 and 100."
            );
        }

        this.scholarshipPercent = scholarshipPercent;
    }

    // Returns due after applying scholarship
    double effectiveDue() {
        double due = getDue();

        return due - (due * scholarshipPercent / 100);
    }
}


// Main class
public class Q2 {

    public static void main(String[] args) {

        // Create one account of each type
        FeeAccount plain =
            new FeeAccount(
                "RA231100301001",
                150000
            );

        HostelFeeAccount hostel =
            new HostelFeeAccount(
                "RA231100301002",
                200000
            );

        ScholarshipFeeAccount scholarship =
            new ScholarshipFeeAccount(
                "RA231100301003",
                180000,
                20
            );


        // Apply payments
        plain.pay(150000);

        hostel.payInTwoInstallments(60000);

        // Scholarship account receives no payment


        // Store all accounts using parent-class references
        FeeAccount[] accounts = {
            plain,
            hostel,
            scholarship
        };


        // Use instanceof to invoke extra behaviour
        for (FeeAccount account : accounts) {

            if (account instanceof ScholarshipFeeAccount) {

                ScholarshipFeeAccount scholarshipAccount =
                    (ScholarshipFeeAccount) account;

                System.out.println(
                    "Scholarship account effective due: Rs "
                    + scholarshipAccount.effectiveDue()
                );

            } else if (account instanceof HostelFeeAccount) {

                HostelFeeAccount hostelAccount =
                    (HostelFeeAccount) account;

                System.out.println(
                    "Hostel account due: Rs "
                    + hostelAccount.getDue()
                );

            } else {

                System.out.println(
                    "Plain account due: Rs "
                    + account.getDue()
                );
            }
        }
    }
}