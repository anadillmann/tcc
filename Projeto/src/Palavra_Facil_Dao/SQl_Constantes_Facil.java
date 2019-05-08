package Palavra_Facil_Dao;

public class SQl_Constantes_Facil {
    
     public static final String INSERT = "insert into "
            + "Palavra_facil (id_facil, nome_palavra_facil) "
            + "values (?,?)";
    
    public static final String SEARCH = "select nome_palavra_facil from Palavra_facil";  
   
}
