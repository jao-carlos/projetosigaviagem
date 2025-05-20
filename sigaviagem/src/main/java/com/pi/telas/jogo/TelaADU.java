package com.pi.telas.jogo;

import com.pi.App;

import javafx.scene.control.Button;
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
        Button botaoVol = new Button("voltar");
        botaoVol.setOnAction(e -> {
            TelaPainelComando.exibir();
        });
        StackPane conteudo = new StackPane(fundo,botaoVol);
        App.root.getChildren().setAll(conteudo);
    }
}
