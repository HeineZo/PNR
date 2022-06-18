package pnr.modele;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class jdbcTahhh {
    public static void main(String[] args) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con=DriverManager.getConnection(
                    "jdbc:mysql://141.94.221.193:3306/bd_pnr","ubuntu","salutLaFamax@2");
            Statement stmt=con.createStatement();  
            ResultSet rs=stmt.executeQuery("show databases;");
            System.out.println("Connected");  
        } catch(Exception e) {
            System.out.println(e);
        }
    }  
}