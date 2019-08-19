package Regras_Qualpalavra;

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
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class FXMLRegra_QualpalavraController implements Initializable {

    @FXML
    Button voltar, jogar;
    @FXML
    TextArea regras;

    @FXML
    public void jogar(ActionEvent event) {
        jogar.getScene().getWindow().hide();

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Qual_palavra_nivel/FXMLNivel.fxml"));
            Parent root = loader.load();

            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setResizable(false);
            Image icon = new Image(getClass().getResourceAsStream("/imagem/abc.png"));
            stage.getIcons().add(icon);
            stage.setScene(scene);
            stage.show();

        } catch (IOException ex) {
            System.out.println("Erro ao abrir janela");
            ex.printStackTrace();
        }

    }

    @FXML
    public void voltar(ActionEvent event) {
        voltar.getScene().getWindow().hide();

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/EscolherJogo/FXMLEscolherJogo.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setResizable(false);
            Image icon = new Image(getClass().getResourceAsStream("/imagem/abc.png"));
            stage.getIcons().add(icon);
            stage.setScene(scene);
            stage.show();

        } catch (IOException ex) {
            System.out.println("Erro ao abrir janela");
            ex.printStackTrace();
        }

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        regras.setEditable(false);
        Font.loadFont(FXMLRegra_QualpalavraController.class.getResource("Doodletoon line.ttf").toExternalForm(), 10);

    }

}
