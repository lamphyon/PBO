import java.io.*;

public class TransactionLogger{

    private static final String LOG_FILE = "transactions.log";

    public static void saveLog(Order order, double price){
        try(FileWriter writer = new FileWriter(LOG_FILE, true)){
            writer.write(order.getDescription() + " | Rp." + (int) price + "\n");
        } 
        catch(IOException e){
            System.out.println("Error");
        }
    }

    public static void showLog(){
        System.out.println("\n=== TRANSACTION LOG ===");
        try(BufferedReader reader = new BufferedReader(new FileReader(LOG_FILE))){
            String line;
            boolean empty = true;
            while((line = reader.readLine()) != null){
                System.out.println(line);
                empty = false;
            }
            if(empty){
                System.out.println("Belum ada transaksi.");
            }
        } 
        catch(IOException e){
            System.out.println("Error");
        }
        System.out.println("========================");
    }
}
