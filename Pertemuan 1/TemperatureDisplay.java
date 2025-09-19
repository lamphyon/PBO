import java.util.Random;

public class TemperatureDisplay {
    private Random random = new Random();

    public String getTemperature() {
        return 25 + random.nextInt(13) + "°C";
    }
}
