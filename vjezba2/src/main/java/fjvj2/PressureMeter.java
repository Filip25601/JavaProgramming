package fjvj2;

import org.eclipse.paho.client.mqttv3.*;
import java.util.Random;

public class PressureMeter extends Meter {
    private final Random rand = new Random();
    public PressureMeter(IMqttClient client) {
        super(client);
    }
    public double generatePressure() {
        return rand.nextInt(65336)/1000.0;
    }
    @Override
    public void sendAllData()throws MqttException {
        double pressure = generatePressure();
        System.out.println("Pressure: "+ pressure+ " Bar");
        publishData("WaterFlowMeter/Pressure", String.valueOf(pressure));
    }



}
