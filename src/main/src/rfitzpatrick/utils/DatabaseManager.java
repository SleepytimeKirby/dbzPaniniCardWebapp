package rfitzpatrick.utils;

import org.jooq.DSLContext;
import org.jooq.SQLDialect;
import org.jooq.impl.DSL;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.HashMap;

public class DatabaseManager{

    private static DatabaseManager instance;
    private static String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    private static String DB_URL = "jdbc:mysql://SERVER HERE/dbz";
    private static String USERNAME = "USER HERE";
    private static String PASSWORD = "PASS HERE";
    private static HashMap<String,Connection> conn = new HashMap<String,Connection>();
    private static  DSLContext create;
    public  static DatabaseManager getDatabaseManager(){

        if (instance == null) {
            instance = new DatabaseManager();
            init("dbz");
        }
        return instance;

    }
    //Move Connection Details to Pref File after testing or when using
    // a database connected to the internet, whatever comes first

    private static void init(String database){
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            //Driver Not Found
            e.printStackTrace();
        }
        try {
            if(!conn.containsKey(database))
            conn.put(database, DriverManager.getConnection(DB_URL + database,USERNAME,PASSWORD));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public DSLContext getDSLContext(String database){

        return DSL.using(conn.get(database), SQLDialect.MYSQL);
    }

    public void restartConnection(String database){
        try {
        conn.get(database).close();
        } catch (SQLException e) {

        }
        conn.remove(database);
        try {
            conn.put(database, DriverManager.getConnection(DB_URL + database,USERNAME,PASSWORD));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void close(){
        try {
            instance = null;
            for(Connection c: conn.values()){
                c.close();
            }
            for(String d: conn.keySet()){
                conn.remove(d);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            instance = null;
            for(String d: conn.keySet()){
                conn.remove(d);
            }
        }

    }

}