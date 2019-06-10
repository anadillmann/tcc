package Palavra_Medio_Dao;

import java.util.Objects;

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

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 83 * hash + this.id_medio;
        hash = 83 * hash + Objects.hashCode(this.nome_palavra_medio);
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
        final Medio other = (Medio) obj;
        if (this.id_medio != other.id_medio) {
            return false;
        }
        if (!Objects.equals(this.nome_palavra_medio, other.nome_palavra_medio)) {
            return false;
        }
        return true;
    }
    
    
    
    

}
