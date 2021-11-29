package uhk.hausy.subsystem.core.model;

import com.bpodgursky.jbool_expressions.*;
import com.bpodgursky.jbool_expressions.eval.EvalEngine;
import com.bpodgursky.jbool_expressions.parsers.ExprParser;
import com.bpodgursky.jbool_expressions.parsers.QuotedMapper;
import com.bpodgursky.jbool_expressions.rules.RuleSet;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by tobou on 05.11.2016.
 */
public class Rule {

    private int id;

    private String name;

    private boolean result;

    private boolean active;

    private String originalExpression;

    private GroupRule groupRule;

    private Expression<String> expression;

    private List<Condition> conditions;

    private List<Action> actions;

    Map<String, Boolean> condMapper;

    public static String getTableSql() {
        String sql = "CREATE TABLE IF NOT EXISTS Rule " +
                "(ID INTEGER PRIMARY KEY                NOT NULL," +
                " GROUP_RULE_ID             INTEGER     NOT NULL," +
                " ACTIVE                    BOOLEAN     NOT NULL," +
                " ORIGINALEXPRESSION        CHAR(20)    NOT NULL," +
                " NAME                      CHAR(20)    NOT NULL);";
        return sql;
    }

    public void parseRuleFromString(String OriginalExp) {

        QuotedMapper<String> intMapper = new QuotedMapper<String>() {
            @Override
            public String getValue(String name) {
                String i = (name);
                return (i);
            }
        };


        Expression<String> expr = ExprParser.parse(originalExpression);
        //System.out.println(expr.toString());
        condMapper = new HashMap<>(conditions.size());
        parse(expr);
        //System.out.println(condMapper.toString());
        setExpression(RuleSet.simplify(expr));

    }

    public boolean isForNode(Node node) {
        for (Action action : actions) {
            if (action.getDevice().getChannel().getNode() == node) {
                return true;
            }
        }
        for (Condition condition : conditions) {
            if (condition.getDevice().getChannel().getNode() == node) {
                return true;
            }
        }

        return false;
    }

    public boolean evaluate() {
        //Map<String, Boolean> results = new HashMap<>();
        for (Condition cond : conditions) {
            //System.out.println(cond.toString() + "=" + cond.evaluate());
            condMapper.replace("'" + String.valueOf(cond.getId()) + "'", cond.evaluate());
        }
        //System.out.println(condMapper.toString());
        boolean result = EvalEngine.evaluateBoolean(expression, condMapper);
        //System.out.println("Rule " + getName() + " is evaluated like " + result);
        setResult(result);
        return result;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isResult() {
        return result;
    }

    public void setResult(boolean result) {
        this.result = result;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public String getOriginalExpression() {
        return originalExpression;
    }

    public void setOriginalExpression(String originalExpression) {
        this.originalExpression = originalExpression;
    }

    public List<Condition> getConditions() {
        return conditions;
    }

    public void setConditions(List<Condition> conditions) {
        this.conditions = conditions;
    }

    public void setGroupRule(GroupRule groupRule) {
        this.groupRule = groupRule;
    }

    public GroupRule getGroupRule() {
        return groupRule;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<Action> getActions() {
        return actions;
    }

    public void setActions(List<Action> actions) {
        this.actions = actions;
    }

    public Expression<String> getExpression() {
        return expression;
    }

    public void setExpression(Expression<String> expression) {
        this.expression = expression;
    }

    @Override
    public String toString() {
        return "Rule{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", result=" + result +
                ", active=" + active +
                ", originalExpression='" + originalExpression + '\'' +
                ", groupRule=" + groupRule.getName() +
                ", expresion=" + expression +
                ", conditions[" + conditions.size() + "]=" + conditions.toString() +
                ", actions=" + actions.toString() +
                '}';
    }


    private void parse(Expression<String> exp) {

        //Expression<Condition> a = exp;
        //System.out.println("ruleEngine.zpracuj()" + "Expression type of exp: " + exp.getExprType());
        if (exp.getExprType().equals("and")) {
            evalAnd((And) exp);
        }

        if (exp.getExprType().equals("or")) {
            evalOR((Or) exp);
        }
        if (exp.getExprType().equals("not")) {
            evalNOT((Not) exp);
        }
    }

    private Expression<String> simplify(Expression<String> exp) {
        return RuleSet.simplify(exp);
    }

    private void evalAnd(And rule) {
        parseRecursive(rule);
    }

    private void evalOR(Or rule) {
        parseRecursive(rule);
    }

    private void evalNOT(Not rule) {
        rule.getExprType();
        //System.out.println("evalNOT()" + rule.getExprType());
        Not not = rule;
        Variable v = (Variable) not.getE();
        String s = (String) v.getValue();
        if (!condMapper.containsKey(s)) {
           condMapper.put(s, null);
        }
    }

    private void parseRecursive(NExpression rule) {

        rule.getExprType();
        List<Expression<String>> children = (List<Expression<String>>) rule.getChildren();

        children.stream().forEach((child) -> {
            if (child.getExprType().equals("variable")) {
                Variable v = (Variable) child;
                String s = (String) v.getValue();

                if (!condMapper.containsKey(s)) {
                    condMapper.put(s, null);
                }
            } else {
                parse(child);
            }
        });

    }

}
