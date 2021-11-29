package uhk.hausy.subsystem.core;

import uhk.hausy.subsystem.core.app.AppConfig;
import uhk.hausy.subsystem.core.app.utils.ConfigUtils;
import uhk.hausy.subsystem.core.communication.CommunicationListener;
import uhk.hausy.subsystem.core.communication.CommunicationManager;
import uhk.hausy.subsystem.core.communication.impl.Mqtt;
import uhk.hausy.subsystem.core.constants.MQTTConst;
import uhk.hausy.subsystem.core.database.base.DbsManager;
import uhk.hausy.subsystem.core.flatBuffer.transformer.TransformerServer;
import uhk.hausy.subsystem.core.flatBuffer.transformer.TransformerSubsystem;
import uhk.hausy.subsystem.core.model.Device;
import uhk.hausy.subsystem.core.model.GroupRule;
import uhk.hausy.subsystem.core.model.Node;
import uhk.hausy.subsystem.core.service.DataService;
import uhk.hausy.subsystem.core.service.ModelService;
import uhk.hausy.subsystem.core.service.impl.DataServiceImpl;
import uhk.hausy.subsystem.core.service.impl.ModelServiceImpl;

import java.util.*;

/**
 * Created by tobou on 13.10.2016.
 */
public class HausyCore {

    private static HausyCore sInstance;

    private boolean running = false;

    public static HausyCore getInstance() {
        if(sInstance == null) {
            sInstance = new HausyCore();
        }
        return sInstance;
    }

    String[] subsystemTopics = {MQTTConst.LOG, MQTTConst.ERROR, MQTTConst.GET, MQTTConst.CONFIGURATION_REQUEST, MQTTConst.ADDRESSES_REQUEST, MQTTConst.PING_CONTROLLER};
    String[] serverTopics = {MQTTConst.CONFIGURATION_RESPONSE_SERVER, MQTTConst.DISCOVERY_REQUEST_SERVER, MQTTConst.NEW_DEVICES, MQTTConst.NEW_RULES,MQTTConst.PING_SERVER };
    int[] subsystemQoS = new int[] {0,0,0,0,0,0};
    int[] serverQoS = new int[] {0,0,0,0,0};

    Map<Short, Node> nodes;
    Map<Integer, Device> devices;
    List<GroupRule> groupRules;

    boolean onlineServer = false;
    boolean onlineController = false;

    boolean onlineLocalMQTT = false;
    boolean onlineServerMQTT = false;

    final static int PING_FREQUENCY = 2000;

    public static void main(String[] args) {
        new HausyCore();
    }

    /**
     * Manager for communication in subsystem area - in raspberry only.
     */
    CommunicationManager communicationManagerSubsystem;

    /**
     * Manager for communication between subsystem and server.
     */
    CommunicationManager communicationManagerServer;

    /**
     * Service for model stuff ???.
      */
    ModelService modelService;

    /**
     * Service for model stuff ???.
     */
    DbsManager dbsManager;

    /**
     * Service for data processing (values from nodes).
     */
    DataService dataService;

    long serverLastPing;
    long controllerLastPing;

    long lastTime = System.currentTimeMillis();
    int counter = 0;

    /**
     * Listener for MQTT communication in subsystem area - in raspberry only.
     */
    CommunicationListener commListenerSubsystem = new CommunicationListener() {

        @Override
        public void connectionSuccess() {
            onlineLocalMQTT = true;
        }

        @Override
        public void connectionFail() {
            onlineLocalMQTT = false;
            communicationManagerSubsystem.connect();
        }

        @Override
        public void connectionLost() {
            onlineLocalMQTT = false;
            communicationManagerSubsystem.connect();
        }

        @Override
        public void messageArrived(String s, byte[] content) {
            try {
                if(s.equals(MQTTConst.PING_CONTROLLER)) {
                    controllerLastPing = System.currentTimeMillis();
                }
                if(s.equals(MQTTConst.LOG)) {
                    communicationManagerServer.sendMessage(MQTTConst.LOG_SERVER, content);
                }
                if(s.equals(MQTTConst.ERROR)) {
                    processError(content);
                }
                if(s.equals(MQTTConst.GET)) {
                    processData(content);
                }
                if(s.equals(MQTTConst.CONFIGURATION_REQUEST)) {
                    configurationRequest(content);
                }
                if(s.equals(MQTTConst.ADDRESSES_REQUEST)) {
                    assignAddresses(content);
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }

        @Override
        public void messageSuccess() {

        }

        @Override
        public void messageFail(String type, byte[] message) {

        }
    };

    /**
     * Listener for MQTT communication between server and subsystem.
     */
    CommunicationListener commListenerServer = new CommunicationListener() {

        @Override
        public void connectionSuccess() {
            onlineServerMQTT = true;
        }

        @Override
        public void connectionFail() {
            onlineServerMQTT = false;
            communicationManagerServer.connect();
        }

        @Override
        public void connectionLost() {
            onlineServerMQTT = false;
            communicationManagerServer.connect();
        }

        @Override
        public void messageArrived(String s, byte[] content) {
            try{
                if(s.equals(MQTTConst.PING_SERVER)) {
                    serverLastPing = System.currentTimeMillis();
                }
                if(s.equals(MQTTConst.CONFIGURATION_RESPONSE_SERVER)) {
                    configurationResponse(content);
                }
                if(s.equals(MQTTConst.DISCOVERY_REQUEST_SERVER)) {
                    if(onlineLocalMQTT) {
                        communicationManagerSubsystem.sendMessage(MQTTConst.DISCOVERY_REQUEST,new byte[0]);
                    }
                }
                if(s.equals(MQTTConst.NEW_DEVICES)) {
                    modelService.saveDevices(nodes, devices, content);
                }
                if(s.equals(MQTTConst.NEW_RULES)) {
                    modelService.updateRules(groupRules, nodes, content);
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
         }

        @Override
        public void messageSuccess() {

        }

        @Override
        public void messageFail(String type, byte[] message) {

        }
    };

    /**
     * Constructor
     */
    private HausyCore() {
        ConfigUtils.loadConfig();
        DbsManager.createInstance();
        this.dbsManager = DbsManager.getInstance();
        this.modelService = new ModelServiceImpl();
        this.dataService = new DataServiceImpl();
        this.communicationManagerSubsystem = new Mqtt(commListenerSubsystem, AppConfig.getInstance().getSubsystem_address(), "subsystem"+AppConfig.getInstance().getSubsystemId()+"outer", subsystemTopics, subsystemQoS);
        this.communicationManagerServer = new Mqtt(commListenerServer, AppConfig.getInstance().getServer_address(), "subsystem"+AppConfig.getInstance().getSubsystemId()+"inner", serverTopics, serverQoS);
        this.nodes = new HashMap<>();
        this.devices = new HashMap<>();
        this.groupRules = new ArrayList<>();
    }

    public void start() {
        if(!running) {
            dbsManager.connect();
            dbsManager.createDatabase();
            modelService.getConfig(nodes, devices, groupRules);

            Runnable checkConnections = () -> {
                while (true) {
                    checkConnection();
                }
            };
            Thread pingThread = new Thread(checkConnections);
            pingThread.start();

            communicationManagerSubsystem.connect();
            communicationManagerServer.connect();
            running = true;
        }
    }

    public void checkConnection() {
        try {
            System.out.print("checking connection......");
            long currentTime = System.currentTimeMillis();
            if(onlineServerMQTT) {
                communicationManagerServer.sendMessage(MQTTConst.PING_SUBSYSTEM, TransformerServer.objectsToPingFB());
                if((currentTime - serverLastPing) < PING_FREQUENCY*2) {
                    onlineServer = true;
                } else {
                    onlineServer = false;
                }
                System.out.print("server...");
                System.out.print(onlineServer);
            } else {
                System.out.print("server MQTT offline...");
            }
            if(onlineLocalMQTT) {
                communicationManagerSubsystem.sendMessage(MQTTConst.PING_CONTROLLER, new byte[0]);
                if((currentTime - controllerLastPing) < PING_FREQUENCY*2) {
                    onlineController = true;
                } else {
                    onlineController = false;
                }
                System.out.print("...controller...");
                System.out.print(onlineController);
            } else {
                System.out.print("local MQTT offline");
            }
            System.out.println();
            Thread.sleep(PING_FREQUENCY);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    /**
     * Process data from one node.
     * @param bytes message content in flatbuffers.
     */
    public void processData(byte[] bytes) {
        counter++;
        if(counter > 1000) {
            long currentTime = System.currentTimeMillis();
            System.out.println(currentTime-lastTime);
            lastTime = currentTime;
            counter = 0;
        }
        Node node = nodes.get(dataService.processData(nodes, bytes));
        if(onlineServer) {
            communicationManagerServer.sendMessage(MQTTConst.GET_SERVER, dataService.transformData(bytes));
        } else {
            for(byte[] message : dataService.setMessages(node, nodes, groupRules)) {
                //    communicationManagerSubsystem.sendMessage(MQTTConst.SET, message);
            }
        }
    }

    /**
     * ProcessError
     */
    public void processError(byte[] bytes) {
        if(onlineServer) {
            communicationManagerServer.sendMessage(MQTTConst.ERROR_SERVER,bytes);
        } else {

        }
    }

    /**
     * Assign addresses
     */
    public void assignAddresses(byte[] bytes) {
        List<Node> newNodes = modelService.assignAddresses(nodes, bytes);
        if(onlineLocalMQTT) {
            communicationManagerSubsystem.sendMessage(MQTTConst.NEW_ADDRESSES, TransformerSubsystem.objectsToNodesFB(newNodes));
        }
        if(onlineServerMQTT) {
            communicationManagerServer.sendMessage(MQTTConst.DEVICES_REQUEST, TransformerServer.objectsToNodesFB(newNodes));
        }
    }


    /**
     * Process communication request from c++ layer.
     * @param bytes message content in flatbuffers.
     */
    public void configurationRequest(byte[] bytes) {
        System.out.println("config request");
        if(onlineServerMQTT && onlineServer) {
            communicationManagerServer.sendMessage(MQTTConst.CONFIGURATION_REQUEST_SERVER,TransformerServer.objectsToConfigurationRequestFB());
        } else {
            communicationManagerSubsystem.sendMessage(MQTTConst.CONFIGURATION_RESPONSE, modelService.getConfig(nodes, devices, groupRules));
        }
    }

    /**
     * Process communication request from c++ layer.
     * @param bytes message content in flatbuffers.
     */
    public void configurationResponse(byte[] bytes) {
        if(TransformerServer.isMyConfig(bytes)) {
            if(onlineLocalMQTT) {
                modelService.clearDbs();
                modelService.saveConfig(nodes, devices, groupRules, bytes);
                communicationManagerSubsystem.sendMessage(MQTTConst.CONFIGURATION_RESPONSE, modelService.getConfig(nodes, devices, groupRules));
            }
        }
    }
}
