package Sortidas_Dao;

/**
 *
 * @author anadi
 */
public class Sortidas {

    private int id_sortida;
    private String nome_sortida;

    public Sortidas(int id_sortida, String nome_sortida) {
        this.id_sortida = id_sortida;
        this.nome_sortida = nome_sortida;
    }

    public Sortidas() {
        
    }

    public int getId_sortida() {
        return id_sortida;
    }

    public void setId_sortida(int id_sortida) {
        this.id_sortida = id_sortida;
    }

    public String getNome_sortida() {
        return nome_sortida;
    }

    public void setNome_sortida(String nome_sortida) {
        this.nome_sortida = nome_sortida;
    }

    @Override
    public String toString() {
        return "id_sortida=" + id_sortida + ", nome_sortida=" + nome_sortida;
    }
    
    
    
    
    

}
