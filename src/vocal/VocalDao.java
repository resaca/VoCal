
/**
 *
 * @author Rahana,Anu,Sebin,Pratheesh
 */
 

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Connection;
import java.sql.Statement;
import java.util.Properties;



public class VocalDao {
    

    public VocalDao(String databaseName) {
        this.dbName = databaseName;
        setDBSystemDir();
        dbProperties = loadDBProperties();
        String driverName = dbProperties.getProperty("derby.driver");
        loadDatabaseDriver(driverName);
        if(!dbExists()) {
            createDatabase();
        }

    }

    private boolean dbExists() {
        boolean bExists = false;
        String dbLocation = getDatabaseLocation();
        File dbFileDir = new File(dbLocation);
        if (dbFileDir.exists()) {
            bExists = true;
        }
        return bExists;
    }

    private void setDBSystemDir() {
        // decide on the db system directory
        String userHomeDir = System.getProperty("/", ".");
        String systemDir = userHomeDir + "/.vocal";
        System.setProperty("derby.system.home", systemDir);

        // create the db system directory
        File fileSystemDir = new File(systemDir);
        fileSystemDir.mkdir();
    }

    private void loadDatabaseDriver(String driverName) {
        // load Derby driver
        try {
            Class.forName(driverName);
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        }

    }

    private Properties loadDBProperties() {
        InputStream dbPropInputStream = null;
        dbPropInputStream = VocalDao.class.getResourceAsStream("Configuration.properties");
        dbProperties = new Properties();
        try {
            dbProperties.load(dbPropInputStream);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return dbProperties;
    }


    private boolean createTables(Connection dbConnection) {
        boolean bCreatedTables = false;
        Statement statement = null;
        try {
            statement = dbConnection.createStatement();
            statement.execute(strCreateLoginTable);
            statement.execute(strCreateAnniversary);
            statement.execute(strCreateBirthday);
            statement.execute(strCreateReminder);
            statement.execute(strCreateNotes);
            statement.execute(strCreateMeeting);
            bCreatedTables = true;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return bCreatedTables;
    }
    private boolean createDatabase() {
        boolean bCreated = false;
        Connection dbConn = null;

        String dbUrl = getDatabaseUrl();
        //System.out.println(dbUrl);
        dbProperties.put("create", "true");

        try {
            //System.out.println(dbUrl);
            dbConn = DriverManager.getConnection(dbUrl, dbProperties);
            bCreated = createTables(dbConn);
            //System.out.println(bCreated);
        } catch (SQLException ex) {
        }
        dbProperties.remove("create");
        return bCreated;
    }

    public boolean connect() {
        try {
            String strUrl = "jdbc:derby:Vo-Cal;user=app;password=app";
            dbConnection = DriverManager.getConnection(strUrl);
            isConnected = dbConnection != null;
        } catch (SQLException ex) {
            isConnected = false;
        }
        return isConnected;
    }
    


    public void disconnect() {
        if(isConnected) {
            String dbUrl = getDatabaseUrl();
            dbProperties.put("shutdown", "true");
            try {
                DriverManager.getConnection(dbUrl, dbProperties);
            } catch (SQLException ex) {
            }
            isConnected = false;
        }
    }

    public String getDatabaseLocation() {
        String dbLocation = System.getProperty("derby.system.home") + "/" + dbName;
        return dbLocation;
    }

    public String getDatabaseUrl() {
        String dbUrl = dbProperties.getProperty("derby.url") + dbName;
        return dbUrl;
    }



    public static void main(String[] args) {
        VocalDao db = new VocalDao("Vo-Cal");
        System.out.println(db.getDatabaseLocation());
        System.out.println(db.getDatabaseUrl());
        db.connect();
        //db.disconnect();
    }

    private Properties dbProperties;
    private boolean isConnected;
    private String dbName;
    Connection dbConnection;
    private static final String strCreateAnniversary =
            "create table APP.ANNIVERSARY ("    +
            "    ID             VARCHAR(5), "   +
            "    USERNAME       VARCHAR(25), "  +
            "    NAME           VARCHAR(25), "  +
            "    DATE           DATE, "         +
            "    ALARM          VARCHAR(3), "   +
            "    ALARMDATE      DATE, "         +
            "    ALARMTIME      TIME, "         +
            "    NOTES          VARCHAR(250) "  +
            ")";
    private static final String strCreateBirthday =
            "create table APP.BIRTHDAY ("       +
            "    ID             VARCHAR(5), "   +
            "    USERNAME       VARCHAR(25), "  +
            "    NAME           VARCHAR(25), "  +
            "    DATE           DATE, "         +
            "    ALARM          VARCHAR(3), "   +
            "    ALARMDATE      DATE, "         +
            "    ALARMTIME      TIME, "         +
            "    NOTES          VARCHAR(250) "  +
            ")";
    private static final String strCreateReminder =
            "create table APP.REMINDER ("       +
            "    ID             VARCHAR(5), "   +
            "    USERNAME       VARCHAR(25), "  +
            "    NAME           VARCHAR(25), "  +
            "    DATE           DATE, "         +
            "    ALARM          VARCHAR(3), "   +
            "    ALARMDATE      DATE, "         +
            "    ALARMTIME      TIME, "         +
            "    NOTES          VARCHAR(250) "  +
            ")";
    private static final String strCreateMeeting =
            "create table APP.MEETING ("    +
            "    ID             VARCHAR(5), "   +
            "    USERNAME       VARCHAR(25), "  +
            "    NAME           VARCHAR(25), "  +
            "    DATE           DATE, "         +
            "    TIMEFROM       TIME, "         +
            "    TIMETO         TIME, "         +
            "    ALARM          VARCHAR(3), "   +
            "    ALARMDATE      DATE, "         +
            "    ALARMTIME      TIME, "         +
            "    NOTES          VARCHAR(250) "  +
            ")";
    private static final String strCreateNotes =
            "create table APP.NOTES ("       +
            "    ID             VARCHAR(5), "   +
            "    USERNAME       VARCHAR(25), "  +
            "    TITLE           VARCHAR(25), "  +
            "    NOTES          VARCHAR(250) "  +
            ")";
    private static final String strCreateLoginTable =
            "create table APP.LOGINTABLE ("                  +
            "USERNAME VARCHAR(25) not null primary key, "   +
            "PASSWORD VARCHAR(25)"                          +
            ")";

}
