package fjvj2;
import org.eclipse.paho.client.mqttv3.*;
import java.util.Random;

public class Sensor {
    private IMqttClient client;
    private Random random;
    private String unit;

    public Sensor(IMqttClient client) {
        this.client = client;
        this.random = new Random();
    }
    public int generateTemperature() {
        unit = "°C";
        return (-32668 + random.nextInt(65336));
    }
    public int generatePressure() {
        unit = "Bar";
        return random.nextInt(65535);
    }
    public int generateConsumptionShorter(){
        unit = "L";
        return random.nextInt(65535);
    }
    public int generateConsumptionLonger(){
        unit = "m³";
        return random.nextInt(65535);
    }
    private void publishData(String topic, String data)throws MqttException {
        MqttMessage message = new MqttMessage(data.getBytes());
        message.setQos(0);
        client.publish(topic, message);
    }

    public void sendData()throws MqttException{
        int temperature = generateTemperature() / 10;
        System.out.println("Temperature: " + temperature + " " + unit);
        publishData("Temperature", String.valueOf(temperature));

        int Pressure = generatePressure() / 1000;
        System.out.println("Pressure: " + Pressure + " " + unit);
        publishData("Pressure", String.valueOf(Pressure));

        int consumption1min = generateConsumptionShorter();
        int consumption10min = generateConsumptionShorter();
        int consumption1hour = generateConsumptionShorter();
        int consumption1day = generateConsumptionShorter();

        System.out.println("consumption1min: " + consumption1min + " " + unit);
        System.out.println("consumption10min: " + consumption10min + " " + unit);
        System.out.println("consumption1hour: " + consumption1hour + " " + unit);
        System.out.println("consumption1day: " + consumption1day + " " + unit);
        publishData("consumption1min", String.valueOf(consumption1min));
        publishData("consumption10min", String.valueOf(consumption10min));
        publishData("consumption1hour", String.valueOf(consumption1hour));
        publishData("consumption1day", String.valueOf(consumption1day));

        int consumption1week = generateConsumptionLonger() / 10;
        int consumption1month = generateConsumptionLonger() / 10;
        int consumption1year = generateConsumptionLonger() / 10;
        System.out.println("consumption1week: " + consumption1week + " " + unit);
        System.out.println("consumption1month: " + consumption1month + " " + unit);
        System.out.println("consumption1year: " + consumption1year + " " + unit);
    }

}
