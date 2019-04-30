package Qual_palavra_facil;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class FXMLFacilController implements Initializable {

    @FXML
    ImageView imagem_facil;
    Label silaba_aparece;
    TextField preencher;
    Button opcao1, opcao2, opcao3, voltar;

    @FXML
    public void verefica_silaba() {
        //vereficar se o botão que o usuário clicou é o correto

    }

    @FXML
    public void voltar(ActionEvent event) {

        voltar.getScene().getWindow().hide();
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Qual_palavra_nivel/FXMLNivel.fxml"));
            Parent root = loader.load();

            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setResizable(false);
            stage.setScene(scene);
            stage.show();

        } catch (IOException ex) {
            System.out.println("Erro ao abrir janela");
            ex.printStackTrace();
        }

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // sortear palavra, selecionar sílaba que vai aparecer, selecionar silabas aleatórias
    }

}
