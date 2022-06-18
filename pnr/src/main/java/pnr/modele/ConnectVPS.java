package pnr.modele;

import java.sql.*;

public class ConnectVPS {
    public static void main(String[] args) {
        try {
            Connection con = DriverManager.getConnection("jdbc:mysql://141.94.221.193:3306/bd_pnr","root","BigoLeBoss@56");
            Statement stmt = con.createStatement();  
            ResultSet rs = stmt.executeQuery("show tables;");
            System.out.println("Connected");  
            while(rs.next()) {
                System.out.println(rs.getString(1));
            }
            con.close(); 
        } catch(Exception e) {
            System.out.println(e);
        }
    }
}