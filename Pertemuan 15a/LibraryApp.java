import java.sql.*;
import java.util.Scanner;

public class LibraryApp {
    private static final Scanner input = new Scanner(System.in);

    public static void main(String[] args) {
        DatabaseGateway gateway = new DatabaseGateway();
        boolean isActive = true;
 
        while (isActive) {
            System.out.println("\n--- SISTEM INFORMASI PERPUSTAKAAN ---");
            System.out.println("1) Registrasi Buku Baru\n2) Daftar Koleksi\n3) Edit Data\n4) Hapus Buku\n5) Keluar");
            System.out.print("Pilih menu: ");
            
            String choice = input.nextLine();
            switch (choice) {
                case "1" -> processInsert(gateway);
                case "2" -> displayAll(gateway);
                case "3" -> processUpdate(gateway);
                case "4" -> processDelete(gateway);
                case "5" -> isActive = false;
                default -> System.out.println("Pilihan tidak tersedia.");
            }
        }
        gateway.terminate();
    }

    private static void processInsert(DatabaseGateway dw) {
        System.out.print("Judul: "); String t = input.nextLine();
        System.out.print("Penulis: "); String w = input.nextLine();
        System.out.print("Tahun: "); int y = Integer.parseInt(input.nextLine());
        System.out.print("Kategori: "); String c = input.nextLine();

        String query = "INSERT INTO book_records (book_title, writer_name, release_year, category_type) VALUES (?,?,?,?)";
        try (PreparedStatement ps = dw.openLink().prepareStatement(query)) {
            ps.setString(1, t);
            ps.setString(2, w);
            ps.setInt(3, y);
            ps.setString(4, c);
            ps.executeUpdate();
            System.out.println("Data berhasil disimpan.");
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private static void displayAll(DatabaseGateway dw) {
        String query = "SELECT * FROM book_records";
        try (Statement st = dw.openLink().createStatement(); 
             ResultSet rs = st.executeQuery(query)) {
            System.out.println("\n--- DAFTAR BUKU ---");
            while (rs.next()) {
                System.out.println(new BookEntry(
                    rs.getInt("serial_id"), rs.getString("book_title"),
                    rs.getString("writer_name"), rs.getInt("release_year"),
                    rs.getString("category_type")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void processUpdate(DatabaseGateway dw) {
        System.out.print("ID Buku yang akan diubah: ");
        int id = Integer.parseInt(input.nextLine());
        System.out.print("Judul Baru: "); String t = input.nextLine();
        
        String query = "UPDATE book_records SET book_title = ? WHERE serial_id = ?";
        try (PreparedStatement ps = dw.openLink().prepareStatement(query)) {
            ps.setString(1, t);
            ps.setInt(2, id);
            int affected = ps.executeUpdate();
            if (affected > 0) System.out.println("Update berhasil.");
        } catch (SQLException e) {
            System.out.println("Gagal update.");
        }
    }

    private static void processDelete(DatabaseGateway dw) {
        System.out.print("ID Buku yang akan dihapus: ");
        int id = Integer.parseInt(input.nextLine());
        String query = "DELETE FROM book_records WHERE serial_id = ?";
        try (PreparedStatement ps = dw.openLink().prepareStatement(query)) {
            ps.setInt(1, id);
            ps.executeUpdate();
            System.out.println("Data telah dihapus.");
        } catch (SQLException e) {
            System.out.println("Gagal menghapus.");
        }
    }
}