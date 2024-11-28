package fjvj3;
import java.util.Random;

public class Sensor {
    private String sensorName;
    private double minValue, maxValue,value;
    private String unit;
    private transient Random random = new Random();

    public Sensor(String sensorName, double minValue, double maxValue, String unit) {
        this.sensorName = sensorName;
        this.minValue = minValue;
        this.maxValue = maxValue;
        this.unit = unit;
    }

    public void generateValue(){
         value = minValue + random.nextDouble(maxValue - minValue + 1);
    }

    public String getSensorName() {
        return sensorName;
    }

    public String SensorData(){
        return "Device: " + sensorName + " value: "+ value + " unit: " + unit;
    }







    public double getMinValue() {
        return minValue;
    }

    public double getMaxValue() {
        return maxValue;
    }

    public double getValue() { return value; }

    public String getUnit() {
        return unit;
    }

}
