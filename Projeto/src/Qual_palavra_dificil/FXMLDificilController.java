package Qual_palavra_dificil;

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
import javafx.event.ActionEvent;
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
import javafx.stage.Stage;

public class FXMLDificilController implements Initializable {

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

    private Button opcoes[];
    private String faltas[];
    private TextField preenche[];
    private Dao_Dificil dao;
    private ArrayList<String> palavras_sorteadas;
    private double progresso_percentagem;
    private int cont = 0;

    public String falta1, falta2, falta3, falta4;
    public String silabas1[], silabas2[];
    private ArrayList<Button> incorretos;

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
        dao = new Dao_Dificil();
        palavras_sorteadas = new ArrayList();
        progresso_percentagem = 0.10;
        progresso.setProgress(progresso_percentagem);
        preenche = new TextField[]{preencher1, preencher2, preencher3, preencher4};
        
        for (TextField t : preenche) {
            t.setEditable(false);
        }
        
        opcoes = new Button[]{opcao1, opcao2, opcao3, opcao4, opcao5, 
            opcao6, opcao7, opcao8, opcao9};
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
                                progresso_percentagem += 0.10;
                                progresso.setProgress(progresso_percentagem);
                                System.out.println("Correta");
                                if (progresso_percentagem >= 1) {
                                    Alert a = new Alert(Alert.AlertType.INFORMATION, ""
                                            + "Prabéns você conseguiu concluir a fase fácil!"
                                            + " Vamos para a fase média?",
                                            ButtonType.YES, ButtonType.NO);
                                    Optional<ButtonType> bt = a.showAndWait();
                                    if (bt.get() == ButtonType.YES) {
                                        ((Stage) progresso.getScene().getWindow()).close();
                                        try {
                                            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Qual_palavra_dificil/FXMLDificilController"));
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

                                } else {
                                    opcao.setDisable(false);
                                    sortear_palavras();
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

    private void sortear_palavras() {
incorretos = new ArrayList();
        preencher1.setText("");
        preencher2.setText("");
        preencher3.setText("");
        preencher4.setText("");

        List<Dificil> palavras = dao.pesquisaTodos();
        
        for (int i = 0; i < opcoes.length; i++) {
            Button op = opcoes[i];

            op.setText("");
        }
        
        Collections.shuffle(palavras);// Método usado para embaralhar a lista
        Random r = new Random();//botões

        silabas1 = palavras.get(r.nextInt(palavras.size())).getNome_palavra_dificil().split("-");
        silaba_aparece1.setText(silabas1[1]);//Atribui ao label a primeira silaba da palavra que vai aparecer
        falta1 = silabas1[0];
        falta2 = silabas1[2];

        boolean repetida = true;
        String concat1, concat2;

        do {
            silabas2 = palavras.get(r.nextInt(palavras.size())).getNome_palavra_dificil().split("-");
            concat1 = silabas1[0].concat(silabas1[1].concat(silabas1[2]));
            concat2 = silabas2[0].concat(silabas2[1].concat(silabas2[2]));

            if (!concat1.equals(concat2)
                    && !palavras_sorteadas.contains(concat1)
                    && !palavras_sorteadas.contains(concat2)) {
                repetida = false;
            }
        } while (repetida);
        palavras_sorteadas.add(concat1);
        palavras_sorteadas.add(concat2);
        silaba_aparece2.setText(silabas2[1]);//Atribui ao label a primeira silaba da palavra que vai aparecer
        falta3 = silabas2[0];
        falta4 = silabas2[2];

        Dao_Sortidas s = new Dao_Sortidas();
        List<Sortidas> sortidas = s.pesquisaTodos();

        Collections.shuffle(sortidas);// Método usado para embaralhar a lista

        int pos1 = r.nextInt(8) + 0;

        opcoes[pos1].setText(silabas1[0]);
        opcoes[pos1].setDisable(false);
        int pos2 = 0;

        do {
            pos2 = r.nextInt(8) + 0;
        } while (pos1 == pos2);
        opcoes[pos2].setText(silabas1[2]);
        opcoes[pos2].setDisable(false);

        int pos3 = 0;
        do {
            pos3 = r.nextInt(8) + 0;
        } while (pos1 == pos3 || pos2 == pos3);
        opcoes[pos3].setText(silabas2[0]);
        opcoes[pos3].setDisable(false);

        int pos4 = 0;
        do {
            pos4 = r.nextInt(8) + 0;
        } while (pos1 == pos4 || pos2 == pos4 || pos3 == pos4);
        opcoes[pos4].setText(silabas2[2]);
        opcoes[pos4].setDisable(false);

        for (int i = 0; i < 9; i++) {
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
