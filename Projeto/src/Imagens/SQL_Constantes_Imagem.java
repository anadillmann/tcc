package Imagens;

/**
 *
 * @author anadi
 */
public class SQL_Constantes_Imagem {

    public static final String INSERT = "insert into "
            + "Imagens (nome, imagem) "
            + "values (?,?)";

    public static final String SEARCH_FACIL = "select idImagens, nome, imagem "
            + "FROM Imagens join Palavra_facil_has_imagens join Palavra_facil "
            + "where idImagens = Imagens_idImagens AND "
            + "id_facil = Palavra_facil_id_facil AND Palavra_facil_id_facil = ?";

    public static final String SEARCH_MEDIO = "select idImagens, nome, imagem "
            + "FROM Imagens join palavra_media_has_imagens join palavra_media "
            + "where idImagens = Imagens_idImagens AND "
            + "id_media = Palavra_media_id_media AND Palavra_media_id_media = ?";

    public static final String SEARCH_DIFICIL = "select idImagens, nome, imagem "
            + "FROM Imagens join palavra_dificil_has_imagens join palavra_dificil "
            + "where idImagens = Imagens_idImagens AND "
            + "id_dificil = Palavra_dificil_id_dificil AND Palavra_dificil_id_dificil = ?";

    public static final String SEARCH_ALL = "SELECT * FROM Imagens";
}
