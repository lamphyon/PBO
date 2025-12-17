public class Dosen {
    private String namaDosen;
    private String kodeDosen;

    public Dosen(String namaDosen, String kodeDosen) {
        this.namaDosen = namaDosen;
        this.kodeDosen = kodeDosen;
    }

    public String getNamaDosen() {
        return namaDosen;
    }

    public String getKodeDosen() {
        return kodeDosen;
    }

    @Override
    public String toString() {
        return kodeDosen + " - " + namaDosen;
    }
}
