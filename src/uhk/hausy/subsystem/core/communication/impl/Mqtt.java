package uhk.hausy.subsystem.core.communication.impl;

import org.eclipse.paho.client.mqttv3.*;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;
import uhk.hausy.subsystem.core.communication.CommunicationListener;
import uhk.hausy.subsystem.core.communication.CommunicationManager;

/**
 * Created by tobou on 11.10.2016.
 */
public class Mqtt implements MqttCallback, CommunicationManager {

    int qos             = 0;
    String broker       ;

    String[] topics;
    int[] quos;

    MqttAsyncClient client;
    //MqttClient sender;
    //MqttClient receiver;

    CommunicationListener communicationListener;

    MemoryPersistence persistence;

    MqttConnectOptions connOpts = new MqttConnectOptions();

    public Mqtt(CommunicationListener communicationListener, String address, String clientId, String[] topics, int[] quos) {
        try {
            this.communicationListener = communicationListener;
            this.persistence = new MemoryPersistence();
            this.broker = address;
            this.client = new MqttAsyncClient(address, clientId, persistence);
            System.out.println(broker);
            System.out.println(clientId);
            this.connOpts = new MqttConnectOptions();
            this.client.setCallback(this);
            this.topics = topics;
            this.quos = quos;
        } catch (MqttException me) {
            System.out.println("reason "+me.getReasonCode());
            System.out.println("msg "+me.getMessage());
            System.out.println("loc "+me.getLocalizedMessage());
            System.out.println("cause "+me.getCause());
            System.out.println("excep "+me);
            me.printStackTrace();
        }
    }

    public void start() {
        connOpts.setCleanSession(true);
    }

    @Override
    public void connectionLost(Throwable throwable) {
        communicationListener.connectionLost();
    }

    @Override
    public void messageArrived(String s, MqttMessage mqttMessage) throws Exception {
        communicationListener.messageArrived(s, mqttMessage.getPayload());
    }

    @Override
    public void deliveryComplete(IMqttDeliveryToken iMqttDeliveryToken) {
        communicationListener.messageSuccess();
    }

    public void connect() {
        if (!client.isConnected()) {
            System.out.println("Connecting to broker: " + broker);
            connOpts.setCleanSession(true);
            try {
                client.connect(connOpts, null, new IMqttActionListener() {

                    public void onSuccess(IMqttToken iMqttToken) {
                        try {
                            client.subscribe(topics, quos);
                            communicationListener.connectionSuccess();
                        } catch (MqttException e) {
                            communicationListener.connectionFail();
                            e.printStackTrace();
                        }
                    }

                    public void onFailure(IMqttToken iMqttToken, Throwable throwable) {
                        try {
                            Thread.sleep(2000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        communicationListener.connectionFail();
                    }
                });
            } catch (MqttException e) {
                e.printStackTrace();
                communicationListener.connectionFail();
            }

        }
    }

    public void sendMessage(String type, byte[] content) {
        try {
            MqttMessage message = new MqttMessage(content);
            message.setQos(qos);
            client.publish(type, message);
            //System.out.println("odesláno:"+type);
        } catch (MqttException ex) {
            communicationListener.messageFail(type, content);
            ex.printStackTrace();
        }
    }

    public void disconnect() {
        try {
            client.disconnect();
        } catch (MqttException ex) {
            ex.printStackTrace();
        }
    }
}