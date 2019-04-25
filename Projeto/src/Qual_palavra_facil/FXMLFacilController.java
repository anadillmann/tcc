package Qual_palavra_facil;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;

public class FXMLFacilController implements Initializable {

    @FXML
    Image imagem_facil;
    Label silaba_aparece;
    TextField preencher;
    Button opcao1, opcao2, opcao3;
    
    
    @FXML
    public void verefica_silaba(){
        //vereficar se o botão que o usuário clicou é o correto
        
    }
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // sortear palavra, selecionar sílaba que vai aparecer, selecionar silabas aleatórias
    }    
    
}
