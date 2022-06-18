package pnr.modele;

import java.sql.*;

/**
 * Class Connect VPS which allows you to connect to the server where the database is stored and run queries
 */
public class ConnectVPS {

    private String url;
    private String user;
    private String password;
    private Connection con;
    private Statement stmt;
    private boolean connected;

    /**
     * ConnectVPS constructor
     * @param url server url
     * @param user server login
     * @param password password
     */
    public ConnectVPS(String url, String user, String password) {
        if (url != null && user != null && password != null) {
            this.url = url;
            this.user = user;
            this.password = password;
            this.connected = false;
            connect();
        } else {
            System.out.println("ERROR ConnectVPS : null parameters");
        }
    }

    /**
     * If the connection is not established, try to connect to the database. If it works, print "BDD
     * connected" and set the connection to true. If it doesn't work, print the error
     */
    public void connect() {
        if (!isConnected()) {
            try {
                this.con = DriverManager.getConnection(this.url, this.user, this.password);
                this.stmt = con.createStatement();
                System.out.println("BDD connected");
                this.connected = true;
            } catch (Exception e) {
                System.out.println(e);
            }
        } else {
            System.out.println("ERROR connect : connexion already established");
        }
    }

    /**
     * If the connection is open, close it. If it's already closed, print an error message
     */
    public void disconnect() {
        if (isConnected()) {
            try {
                this.con.close();
                this.connected = false;
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("ERROR disconnect : connexion already closed");
        }
    }

    /**
     * This function returns a boolean value that indicates whether the client is connected to the
     * server
     * 
     * @return The boolean value of the variable connected.
     */
    public boolean isConnected() {
        return this.connected;
    }

    /**
     * It executes a query and returns a ResultSet
     * 
     * @param query the query to execute
     * @return A ResultSet object.
     */
    public ResultSet executeQuery(String query) {
        ResultSet ret = null;
        if (query != null && isConnected()) {
            try {
                ret = stmt.executeQuery(query);
            } catch (Exception e) {
                System.out.println(e);
            }
        } else {
            System.err.println("ERROR executeQuery : query is null");
        }
        return ret;

        // ou avec return String
        /*
         * String ret = null;
         * if(query != null) {
         * try {
         * ResultSet rs = stmt.executeQuery(query);
         * while(rs.next()) {
         * ret = ret + rs.getString(1);
         * }
         * } catch(Exception e) {
         * System.out.println(e);
         * }
         * } else {
         * System.err.println("ERROR executeQuery : query is null");
         * }
         * return ret;
         */
    }
}