import fjvj2.*;

import org.eclipse.paho.client.mqttv3.*;

public class Main {
    public static void main(String[] args) throws Exception {
        String broker = "tcp://broker.hivemq.com:1883";
        String clientId = MqttClient.generateClientId();

        IMqttClient client = new MqttClient(broker, clientId);
        client.connect();

        TemperatureMeter temperatureMeter = new TemperatureMeter(client);
        PressureMeter pressureMeter = new PressureMeter(client);
        ShortTermMeter shortTermMeter = new ShortTermMeter(client);
        LongTermMeter longTermMeter = new LongTermMeter(client);

        temperatureMeter.sendAllData();
        pressureMeter.sendAllData();
        shortTermMeter.sendAllData();
        longTermMeter.sendAllData();
        System.out.println("Data published.");

        client.disconnect();
    }
}
