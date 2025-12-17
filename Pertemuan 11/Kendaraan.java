public class Kendaraan {
    private String merk;
    private String model;
    private int tahunProduksi;

    public Kendaraan(String merk, String model, int tahunProduksi) {
        this.merk = merk;
        this.model = model;
        this.tahunProduksi = tahunProduksi;
    }

    public String getInfo() {
        return merk + " " + model + " (" + tahunProduksi + ")";
    }
}