package Palavra_Dificil_Dao;

import Conexao.Conexao_BD;
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
public class Dao_Dificil implements Dao<Dificil> {

    @Override
    public boolean adiciona(Dificil palavra_d) {
        String sql = SQL_Constantes_Dificil.INSERT;
        try {
            try (Connection connection = Conexao_BD.getConnection();
                    PreparedStatement stmt = connection.prepareStatement(sql)) {
                stmt.setString(1, null);
                stmt.setString(2, palavra_d.getNome_palavra_dificil());
                stmt.execute();
            }
        } catch (SQLException e) {
            System.out.println("Erro ao inserir dados na tabela Palavra Dificil!");
            return false;
        }
        return true;
    }

    @Override
    public boolean pesquisa(Dificil palavra_d) {
        List<Dificil> todos = pesquisaTodos();

        for (Dificil cc : todos) {
            if (((Dificil) cc).equals(palavra_d)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public List<Dificil> pesquisaTodos() {
        List<Dificil> palavra_d = new ArrayList();
        try {
            try (Connection connection = Conexao_BD.getConnection();
                    PreparedStatement stmt = connection.prepareStatement(SQL_Constantes_Dificil.SEARCH);
                    ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Dificil contato = new Dificil();
                    contato.setId_dificil(rs.getInt("id_dificil"));
                    contato.setNome_palavra_dificil(rs.getString("nome_palavra_dificil"));
                    palavra_d.add(contato);
                }
            }
        } catch (SQLException e) {
            System.out.println("Erro ao pesquisar por palavras dificeis no banco de dados!");
        }
        return palavra_d;
    }

}
