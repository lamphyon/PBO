import java.util.ArrayList;
import java.util.Scanner;

public class PengelolaCatatan {
    private static ArrayList<Memo> daftarMemo = new ArrayList<>();
    private static Scanner input = new Scanner(System.in);

    public static void main(String[] args) {
        boolean berjalan = true;

        while (berjalan) {
            tampilkanMenu();
            String opsi = input.nextLine();

            switch (opsi) {
                case "1": buatBaru(); break;
                case "2": lihatList(); break;
                case "3": cariBerdasarkanJudul(); break;
                case "4": buangCatatan(); break;
                case "5":
                    System.out.println("Menutup program...");
                    berjalan = false;
                    break;
                default:
                    System.out.println("Pilihan tidak tersedia, silakan coba lagi.");
            }
        }
    }

    private static void tampilkanMenu() {
        System.out.println("\n>>> SISTEM CATATAN DIGITAL <<<");
        System.out.println("1) Tambah Memo");
        System.out.println("2) List Judul");
        System.out.println("3) Temukan Memo");
        System.out.println("4) Hapus Berdasarkan Judul");
        System.out.println("5) Keluar");
        System.out.print("Input aksi: ");
    }

    private static void buatBaru() {
        System.out.print("Judul Catatan: ");
        String jdl = input.nextLine();
        System.out.print("Isi Catatan  : ");
        String isi = input.nextLine();
        
        daftarMemo.add(new Memo(jdl, isi));
        System.out.println("=> Berhasil disimpan.");
    }

    private static void lihatList() {
        if (daftarMemo.isEmpty()) {
            System.out.println("Database kosong.");
            return;
        }
        System.out.println("\n--- DAFTAR MEMO ANDA ---");
        for (int i = 0; i < daftarMemo.size(); i++) {
            System.out.printf("[%d] %s\n", (i + 1), daftarMemo.get(i).getSubjek());
        }
    }

    private static void cariBerdasarkanJudul() {
        System.out.print("Ketik kata kunci judul: ");
        String keyword = input.nextLine().toLowerCase();
        boolean ditemukan = false;

        for (Memo m : daftarMemo) {
            if (m.getSubjek().toLowerCase().contains(keyword)) {
                m.cetakMemo();
                ditemukan = true;
            }
        }
        if (!ditemukan) System.out.println("Data tidak ditemukan.");
    }

    private static void buangCatatan() {
        System.out.print("Judul yang akan dibuang: ");
        String target = input.nextLine();
        
        boolean status = false;
        for (int i = 0; i < daftarMemo.size(); i++) {
            if (daftarMemo.get(i).getSubjek().equalsIgnoreCase(target)) {
                daftarMemo.remove(i);
                status = true;
                break; 
            }
        }
        
        if (status) System.out.println("Data berhasil dieliminasi.");
        else System.out.println("Judul tidak terdaftar.");
    }
}
