package com.pi.telas.jogo;

import com.pi.App;

import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class TelaPainelComando {

    public static void exibir(Stage stage) {
        // Carregar a imagem de fundo
        Image imagemFundo = new Image(App.class.getResource("/imagens/painelatualizado.jpg").toExternalForm());
        ImageView fundo = new ImageView(imagemFundo);
        fundo.setPreserveRatio(false); // queremos preencher
        fundo.setFitWidth(1920);
        fundo.setFitHeight(1080);

        // StackPane para manter proporções
        StackPane root = new StackPane();
        Pane camadaInterativa = new Pane(); // Para colocar botões sobre o fundo

        // Botão invisível
        Button botaoADU = new Button();
        botaoADU.setOpacity(1); // invisível
        botaoADU.setStyle("-fx-background-color: transparent; -fx-border-color: transparent;");
        camadaInterativa.getChildren().add(botaoADU);

        StackPane overlay = new StackPane(fundo, camadaInterativa);
        root.getChildren().add(overlay);

        Scene cena = new Scene(root, 1280, 720);

        fundo.fitWidthProperty().bind(cena.widthProperty());
        fundo.fitHeightProperty().bind(cena.heightProperty());

        botaoADU.prefWidthProperty().bind(cena.widthProperty().multiply(423.07 / 1920));
        botaoADU.prefHeightProperty().bind(cena.heightProperty().multiply(222.0 / 1080));
        botaoADU.layoutXProperty().bind(cena.widthProperty().multiply(700.7 / 1920));
        botaoADU.layoutYProperty().bind(cena.heightProperty().multiply(340.31 / 1080));

        botaoADU.setOnMouseEntered(e -> {
            botaoADU.setCursor(Cursor.HAND);
            botaoADU.setStyle("-fx-background-color: transparent; -fx-border-color: yellow; -fx-border-width: 3px;");
        });
        botaoADU.setOnMouseExited(e -> {
            botaoADU.setCursor(Cursor.DEFAULT);
            botaoADU.setStyle("-fx-background-color: transparent; -fx-border-color: transparent;");
        });

        botaoADU.setOnAction(e -> {
            System.out.println("Botão invisível clicado!");
            //Telas_DU.exibir(stage);
            TelaADU.exibir(stage);
        });

        stage.setTitle("Painel Responsivo");
        stage.setScene(cena);
        stage.setFullScreen(true); // ou setMaximized(true);
        stage.show();
    }
}
