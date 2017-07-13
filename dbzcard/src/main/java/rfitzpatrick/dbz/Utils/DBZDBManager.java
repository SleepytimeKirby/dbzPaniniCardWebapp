package rfitzpatrick.dbz.Utils;

import org.jooq.DSLContext;
import org.jooq.SQLDialect;
import org.jooq.impl.DSL;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBZDBManager {

    private static DBZDBManager instance;
    private static String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    private static String DB_URL = "jdbc:mysql://SERVER HERE/dbz";
    private static String USERNAME = "USER HERE";
    private static String PASSWORD = "PASS HERE";
    private static Connection conn;
    private static DSLContext create;
    public  static DBZDBManager getDatabaseManager(){

        if (instance == null) {
            instance = new DBZDBManager();
            init();
        }
        return instance;

    }
    //Move Connection Details to Pref File after testing or when using
    // a database connected to the internet, whatever comes first

    private static void init(){
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            //Driver Not Found
            e.printStackTrace();
        }
        try {
            conn = DriverManager.getConnection(DB_URL,USERNAME,PASSWORD);
            create = DSL.using(conn, SQLDialect.MYSQL);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public DSLContext getDSLContext(){
        return create;
    }
    public void close(){
        try {
            conn.close();
            instance = null;
            conn = null;
        } catch (SQLException e) {
            e.printStackTrace();
            instance = null;
            conn = null;
        }

    }

}