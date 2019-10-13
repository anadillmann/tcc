package Roleta_Dificil;

import Palavra_Dificil_Dao.Dao_Dificil;
import Palavra_Dificil_Dao.Dificil;
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
    Label silaba1, silaba2, silaba3, silaba4, silaba5, silaba6, silaba7,
            silaba8, silaba9, silaba10, silaba11, silaba12, silaba13, silaba14,
            silaba15, silaba16, silaba17, silaba18;
    @FXML
    ImageView r;

    private Label circle[];
    private String faltas[];
    private ArrayList<Button> incorretos;
    String silabas[];
    public String falta1, falta2;
    private Button opcoes[];
    private double progresso_percentagem;
    private ArrayList<String> palavras_sorteadas;
    private TextField preenche[];
    private int cont = 0;

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
            if (silaba_aparece.getText().equals(circle[i].getText())) {
                pos = i;
                break;
            }
        }
        
        RotateTransition girar_roleta = new RotateTransition(Duration.seconds(4)
                , r);
        Random r = new Random();//botões
        int numAleatorio = ((pos + 4) % 18) * 20 + 5 * 360 + 10;
        girar_roleta.setFromAngle(0);
        girar_roleta.setToAngle(numAleatorio);
        girar_roleta.play();
        Timeline animacao = new Timeline(new KeyFrame(Duration.seconds(5), 
                new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                silaba_falta1.setVisible(true);
                silaba_falta2.setVisible(true);
                silaba_aparece.setVisible(true);
                for (int j = 0; j < opcoes.length; j++) {
                    opcoes[j].setVisible(true);
                }

            }
        }));
        animacao.play();

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Font.loadFont(FXMLRoleta_DificilController.class.getResource("Doodletoon line.ttf").toExternalForm(), 10);
        opcoes = new Button[]{opcao1, opcao2, opcao3, opcao4, opcao5, opcao6,
            opcao7, opcao8, opcao9};
        preenche = new TextField[]{silaba_falta1, silaba_falta2};

        progresso_percentagem = 0.10;
        silaba_falta1.setEditable(false);
        silaba_falta2.setEditable(false);
        palavras_sorteadas = new ArrayList();

        String palavra = sortear_palavra();
        palavras_sorteadas.add(palavra);

        for (Button opcao : opcoes) {
            opcao.setOnAction(e -> {
                Button botao = ((Button) e.getSource());
                if (!incorretos.contains(botao)) {
                    for (int i = 0; i < faltas.length; i++) {
                        if (opcao.getText().equals(faltas[i])) {
                            cont++;
                            preenche[i].setText(faltas[i]);
                            opcao.setDisable(true);
                            if (cont == 2) {
                                progresso_percentagem += 0.10;
                                progresso.setProgress(progresso_percentagem);
                                System.out.println("Correta");
                                if (progresso_percentagem >= 1) {
                                    Alert a = new Alert(Alert.AlertType.INFORMATION, ""
                                            + "Prabéns você conseguiu concluir a fase dificil!", ButtonType.YES);
                                    Optional<ButtonType> bt = a.showAndWait();
                                    if (bt.get() == ButtonType.YES) {
                                        ((Stage) progresso.getScene().getWindow()).close();
                                        try {
                                            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Tela_Fim/FXML_Fim.fxml"));
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
                            }

                        }

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
        circle = new Label[]{silaba1, silaba2, silaba3, silaba4,
            silaba5, silaba6, silaba7,
            silaba8, silaba9, silaba10, silaba11, silaba12, silaba13, silaba14,
            silaba15, silaba16, silaba17, silaba18};

        incorretos = new ArrayList();
        silaba_falta1.setText("");
        silaba_falta2.setText("");

        Dao_Dificil a = new Dao_Dificil();
        List<Dificil> palavras = a.pesquisaTodos();

        Collections.shuffle(palavras);// Método usado para embaralhar a lista
        Random r = new Random();//botões
        int i = r.nextInt(palavras.size());

        silabas = palavras.get(i).getNome_palavra_dificil().split("-");
        int id = palavras.get(i).getId_dificil();

        silaba_aparece.setText(silabas[1]);//Atribui ao label a primeira silaba da palavra que vai aparecer
        falta1 = silabas[0];
        falta2 = silabas[2];

        silaba_aparece.setVisible(false);
        silaba_falta1.setVisible(false);
        silaba_falta2.setVisible(false);

        int pos_c = r.nextInt(17) + 0;
        circle[pos_c].setText(silabas[1]);

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

        for (Button op : opcoes) {
            op.setText("");
        }

        int pos1 = r.nextInt(8) + 0;

        opcoes[pos1].setText(silabas[0]);
        opcoes[pos1].setDisable(false);

        int pos2 = 0;
        do {
            pos2 = r.nextInt(8) + 0;
        } while (pos1 == pos2);
        opcoes[pos2].setText(silabas[2]);
        opcoes[pos2].setDisable(false);

        for (int j = 0; j < 9; j++) {
            opcoes[j].setDisable(false);
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

        cont = 0;
        for (int k = 0; k < preenche.length; k++) {
            preenche[k].setText("");
        }
        faltas = new String[]{falta1, falta2};

        return silabas[0] + "-" + silabas[1] + "-" + silabas[2];

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
