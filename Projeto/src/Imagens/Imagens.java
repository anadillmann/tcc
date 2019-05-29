package Imagens;
import java.util.Objects;
import javafx.scene.image.Image;

/**
 *
 * @author anadi
 */
public class Imagens {
    
    private int idImagens;
    private String nome;
    private Image imagem;

    public Imagens(int idImagens, String nome, Image imagem) {
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

    public Image getImagem() {
        return imagem;
    }

    public void setImagem(Image imagem) {
        this.imagem = imagem;
    }

    @Override
    public String toString() {
        return "idImagens=" + idImagens + ", nome=" + nome + ", imagem=" + imagem;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 41 * hash + this.idImagens;
        hash = 41 * hash + Objects.hashCode(this.nome);
        hash = 41 * hash + Objects.hashCode(this.imagem);
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
        final Imagens other = (Imagens) obj;
        if (this.idImagens != other.idImagens) {
            return false;
        }
        if (!Objects.equals(this.nome, other.nome)) {
            return false;
        }
        if (!Objects.equals(this.imagem, other.imagem)) {
            return false;
        }
        return true;
    }
    
    
    
    
    
    
}
