package Conexao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author anadi
 */
public class Conexao_BD {

    public static Connection getConnection() throws SQLException {
        try {
            return DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/mydb",
                    "root",
                    "");
        } catch (SQLException e) {
            System.out.println("Erro ao conectar com o Banco de Dados!");
        }
        return null;

    }
    
    public static void main(String[] args) throws SQLException{
        Connection con = null;
        try{
            con = getConnection();
            
        }catch(SQLException ex){
            System.out.println("Erro ao conectar!");
            
        }
        if(con!= null){
            System.out.println("Conectado");
            
            con.close();
        }
    }

}
