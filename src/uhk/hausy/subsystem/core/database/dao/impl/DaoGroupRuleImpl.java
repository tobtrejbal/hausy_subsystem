package uhk.hausy.subsystem.core.database.dao.impl;

import uhk.hausy.subsystem.core.database.base.DbsManager;
import uhk.hausy.subsystem.core.database.dao.DaoGroupRule;
import uhk.hausy.subsystem.core.model.*;
import uhk.hausy.subsystem.core.utils.Utils;

import java.sql.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Created by tobou on 05.11.2016.
 */
public class DaoGroupRuleImpl implements DaoGroupRule{

    private Connection connection;

    private DbsManager dbsManager = DbsManager.getInstance();

    @Override
    public void createOrUpdate(GroupRule entity) throws Exception {

    }

    @Override
    public void persist(GroupRule entity) throws Exception {

    }

    @Override
    public void remove(GroupRule entity) throws Exception {

    }

    @Override
    public GroupRule findById(Long id) throws Exception {
        return null;
    }

    @Override
    public List<GroupRule> list() throws Exception {

        System.out.println("CONDITIONS --------------------");

        connection = dbsManager.getConnection();

        String sqlCondition = "SELECT * FROM Condition c;";

        Statement stmtCondition = connection.createStatement();
        stmtCondition.execute(sqlCondition);
        ResultSet rsConditions = stmtCondition.getResultSet();
        while(rsConditions.next()) {
            System.out.print("id:"+rsConditions.getInt(1));
            System.out.print("rule_id:"+rsConditions.getInt(2));
            System.out.print("device_id:"+rsConditions.getInt(3));
            System.out.print("operator:"+rsConditions.getByte(4));
            System.out.print("name:"+rsConditions.getString(5));
           System.out.println();
        }

        System.out.println("ACTIONS --------------------");

        String sqlAction = "SELECT * FROM Action a;";

        Statement stmtAction = connection.createStatement();
        stmtAction.execute(sqlAction);
        ResultSet rsAction = stmtAction.getResultSet();
        while(rsAction.next()) {
            System.out.print("id:"+rsAction.getInt(1));
            System.out.print("rule_id:"+rsAction.getInt(2));
            System.out.print("device_id:"+rsAction.getInt(3));
            System.out.print("name:"+rsAction.getString(4));
            System.out.println();
        }

        System.out.println("NODES --------------------");

        String sqlNodes = "SELECT * FROM NOde n;";

        Statement stmtNodes = connection.createStatement();
        stmtNodes.execute(sqlNodes);
        ResultSet rsNodes = stmtNodes.getResultSet();
        while(rsNodes.next()) {
            System.out.print("id:"+rsNodes.getString(1));
            System.out.print("localID:"+rsNodes.getShort(2));
            System.out.print("nodeType:"+rsNodes.getByte(3));
            System.out.print("channelCount:"+rsNodes.getByte(4));
            System.out.print("macAddress:"+rsNodes.getString(5));
            System.out.println();
        }

        System.out.println("NODE TYPES --------------------");

        String sqlNodeTypes = "SELECT * FROM NodeType t;";

        Statement stmtNodeType = connection.createStatement();
        stmtNodeType.execute(sqlNodeTypes);
        ResultSet rsNodeType = stmtNodeType.getResultSet();
        while(rsNodeType.next()) {
            System.out.print("id:"+rsNodeType.getInt(1));
            System.out.print("dataRange:"+rsNodeType.getByte(2));
            System.out.print("dataCount:"+rsNodeType.getByte(3));
            System.out.print("latency:"+rsNodeType.getByte(4));
            System.out.print("accessType:"+rsNodeType.getByte(5));
            System.out.println();
        }
        return null;
    }

    @Override
    public void persistMultiple(List<GroupRule> groupRules) throws Exception {
        String sql = "REPLACE INTO GroupRule VALUES (?,?,?);";
        connection = dbsManager.getConnection();
        PreparedStatement prep = connection.prepareStatement(sql);
        for (GroupRule g : groupRules) {
            prep.setInt(1, g.getId());
            prep.setBoolean(2, g.isActive());
            prep.setString(3, g.getName());
            prep.addBatch();
            String sqlRule = "REPLACE INTO Rule VALUES (?,?,?,?,?);";
            PreparedStatement preparedStatementRule = connection.prepareStatement(sqlRule);
            for (Rule r : g.getRules()) {
                preparedStatementRule.setInt(1, r.getId());
                preparedStatementRule.setInt(2, r.getGroupRule().getId());
                preparedStatementRule.setBoolean(3, r.isActive());
                preparedStatementRule.setString(4, r.getOriginalExpression());
                preparedStatementRule.setString(5, r.getName());
                preparedStatementRule.addBatch();
                String sqlCondition = "REPLACE INTO Condition VALUES (?,?,?,?,?,?);";
                PreparedStatement preparedStatementCondition = connection.prepareStatement(sqlCondition);
                for (Condition c : r.getConditions()) {
                    preparedStatementCondition.setInt(1, c.getId());
                    preparedStatementCondition.setInt(2, c.getRule().getId());
                    preparedStatementCondition.setInt(3, c.getDevice().getId());
                    preparedStatementCondition.setBytes(4, c.getOperators());
                    preparedStatementCondition.setString(5, c.getName());
                    preparedStatementCondition.setBytes(6, Utils.intToByte(c.getValues()));
                    preparedStatementCondition.addBatch();
                    System.out.println("CONDITIOOON");
                    for(int i : c.getValues()) {
                        System.out.print(","+i);
                    }
                    byte[] array = Utils.intToByte(c.getValues());
                    System.out.println("CONDITION_REHAB");
                    for(int i : Utils.byteToInt(array)) {
                        System.out.print(","+i);
                    }
                }
                preparedStatementCondition.executeBatch();

                String sqlAction = "REPLACE INTO Action VALUES (?,?,?,?,?);";
                PreparedStatement preparedStatementAction = connection.prepareStatement(sqlAction);
                for (Action a : r.getActions()) {
                    preparedStatementAction.setInt(1, a.getId());
                    preparedStatementAction.setInt(2, a.getRule().getId());
                    preparedStatementAction.setInt(3, a.getDevice().getId());
                    preparedStatementAction.setString(4, a.getName());
                    preparedStatementAction.setBytes(5, Utils.intToByte(a.getValues()));
                    preparedStatementAction.addBatch();
                }
                preparedStatementAction.executeBatch();
            }
            preparedStatementRule.executeBatch();
        }
        int[] updateCounts = prep.executeBatch();
    }

    @Override
    public List<GroupRule> list(Map<Short, Node> nodes) throws Exception {
        connection = dbsManager.getConnection();
        String sql = "SELECT * FROM GroupRule";
        Statement stmt = connection.createStatement();
        stmt.execute(sql);
        ResultSet rs = stmt.getResultSet();
        List<GroupRule> groupRules = new ArrayList<>();
        while(rs.next()) {
            GroupRule groupRule = new GroupRule();
            groupRule.setId(rs.getInt(1));
            groupRule.setActive(rs.getBoolean(2));
            groupRule.setName(rs.getString(3));

            String sqlRules = "SELECT * FROM Rule r WHERE  r.group_rule_id = "+groupRule.getId()+";";
            Statement stmtRules = connection.createStatement();
            stmtRules.execute(sqlRules);
            List<Rule> rules = new ArrayList<>();
            ResultSet rsRules = stmtRules.getResultSet();
            while(rsRules.next()) {
                Rule rule = new Rule();
                rule.setId(rsRules.getInt(1));
                rule.setGroupRule(groupRule);
                rule.setActive(rsRules.getBoolean(3));
                rule.setOriginalExpression(rsRules.getString(4));
                rule.setName(rsRules.getString(5));
                rules.add(rule);

                String sqlCondition = "SELECT * FROM Condition c WHERE  c.rule_id = "+rule.getId()+";";
                Statement stmtCondition = connection.createStatement();
                stmtCondition.execute(sqlCondition);
                List<Condition> conditions = new ArrayList<>();
                ResultSet rsConditions = stmtCondition.getResultSet();
                while(rsConditions.next()) {
                    Condition condition = new Condition();
                    condition.setId(rsConditions.getInt(1));
                    Iterator iterator = nodes.entrySet().iterator();
                    while(iterator.hasNext()) {
                        Map.Entry thisEntry = (Map.Entry) iterator.next();
                        Node node = (Node) thisEntry.getValue();
                        for(Channel channel : node.getChannels()) {
                            if(channel.getDevice() != null) {
                                if(channel.getDevice().getId() == rsConditions.getInt(3)) {
                                    condition.setDevice(channel.getDevice());
                                }
                            }
                        }
                    }
                    condition.setRule(rule);
                    condition.setOperators(rsConditions.getBytes(4));
                    condition.setName(rsConditions.getString(5));
                    condition.setValues(Utils.byteToInt(rsConditions.getBytes(6)));
                    System.out.println("CONDITIOOON");
                    for(int i : condition.getValues()) {
                        System.out.print(","+i);
                    }
                    conditions.add(condition);
                }
                rule.setConditions(conditions);

                String sqlAction = "SELECT * FROM Action a WHERE  a.rule_id = "+rule.getId()+";";
                Statement stmtAction = connection.createStatement();
                stmtAction.execute(sqlAction);
                List<Action> actions = new ArrayList<>();
                ResultSet rsActions = stmtAction.getResultSet();
                while(rsActions.next()) {
                    Action action = new Action();
                    action.setId(rsActions.getInt(1));
                    action.setRule(rule);
                    Iterator iterator = nodes.entrySet().iterator();
                    while(iterator.hasNext()) {
                        Map.Entry thisEntry = (Map.Entry) iterator.next();
                        Node node = (Node) thisEntry.getValue();
                        for(Channel channel : node.getChannels()) {
                            if(channel.getDevice() != null) {
                                if(channel.getDevice().getId() == rsActions.getInt(3)) {
                                    action.setDevice(channel.getDevice());
                                }
                            }
                        }
                    }
                    action.setName(rsActions.getString(4));
                    action.setValues(Utils.byteToInt(rsActions.getBytes(5)));
                    actions.add(action);
                }

                rule.setActions(actions);



            }
            groupRule.setRules(rules);
            groupRules.add(groupRule);
        }
        return groupRules;
    }
}
