package Palavra_Medio_Dao;

/**
 *
 * @author anadi
 */
public class SQL_Constantes_Medio {

    public static final String INSERT = "insert into "
            + "Palavra_media (id_medio, nome_palavra_media) "
            + "values (?,?)";

    public static final String SEARCH = "select nome_palavra_media from Palavra_media";

}
