import java.util.Random;

public class TemperatureDisplay {
    private Random random = new Random(); // mendapatkan angka random

    public String getTemperature() { // mereturn angka random dari 25 hingga 38
        return 25 + random.nextInt(13) + "°C";
    }
}
