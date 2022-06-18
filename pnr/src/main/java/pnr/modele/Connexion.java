package pnr.modele;

import java.io.ByteArrayOutputStream;

import com.jcraft.jsch.*;

public class Connexion {
    static int port;
    static String password, command, username, host;
    static Session session = null;
    static ChannelExec channel = null;

    public static void main(String[] args) throws JSchException, InterruptedException, SftpException {
        try {
            session = new JSch().getSession("ubuntu", "141.94.221.193", 56000);
            session.setPassword("LeBestCSafe56");
            session.setConfig("StrictHostKeyChecking", "no");
            session.connect();

            ChannelSftp sftp = (ChannelSftp) session.openChannel("sftp");
            sftp.connect();
     
            // sftp.cd("PNR/src/main/java/pnr/modele");
            System.err.println(sftp.pwd());
            // System.err.println(sftp.ls(command));
     
            sftp.disconnect();
            session.disconnect();
        } catch (JSchException e) {
            e.printStackTrace();
        }
            // channel = (ChannelExec) session.openChannel("exec");
            // channel.setCommand("cd /");
            // channel.setCommand("ls");
            // ByteArrayOutputStream responseStream = new ByteArrayOutputStream();
            // channel.setOutputStream(responseStream);
            // channel.connect();

            // while (channel.isConnected()) {
            //     Thread.sleep(100);
        //     }

        //     String responseString = new String(responseStream.toByteArray());
        //     System.out.println(responseString);
        // } finally {
        //     if (session != null) {
        //         session.disconnect();
        //     }
        //     if (channel != null) {
        //         channel.disconnect();
        //     }
        // }
    }
}
