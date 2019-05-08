package Imagens;

/**
 *
 * @author anadi
 */
public class SQL_Constantes_Imagem {
         public static final String INSERT = "insert into "
            + "Imagens (idImagens, nome, imagem) "
            + "values (?,?,?)";
    
    public static final String SEARCH = "select imagem from Imagens"; 
}
