package Qual_palavra_facil;

import Imagens.Dao_Imagens;
import Imagens.Imagens;
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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.util.Duration;

public class FXMLFacilController implements Initializable {

    public String falta;

    @FXML
    ImageView imagem_facil, desenho;
    @FXML
    Label silaba_aparece;
    @FXML
    TextField preencher;
    @FXML
    Button opcao1, opcao2, opcao3, voltar;
    @FXML
    ProgressBar progresso;

    private Button opcoes[];
    private double progresso_percentagem;
    private ArrayList<String> palavras_sorteadas;
    private List<Imagens> img;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Font.loadFont(FXMLFacilController.class.getResource("Doodletoon line.ttf").toExternalForm(), 10);
        Dao_Imagens k = new Dao_Imagens();
        img = k.pesquisaTodos();
        palavras_sorteadas = new ArrayList();
        progresso_percentagem = 0.10;
        progresso.setProgress(progresso_percentagem);
        preencher.setEditable(false);
        desenho.setImage(null);
        String palavra = sorteia_palavra();

        palavras_sorteadas.add(palavra);
        opcoes = new Button[]{opcao1, opcao2, opcao3};

        for (Button opcao : opcoes) {
            opcao.setOnAction(click -> {
                if (opcao.getText().equals(falta)) {
                    progresso_percentagem += 0.10;
                    progresso.setProgress(progresso_percentagem);
                    preencher.setText(opcao.getText());
                    desenho.setImage(img.get(63).getImagem());
                    Timeline animacao = new Timeline(new KeyFrame(Duration.seconds(2), new EventHandler<ActionEvent>() {
                        @Override
                        public void handle(ActionEvent event) {
                            desenho.setImage(null);
                            if (progresso_percentagem >= 1) {
                                Platform.runLater(() -> {
                                    Alert a = new Alert(Alert.AlertType.INFORMATION, ""
                                            + "Prabéns você conseguiu concluir a fase fácil!"
                                            + " Vamos para a fase média?",
                                            ButtonType.YES, ButtonType.NO);
                                    Optional<ButtonType> bt = a.showAndWait();
                                    if (bt.get() == ButtonType.YES) {
                                        ((Stage) progresso.getScene().getWindow()).close();
                                        try {
                                            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Qual_palavra_medio/FXMLMedio.fxml"));
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
                                });
                            } else {
                                String nova_palavra = "";
                                boolean repetida = true;
                                do {
                                    nova_palavra = sorteia_palavra();

                                    if (!nova_palavra.equals(palavras_sorteadas)) {
                                        repetida = false;
                                    }

                                } while (repetida);
                            }
                        }
                    }));
                    //animacao.setCycleCount(Timeline.INDEFINITE);
                    animacao.play();

                } else {
                    desenho.setImage(img.get(64).getImagem());
                    Timeline animacao2 = new Timeline(new KeyFrame(Duration.seconds(2), new EventHandler<ActionEvent>() {
                        @Override
                        public void handle(ActionEvent event) {
                            desenho.setImage(null);
                            Alert a = new Alert(Alert.AlertType.ERROR);
                            a.setTitle("ERRO");
                            a.setContentText("Ops! Sua resposta não está correta, tente de novo");
                            a.show();
                            System.out.println("Incorreta");
                        }

                    }));
                    animacao2.play();
                }
            });
        }
    }

    private String sorteia_palavra() {
        preencher.setText("");
        Dao_Facil a = new Dao_Facil();
        List<Facil> palavras = a.pesquisaTodos();

        Collections.shuffle(palavras);// Método usado para embaralhar a lista
        Random r = new Random();//botões

        int i = r.nextInt(palavras.size());

        String silabas[] = palavras.get(i).getNome_palavra_facil().split("-");
        int id = palavras.get(i).getId_facil();

        silaba_aparece.setText(silabas[0]);//Atribui ao label a primeira silaba da palavra que vai aparecer
        falta = silabas[1];

        Dao_Imagens c = new Dao_Imagens();
        Imagens img = c.pesquisa_facil(id);
        imagem_facil.setImage(img.getImagem());

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
        return silabas[0] + "-" + silabas[1];
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
            Image icon = new Image(getClass().getResourceAsStream("/imagem/abc.png"));
            stage.getIcons().add(icon);
            stage.setScene(scene);
            stage.show();

        } catch (IOException ex) {
            System.out.println("Erro ao abrir janela");
            ex.printStackTrace();
        }

    }

}
