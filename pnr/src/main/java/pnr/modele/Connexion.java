package pnr.modele;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.*;

import com.jcraft.jsch.*;

public class Connexion {
    static int port;
    static String password, command, username, host;
    static Session session = null;
    static ChannelExec channel = null;

    public static void main(String[] args) throws JSchException, InterruptedException, SftpException, SQLException, InstantiationException, IllegalAccessException, ClassNotFoundException, IOException {
        try {
            session = new JSch().getSession("ubuntu", "141.94.221.193", 56000);
            session.setPassword("LeBestCSafe56");
            session.setConfig("StrictHostKeyChecking", "no");
            session.connect();
            ChannelSftp sftp = (ChannelSftp) session.openChannel("sftp");
            sftp.connect();
            System.err.println(sftp.pwd());

            Connection c = DriverManager.getConnection("jdbc:mysql://141.94.221.193:3306/bd_pnr?allowPublicKeyRetrieval=true&useSSL=false", "root", "Jesuisroot44");
            Statement stmt=c.createStatement();  
            ResultSet rs=stmt.executeQuery("select * from aobserve");  
            while(rs.next())  
                System.out.println(rs.getInt(1)+" "+rs.getString(2)+""+rs.getString(3));  
            c.close(); 
            // Channel channel = session.openChannel("exec");
            // ((ChannelExec)channel).setCommand("mysql -u ubuntu -p;");
            // channel.setInputStream(null);
            // ((ChannelExec)channel).setErrStream(System.err);
             
            // InputStream input = channel.getInputStream();
            // channel.connect();
             
            // System.out.println("Channel Connected to machine " + host + " server with command: " + command ); 
             
            // try{
            //     InputStreamReader inputReader = new InputStreamReader(input);
            //     BufferedReader bufferedReader = new BufferedReader(inputReader);
            //     String line = null;
                 
            //     while((line = bufferedReader.readLine()) != null){
            //         System.out.println(line);
            //     }
            //     bufferedReader.close();
            //     inputReader.close();
            // }catch(IOException ex){
            //     ex.printStackTrace();
            // }
            // sftp.connect();
     
            
            // System.err.println(sftp.ls("/home/ubuntu"));


            sftp.disconnect();
            session.disconnect();

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
