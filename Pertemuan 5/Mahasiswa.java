import java.util.ArrayList;

public class Mahasiswa {
    private String nim;
    private String nama;
    private ArrayList<MataKuliah> matkulDiambil;

    public Mahasiswa(String nim, String nama) {
        this.nim = nim;
        this.nama = nama;
        this.matkulDiambil = new ArrayList<>();
    }

    public String getNim() {
        return nim;
    }

    public String getNama() {
        return nama;
    }

    public void tambahMatkul(MataKuliah matkul) {
        matkulDiambil.add(matkul);
    }

    public boolean hapusMatkul(String kodeMatkul) {
        for (int i = 0; i < matkulDiambil.size(); i++) {
            if (matkulDiambil.get(i).getKodeMatkul().equals(kodeMatkul)) {
                matkulDiambil.remove(i);
                return true;
            }
        }
        return false;
    }

    public ArrayList<MataKuliah> getMatkulDiambil() {
        return matkulDiambil;
    }

    public int getTotalSKS() {
        int total = 0;
        for (MataKuliah mk : matkulDiambil) {
            total += mk.getSks();
        }
        return total;
    }
}
