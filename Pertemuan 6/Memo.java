public class Memo {
    private String subjek;
    private String deskripsi;

    public Memo(String subjek, String deskripsi) {
        this.subjek = subjek;
        this.deskripsi = deskripsi;
    }

    public String getSubjek() {
        return subjek;
    }

    public String getDeskripsi() {
        return deskripsi;
    }

    public void cetakMemo() {
        System.out.println("---------------------------");
        System.out.println("Topik : " + subjek);
        System.out.println("Pesan : " + deskripsi);
        System.out.println("---------------------------");
    }
}
