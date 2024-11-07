import fjvj2.*;
import org.eclipse.paho.client.mqttv3.*;


public class Main {
    public static void main(String[] args) throws Exception {
        String broker = "tcp://broker.hivemq.com:1883"; // URL MQTT broker-a
        String clientId = MqttClient.generateClientId();

        IMqttClient client = new MqttClient(broker, clientId);
        client.connect();

        TemperatureMeter temperatureMeter = new TemperatureMeter(client);
        PressureMeter pressureMeter = new PressureMeter(client);
        ShortTermMeter shortTermMeter = new ShortTermMeter(client);

        temperatureMeter.sendAllData();
        pressureMeter.sendAllData();
        shortTermMeter.sendAllData();
        System.out.println("Data published.");


        client.disconnect();
    }
}
