package com.pi.telas.jogo;

import com.pi.App;
import com.pi.Classes.Seta;

import javafx.scene.Cursor;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
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

        Button botaoDDu = new Button();
        botaoDDu.setStyle("-fx-background-color: transparent; -fx-border-color: transparent;");
        camadaInterativa.getChildren().add(botaoDDu);
        botaoDDu.setRotate(-15);
        botaoDDu.setOnMouseEntered(e -> {
            botaoDDu.setCursor(Cursor.HAND);
            botaoDDu.setStyle("-fx-background-color: transparent; -fx-border-color: yellow; -fx-border-width: 3px;");
        });
        botaoDDu.setOnMouseExited(e -> {
            botaoDDu.setCursor(Cursor.DEFAULT);
            botaoDDu.setStyle("-fx-background-color: transparent; -fx-border-color: transparent;");
        });

        botaoDDu.setOnAction(e -> {
            TelaDDU.exibir(); 
        });

        botaoDDu.prefWidthProperty().bind(App.primaryStage.getScene().widthProperty().multiply(380.00 / 1920));
        botaoDDu.prefHeightProperty().bind(App.primaryStage.getScene().heightProperty().multiply(230.00 / 1080));
        botaoDDu.layoutXProperty().bind(App.primaryStage.getScene().widthProperty().multiply(50.00 / 1920));
        botaoDDu.layoutYProperty().bind(App.primaryStage.getScene().heightProperty().multiply(400.00 / 1080));

        Button botaofinalizar = new Button();
        botaofinalizar.setStyle("-fx-background-color: transparent; -fx-border-color: transparent;");
        camadaInterativa.getChildren().add(botaofinalizar);

        botaofinalizar.prefWidthProperty().bind(App.primaryStage.getScene().widthProperty().multiply(130.00/1920));
        botaofinalizar.prefHeightProperty().bind(App.primaryStage.getScene().heightProperty().multiply(230.00 / 1080));
        botaofinalizar.layoutXProperty().bind(App.primaryStage.getScene().widthProperty().multiply(998.00 / 1920));
        botaofinalizar.layoutYProperty().bind(App.primaryStage.getScene().heightProperty().multiply(630.00 / 1080));

        botaofinalizar.setOnMouseEntered(e -> {
            botaofinalizar.setCursor(Cursor.HAND);
            botaofinalizar.setStyle("-fx-background-color: transparent; -fx-border-color: yellow; -fx-border-width: 3px;");
        });
        botaofinalizar.setOnMouseExited(e -> {
            botaofinalizar.setCursor(Cursor.DEFAULT);
            botaofinalizar.setStyle("-fx-background-color: transparent; -fx-border-color: transparent;");
        });

        Button painelDIC = Seta.buttonSeta("Direita",
            App.primaryStage.getScene().widthProperty().multiply(180.07 / 1920),
            App.primaryStage.getScene().heightProperty().multiply(145.0 / 1080),
            "dir");
        camadaInterativa.getChildren().add(painelDIC);

        painelDIC.layoutXProperty().bind(App.primaryStage.getScene().widthProperty().multiply(1600.0 / 1920));
        painelDIC.layoutYProperty().bind(App.primaryStage.getScene().heightProperty().multiply(900.00 / 1080));
        painelDIC.setOnAction(e -> {
            TelaPainelDIC.exibir(); 
        });

        
        StackPane conteudo = new StackPane(fundo, camadaInterativa);

        conteudo.setOnMouseClicked((MouseEvent event) -> {// função que da a posição e pixels 
            double x = event.getX();
            double y = event.getY();
            System.out.println("Clique em: (" + x + ", " + y + ")");
        });

        App.root.getChildren().setAll(conteudo);
    }
}
