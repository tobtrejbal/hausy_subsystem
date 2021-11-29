package uhk.hausy.subsystem.core.database.base;

import uhk.hausy.subsystem.core.model.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by tobou on 12.10.2016.
 */
public class DbsManager {

    public static ReentrantLock databaseLock = new ReentrantLock();

    private static DbsManager sInstance;

    private DbsManager() {}

    public static DbsManager getInstance() {
        return sInstance;
    }

    public static void createInstance() {
        if(sInstance == null) {
            sInstance = new DbsManager();
        }
    }

    private Connection connection;

    public Connection getConnection() {
        return connection;
    }

    public void connect() {
        try {
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection("jdbc:sqlite:/home/ubuntu/hausy-subsystem/database/database.db");
        } catch ( Exception e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            System.exit(0);
        }
        System.out.println("Opened database successfully");
    }

    public void openSession() throws Exception {
        // need to call before you interact with database
        if (databaseLock.tryLock(5000, TimeUnit.MILLISECONDS)) {
            connection.setAutoCommit(false);
            System.out.println("opened");
        }
    }

    public void commitSession() throws Exception {
        // need to call after you interact with database
        connection.commit();
        databaseLock.unlock();
        System.out.println("closed");
    }

    public void rollback() {
        // call in catch - in case something fails :P
        try {
            connection.rollback();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        databaseLock.unlock();
        System.out.println("rolledback");
    }

    public void createDatabase() {
        try {
            Statement stmt = connection.createStatement();

            //destroyTables(stmt);
            //clearTables(stmt);
            createTables(stmt);
            stmt.close();
            //connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void destroyTables(Statement statement) throws SQLException {
        System.out.println("DROP TABLE Node");
        statement.executeUpdate("DROP TABLE Node;");
        System.out.println("DROP TABLE NodeType");
        statement.executeUpdate("DROP TABLE NodeType;");
        System.out.println("DROP TABLE NodeType");
        statement.executeUpdate("DROP TABLE Channel;");
        System.out.println("DROP TABLE Channel");
        statement.executeUpdate("DROP TABLE Log;");
        System.out.println("DROP TABLE Log");
        statement.executeUpdate("DROP TABLE Device;");
        System.out.println("DROP TABLE Device");
        statement.executeUpdate("DROP TABLE GroupRule;");
        System.out.println("DROP TABLE GroupRule");
        statement.executeUpdate("DROP TABLE Rule;");
        System.out.println("DROP TABLE Rule");
        statement.executeUpdate("DROP TABLE Condition;");
        System.out.println("DROP TABLE Condition");
        statement.executeUpdate("DROP TABLE Action;");
        System.out.println("DROP TABLE Action");
    }

    public void clearTables(Statement statement) throws SQLException {
        System.out.println("CLEAR TABLE Node");
        statement.executeUpdate("DELETE FROM Node;");
        System.out.println("CLEAR TABLE NodeType");
        statement.executeUpdate("DELETE FROM NodeType;");
        System.out.println("CLEAR TABLE NodeType");
        statement.executeUpdate("DELETE FROM Channel;");
        System.out.println("CLEAR TABLE Channel");
        statement.executeUpdate("DELETE FROM Device;");
        System.out.println("CLEAR TABLE Device");
        statement.executeUpdate("DELETE FROM GroupRule;");
        System.out.println("CLEAR TABLE GroupRule");
        statement.executeUpdate("DELETE FROM Rule;");
        System.out.println("CLEAR TABLE Rule");
        statement.executeUpdate("DELETE FROM Condition;");
        System.out.println("CLEAR TABLE Condition");
        statement.executeUpdate("DELETE FROM Action;");
        System.out.println("CLEAR TABLE Action");
    }

    public void createTables(Statement stmt) throws SQLException {
        stmt.executeUpdate(Node.getTableSql());
        stmt.executeUpdate(NodeType.getTableSql());
        stmt.executeUpdate(Channel.getTableSql());
        stmt.executeUpdate(Log.getTableSql());
        stmt.execute(Device.getTableSql());
        stmt.executeUpdate(GroupRule.getTableSql());
        stmt.executeUpdate(Rule.getTableSql());
        stmt.executeUpdate(Condition.getTableSql());
        stmt.executeUpdate(Action.getTableSql());
    }

    public void clearConfig() {
        try {
            Statement stmt = connection.createStatement();

            clearTables(stmt);
            stmt.close();
            //connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
