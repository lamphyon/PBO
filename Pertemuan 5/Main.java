import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    private static ArrayList<Mahasiswa> daftarMahasiswa = new ArrayList<>();
    private static ArrayList<MataKuliah> daftarMatkul = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);
    private static Mahasiswa currentUser = null; // session login

    public static void main(String[] args) {
        initData();

        while (true) {
            if (currentUser == null) {
                tampilkanMenuUtama();
            } else {
                tampilkanMenuMahasiswa();
            }
        }
    }

    private static void tampilkanMenuUtama() {
        System.out.println("\n===== SISTEM FRS AKADEMIK =====");
        System.out.println("1. Login Mahasiswa (NRP)");
        System.out.println("2. Lihat Semua Mata Kuliah");
        System.out.println("3. Keluar");
        System.out.print("Pilih: ");
        
        int pilihan = ambilInputInt();
        switch (pilihan) {
            case 1: login(); break;
            case 2: tampilkanSemuaMatkul(); break;
            case 3: 
                System.out.println("Terima kasih!");
                System.exit(0);
            default: System.out.println("Pilihan tidak valid.");
        }
    }

    private static void tampilkanMenuMahasiswa() {
        System.out.println("\n===== MENU MAHASISWA =====");
        System.out.println("Login sebagai: " + currentUser.getNama() + " (" + currentUser.getNim() + ")");
        System.out.println("1. Ambil Mata Kuliah");
        System.out.println("2. Drop (Hapus) Mata Kuliah");
        System.out.println("3. Lihat KHS (Mata Kuliah Diambil)");
        System.out.println("4. Logout");
        System.out.print("Pilih: ");

        int pilihan = ambilInputInt();
        switch (pilihan) {
            case 1: tambahMatkulKeMahasiswa(); break;
            case 2: hapusMatkulMahasiswa(); break;
            case 3: tampilkanMatkulDiambil(); break;
            case 4: 
                currentUser = null; 
                System.out.println("Berhasil logout.");
                break;
            default: System.out.println("Pilihan tidak valid.");
        }
    }

    private static void login() {
        System.out.print("Masukkan NRP: ");
        String nrp = scanner.nextLine();
        
        // Cari mahasiswa di database memory
        for (Mahasiswa m : daftarMahasiswa) {
            if (m.getNim().equals(nrp)) {
                currentUser = m;
                System.out.println("Selamat datang kembali, " + m.getNama() + "!");
                return;
            }
        }

        // Jika tidak ketemu, buat baru
        System.out.print("NRP belum terdaftar. Masukkan Nama Anda: ");
        String nama = scanner.nextLine();
        Mahasiswa baru = new Mahasiswa(nrp, nama);
        daftarMahasiswa.add(baru);
        currentUser = baru;
        System.out.println("Akun baru berhasil dibuat!");
    }

    private static void tambahMatkulKeMahasiswa() {
        tampilkanSemuaMatkul();
        System.out.print("Masukkan Kode MK yang ingin diambil: ");
        String kode = scanner.nextLine();

        for (MataKuliah mk : daftarMatkul) {
            if (mk.getKodeMatkul().equalsIgnoreCase(kode)) {
                // Validasi duplikasi
                for (MataKuliah m : currentUser.getMatkulDiambil()) {
                    if (m.getKodeMatkul().equalsIgnoreCase(kode)) {
                        System.out.println("Maaf, Anda sudah mengambil mata kuliah ini.");
                        return;
                    }
                }
                currentUser.tambahMatkul(mk);
                System.out.println("Berhasil mengambil: " + mk.getNamaMatkul());
                return;
            }
        }
        System.out.println("Kode Mata Kuliah tidak ditemukan.");
    }

    private static void hapusMatkulMahasiswa() {
        if (currentUser.getMatkulDiambil().isEmpty()) {
            System.out.println("Anda belum mengambil mata kuliah apapun.");
            return;
        }
        tampilkanMatkulDiambil();
        System.out.print("Masukkan Kode MK yang ingin di-drop: ");
        String kode = scanner.nextLine();
        
        if (currentUser.hapusMatkul(kode)) {
            System.out.println("Mata kuliah berhasil dihapus.");
        } else {
            System.out.println("Kode tidak ditemukan di daftar Anda.");
        }
    }

    private static void tampilkanMatkulDiambil() {
        System.out.println("\n--- Daftar Mata Kuliah " + currentUser.getNama() + " ---");
        if (currentUser.getMatkulDiambil().isEmpty()) {
            System.out.println("Kosong.");
        } else {
            for (MataKuliah mk : currentUser.getMatkulDiambil()) {
                System.out.println("- " + mk);
            }
            System.out.println("Total SKS: " + currentUser.getTotalSKS());
        }
    }

    private static void tampilkanSemuaMatkul() {
        System.out.println("\n--- Daftar Mata Kuliah Tersedia ---");
        for (MataKuliah mk : daftarMatkul) {
            System.out.println(mk.getKodeMatkul() + " | " + mk.getNamaMatkul() + " (" + mk.getSks() + " SKS)");
        }
    }

    private static void initData() {
        Dosen d1 = new Dosen("Dr. Ahmad", "D01");
        Dosen d2 = new Dosen("Dr. Siti", "D02");

        daftarMatkul.add(new MataKuliah("IF01", "PBO", d1, 3));
        daftarMatkul.add(new MataKuliah("IF02", "Struktur Data", d2, 3));
        daftarMatkul.add(new MataKuliah("IF03", "Basis Data", d1, 4));
    }

    private static int ambilInputInt() {
        try {
            int input = scanner.nextInt();
            scanner.nextLine();
            return input;
        } catch (Exception e) {
            scanner.nextLine();
            return -1;
        }
    }
}
