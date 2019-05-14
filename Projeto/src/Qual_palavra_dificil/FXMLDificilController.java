package Qual_palavra_dificil;

import Palavra_Dificil_Dao.Dao_Dificil;
import Palavra_Dificil_Dao.Dificil;
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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class FXMLDificilController implements Initializable {

    public String falta1, falta2, falta3, falta4;
    public String silabas2[], silabas1[];

    @FXML
    ImageView imagem1, imagem2;
    @FXML
    Label silaba_aparece1, silaba_aparece2;
    @FXML
    TextField preencher1, preencher2, preencher3, preencher4;
    @FXML
    Button opcao1, opcao2, opcao3, opcao4, opcao5, opcao6, opcao7,
            opcao8, opcao9, voltar;
    @FXML
    ProgressBar progresso;

    @FXML
    public void verefica_silabas() {

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
        preencher1.setEditable(false);
        preencher2.setEditable(false);
        preencher3.setEditable(false);
        preencher4.setEditable(false);

        Dao_Dificil a = new Dao_Dificil();
        List<Dificil> palavras = a.pesquisaTodos();

        Collections.shuffle(palavras);// Método usado para embaralhar a lista
        Random r = new Random();//botões

        String silabas1[] = palavras.get(r.nextInt(palavras.size())).getNome_palavra_dificil().split("-");
        
        silaba_aparece1.setText(silabas1[1]);//Atribui ao label a primeira silaba da palavra que vai aparecer
        falta1 = silabas1[0];
        falta2 = silabas1[2];

        String silabas2[] = palavras.get(r.nextInt(palavras.size())).getNome_palavra_dificil().split("-");
        String concat1 = silabas1[0].concat(silabas1[1].concat(silabas1[2]));
        String concat2 = silabas2[0].concat(silabas2[1].concat(silabas2[2]));
       
        do {
            concat2 = silabas2[0].concat(silabas2[1].concat(silabas2[2]));

        } while (concat1.equals(concat2));

        silaba_aparece2.setText(silabas2[1]);//Atribui ao label a primeira silaba da palavra que vai aparecer
        falta3 = silabas2[0];
        falta4 = silabas2[2];

        Dao_Sortidas s = new Dao_Sortidas();
        List<Sortidas> sortidas = s.pesquisaTodos();

        Collections.shuffle(sortidas);// Método usado para embaralhar a lista  
    }

}
