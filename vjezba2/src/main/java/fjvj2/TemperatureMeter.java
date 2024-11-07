package fjvj2;
import org.eclipse.paho.client.mqttv3.*;
import java.util.Random;

public class TemperatureMeter extends Meter {
    private final Random random = new Random();

    public TemperatureMeter(IMqttClient client) {
        super(client);
    }

    public short generateTemperature() {
        return (short) (-32668 + random.nextInt(65336));
    }
    @Override
    public void sendAllData() throws MqttException {
        short rawTemperature = generateTemperature();
        double actualTemperature = rawTemperature /10.0;

        System.out.println("Temperature: " + actualTemperature + " °C");
        publishData("waterFlowMeter/temperature", String.valueOf(actualTemperature));
    }

}
