package Qual_palavra_medio;

import Imagens.Dao_Imagens;
import Imagens.Imagens;
import Palavra_Medio_Dao.Dao_Medio;
import Palavra_Medio_Dao.Medio;
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
import javafx.animation.Timeline;
import javafx.application.Platform;
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
import javafx.scene.image.ImageView;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.util.Duration;

public class FXMLMedioController implements Initializable {

    @FXML
    ImageView imagem1, imagem2, desenho;
    @FXML
    Label silaba_aparece1, silaba_aparece2;
    @FXML
    TextField preencher1, preencher2, preencher3, preencher4;
    @FXML
    Button opcao1, opcao2, opcao3, opcao4, opcao5, opcao6, voltar;
    @FXML
    ProgressBar progresso;

    private Button opcoes[];
    private String faltas[];
    private TextField preenche[];
    private Dao_Medio dao;
    private ArrayList<String> palavras_sorteadas;
    private double progresso_percentagem;
    private int cont = 0;
    private List<Medio> palavras;
    private Dao_Imagens dao_imagens;

    public String falta1, falta2, falta3, falta4;
    public String silabas1[], silabas2[];
    private ArrayList<Button> incorretos;
    private List<Imagens> img;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Font.loadFont(FXMLMedioController.class.getResource("Doodletoon line.ttf").toExternalForm(), 10);

        dao = new Dao_Medio();
        dao_imagens = new Dao_Imagens();
        palavras_sorteadas = new ArrayList();
        progresso_percentagem = 0.10;
        progresso.setProgress(progresso_percentagem);
        preenche = new TextField[]{preencher1, preencher2, preencher3, preencher4};

        desenho.setImage(null);
        img = dao_imagens.pesquisaTodos();

        palavras = dao.pesquisaTodos();

        for (TextField t : preenche) {
            t.setEditable(false);
        }

        opcoes = new Button[]{opcao1, opcao2, opcao3, opcao4, opcao5, opcao6};
        sortear_palavras();

        for (Button opcao : opcoes) {
            opcao.setOnAction(e -> {
                Button botao = ((Button) e.getSource());
                if (!incorretos.contains(botao)) {
                    for (int i = 0; i < faltas.length; i++) {
                        if (opcao.getText().equals(faltas[i])) {
                            cont++;
                            preenche[i].setText(faltas[i]);
                            opcao.setDisable(true);
                            if (cont == 4) {
                                desenho.setImage(img.get(63).getImagem());
                                Timeline animacao = new Timeline(new KeyFrame(Duration.seconds(2), new EventHandler<ActionEvent>() {
                                    @Override
                                    public void handle(ActionEvent event) {
                                        desenho.setImage(null);
                                        progresso_percentagem += 0.10;
                                        progresso.setProgress(progresso_percentagem);
                                        System.out.println("Correta");
                                        if (progresso_percentagem >= 1) {
                                            Platform.runLater(() -> {
                                                Alert a = new Alert(Alert.AlertType.INFORMATION, ""
                                                        + "Prabéns você conseguiu concluir a fase média!"
                                                        + " Vamos para a fase difícil?",
                                                        ButtonType.YES, ButtonType.NO);
                                                Optional<ButtonType> bt = a.showAndWait();
                                                if (bt.get() == ButtonType.YES) {
                                                    ((Stage) progresso.getScene().getWindow()).close();
                                                    try {
                                                        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Qual_palavra_dificil/FXMLDificil.fxml"));
                                                        Parent root = loader.load();
                                                        Scene scene = new Scene(root);
                                                        Stage stage = new Stage();
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
                                                        stage.setScene(scene);
                                                        stage.show();

                                                    } catch (IOException ex) {
                                                        System.out.println("Erro ao abrir janela");
                                                        ex.printStackTrace();
                                                    }
                                                }
                                            });

                                        } else {
                                            sortear_palavras();
                                        }

                                    }

                                }));
                                animacao.play();

                            }

                        }

                    }

                } else {
                    desenho.setImage(img.get(64).getImagem());
                    Timeline animacao2 = new Timeline(new KeyFrame(Duration.seconds(2), new EventHandler<ActionEvent>() {
                        @Override
                        public void handle(ActionEvent event) {
                            Platform.runLater(() -> {
                                desenho.setImage(null);
                                Alert a = new Alert(Alert.AlertType.ERROR);
                                a.setTitle("ERRO");
                                a.setContentText("Ops! Sua resposta não está correta, tente de novo");
                                a.show();
                                System.out.println("Incorreta");
                            });
                        }

                    }
                    ));
                    animacao2.play();
                }

            });
        }
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

    private void sortear_palavras() {
        incorretos = new ArrayList();
        preencher1.setText("");
        preencher2.setText("");
        preencher3.setText("");
        preencher4.setText("");

        for (int i = 0; i < opcoes.length; i++) {
            Button op = opcoes[i];

            op.setText("");
        }

        Collections.shuffle(palavras);// Método usado para embaralhar a lista
        Random r = new Random();//botões

        int k = r.nextInt(palavras.size());
        Medio pal1 = palavras.get(k);
        silabas1 = pal1.getNome_palavra_medio().split("-");
        silaba_aparece1.setText(silabas1[1]);//Atribui ao label a primeira silaba da palavra que vai aparecer
        falta1 = silabas1[0];
        falta2 = silabas1[2];
        String concat1 = silabas1[0].concat(silabas1[1].concat(silabas1[2]));
        int id1 = pal1.getId_medio();
        Imagens img = dao_imagens.pesquisa_medio(id1);
        imagem1.setImage(img.getImagem());
        palavras.remove(pal1);
        palavras_sorteadas.add(concat1);

        int j = r.nextInt(palavras.size());
        Medio pal2 = palavras.get(j);
        silabas2 = pal2.getNome_palavra_medio().split("-");
        silaba_aparece2.setText(silabas2[1]);//Atribui ao label a primeira silaba da palavra que vai aparecer
        falta3 = silabas2[0];
        falta4 = silabas2[2];
        String concat2 = silabas2[0].concat(silabas2[1].concat(silabas2[2]));
        int id2 = pal2.getId_medio();
        Imagens img1 = dao_imagens.pesquisa_medio(id2);
        imagem2.setImage(img1.getImagem());
        palavras.remove(pal2);
        palavras_sorteadas.add(concat2);

        Dao_Sortidas s = new Dao_Sortidas();
        List<Sortidas> sortidas = s.pesquisaTodos();

        Collections.shuffle(sortidas);// Método usado para embaralhar a lista

        int pos1 = r.nextInt(5) + 0;

        opcoes[pos1].setText(silabas1[0]);
        opcoes[pos1].setDisable(false);
        int pos2 = 0;

        do {
            pos2 = r.nextInt(5) + 0;
        } while (pos1 == pos2);
        opcoes[pos2].setText(silabas1[2]);
        opcoes[pos2].setDisable(false);

        int pos3 = 0;
        do {
            pos3 = r.nextInt(5) + 0;
        } while (pos1 == pos3 || pos2 == pos3);
        opcoes[pos3].setText(silabas2[0]);
        opcoes[pos3].setDisable(false);

        int pos4 = 0;
        do {
            pos4 = r.nextInt(5) + 0;
        } while (pos1 == pos4 || pos2 == pos4 || pos3 == pos4);
        opcoes[pos4].setText(silabas2[2]);
        opcoes[pos4].setDisable(false);

        for (int i = 0; i < 6; i++) {
            opcoes[i].setDisable(false);
            if (opcoes[i].getText().isEmpty()) {
                String sortida_sorteada;
                do {
                    sortida_sorteada = sortidas.get(r.nextInt(sortidas.size())).getNome_sortida();
                } while (esta_contida(sortida_sorteada));
                opcoes[i].setText(sortida_sorteada);
                incorretos.add(opcoes[i]);
            }
        }
        cont = 0;
        for (int i = 0; i < preenche.length; i++) {
            preenche[i].setText("");
        }
        faltas = new String[]{falta1, falta2, falta3, falta4};
    }

    private boolean esta_contida(String sortida_sorteada) {
        for (int i = 0; i < silabas1.length; i++) {
            if (silabas1[i].equals(sortida_sorteada) || silabas2[i].equals(sortida_sorteada)) {
                return true;
            }
        }
        return false;
    }
}
