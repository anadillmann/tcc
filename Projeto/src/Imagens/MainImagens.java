package Imagens;

import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class MainImagens extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        Dao_Imagens i = new Dao_Imagens();

        Imagens c = new Imagens(1, "Arvore",
                new Image("imagem/arvore.png"));
        i.adiciona(c);
        
        

    }

    public static void main(String[] args) {
        launch(args);
    }

}
