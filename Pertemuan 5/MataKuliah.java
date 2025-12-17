public class MataKuliah {
    private String kodeMatkul;
    private String namaMatkul;
    private Dosen dosenPengajar;
    private int sks;

    public MataKuliah(String kodeMatkul, String namaMatkul, Dosen dosenPengajar, int sks) {
        this.kodeMatkul = kodeMatkul;
        this.namaMatkul = namaMatkul;
        this.dosenPengajar = dosenPengajar;
        this.sks = sks;
    }

    public String getKodeMatkul() {
        return kodeMatkul;
    }

    public String getNamaMatkul() {
        return namaMatkul;
    }

    public Dosen getDosenPengajar() {
        return dosenPengajar;
    }

    public int getSks() {
        return sks;
    }

    @Override
    public String toString() {
        return kodeMatkul + " - " + namaMatkul + " (" + sks + " SKS) - Dosen: " + dosenPengajar.getNamaDosen();
    }
}
