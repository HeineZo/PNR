package pnr.modele;


public class Connexion {

    public static void main(String[] args) {
        ConnectVPS connect = new ConnectVPS("jdbc:mysql://141.94.221.193:3306/bd_pnr", "user", "Mdp@user1");

        System.out.println(connect.isConnected());

        connect.disconnect();
    }
}