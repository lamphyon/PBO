import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Random;

public class Responder {
    private HashMap<String, String> responseMap;
    private ArrayList<String> defaultResponses;
    private Random randomGenerator;

    public Responder() {
        responseMap = new HashMap<>();
        defaultResponses = new ArrayList<>();
        randomGenerator = new Random();
        
        fillResponsesMap();
        fillDefaultResponses();
    }

    public String generateResponse(HashSet<String> words) {
        for (String word : words) {
            String response = responseMap.get(word);
            if (response != null) {
                return response;
            }
        }
        return pickDefaultResponse();
    }

    private String pickDefaultResponse() {
        int index = randomGenerator.nextInt(defaultResponses.size());
        return defaultResponses.get(index);
    }

    private void fillResponsesMap() {
        responseMap.put("krs", "Pastikan sudah bayar UKT untuk mengisi KRS di portal akademik.");
        responseMap.put("nilai", "Sanggahan nilai maksimal 2 minggu setelah pengumuman.");
        responseMap.put("wali", "Konsultasi dosen wali bisa melalui email atau janji temu di ruangan.");
        responseMap.put("sks", "Beban SKS bergantung pada IPS semester sebelumnya (Max 24 SKS).");
        responseMap.put("ukt", "Masalah UKT silakan hubungi bagian keuangan atau BAAK.");
        responseMap.put("skripsi", "Syarat skripsi adalah minimal 144 SKS dan lulus mata kuliah prasyarat.");

        responseMap.put("error", "Jika muncul pesan kesalahan, coba refresh halaman atau hapus cache browser.");
        responseMap.put("server", "Sistem sedang sibuk. Mohon tunggu beberapa menit atau cek koneksi internet.");
        
        responseMap.put("login", "Gagal masuk? Pastikan NRP dan password benar. Coba reset jika lupa.");
        responseMap.put("masuk", responseMap.get("login"));
        responseMap.put("pembayaran", responseMap.get("ukt"));
        responseMap.put("ta", responseMap.get("skripsi"));
    }

    private void fillDefaultResponses() {
        defaultResponses.add("Bisa jelaskan lebih detail masalahnya?");
        defaultResponses.add("Saya butuh informasi tambahan agar bisa membantu Anda.");
        defaultResponses.add("Coba gunakan kata kunci lain seperti 'KRS', 'UKT', atau 'Login'.");
        defaultResponses.add("Apakah Anda sudah mencoba bertanya ke bagian akademik fakultas?");
    }
}
