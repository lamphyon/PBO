public class Penyewa {
    private String namaPenyewa;
    private Kendaraan kendaraan;

    public Penyewa(String namaPenyewa, Kendaraan kendaraan) {
        this.namaPenyewa = namaPenyewa;
        this.kendaraan = kendaraan;
    }

    public String getDetail() {
        return "Penyewa: " + namaPenyewa + " -> " + kendaraan.getInfo();
    }
}