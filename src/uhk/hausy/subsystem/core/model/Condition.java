package uhk.hausy.subsystem.core.model;

import uhk.hausy.subsystem.core.rule.Evaluer;

/**
 * Created by tobou on 05.11.2016.
 */
public class Condition {

    private int id;
    private String name;
    private byte[] operators;
    private Rule rule;
    private Device device;
    private int[] values;

    public static String getTableSql() {
        String sql = "CREATE TABLE IF NOT EXISTS Condition " +
                "(ID INTEGER PRIMARY KEY                NOT NULL," +
                " RULE_ID                   INTEGER     NOT NULL," +
                " DEVICE_ID                 INTEGER     NOT NULL," +
                " OPERATOR                  BLOB        NOT NULL," +
                " NAME                      CHAR(20)    NOT NULL," +
                " CONDITION_VALUES          BLOB        NOT NULL);";
        return sql;
    }


    public boolean evaluate() {
        //System.out.println("uhk.hausy.subsystem.core.model.Condition.evalueate()");
        //boolean res = Evaluer.compareValues( device.getChannel().getData(), new int[]{1}, getOperators());
        boolean res = Evaluer.compareValues( device.getChannel().getData(), getValues(), getOperators());

        return res;
    }

    @Override
    public String toString() {
        return "Condition{" + "ID=" + id +" name="+ name + '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public byte[] getOperators() {
        return operators;
    }

    public void setOperators(byte[] operators) {
        this.operators = operators;
    }

    public Rule getRule() {
        return rule;
    }

    public void setRule(Rule rule) {
        this.rule = rule;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
}
