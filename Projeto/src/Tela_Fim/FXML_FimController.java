package Tela_Fim;

import Inicial.Aluno;
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
import javafx.scene.image.Image;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class FXML_FimController implements Initializable {

    @FXML
    Button escolher;

    @FXML
    Label nome;

    @FXML
    public void escolher_jogo() {
        escolher.getScene().getWindow().hide();

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/EscolherJogo/FXMLEscolherJogo.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            Image icon = new Image(getClass().getResourceAsStream("/imagem/abc.png"));
            stage.getIcons().add(icon);
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

        nome.setText(Aluno.getNome());

        Font.loadFont(FXML_FimController.class.getResource("Doodletoon line.ttf").toExternalForm(), 10);
        // TODO
    }

}
