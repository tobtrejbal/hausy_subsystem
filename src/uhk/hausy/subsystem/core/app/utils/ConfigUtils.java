package uhk.hausy.subsystem.core.app.utils;

import org.json.JSONObject;
import uhk.hausy.subsystem.core.app.AppConfig;

import java.io.*;

/**
 * Created by admin on 01.02.2017.
 */
public class ConfigUtils {

    static final String configFilePath = "/home/ubuntu/hausy-subsystem/config.json";

    public static void saveConfig() {

        AppConfig appConfig = AppConfig.getInstance();

        FileOutputStream fop = null;
        File file;

        try {

            JSONObject jsonObject = new JSONObject();
            jsonObject.put("subsystem_id",appConfig.getSubsystemId());
            jsonObject.put("subsystem_address",appConfig.getSubsystem_address());
            jsonObject.put("server_address",appConfig.getServer_address());

            //file = new File("/home/ubuntu/hausy-subsystem/config.json");
            file = new File(configFilePath);
            fop = new FileOutputStream(file);

            // if file doesnt exists, then create it
            if (!file.exists()) {
                file.createNewFile();
            }

            // get the content in bytes
            byte[] contentInBytes = jsonObject.toString().getBytes();

            fop.write(contentInBytes);
            fop.flush();
            fop.close();

            System.out.println("Done");

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (fop != null) {
                    fop.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    public static void loadConfig() {

        System.out.println("loading config");
        AppConfig appConfig = AppConfig.getInstance();

        try {
            BufferedReader br = new BufferedReader(new FileReader(configFilePath));
            try {
                StringBuilder sb = new StringBuilder();
                String line = br.readLine();

                while (line != null) {
                    sb.append(line);
                    sb.append(System.lineSeparator());
                    line = br.readLine();
                }
                String everything = sb.toString();

                JSONObject jsonObject = new JSONObject(everything);
                appConfig.setSubsystemId(jsonObject.getInt("subsystem_id"));
                appConfig.setServer_address(jsonObject.getString("server_address"));
                appConfig.setSubsystem_address(jsonObject.getString("subsystem_address"));

            } finally {
                br.close();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }

}
