package Palavra_Medio_Dao;

/**
 *
 * @author anadi
 */
public class Medio {

    private int id_medio;
    private String nome_palavra_medio;

    public Medio(int id_medio, String nome_palavra_medio) {
        this.id_medio = id_medio;
        this.nome_palavra_medio = nome_palavra_medio;
    }

    public Medio() {
        
    }

    public int getId_medio() {
        return id_medio;
    }

    public void setId_medio(int id_medio) {
        this.id_medio = id_medio;
    }

    public String getNome_palavra_medio() {
        return nome_palavra_medio;
    }

    public void setNome_palavra_medio(String nome_palavra_medio) {
        this.nome_palavra_medio = nome_palavra_medio;
    }

    @Override
    public String toString() {
        return "id_medio=" + id_medio + ", nome_palavra_medio=" + nome_palavra_medio;
    }
    
    
    
    

}
