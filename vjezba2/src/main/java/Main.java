import fjvj2.Sensor;
import org.eclipse.paho.client.mqttv3.*;


public class Main {
    public static void main(String[] args) throws Exception {
        String brokerUrl = "tcp://broker.hivemq.com:1883";
        String clientId = MqttClient.generateClientId();

        IMqttClient client = new MqttClient(brokerUrl, clientId);
        client.connect();
//    public Sensor(IMqttClient client,String sensorName ,int minValue, int maxValue, String unit) {
        Sensor temperatureSensor = new Sensor(client, "temperatureSensor",-3266.8, 3266.8, "°C");
        Sensor pressureSensor = new Sensor(client, "temperatureSensor",0, 65.336, "Bar");
        Sensor shortTermConsumptionSensor = new Sensor(client, "temperatureSensor",0, 6533.6, "L");
        Sensor longTermConsumptionSensor = new Sensor(client, "temperatureSensor",0, 6533.6, "m³");

        temperatureSensor.sendData("Temperature");
        pressureSensor.sendData("Pressure");
        shortTermConsumptionSensor.sendData("ShortTermConsumption");
        longTermConsumptionSensor.sendData("LongTermConsumption");
        
        client.disconnect();
    }
}
