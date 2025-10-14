public class Coffee{
    private String name; // atribut
    private int basePrice; // atribut

    public Coffee(String name, int basePrice){ // untuk menyocokkan
        this.name = name;
        this.basePrice = basePrice;
    }

    public String getName(){ // untuk input nama
        return name;
    }

    public int getBasePrice(){ // untuk input harga awal sebelum dikali size
        return basePrice;
    }
}
