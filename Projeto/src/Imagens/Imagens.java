package Imagens;

import java.sql.Blob;

/**
 *
 * @author anadi
 */
public class Imagens {
    
    private int idImagens;
    private String nome;
    private Blob imagem;

    public Imagens(int idImagens, String nome, Blob imagem) {
        this.idImagens = idImagens;
        this.nome = nome;
        this.imagem = imagem;
    }

    public Imagens() {
        
    }

    public int getIdImagens() {
        return idImagens;
    }

    public void setIdImagens(int idImagens) {
        this.idImagens = idImagens;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Blob getImagem() {
        return imagem;
    }

    public void setImagem(Blob imagem) {
        this.imagem = imagem;
    }

    @Override
    public String toString() {
        return "idImagens=" + idImagens + ", nome=" + nome + ", imagem=" + imagem;
    }
    
    
    
    
    
}
