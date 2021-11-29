package uhk.hausy.subsystem.core.model;

/**
 * Created by tobou on 07.11.2016.
 */
public class Action {

    private int id;
    private String name;
    private Device device;
    private Rule rule;
    private int[] values;

    public static String getTableSql() {
        String sql = "CREATE TABLE IF NOT EXISTS Action " +
                "(ID INTEGER PRIMARY KEY                NOT NULL," +
                " RULE_ID                   INTEGER     NOT NULL," +
                " DEVICE_ID                 INTEGER     NOT NULL," +
                " NAME                      CHAR(20)    NOT NULL," +
                " ACTION_VALUES             BLOB        NOT NULL);";
        return sql;
    }

    @Override
    public String toString() {
        return "Action{" + "ID=" + id + '}';
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

    public Device getDevice() {
        return device;
    }

    public void setDevice(Device device) {
        this.device = device;
    }

    public int[] getValues() {
        return values;
    }

    public void setValues(int[] values) {
        this.values = values;
    }

    public Rule getRule() {
        return rule;
    }

    public void setRule(Rule rule) {
        this.rule = rule;
    }
}
