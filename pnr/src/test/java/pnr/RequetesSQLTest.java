package pnr;

import static org.junit.Assert.*;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.junit.Test;

import pnr.controleur.*;
import pnr.modele.*;

/**
 * Unit test
 */
public class RequetesSQLTest {
    

    @Test
    public void TestRequetesSQL() throws SQLException {
        
        ConnectVPS connect = new ConnectVPS("jdbc:mysql://141.94.221.193:3306/bd_pnr", "user", "Mdp@user1");

        ResultSet rs = connect.executeQuery("SELECT * FROM Observateur");
        assertTrue(rs.next());
        assertEquals(1, rs.getInt("idObservateur"));
        assertEquals("ARAUJO", rs.getString("nom"));
        assertEquals("Marie", rs.getString("prenom"));

        
        rs = connect.executeQuery("SELECT * FROM Utilisateur");
        assertTrue(rs.next());
        assertEquals("nom_admin", rs.getString("nom"));
        assertEquals("prenom_admin", rs.getString("prenom"));
        assertEquals("admin", rs.getString("pseudonyme"));
        
        rs = connect.executeQuery("SELECT pseudonyme FROM Utilisateur WHERE nom = 'nom_user'");
        assertTrue(rs.next());
        assertEquals("user", rs.getString("pseudonyme"));
        assertFalse(rs.next());

        rs = connect.executeQuery("SELECT nom, prenom FROM Utilisateur");
        assertTrue(rs.next());
        assertEquals("nom_admin", rs.getString("nom"));
        assertEquals("prenom_admin", rs.getString("prenom"));
        
        rs = connect.executeQuery("SELECT pseudonyme, mdpUtilisateur, permission FROM Utilisateur WHERE pseudonyme = 'admin'");
        assertTrue(rs.next());
        assertEquals(1, rs.getInt("permission"));
        assertFalse(rs.next());

        connect.executeUpdate("INSERT INTO Utilisateur VALUES('test','nomtest','prenomtest','mdptest',0);");
        rs = connect.executeQuery("SELECT pseudonyme FROM Utilisateur WHERE pseudonyme = 'test'");
        assertTrue(rs.next());
        assertEquals("test", rs.getString("pseudonyme"));

        connect.executeUpdate("UPDATE Utilisateur SET nom ='nomtest2' WHERE pseudonyme ='test' ;");
        rs = connect.executeQuery("SELECT nom FROM Utilisateur WHERE pseudonyme = 'test'");
        assertTrue(rs.next());
        assertEquals("nomtest2", rs.getString("nom"));

        connect.executeUpdate("UPDATE Utilisateur SET mdpUtilisateur ='test2' WHERE pseudonyme ='test' ;");
        rs = connect.executeQuery("SELECT mdpUtilisateur FROM Utilisateur WHERE pseudonyme = 'test'");
        assertTrue(rs.next());
        assertEquals("test2", rs.getString("mdpUtilisateur"));
        assertFalse(rs.next());

        connect.executeUpdate("UPDATE Utilisateur SET permission = 1 WHERE pseudonyme ='test' ;");
        rs = connect.executeQuery("SELECT permission FROM Utilisateur WHERE pseudonyme = 'test'");
        assertTrue(rs.next());
        assertEquals(1, rs.getInt("permission"));
        assertFalse(rs.next());

        connect.executeUpdate("UPDATE Utilisateur SET prenom ='prenomtest2' WHERE pseudonyme ='test' ;");
        rs = connect.executeQuery("SELECT prenom FROM Utilisateur WHERE pseudonyme = 'test'");
        assertTrue(rs.next());
        assertEquals("prenomtest2", rs.getString("prenom"));
        assertFalse(rs.next());

        connect.executeUpdate("UPDATE Utilisateur SET pseudonyme ='test2' WHERE pseudonyme ='test' ;");
        rs = connect.executeQuery("SELECT pseudonyme FROM Utilisateur WHERE pseudonyme = 'test2'");
        assertTrue(rs.next());
        assertEquals("test2", rs.getString("pseudonyme"));
        assertFalse(rs.next());

        connect.executeUpdate("DELETE FROM Utilisateur WHERE pseudonyme = 'test2'");
        rs = connect.executeQuery("SELECT pseudonyme FROM Utilisateur WHERE pseudonyme = 'test2'");
        assertFalse(rs.next());
    }
}