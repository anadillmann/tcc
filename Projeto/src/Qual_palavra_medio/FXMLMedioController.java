package Qual_palavra_medio;

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
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class FXMLMedioController implements Initializable {

    @FXML
    ImageView imagem1, imagem2;
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

    public String falta1, falta2, falta3, falta4;
    public String silabas1[], silabas2[];

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        palavras_sorteadas = new ArrayList();
        progresso_percentagem = 0.10;
        progresso.setProgress(progresso_percentagem);
        preencher1.setEditable(false);
        preencher2.setEditable(false);
        preencher3.setEditable(false);
        preencher4.setEditable(false);

        String palavra = sortear_palavras(); //ca-ne-ta-me-ni-na
        palavras_sorteadas.add(palavra);
        opcoes = new Button[]{opcao1, opcao2, opcao3, opcao4, opcao5, opcao6};
        faltas = new String[]{falta1, falta2, falta3, falta4};
        preenche = new TextField[]{preencher1, preencher2, preencher3, preencher4};

        for (Button opcao : opcoes) {
            opcao.setOnAction(click -> {
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
                                String nova_palavra1 = "";
                                String nova_palavra2 = "";

                                do {
                                    nova_palavra1 = sortear_palavras();
                                    nova_palavra2 = sortear_palavras();

                                } while (palavras_sorteadas.equals(nova_palavra1)
                                        && palavras_sorteadas.equals(nova_palavra2)
                                        && nova_palavra1.equals(nova_palavra2));
                            }
                        }

                    }
                    ///PQ ESSE ELSE NÃO FUNCIONA??
                    /*else {
                        Alert a = new Alert(Alert.AlertType.ERROR);
                        a.setTitle("ERRO");
                        a.setContentText("Ops! Sua resposta não está correta, tente de novo");
                        a.showAndWait();
                        System.out.println("Incorreta");
                    }
                     */
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

    private String sortear_palavras() {
        preencher1.setText("");
        preencher2.setText("");
        preencher3.setText("");
        preencher4.setText("");
        

        dao = new Dao_Medio();
        List<Medio> palavras = dao.pesquisaTodos();
        opcoes = new Button[]{opcao1, opcao2, opcao3, opcao4, opcao5, opcao6};

        Collections.shuffle(palavras);// Método usado para embaralhar a lista
        Random r = new Random();//botões

        silabas1 = palavras.get(r.nextInt(palavras.size())).getNome_palavra_medio().split("-");
        silaba_aparece1.setText(silabas1[1]);//Atribui ao label a primeira silaba da palavra que vai aparecer
        falta1 = silabas1[0];
        falta2 = silabas1[2];

        boolean repetida = true;

        do {
            silabas2 = palavras.get(r.nextInt(palavras.size())).getNome_palavra_medio().split("-");
            String concat1 = silabas1[0].concat(silabas1[1].concat(silabas1[2]));
            String concat2 = silabas2[0].concat(silabas2[1].concat(silabas2[2]));

            if (!concat1.equals(concat2)) {
                repetida = false;
            }
        } while (repetida);
        silaba_aparece2.setText(silabas2[1]);//Atribui ao label a primeira silaba da palavra que vai aparecer
        falta3 = silabas2[0];
        falta4 = silabas2[2];
        
        
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
        opcoes[pos2].setDisable(false);

        int pos4 = 0;
        do {
            pos4 = r.nextInt(5) + 0;
        } while (pos1 == pos4 || pos2 == pos4 || pos3 == pos4);
        opcoes[pos4].setText(silabas2[2]);
        opcoes[pos4].setDisable(false);


        for (int i = 0; i < 6; i++) {
            if (opcoes[i].getText().isEmpty()) {
                opcoes[i].setDisable(false);
                opcoes[i].setText(sortidas.get(r.nextInt(sortidas.size())).getNome_sortida());
            }
        }
        

        return silabas1[0] + "-" + silabas1[1] + "-" + silabas1[2];
        
        ///preciso retornar a segunda palavra
    }
}
