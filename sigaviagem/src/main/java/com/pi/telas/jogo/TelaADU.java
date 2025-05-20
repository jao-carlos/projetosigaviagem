package com.pi.telas.jogo;

import com.pi.App;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;

public class TelaADU {

    public static void exibir() {
        Image imagem = new Image(App.class.getResource("/imagens/painelADU.jpg").toExternalForm());
        ImageView fundo = new ImageView(imagem);
        fundo.setPreserveRatio(false);

        fundo.fitWidthProperty().bind(App.primaryStage.getScene().widthProperty());
        fundo.fitHeightProperty().bind(App.primaryStage.getScene().heightProperty());

        StackPane conteudo = new StackPane(fundo);
        App.root.getChildren().setAll(conteudo);
    }
}
