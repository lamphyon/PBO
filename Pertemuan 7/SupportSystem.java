import java.util.HashSet;

public class SupportSystem {
    private InputReader reader;
    private Responder responder;

    public SupportSystem() {
        reader = new InputReader();
        responder = new Responder();
    }

    public static void main(String[] args) {
        SupportSystem system = new SupportSystem();
        system.start();
    }

    public void start() {
        boolean finished = false;
        printWelcome();

        while (!finished) {
            HashSet<String> input = reader.getInput();
            if (input.contains("bye")) {
                finished = true;
            } else {
                String response = responder.generateResponse(input);
                System.out.println(response);
            }
        }
        printGoodbye();
    }

    private void printWelcome() {
        System.out.println("Sistem Bantuan FRS Terpadu (Akademik & Teknis)");
        System.out.println("Ketik masalah Anda (contoh: 'krs error' atau 'bayar ukt').");
        System.out.println("Ketik 'bye' untuk berhenti.");
    }

    private void printGoodbye() {
        System.out.println("Terima kasih, semoga masalah Anda segera teratasi!");
    }
}
