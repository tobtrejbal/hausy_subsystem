package uhk.hausy.subsystem.core.model;

/**
 * Created by admin on 31.01.2017.
 */
public class ChannelData {

    int id;
    Channel channel;
    int channelValue;
    long timeStamp;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getChannelValue() {
        return channelValue;
    }

    public void setChannelValue(int channelValue) {
        this.channelValue = channelValue;
    }

    public long getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(long timeStamp) {
        this.timeStamp = timeStamp;
    }

    public Channel getChannel() {
        return channel;
    }

    public void setChannel(Channel channel) {
        this.channel = channel;
    }
}
