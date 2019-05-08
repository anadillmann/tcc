package Palavra_Dificil_Dao;

/**
 *
 * @author anadi
 */
public class Dificil {

    private int id_dificil;
    private String nome_palavra_dificil;

    public Dificil(int id_dificil, String nome_palavra_dificil) {
        this.id_dificil = id_dificil;
        this.nome_palavra_dificil = nome_palavra_dificil;
    }

    public Dificil() {

    }

    public int getId_dificil() {
        return id_dificil;
    }

    public void setId_dificil(int id_dificil) {
        this.id_dificil = id_dificil;
    }

    public String getNome_palavra_dificil() {
        return nome_palavra_dificil;
    }

    public void setNome_palavra_dificil(String nome_palavra_dificil) {
        this.nome_palavra_dificil = nome_palavra_dificil;
    }

    @Override
    public String toString() {
        return "id_dificil=" + id_dificil + ", nome_palavra_dificil=" + nome_palavra_dificil;
    }
    
    

}
