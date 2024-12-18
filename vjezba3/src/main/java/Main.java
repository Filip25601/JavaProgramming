import fjvj3.*;

public class Main {
    public static void main(String[] args) throws Exception {

        Sensor temperatureSensor = new Sensor("TemperatureSensor",-3266.8, 3266.8, "°C");
        Sensor pressureSensor = new Sensor( "PressureSensor",0, 65.336, "Bar");
        Sensor shortTermSensor = new Sensor( "ShortTermSensor",0, 65336, "L");
        Sensor longTermSensor = new Sensor( "LongTermSensor",0, 6533.6, "m³");
        Device device = new Device("Device","tcp://test.mosquitto.org:1883");
        device.addSensor(temperatureSensor);
        device.addSensor(pressureSensor);
        device.addSensor(shortTermSensor);
        device.addSensor(longTermSensor);
        device.sendData();
        /*
        "tcp://broker.hivemq.com:1883"
        Gson gson = new Gson();
        String json = gson.toJson(device);
        System.out.println(json);
        */

        String jsonPath ="src/main/resources/serialised.json";
        Device device1 = Deserialization.loadDevice(jsonPath);
        assert device1 != null;
        System.out.println(device1.listSensors());
    }
}
