package uhk.hausy.subsystem.core.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tobou on 05.11.2016.
 */
public class GroupRule {

    private int id;

    private String name;

    private boolean active;

    private List<Rule> rules;

    public static String getTableSql() {
        String sql = "CREATE TABLE IF NOT EXISTS GroupRule " +
                "(ID INTEGER PRIMARY KEY                NOT NULL," +
                " ACTIVE                    BOOLEAN     NOT NULL," +
                " NAME                      CHAR(20)    NOT NULL);";
        return sql;
    }

    public List<Rule> findActiveRules(){
        List<Rule> candidates = new ArrayList<>();

        rules.stream().filter((rule) -> (rule.isActive())).forEach((rule) -> {
            candidates.add(rule);
        });
        return candidates;
    }

    public void activateGroup(){
        this.active = true;
    }
    public void deactivateGroup(){
        this.active = false;
    }

    public void addRule(Rule r){
        if(rules == null){
            rules = new ArrayList<>();
        }
        rules.add(r);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public List<Rule> getRules() {
        return rules;
    }

    public void setRules(List<Rule> rules) {
        this.rules = rules;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
