package uhk.hausy.subsystem.core.communication;

/**
 * Created by tobou on 13.10.2016.
 */
public interface CommunicationListener {

    void connectionSuccess();
    void connectionFail();
    void connectionLost();
    void messageArrived(String s, byte[] content);
    void messageSuccess();
    void messageFail(String type, byte[] message);

}
