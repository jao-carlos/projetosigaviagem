package com.pi;

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
        Button botaoDDU = new Button();
        botaoDDU.setOpacity(0); // invisível
        botaoDDU.setStyle("-fx-background-color: transparent;");
        camadaInterativa.getChildren().add(botaoDDU);

        StackPane overlay = new StackPane(fundo, camadaInterativa);
        root.getChildren().add(overlay);

        Scene cena = new Scene(root, 1280, 720);

        fundo.fitWidthProperty().bind(cena.widthProperty());
        fundo.fitHeightProperty().bind(cena.heightProperty());

        botaoDDU.prefWidthProperty().bind(cena.widthProperty().multiply(423.07 / 1920));
        botaoDDU.prefHeightProperty().bind(cena.heightProperty().multiply(222.0 / 1080));
        botaoDDU.layoutXProperty().bind(cena.widthProperty().multiply(700.7 / 1920));
        botaoDDU.layoutYProperty().bind(cena.heightProperty().multiply(340.31 / 1080));

        botaoDDU.setOnAction(e -> {
            System.out.println("Botão invisível clicado!");
            Telas_DU.exibir(stage);
        });

        stage.setTitle("Painel Responsivo");
        stage.setScene(cena);
        stage.setFullScreen(true); // ou setMaximized(true);
        stage.show();
    }
}
