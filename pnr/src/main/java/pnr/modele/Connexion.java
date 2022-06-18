package pnr.modele;

import java.io.ByteArrayOutputStream;
import java.sql.*;

import com.jcraft.jsch.*;

public class Connexion {
    static int port;
    static String password, command, username, host;
    static Session session = null;
    static ChannelExec channel = null;

    public static void main(String[] args) throws JSchException, InterruptedException, SftpException, SQLException, InstantiationException, IllegalAccessException, ClassNotFoundException {
        try {
            session = new JSch().getSession("ubuntu", "141.94.221.193", 56000);
            session.setPassword("LeBestCSafe56");
            session.setConfig("StrictHostKeyChecking", "no");
            session.connect();

            ChannelSftp sftp = (ChannelSftp) session.openChannel("sftp");
            sftp.connect();
     
            System.err.println(sftp.pwd());
            System.err.println(sftp.ls("/home/ubuntu"));

            Connection c = DriverManager.getConnection("jdbc:mysql://141.94.221.193:3306/bd_pnr", "ubuntu", "salutLaFamax");
            sftp.disconnect();
            session.disconnect();


            // String jumpserverHost = "141.94.221.193";
            // String jumpserverUsername = "ubuntu";
            // // The hostname/IP address and port, you would use on the SSH server
            // // to connect to the database.
            // // If the database runs on the same machine as the SSH server, use "localhost".
            // String databaseHost = "bd_pnr";
            // int databasePort = 3306;
            // String databaseUsername = "ubuntu";
            // String databasePassword = "salutLaFamax";      // The password for the database user.

            // JSch jsch = new JSch();
            // // Public key authentication example
            // // (but you can use password authentication, if appropriate).
            // // jsch.addIdentity("~/.ssh/id_rsa");

            // // Connect to SSH jump server (this does not show an authentication code)
            // Session session = jsch.getSession(jumpserverUsername, jumpserverHost);
            // session.setConfig("StrictHostKeyChecking", "no");
            // session.connect();

            // // Forward randomly chosen local port through the SSH channel to database host/port
            // int forwardedPort = session.setPortForwardingL(0, databaseHost, databasePort);

            // // Connect to the forwarded port (the local end of the SSH tunnel)
            // // If you don't use JDBC, but another database client,
            // // just connect it to the localhost:forwardedPort
            // String url = "jdbc:mysql://localhost:" + forwardedPort;
            // Connection con =DriverManager.getConnection(url, databaseUsername, databasePassword);
        } catch (JSchException e) {
            e.printStackTrace();
        }

    }

    //Connect to a database using JDBC
    // public static Connection connect() throws SQLException {

    //     // DriverManager.registerDriver(new com.microsoft.sqlserver.jdbc.SQLServerDriver());
        
    //     return c;
    // }

}
