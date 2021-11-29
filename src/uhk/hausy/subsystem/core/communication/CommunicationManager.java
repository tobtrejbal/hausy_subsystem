package uhk.hausy.subsystem.core.communication;

/**
 * Created by tobou on 13.10.2016.
 */
public interface CommunicationManager {

    void connect();
    void disconnect();
    void sendMessage(String type, byte[] content);
}
