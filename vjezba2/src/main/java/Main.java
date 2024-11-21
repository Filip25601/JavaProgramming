import fjvj2.*;
import org.eclipse.paho.client.mqttv3.*;


public class Main {
    public static void main(String[] args) throws Exception {
        String brokerUrl = "tcp://broker.hivemq.com:1883";
        String clientId = MqttClient.generateClientId();

        IMqttClient client = new MqttClient(brokerUrl, clientId);
        client.connect();

        Sensor waterSensor = new Sensor(client);
        waterSensor.sendData();
        
        client.disconnect();
    }
}
