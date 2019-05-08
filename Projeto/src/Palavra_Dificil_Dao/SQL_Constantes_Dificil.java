package Palavra_Dificil_Dao;

/**
 *
 * @author anadi
 */
public class SQL_Constantes_Dificil {
     public static final String INSERT = "insert into "
            + "Palavra_dificil (id_dificil, nome_palavra_dificil) "
            + "values (?,?)";
    
    public static final String SEARCH = "select nome_palavra_dificil from Palavra_dificil"; 
    
}
