package Palavra_Medio_Dao;

import Conexao.Conexao_BD;
import Conexao.Dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author anadi
 */
public class Dao_Medio implements Dao<Medio> {

    @Override
    public void adiciona(Medio palavra_m) {
        String sql = SQL_Constantes_Medio.INSERT;
        try {
            try (Connection connection = Conexao_BD.getConnection();
                    PreparedStatement stmt = connection.prepareStatement(sql)) {
                stmt.setString(1, null);
                stmt.setString(2, palavra_m.getNome_palavra_medio());
                stmt.execute();
            }
        } catch (SQLException e) {
            System.out.println("Erro ao inserir dados na tabela Palavra Medio!");
        }
    }

    /*
    @Override
    public boolean pesquisa(Medio palavra_m) {
        List<Medio> todos = pesquisaTodos();

        for (Medio cc : todos) {
            if (((Medio) cc).equals(palavra_m)) {
                return true;
            }
        }
        return false;
    }
*/

    @Override
    public List<Medio> pesquisaTodos() {
        List<Medio> palavra_m = new ArrayList();
        try {
            try (Connection connection = Conexao_BD.getConnection();
                    PreparedStatement stmt = connection.prepareStatement(SQL_Constantes_Medio.SEARCH);
                    ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Medio contato = new Medio();
                    contato.setId_medio(rs.getInt("id_media"));
                    contato.setNome_palavra_medio(rs.getString("nome_palavra_media"));
                    palavra_m.add(contato);
                }
            }
        } catch (SQLException e) {
            System.out.println("Erro ao pesquisar por palavras medias no banco de dados!");
        }
        return palavra_m;
    }

    @Override
    public boolean pesquisa(Medio m) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
