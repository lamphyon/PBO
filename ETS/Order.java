public class Order{
    private Coffee coffee;
    private String size;
    private boolean addSugar;
    private boolean addMilk;

    public Order(Coffee coffee, String size, boolean addSugar, boolean addMilk){
        this.coffee = coffee;
        this.size = size;
        this.addSugar = addSugar;
        this.addMilk = addMilk;
    }

    public double calculatePrice(){
        double kali = 1.0;
        if(size.equalsIgnoreCase("M")){
            kali = 1.5;
        }
        else if (size.equalsIgnoreCase("L")){
            kali = 2.0;
        }

        double price = coffee.getBasePrice() * kali;
        if(addSugar) price += 2500;
        if(addMilk) price += 3500;
        return price;
    }

    public double[] getIngredients(){
        double coffee = 1;
        double sugar = 0;
        double milk = 0;
        double water = 2;

        if(size.equalsIgnoreCase("M")){
            coffee *= 1.5;
            water *= 1.5;
        } 
        else if(size.equalsIgnoreCase("L")){
            coffee *= 2;
            water *= 2;
        }

        if(addSugar) sugar += 1;
        if(addMilk) milk += 1;

        return new double[]{coffee, sugar, milk, water};
    }

    public String getDescription(){
        return size.toUpperCase() + " " + coffee.getName() + (addSugar ? " + gula" : "") + (addMilk ? " + susu" : "");
    }
}
