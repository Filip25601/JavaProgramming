package fjvj2;
import org.eclipse.paho.client.mqttv3.*;
import java.util.Random;

public class Sensor {
    private IMqttClient client;
    private Random random;
    private double minValue, maxValue;
    private String unit;
    private String sensorName;

    public Sensor(IMqttClient client,String sensorName ,double minValue, double maxValue, String unit) {
        this.client = client;
        this.minValue = minValue;
        this.maxValue = maxValue;
        this.unit = unit;
        this.sensorName = sensorName;
        this.random = new Random();
    }
    /*
    public Sensor(IMqttClient client) {
        this.client = client;
        this.random = new Random();
    }*/
    public double generateValue(){
        return minValue + random.nextDouble(maxValue - minValue + 1);
    }

    private void publishData(String topic, String data)throws MqttException {
        MqttMessage message = new MqttMessage(data.getBytes());
        message.setQos(0);
        client.publish(topic, message);
    }

    public void sendData(String topic)throws MqttException{
        double Value = generateValue();
        System.out.println(sensorName + ": " + Value + " " + unit);
        publishData(topic,String.valueOf(Value));
    }
}
