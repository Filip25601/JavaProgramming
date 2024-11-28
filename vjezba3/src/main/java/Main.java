import fjvj3.*;
import com.google.gson.*;

public class Main {
    public static void main(String[] args) throws Exception {

        Sensor temperatureSensor = new Sensor("TemperatureSensor",-3266.8, 3266.8, "°C");
        Sensor pressureSensor = new Sensor( "PressureSensor",0, 65.336, "Bar");
        Sensor shortTermSensor = new Sensor( "ShortTermSensor",0, 65336, "L");
        Sensor longTermSensor = new Sensor( "LongTermSensor",0, 6533.6, "m³");
        Device device = new Device("Device","tcp://broker.hivemq.com:1883");
        device.addSensor(temperatureSensor);
        device.addSensor(pressureSensor);
        device.addSensor(shortTermSensor);
        device.addSensor(longTermSensor);
        device.sendData();
        /*
        Gson gson = new Gson();
        String json = gson.toJson(device);
        System.out.println(json);
        */

        String jsonPath ="src/main/resources/serialised.json";

        Device device1 = Deserialization.loadDeviceConfig(jsonPath);
        System.out.println(device1.listSensors());
    }
}
