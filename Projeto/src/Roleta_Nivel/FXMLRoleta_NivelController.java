package Roleta_Nivel;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class FXMLRoleta_NivelController implements Initializable {

    @FXML
    Button voltar, facil, medio, dificil;

    @FXML
    public void facil() {
        facil.getScene().getWindow().hide();

        try {
            FXMLLoader loader = new FXMLLoader(getClass()
                    .getResource("/Roleta_Facil/FXMLRoleta_Facil.fxml"));
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
    public void medio() {

        medio.getScene().getWindow().hide();

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Roleta_Medio/FXMLRoleta_Medio.fxml"));
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
    public void dificil() {
        dificil.getScene().getWindow().hide();

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Roleta_Dificil/FXMLRoleta_Dificil.fxml"));
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
    public void voltar() {
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
        Font.loadFont(FXMLRoleta_NivelController.class.getResource("Doodletoon line.ttf").toExternalForm(), 10);

    }

}
