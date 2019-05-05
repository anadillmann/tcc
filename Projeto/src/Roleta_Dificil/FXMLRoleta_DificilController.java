package Roleta_Dificil;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class FXMLRoleta_DificilController implements Initializable {

    @FXML
    Button opcao1, opcao2, opcao3, opcao4, opcao5, opcao6, opcao7,
            opcao8, opcao9, voltar;
    @FXML
    Label silaba_aparece;
    @FXML
    TextField silaba_falta1, silaba_falta2;
    @FXML
    ProgressBar progresso;

    @FXML
    public void verefica_silaba() {

    }

    @FXML
    public void voltar() {
        voltar.getScene().getWindow().hide();

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Roleta_Nivel/FXMLRoleta_Nivel.fxml"));
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
        // TODO
    }

}
