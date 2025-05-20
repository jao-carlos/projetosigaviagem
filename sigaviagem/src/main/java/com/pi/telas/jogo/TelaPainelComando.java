package com.pi.telas.jogo;

import com.pi.App;
import javafx.scene.Cursor;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;

public class TelaPainelComando {

    public static void exibir() {
        Image imagemFundo = new Image(App.class.getResource("/imagens/painelatualizado.jpg").toExternalForm());
        ImageView fundo = new ImageView(imagemFundo);
        fundo.setPreserveRatio(false);

        fundo.fitWidthProperty().bind(App.primaryStage.getScene().widthProperty());
        fundo.fitHeightProperty().bind(App.primaryStage.getScene().heightProperty());

        Pane camadaInterativa = new Pane();
        Button botaoADU = new Button();
        botaoADU.setStyle("-fx-background-color: transparent; -fx-border-color: transparent;");
        camadaInterativa.getChildren().add(botaoADU);

        botaoADU.prefWidthProperty().bind(App.primaryStage.getScene().widthProperty().multiply(423.07 / 1920));
        botaoADU.prefHeightProperty().bind(App.primaryStage.getScene().heightProperty().multiply(222.0 / 1080));
        botaoADU.layoutXProperty().bind(App.primaryStage.getScene().widthProperty().multiply(700.7 / 1920));
        botaoADU.layoutYProperty().bind(App.primaryStage.getScene().heightProperty().multiply(340.31 / 1080));

        botaoADU.setOnMouseEntered(e -> {
            botaoADU.setCursor(Cursor.HAND);
            botaoADU.setStyle("-fx-background-color: transparent; -fx-border-color: yellow; -fx-border-width: 3px;");
        });
        botaoADU.setOnMouseExited(e -> {
            botaoADU.setCursor(Cursor.DEFAULT);
            botaoADU.setStyle("-fx-background-color: transparent; -fx-border-color: transparent;");
        });

        botaoADU.setOnAction(e -> {
            TelaADU.exibir(); 
        });

        StackPane conteudo = new StackPane(fundo, camadaInterativa);
        App.root.getChildren().setAll(conteudo);
    }
}
