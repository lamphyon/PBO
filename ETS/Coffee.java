public class Coffee{
    private String name;
    private int basePrice;

    public Coffee(String name, int basePrice){
        this.name = name;
        this.basePrice = basePrice;
    }

    public String getName(){
        return name;
    }

    public int getBasePrice(){
        return basePrice;
    }
}
