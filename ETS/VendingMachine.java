import java.util.ArrayList;
import java.util.Scanner;

public class VendingMachine{
    private ArrayList<Coffee> coffees;
    private Stock stock;
    private ArrayList<Order> transactions;

    public VendingMachine(){
        coffees = new ArrayList<>();
        coffees.add(new Coffee("Luwak White Coffee", 8000));
        coffees.add(new Coffee("Goodday Cappucino", 12000));
        coffees.add(new Coffee("BlendCafe", 5000));

        stock = new Stock();
        transactions = new ArrayList<>();
    }

    public void displayMenu(){
        System.out.println("\n=== MENU KOPI ===");
        for(int i = 0; i < coffees.size(); i++){
            Coffee c = coffees.get(i);
            System.out.println((i + 1) + ". " + c.getName() + " - Rp" + c.getBasePrice());
        }
    }

    public Order takeOrder(){
        Scanner sc = new Scanner(System.in);
        displayMenu();

        System.out.print("Pilih jenis kopi: ");
        int choice = sc.nextInt();
        Coffee coffeeType = coffees.get(choice - 1);

        System.out.print("Pilih ukuran (S/M/L): ");
        String size = sc.next();

        System.out.print("Tambah gula? (y/n): ");
        boolean addSugar = sc.next().equalsIgnoreCase("y");

        System.out.print("Tambah susu? (y/n): ");
        boolean addMilk = sc.next().equalsIgnoreCase("y");

        return new Order(coffeeType, size, addSugar, addMilk);
    }

    public void run(){
        Scanner sc = new Scanner(System.in);
        boolean running = true;

        while(running){
            System.out.println("\n===== VENDING COFFEE MACHINE =====");
            System.out.println("1. Beli kopi");
            System.out.println("2. Lihat log");
            System.out.println("3. Keluar");
            System.out.print("Pilih menu: ");
            String choice = sc.next();

            switch(choice){
                case "1":
                    Order order = takeOrder();
                    double[] need = order.getIngredients();

                    if(!stock.isEnough(need[0], need[1], need[2], need[3])){
                        System.out.println("Stok tidak cukup. Panggil admin untuk refill.");
                        break;
                    }

                    double totalPrice = order.calculatePrice();
                    Payment payment = new Payment(totalPrice);

                    if(payment.process()){
                        System.out.println("Brewing... Kopi siap! Silakan diambil.");
                        stock.useIngredients(need[0], need[1], need[2], need[3]);
                        transactions.add(order);
                        TransactionLogger.saveLog(order, totalPrice);
                        System.out.println("Transaksi berhasil dan disimpan ke log.");
                    }

                    break;

                case "2":
                    TransactionLogger.showLog();
                    break;

                case "3":
                    System.out.println("Terima kasih telah menggunakan mesin kopi");
                    running = false;
                    break;

                default:
                    System.out.println("Pilihan tidak valid.");
            }
        }
    }

    public static void main(String[] args){
        VendingMachine vm = new VendingMachine();
        vm.run();
    }
}
