package com.pi.telas.jogo;

import com.pi.App;
import com.pi.classes.ControladorDeEstados;
import com.pi.classes.Seta;

import javafx.scene.Cursor;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

public class TelaPainelDIC {
    public static void exibir(ControladorDeEstados estados){
        Image imagemFundo = new Image(App.class.getResource("/imagens/painelDIC.jpg").toExternalForm());
        ImageView fundo = new ImageView(imagemFundo);
        fundo.setPreserveRatio(false);

        fundo.fitWidthProperty().bind(App.primaryStage.getScene().widthProperty());
        fundo.fitHeightProperty().bind(App.primaryStage.getScene().heightProperty());


        Pane camadaInterativa = new Pane();
        Button chaveCBTC = new Button();
        chaveCBTC.setStyle("-fx-background-color: transparent; -fx-border-color: transparent;");
        camadaInterativa.getChildren().add(chaveCBTC);

        chaveCBTC.prefWidthProperty().bind(App.primaryStage.getScene().widthProperty().multiply(135.07 / 1920));
        chaveCBTC.prefHeightProperty().bind(App.primaryStage.getScene().heightProperty().multiply(145.0 / 1080));
        chaveCBTC.layoutXProperty().bind(App.primaryStage.getScene().widthProperty().multiply(825.7 / 1920));
        chaveCBTC.layoutYProperty().bind(App.primaryStage.getScene().heightProperty().multiply(880.00 / 1080));

        chaveCBTC.setOnMouseEntered(e -> {
            chaveCBTC.setCursor(Cursor.HAND);
            chaveCBTC.setStyle("-fx-background-color: transparent; -fx-border-color: yellow; -fx-border-width: 3px;");
        });
        chaveCBTC.setOnMouseExited(e -> {
            chaveCBTC.setCursor(Cursor.DEFAULT);
            chaveCBTC.setStyle("-fx-background-color: transparent; -fx-border-color: transparent;");
        });

        chaveCBTC.setOnAction(e -> {
            TelaChaveCBTC.exibir(estados); 
        });


        Button voltar = Seta.buttonSeta("Esquerda",
            App.primaryStage.getScene().widthProperty().multiply(180.07 / 1920),
            App.primaryStage.getScene().heightProperty().multiply(145.0 / 1080),
            "esq");
        camadaInterativa.getChildren().add(voltar);

        voltar.layoutXProperty().bind(App.primaryStage.getScene().widthProperty().multiply(0.0 / 1920));
        voltar.layoutYProperty().bind(App.primaryStage.getScene().heightProperty().multiply(900.00 / 1080));
        voltar.setOnAction(e -> {
            TelaPainelComando.exibir(estados); 
        });

        Button direita = Seta.buttonSeta("direita",
            App.primaryStage.getScene().widthProperty().multiply(180.07 / 1920),
            App.primaryStage.getScene().heightProperty().multiply(145.0 / 1080),
            "dir");
        camadaInterativa.getChildren().add(direita);

        direita.layoutXProperty().bind(App.primaryStage.getScene().widthProperty().multiply(1720.00 / 1920));
        direita.layoutYProperty().bind(App.primaryStage.getScene().heightProperty().multiply(900.00 / 1080));
        direita.setOnAction(e -> {
            TelaPortaLateralDireita.exibir(estados); 
        });


        
        App.root.getChildren().addAll(fundo,camadaInterativa);
    }
}
