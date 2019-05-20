package Sortidas_Dao;

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
public class Dao_Sortidas implements Dao<Sortidas> {

    @Override
    public boolean adiciona(Sortidas sort) {
        String sql = SQL_Constantes_Sortidas.INSERT;
        try {
            try (Connection connection = Conexao_BD.getConnection();
                    PreparedStatement stmt = connection.prepareStatement(sql)) {
                stmt.setString(1, null);
                stmt.setString(2, sort.getNome_sortida());
                stmt.execute();
            }
        } catch (SQLException e) {
            System.out.println("Erro ao inserir dados na tabela Sortidas!");
            return false;
        }
        return true;
    }

    @Override
    public boolean pesquisa(Sortidas sort) {
        List<Sortidas> todos = pesquisaTodos();

        for (Sortidas cc : todos) {
            if (((Sortidas) cc).equals(sort)) {
                return true;
            }
        }
        return false;    }

    @Override
    public List<Sortidas> pesquisaTodos() {
        List<Sortidas> sort = new ArrayList();
        try {
            try (Connection connection = Conexao_BD.getConnection();
                    PreparedStatement stmt = connection.prepareStatement(SQL_Constantes_Sortidas.SEARCH);
                    ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Sortidas contato = new Sortidas();
                    //contato.setId_sortida(rs.getInt("idSilabas"));
                    contato.setNome_sortida(rs.getString("silaba"));
                    sort.add(contato);
                }
            }
        } catch (SQLException e) {
            System.out.println("Erro ao pesquisar por Silabas sortidas no banco de dados!");
        }
        return sort;    }

}
