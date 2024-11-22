package test;
import fjvj2.Sensor;
import org.eclipse.paho.client.mqttv3.*;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


public class SensorTest {

    @Test
    public void test() throws MqttException {
        String brokerUrl = "tcp://broker.hivemq.com:1883";
        String clientId = MqttClient.generateClientId();
        IMqttClient client = new MqttClient(brokerUrl, clientId);
        client.connect();
        Sensor temperatureSensor = new Sensor(client, "TemperatureSensor",-3266.8, 3266.8, "°C");
        double value = temperatureSensor.generateValue();
        assertTrue(value>=-3266.8 && value<=3266.8, "Value should be between -3266.8 and 3266.8");
        client.disconnect();
    }


}
