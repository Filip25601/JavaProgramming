package fjvj3;

import com.google.gson.Gson;
import java.io.FileReader;

public class Deserialization {
    public static Device loadDevice(String filePath) {
        Gson gson = new Gson();
        try (FileReader reader = new FileReader(filePath)) {
           return gson.fromJson(reader, Device.class);
        }catch (Exception e){
            System.err.println("Json error: " + e.getMessage());
        }
    return null;}
}
