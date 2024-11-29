package fjvj3;
import org.eclipse.paho.client.mqttv3.*;

import java.util.ArrayList;
import java.util.List;

public class Device {
    private List<Sensor> Sensors;
    private transient IMqttClient mqttClient;
    private String BrokerUrl;
    private String DeviceName;

    public Device(String DeviceName,String BrokerUrl) {
        this.DeviceName = DeviceName;
        this.BrokerUrl = BrokerUrl;
        Sensors = new ArrayList<Sensor>();

            try {
                mqttClient = new MqttClient(BrokerUrl, DeviceName);
            } catch (MqttException e) {
                System.out.println("Error while creating MqttClient: " + e.getMessage());
            }
    }

    public void addSensor(Sensor sensor) {
        Sensors.add(sensor);
    }

    public void sendData() throws MqttException {
        mqttClient.connect();
        for (Sensor sensor : Sensors) {
            sensor.generateValue();
            String message = sensor.SensorData();
            MqttMessage mqttMessage = new MqttMessage(message.getBytes());
            mqttMessage.setQos(1);
            mqttClient.publish(DeviceName, mqttMessage);
            System.out.println("Message published");
        }
        mqttClient.disconnect();
        mqttClient.close();
    }
    public String listSensors() {
        if (Sensors.isEmpty()) {
            return "No sensors available in this device.";
        }

        StringBuilder sensorDetails = new StringBuilder("Sensors in device \"" + DeviceName + "\":\n");
        for (Sensor sensor : Sensors) {
            sensorDetails.append(" - Name: ").append(sensor.getSensorName())
                    .append(", Min Value: ").append(sensor.getMinValue())
                    .append(", Max Value: ").append(sensor.getMaxValue())
                    .append(", Value: ").append(sensor.getValue())
                    .append(", Unit: ").append(sensor.getUnit()).append("\n");
        }
        return sensorDetails.toString();
    }
}