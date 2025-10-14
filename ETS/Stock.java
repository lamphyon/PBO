public class Stock{
    private double coffee;
    private double sugar;
    private double milk;
    private double water;

    public Stock(){
        this.coffee = 10;
        this.sugar = 8;
        this.milk = 7;
        this.water = 20;
    }

    public boolean isEnough(double needCoffee, double needSugar, double needMilk, double needWater){
        return(coffee >= needCoffee && sugar >= needSugar && milk >= needMilk && water >= needWater);
    }

    public void useIngredients(double needCoffee, double needSugar, double needMilk, double needWater){
        coffee -= needCoffee;
        sugar -= needSugar;
        milk -= needMilk;
        water -= needWater;
    }
}
