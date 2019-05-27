package Imagens;

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
public class Dao_Imagens implements Dao<Imagens> {

    @Override
    public boolean adiciona(Imagens img) {
        String sql = SQL_Constantes_Imagem.INSERT;
        try {
            try (Connection connection = Conexao_BD.getConnection();
                    PreparedStatement stmt = connection.prepareStatement(sql)) {
                stmt.setString(1, null);
                stmt.setString(2, img.getNome());
                stmt.setBlob(3, img.getImagem());
                stmt.execute();
            }
        } catch (SQLException e) {
            System.out.println("Erro ao inserir imagem!");
            return false;
        }
        return true;
    }

    @Override
    public boolean pesquisa(Imagens img) {
        List<Imagens> todos = pesquisaTodos();

        for (Imagens cc : todos) {
            if (((Imagens) cc).equals(img)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public List<Imagens> pesquisaTodos() {
        List<Imagens> img = new ArrayList();
        try {
            try (Connection connection = Conexao_BD.getConnection();
                    PreparedStatement stmt = connection.prepareStatement(SQL_Constantes_Imagem.SEARCH);
                    ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Imagens contato = new Imagens();
                    contato.setIdImagens(rs.getInt("idImagens"));
                    contato.setNome(rs.getString("nome"));
                    contato.setImagem(rs.getBlob("imagem"));
                    img.add(contato);
                }
            }
        } catch (SQLException e) {
            System.out.println("Erro ao pesquisar por palavras dificeis no banco de dados!");
        }
        return img;
    }

}
