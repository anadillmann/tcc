package Palavra_Facil_Dao;

public class Facil {

    private int id_facil;
    private String nome_palavra_facil;

    public Facil(int id_facil, String nome_palavra_facil) {
        this.id_facil = id_facil;
        this.nome_palavra_facil = nome_palavra_facil;
    }

    public Facil() {

    }

    public int getId_facil() {
        return id_facil;
    }

    public void setId_facil(int id_facil) {
        this.id_facil = id_facil;
    }

    public String getNome_palavra_facil() {
        return nome_palavra_facil;
    }

    public void setNome_palavra_facil(String nome_palavra_facil) {
        this.nome_palavra_facil = nome_palavra_facil;
    }

    @Override
    public String toString() {
        return "id_facil=" + id_facil + ", nome_palavra_facil=" + nome_palavra_facil + '}';
    }

}
