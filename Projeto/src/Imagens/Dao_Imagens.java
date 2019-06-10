package Imagens;

import Conexao.Conexao_BD;
import Conexao.Dao;
import java.io.IOException;
import java.sql.Blob;
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

    public Imagens pesquisa_facil(int idFacil) {
        Imagens contato = new Imagens();
        try (Connection connection = Conexao_BD.getConnection();
                PreparedStatement stmt = connection.prepareStatement(SQL_Constantes_Imagem.SEARCH_FACIL)) {
            stmt.setInt(1, idFacil);
            ResultSet rs = stmt.executeQuery();
            System.out.println(rs);
            rs.next();

            contato.setIdImagens(rs.getInt("idImagens"));
            contato.setNome(rs.getString("nome"));
            Blob blob = rs.getBlob("imagem");

            try {
                contato.setImagem(ConverterImagemByte.paraImagem(
                        blob.getBytes(1, (int) blob.length())));
            } catch (ClassNotFoundException | IOException ex) {
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Erro ao pesquisar por imagens");
        }
        return contato;
    }

    public Imagens pesquisa_medio(int idMedio) {
        Imagens contato = new Imagens();
        try (Connection connection = Conexao_BD.getConnection();
                PreparedStatement stmt = connection.prepareStatement(SQL_Constantes_Imagem.SEARCH_MEDIO)) {

            stmt.setInt(1, idMedio);
            ResultSet rs = stmt.executeQuery();
            System.out.println(rs);
            rs.next();

            contato.setIdImagens(rs.getInt("idImagens"));
            contato.setNome(rs.getString("nome"));
            Blob blob = rs.getBlob("imagem");

            try {
                contato.setImagem(ConverterImagemByte.paraImagem(
                        blob.getBytes(1, (int) blob.length())));
            } catch (ClassNotFoundException | IOException ex) {
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Erro ao pesquisar por imagens");
        }
        return contato;
    }

    public Imagens pesquisa_dificil(int idDificil) {
        Imagens contato = new Imagens();
        try (Connection connection = Conexao_BD.getConnection();
                PreparedStatement stmt = connection.prepareStatement(SQL_Constantes_Imagem.SEARCH_DIFICIL)) {

            stmt.setInt(1, idDificil);
            ResultSet rs = stmt.executeQuery();
            System.out.println(rs);
            rs.next();

            contato.setIdImagens(rs.getInt("idImagens"));
            contato.setNome(rs.getString("nome"));
            Blob blob = rs.getBlob("imagem");

            try {
                contato.setImagem(ConverterImagemByte.paraImagem(
                        blob.getBytes(1, (int) blob.length())));
            } catch (ClassNotFoundException | IOException ex) {
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Erro ao pesquisar por imagens");
        }
        return contato;
    }

    @Override
    public void adiciona(Imagens img) {
        String sql = SQL_Constantes_Imagem.INSERT;
        try (Connection connection = Conexao_BD.getConnection();
                PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, img.getNome());
            Blob blob = connection.createBlob();
            byte[] arr;
            try {
                arr = ConverterImagemByte.paraByteArray(img.getImagem());
                blob.setBytes(1, arr);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            stmt.setBlob(2, blob);
            stmt.execute();
            connection.close();
        } catch (SQLException e) {
            System.out.println("Erro ao inserir dados na tabela contato!");
            e.printStackTrace();
        }
    }

    @Override
    public List<Imagens> pesquisaTodos() {
        List<Imagens> img = new ArrayList();
        try {
            try (Connection connection = Conexao_BD.getConnection();
                    PreparedStatement stmt = connection.prepareStatement(SQL_Constantes_Imagem.SEARCH_ALL);
                    ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Imagens contato = new Imagens();
                    contato.setIdImagens(rs.getInt("idImagens"));
                    contato.setNome(rs.getString("nome"));
                    Blob blob = rs.getBlob("imagem");
                    try {
                        contato.setImagem(ConverterImagemByte.paraImagem(
                                blob.getBytes(1, (int) blob.length())));
                    } catch (ClassNotFoundException | IOException ex) {
                    }
                    img.add(contato);

                }
            }
        } catch (SQLException e) {
            System.out.println("Erro ao pesquisar por imagens");
        }
        return img;
    }

    @Override
    public boolean pesquisa(Imagens m) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
