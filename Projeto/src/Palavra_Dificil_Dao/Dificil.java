package Palavra_Dificil_Dao;

import java.util.Objects;

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

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 37 * hash + this.id_dificil;
        hash = 37 * hash + Objects.hashCode(this.nome_palavra_dificil);
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
        final Dificil other = (Dificil) obj;
        if (this.id_dificil != other.id_dificil) {
            return false;
        }
        if (!Objects.equals(this.nome_palavra_dificil, other.nome_palavra_dificil)) {
            return false;
        }
        return true;
    }
    
    

}
