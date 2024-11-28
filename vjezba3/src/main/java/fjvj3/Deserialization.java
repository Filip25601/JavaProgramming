package fjvj3;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.eclipse.paho.client.mqttv3.IMqttClient;

import java.io.FileReader;
import java.util.List;
import java.io.IOException;

public class Deserialization {
    public static Device loadDeviceConfig(String filePath) {
        Gson gson = new Gson();
        try (FileReader reader = new FileReader(filePath)) {
           return gson.fromJson(reader, Device.class);
        }catch (Exception e){
            System.err.println("Json error: " + e.getMessage());
        }
    return null;}
}
