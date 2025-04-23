package conexao;

import java.sql.SQLException;
import java.sql.DriverManager;
import java.sql.Connection;

public class FabricaConexao {
    public static Connection getConexao() throws SQLException{
        return DriverManager.getConnection("jdbc:mysql://localhost/veiculos", "root", "root");
    }
}
