package uhk.hausy.subsystem.core.model;

/**
 * Created by tobou on 07.11.2016.
 */
public class Device {

    private int id;

    //
    private String name;

    private Channel channel;

    public static String getTableSql() {
        String sql = "CREATE TABLE IF NOT EXISTS Device " +
                "(ID INT PRIMARY KEY                    NOT NULL," +
                " CHANNEL_ID                CHAR(20)    NOT NULL," +
                " NAME                      CHAR(20)    NOT NULL);";
        return sql;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Channel getChannel() {
        return channel;
    }

    public void setChannel(Channel channel) {
        this.channel = channel;
    }
}
