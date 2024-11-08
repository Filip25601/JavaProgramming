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
        short Temperature = (short) (generateTemperature() / 10);
        System.out.println("Temperature: " + Temperature + " °C");
        publishData("WaterFlowMeter/temperature", String.valueOf(Temperature));
    }

}
