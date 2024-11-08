package fjvj2;

import org.eclipse.paho.client.mqttv3.IMqttClient;
import org.eclipse.paho.client.mqttv3.MqttException;

import java.util.Random;

//import java.util.Random;
public class ShortTermMeter extends Meter {
    private final Random rand = new Random();
    public ShortTermMeter(IMqttClient client) {
        super(client);
    }
    public int generateConsumption() {
        return rand.nextInt(65336);
    }
    @Override
    public void sendAllData() throws MqttException {
        int consumption1min = generateConsumption();

        int consumption10min = 0;
        for (int i = 0; i < 10; i++) {
            consumption10min += consumption1min;
        }
        int consumption1hour = 0;
        for (int i = 0; i < 6; i++) {
            consumption1hour += consumption10min;
        }
        int consumption1day = 0;
        for (int i = 0; i < 24; i++) {
            consumption1day += consumption1hour;
        }

        System.out.println("consumption1min: " + consumption1min + " L");
        System.out.println("consumption10min: " + consumption10min + " L");
        System.out.println("consumption1hour: " + consumption1hour + " L");
        System.out.println("consumption1day: " + consumption1day + " L");

        publishData("waterFlowMeter/consumption/1min", String.valueOf(consumption1min));
        publishData("waterFlowMeter/consumption/10min", String.valueOf(consumption10min));
        publishData("waterFlowMeter/consumption/1hour", String.valueOf(consumption1hour));
        publishData("waterFlowMeter/consumption/1day", String.valueOf(consumption1day));
    }

}
