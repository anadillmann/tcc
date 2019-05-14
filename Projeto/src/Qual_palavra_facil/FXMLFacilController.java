package Qual_palavra_facil;

import Palavra_Facil_Dao.Dao_Facil;
import Palavra_Facil_Dao.Facil;
import Sortidas_Dao.Dao_Sortidas;
import Sortidas_Dao.Sortidas;
import java.io.IOException;
import java.net.URL;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class FXMLFacilController implements Initializable {

    public String falta;

    @FXML
    ImageView imagem_facil;
    @FXML
    Label silaba_aparece;
    @FXML
    TextField preencher;
    @FXML
    Button opcao1, opcao2, opcao3, voltar;
    @FXML
    ProgressBar progresso;

    @FXML
    public void verefica_silaba(ActionEvent event) {
        opcao1.setOnAction(click -> {
            if (opcao1.getText().equals(falta)) {
                System.out.println("Correta");
                preencher.setText(opcao1.getText());
            } else {
                System.out.println("Incorreta");
                preencher.setText(falta);
            }
        }
        );
        
        opcao2.setOnAction(click -> {
            if (opcao2.getText().equals(falta)) {
                System.out.println("Correta");
                preencher.setText(opcao2.getText());
            } else {
                System.out.println("Incorreta");
                preencher.setText(falta);
            }
        }
        );

        opcao3.setOnAction(click -> {
            if (opcao3.getText().equals(falta)) {
                System.out.println("Correta");
                preencher.setText(opcao3.getText());
            } else {
                System.out.println("Incorreta");
                preencher.setText(falta);
            }
        }
        );

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
        preencher.setEditable(false);
        Dao_Facil a = new Dao_Facil();
        List<Facil> palavras = a.pesquisaTodos();

        Collections.shuffle(palavras);// Método usado para embaralhar a lista
        Random r = new Random();//botões

        String silabas[] = palavras.get(r.nextInt(palavras.size())).getNome_palavra_facil().split("-");
        silaba_aparece.setText(silabas[0]);//Atribui ao label a primeira silaba da palavra que vai aparecer
        falta = silabas[1];

        Dao_Sortidas s = new Dao_Sortidas();
        List<Sortidas> sortidas = s.pesquisaTodos();
        Collections.shuffle(sortidas);// Método usado para embaralhar a lista        

        int n = r.nextInt(3) + 1;
        switch (n) {
            case 1:
                opcao1.setText(silabas[1]);
                opcao2.setText(sortidas.get(r.nextInt(sortidas.size())).getNome_sortida());
                opcao3.setText(sortidas.get(r.nextInt(sortidas.size())).getNome_sortida());
                break;
            case 2:
                opcao2.setText(silabas[1]);
                opcao1.setText(sortidas.get(r.nextInt(sortidas.size())).getNome_sortida());
                opcao3.setText(sortidas.get(r.nextInt(sortidas.size())).getNome_sortida());
                break;
            case 3:
                opcao3.setText(silabas[1]);
                opcao2.setText(sortidas.get(r.nextInt(sortidas.size())).getNome_sortida());
                opcao1.setText(sortidas.get(r.nextInt(sortidas.size())).getNome_sortida());
                break;

        }
    }

}
