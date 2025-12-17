import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        List<Kendaraan> daftarKendaraan = new ArrayList<>();
        List<Penyewa> daftarPenyewaan = new ArrayList<>();

        daftarKendaraan.add(new Mobil("Toyota", "Avanza", 2021, 4));
        daftarKendaraan.add(new Motor("Honda", "Beat", 2019, 2));
        daftarKendaraan.add(new Sepeda("Polygon", "Xtrada", 2020, "Gunung"));

        daftarPenyewaan.add(new Penyewa("Andi", daftarKendaraan.get(0)));
        daftarPenyewaan.add(new Penyewa("Budi", daftarKendaraan.get(2)));
        daftarPenyewaan.add(new Penyewa("Cuman", daftarKendaraan.get(1)));

        int choice;
        do {
            System.out.println("\n=== Menu Utama ===");
            System.out.println("1. Daftar Kendaraan");
            System.out.println("2. Daftar Penyewa");
            System.out.println("3. Tambah Penyewa");
            System.out.println("4. Hapus Penyewa");
            System.out.println("5. Keluar");
            System.out.print("Pilih menu: ");

            choice = input.nextInt();
            input.nextLine();

            switch (choice) {
                case 1:
                    for (Kendaraan k : daftarKendaraan) {
                        System.out.println(k.getInfo());
                    }
                    break;
                case 2:
                    for (Penyewa p : daftarPenyewaan) {
                        System.out.println(p.getDetail());
                    }
                    break;
                case 3:
                    System.out.print("Tambahkan Nama Penyewa: ");
                    String nama = input.nextLine();

                    System.out.println("Daftar Kendaraan:");
                    for (int i = 0; i < daftarKendaraan.size(); i++) {
                        System.out.println((i + 1) + ". " + daftarKendaraan.get(i).getInfo());
                    }
                    System.out.print("Pilih Kendaraan (nomor): ");
                    int pilih = input.nextInt();
                    input.nextLine();

                    if (pilih < 1 || pilih > daftarKendaraan.size()) {
                        System.out.println("Pilihan tidak valid!");
                    } else {
                        Kendaraan dipilih = daftarKendaraan.get(pilih - 1);
                        daftarPenyewaan.add(new Penyewa(nama, dipilih));
                        System.out.println("Penyewa berhasil ditambahkan!");
                    }
                    break;
                case 4:
                    System.out.println("Daftar Penyewa:");
                    for (int i = 0; i < daftarPenyewaan.size(); i++) {
                        System.out.println((i + 1) + ". " + daftarPenyewaan.get(i).getDetail());
                    }
                    System.out.print("Pilih Penyewa yang akan dihapus: ");
                    int pilihPenyewa = input.nextInt();
                    input.nextLine();

                    if (pilihPenyewa < 1 || pilihPenyewa > daftarPenyewaan.size()) {
                        System.out.println("Penyewa tidak ditemukan!");
                    } else {
                        daftarPenyewaan.remove(pilihPenyewa - 1);
                        System.out.println("Penyewa berhasil dihapus!");
                    }
                    break;
                case 5:
                    System.out.println("Terima kasih telah menggunakan sistem rental!");
                    break;
                default:
                    System.out.println("Menu tidak tersedia.");
                    break;
            }
        } while (choice != 5);
        input.close();
    }
}