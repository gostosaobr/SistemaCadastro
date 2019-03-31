package cadastrocliente;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.*;

public class ConnectionFactory {

    public static Connection getConnection() {
        Connection conexao = null;

        String url = "jdbc:mysql://localhost:3306/testearthur";
        String user = "root";
        String passa = "123456";

        try {

            conexao = DriverManager.getConnection(url, user, passa);
            System.out.println("Conectado");
            return conexao;
        } catch (SQLException ex) {
            Logger.getLogger(ConnectionFactory.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Não conectado");
        }

        return conexao;
        
    }
      
    

}

 