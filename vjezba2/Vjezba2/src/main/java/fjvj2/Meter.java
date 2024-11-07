package fjvj2;

import org.eclipse.paho.client.mqttv3.*;

public abstract class Meter {

    protected IMqttClient mqttClient;

    public Meter (IMqttClient client){
        this.mqttClient = client;
    }

    public void publishData(String topic, String message)throws MqttException{
        MqttMessage mqttMessage = new MqttMessage(message.getBytes());
        mqttMessage.setQos(0);
        mqttClient.publish(topic, mqttMessage);
        }

    public abstract void sendAllData() throws MqttException;
}
