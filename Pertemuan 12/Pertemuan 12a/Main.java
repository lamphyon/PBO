public class Main {
    public static void main(String[] args) {
        LivingBeing human = new Human("Agung");
        LivingBeing animal = new Animal("Kucing");
        LivingBeing plant = new Plant("Anggrek");

        human.breathe();
        human.grow();

        animal.breathe();
        animal.grow();

        plant.breathe();
        plant.grow();
        
        ((Human) human).speak();
        ((Animal) animal).move();
        ((Plant) plant).photosynthesize();
    }
}