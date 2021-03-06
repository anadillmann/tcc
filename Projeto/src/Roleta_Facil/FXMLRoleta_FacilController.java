package Roleta_Facil;

import Palavra_Facil_Dao.Dao_Facil;
import Palavra_Facil_Dao.Facil;
import Sortidas_Dao.Dao_Sortidas;
import Sortidas_Dao.Sortidas;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.ResourceBundle;
import javafx.animation.KeyFrame;
import javafx.animation.RotateTransition;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.util.Duration;

public class FXMLRoleta_FacilController implements Initializable {

    @FXML
    Button opcao1, opcao2, opcao3, voltar, girar;
    @FXML
    Label silaba_mostra;
    @FXML
    TextField silaba_falta;
    @FXML
    ProgressBar progresso;
    @FXML
    Label silaba1, silaba2, silaba3, silaba4, silaba5, silaba6, silaba7,
            silaba8, silaba9, silaba10, silaba11, silaba12, silaba13, silaba14,
            silaba15, silaba16, silaba17, silaba18;
    @FXML
    Canvas roleta;

    @FXML
    ImageView r;

    String falta;
    String silabas[];
    private Label circle[];
    private ArrayList<Button> incorretos;
    private Button opcoes[];
    private double progresso_percentagem;
    private ArrayList<String> palavras_sorteadas;
    private GraphicsContext ctx;

    @FXML
    public void voltar() {
        voltar.getScene().getWindow().hide();

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Roleta_Nivel/FXMLRoleta_Nivel.fxml"));
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
    public void girar() {

        int pos = 0;

        for (int i = 0; i < circle.length; i++) {
            if (silaba_mostra.getText().equals(circle[i].getText())) {
                pos = i;
                break;
            }
        }

        RotateTransition girar_roleta = new RotateTransition(Duration.seconds(4), r);
        Random r = new Random();//botões

        int numAleatorio = 90 + 5 * 360 + pos * 20;
        //Como a seta inciia no meio da roleta, é necessário que comece na posição 0 (90º)
        //ele gira 5 vezes
        //Em seguida, de acordo com a posição que tiver a resposta ele vai se deslocar
        girar_roleta.setFromAngle(0);
        girar_roleta.setToAngle(numAleatorio);

        girar_roleta.play();

        Timeline animacao = new Timeline(new KeyFrame(Duration.seconds(5), new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                silaba_falta.setVisible(true);
                silaba_mostra.setVisible(true);

                for (int j = 0; j < opcoes.length; j++) {
                    opcoes[j].setVisible(true);
                }

            }
        }));
        animacao.play();

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Font.loadFont(FXMLRoleta_FacilController.class.getResource("Doodletoon line.ttf").toExternalForm(), 10);
        opcoes = new Button[]{opcao1, opcao2, opcao3};
        progresso_percentagem = 0.10;
        silaba_falta.setEditable(false);
        palavras_sorteadas = new ArrayList();

        String palavra = sortear_palavra();
        palavras_sorteadas.add(palavra);

        for (Button opcao : opcoes) {
            opcao.setOnAction(click -> {
                if (opcao.getText().equals(falta)) {
                    progresso_percentagem += 0.10;
                    progresso.setProgress(progresso_percentagem);
                    System.out.println("Correta");

                    silaba_falta.setText(opcao.getText());

                    if (progresso_percentagem >= 1) {
                        Alert a = new Alert(Alert.AlertType.INFORMATION, ""
                                + "Prabéns você conseguiu concluir a fase fácil!"
                                + " Vamos para a fase média?",
                                ButtonType.YES, ButtonType.NO);
                        Optional<ButtonType> bt = a.showAndWait();
                        if (bt.get() == ButtonType.YES) {
                            ((Stage) progresso.getScene().getWindow()).close();
                            try {
                                FXMLLoader loader = new FXMLLoader(getClass().getResource("/Roleta_Medio/FXMLRoleta_Medio.fxml"));
                                Parent root = loader.load();
                                Scene scene = new Scene(root);
                                Stage stage = new Stage();
                                Image icon = new Image(getClass().getResourceAsStream("/imagem/abc.png"));
                                stage.getIcons().add(icon);
                                stage.setScene(scene);
                                stage.show();

                            } catch (IOException ex) {
                                System.out.println("Erro ao abrir janela");
                                ex.printStackTrace();
                            }

                        } else {
                            try {
                                FXMLLoader loader = new FXMLLoader(getClass().getResource("/Inicial/FXMLInicial.fxml"));
                                Parent root = loader.load();

                                Scene scene = new Scene(root);
                                Stage stage = new Stage();
                                Image icon = new Image(getClass().getResourceAsStream("/imagem/abc.png"));
                                stage.getIcons().add(icon);
                                stage.setScene(scene);
                                stage.show();

                            } catch (IOException ex) {
                                System.out.println("Erro ao abrir janela");
                                ex.printStackTrace();
                            }

                        }

                    } else {
                        String nova_palavra = "";
                        boolean repetida = true;
                        do {
                            nova_palavra = sortear_palavra();

                            if (!nova_palavra.equals(palavras_sorteadas)) {
                                repetida = false;
                            }

                        } while (repetida);
                    }

                } else {
                    Alert a = new Alert(Alert.AlertType.ERROR);
                    a.setTitle("ERRO");
                    a.setContentText("Ops! Sua resposta não está correta, tente de novo");
                    a.showAndWait();
                    System.out.println("Incorreta");
                }

            });

        }
    }

    private String sortear_palavra() {

        circle = new Label[]{silaba1, silaba2, silaba3, silaba4, silaba5, silaba6, silaba7,
            silaba8, silaba9, silaba10, silaba11, silaba12, silaba13, silaba14,
            silaba15, silaba16, silaba17, silaba18};

        incorretos = new ArrayList();
        silaba_falta.setVisible(false);

        silaba_falta.setText("");
        Dao_Facil a = new Dao_Facil();
        List<Facil> palavras = a.pesquisaTodos();

        Collections.shuffle(palavras);// Método usado para embaralhar a lista
        Random r = new Random();//botões
        int i = r.nextInt(palavras.size());

        silabas = palavras.get(i).getNome_palavra_facil().split("-");
        int id = palavras.get(i).getId_facil();

        silaba_mostra.setText(silabas[0]);//Atribui ao label a primeira silaba da palavra que vai aparecer
        silaba_mostra.setVisible(false);
        falta = silabas[1];

        int pos1 = r.nextInt(17) + 0;
        circle[pos1].setText(silabas[0]);

        Dao_Sortidas s = new Dao_Sortidas();
        List<Sortidas> sortidas = s.pesquisaTodos();
        Collections.shuffle(sortidas);// Método usado para embaralhar a lista

        for (int j = 0; j < 18; j++) {
            if (circle[j].getText().isEmpty()) {
                String silaba_sortida;
                silaba_sortida = sortidas.get(r.nextInt(sortidas.size())).getNome_sortida();
                circle[j].setText(silaba_sortida);
            }

        }

        int pos = r.nextInt(3) + 0;
        opcoes[pos].setText(silabas[1]);

        for (int j = 0; j < 3; j++) {
            if (opcoes[j].getText().isEmpty()) {
                String sortida_sorteada;
                do {
                    sortida_sorteada = sortidas.get(r.nextInt(sortidas.size())).getNome_sortida();
                } while (esta_contida(sortida_sorteada));
                opcoes[j].setText(sortida_sorteada);
                incorretos.add(opcoes[j]);
            }
            opcoes[j].setVisible(false);
        }

        return silabas[0] + "-" + silabas[1];
    }

    private boolean esta_contida(String sortida_sorteada) {
        for (int i = 0; i < silabas.length; i++) {
            if (silabas[i].equals(sortida_sorteada)) {
                return true;
            }
        }
        return false;
    }

}
