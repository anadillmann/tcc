package Sortidas_Dao;

/**
 *
 * @author anadi
 */
public class SQL_Constantes_Sortidas {

    public static final String INSERT = "insert into "
            + "Silabas_sortidas (idSilabas, silaba) "
            + "values (?,?)";

    public static final String SEARCH = "select silaba from Silabas_sortidas";

}
