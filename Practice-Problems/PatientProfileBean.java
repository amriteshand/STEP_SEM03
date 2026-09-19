import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class PatientProfileBean {

    private String patientId;
    private String name;
    private boolean discharged;
    private String lockerPinHash;

    public PatientProfileBean() {
        this(null, null);
    }

    public PatientProfileBean(String name) {
        this(null, name);
    }

    public PatientProfileBean(String patientId, String name) {
        this.patientId = patientId;
        this.name = name;
        this.discharged = false;
    }

    public String getPatientId() {
        return patientId;
    }

    public void setPatientId(String id) {

        if (patientId == null) {
            patientId = id;
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isDischarged() {
        return discharged;
    }

    public void setDischarged(boolean discharged) {
        this.discharged = discharged;
    }

    public void setLockerPin(String pin) {

        if (pin == null || !pin.matches("\\d{4,6}")) {
            return;
        }

        lockerPinHash = hashPin(pin);
    }

    private String hashPin(String pin) {

        try {
            MessageDigest digest =
                    MessageDigest.getInstance("SHA-256");

            byte[] bytes = digest.digest(
                    pin.getBytes(StandardCharsets.UTF_8));

            StringBuilder result = new StringBuilder();

            for (byte b : bytes) {
                result.append(
                        String.format("%02x", b));
            }

            return result.toString();

        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {

        PatientProfileBean p =
                new PatientProfileBean("Arjun Iyer");

        System.out.println(p.getPatientId());

        PatientProfileBean p2 =
                new PatientProfileBean(
                        "MT2026-0142",
                        "Arjun Iyer");

        System.out.println(p2.getPatientId());

        PatientProfileBean p3 =
                new PatientProfileBean();

        p3.setPatientId("MT2026-0142");
        p3.setPatientId("HACKED-0000");

        System.out.println(p3.getPatientId());

        p3.setLockerPin("1234");

        System.out.println(p3.isDischarged());
    }
}