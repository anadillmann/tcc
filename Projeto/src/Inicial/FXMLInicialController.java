package Inicial;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class FXMLInicialController implements Initializable {

    @FXML
    Button entrar;
    @FXML
    TextField nome;

    @FXML
    public void entrar(ActionEvent event) {
        if (nome.getText().isEmpty()) {
            Alert dialogoErro = new Alert(Alert.AlertType.ERROR);
            dialogoErro.setTitle("Erro!");
            dialogoErro.setHeaderText("Ocorreu um erro ao acesar");
            dialogoErro.setContentText("Acho que você não inseriu seu nome! ");
            dialogoErro.showAndWait();
            
        }else{
            entrar.getScene().getWindow().hide();

            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/EscolherJogo/FXMLEscolherJogo.fxml"));
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
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

}
