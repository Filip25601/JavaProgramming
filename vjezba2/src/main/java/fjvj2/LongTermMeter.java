package fjvj2;

import org.eclipse.paho.client.mqttv3.*;
import java.util.Random;

public class LongTermMeter extends Meter {
    private Random rand = new Random();

    public LongTermMeter (IMqttClient client) {
        super(client);
    }
    public int generateConsumption(){
        return rand.nextInt(65337);
    }
    @Override
    public void sendAllData()throws MqttException {
        int consumption1week = generateConsumption();
        int consumption1month = generateConsumption();
        int consumption1year = generateConsumption();

        System.out.println("Consumption in 1 week: " + consumption1week / 10 + " m³");
        System.out.println("Consumption in 1 month: " + consumption1month / 10.0 + " m³");
        System.out.println("Consumption in 1 year: " + consumption1year / 10.0 + " m³");

   publishData("waterflowmeter/consumption/1week", String.valueOf(consumption1week));
   publishData("waterflowmeter/consumption/1week", String.valueOf(consumption1week));
   publishData("waterflowmeter/consumption/1week", String.valueOf(consumption1week));

    }

}
