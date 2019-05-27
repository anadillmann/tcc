package Palavra_Facil_Dao;

import Conexao.Dao;
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
public class Dao_Facil implements Dao<Facil> {

    @Override
    public boolean adiciona(Facil palavra_f) {
        String sql = SQl_Constantes_Facil.INSERT;
        try {
            try (Connection connection = Conexao_BD.getConnection();
                    PreparedStatement stmt = connection.prepareStatement(sql)) {
                stmt.setString(1, null);
                stmt.setString(2, palavra_f.getNome_palavra_facil());
                stmt.execute();
            }
        } catch (SQLException e) {
            System.out.println("Erro ao inserir dados na tabela Palavra Facil!");
            return false;
        }
        return true;
    }

    @Override
    public boolean pesquisa(Facil palavra_f) {
        List<Facil> todos = pesquisaTodos();

        for (Facil cc : todos) {
            if (((Facil) cc).equals(palavra_f)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public List<Facil> pesquisaTodos() {
        List<Facil> palavra_f = new ArrayList();
        try {
            try (Connection connection = Conexao_BD.getConnection();
                    PreparedStatement stmt = connection.prepareStatement(SQl_Constantes_Facil.SEARCH);
                    ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Facil contato = new Facil();
                    contato.setId_facil(rs.getInt("id_facil"));
                    contato.setNome_palavra_facil(rs.getString("nome_palavra_facil"));
                    //rs.getBlob(string)
                    palavra_f.add(contato);
                }
            }
        } catch (SQLException e) {
            System.out.println("Erro ao pesquisar por palavras no banco de dados!");
        }
        return palavra_f;
    }
}
