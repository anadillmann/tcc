package Palavra_Facil_Dao;

import java.util.Objects;

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

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 47 * hash + this.id_facil;
        hash = 47 * hash + Objects.hashCode(this.nome_palavra_facil);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Facil other = (Facil) obj;
        if (this.id_facil != other.id_facil) {
            return false;
        }
        if (!Objects.equals(this.nome_palavra_facil, other.nome_palavra_facil)) {
            return false;
        }
        return true;
    }
    
    

}
