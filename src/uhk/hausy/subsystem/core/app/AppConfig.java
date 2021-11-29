package uhk.hausy.subsystem.core.app;

/**
 * Created by admin on 30.01.2017.
 */
public class AppConfig {

    private static AppConfig sInstance;

    public static AppConfig getInstance() {
        if(sInstance == null) {
            sInstance = new AppConfig();
        }
        return sInstance;
    }

    private String server_address;

    private String subsystem_address;

    private int subsystemId = 50;

    public int getSubsystemId() {
        return subsystemId;
    }

    public void setSubsystemId(int subsystemId) {
        this.subsystemId = subsystemId;
    }

    public String getServer_address() {
        return server_address;
    }

    public void setServer_address(String server_address) {
        this.server_address = server_address;
    }

    public String getSubsystem_address() {
        return subsystem_address;
    }

    public void setSubsystem_address(String subsystem_address) {
        this.subsystem_address = subsystem_address;
    }
}
