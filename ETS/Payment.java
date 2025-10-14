import java.util.Scanner;

public class Payment{
    private double totalPrice;

    public Payment(double totalPrice){
        this.totalPrice = totalPrice;
    }

    public boolean process(){
        Scanner sc = new Scanner(System.in);
        System.out.println("\nTotal harga: Rp" + String.format("%,.0f", totalPrice));
        System.out.print("Input uang yang dibayarkan: Rp");
        double money = sc.nextDouble();

        if(money == totalPrice){
            System.out.println("Pembayaran berhasil.");
            return true;
        }
        else if(money > totalPrice){
            double change = money - totalPrice;
            System.out.println("Pembayaran berhasil. Kembalian: Rp" + String.format("%,.0f", change));
            return true;
        } 
        else{
            System.out.println("Uang tidak cukup. Transaksi dibatalkan.");
            return false;
        }
    }
}
