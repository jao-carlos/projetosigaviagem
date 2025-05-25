package com.pi.telas.jogo;

import com.pi.App;
import com.pi.Classes.Seta;

import javafx.scene.Cursor;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

public class TelaPortaLateralDireita {
     public static void exibir(){
        Image imagemFundo = new Image(App.class.getResource("/imagens/portaLateralDireita.jpg").toExternalForm());
        ImageView fundo = new ImageView(imagemFundo);
        fundo.setPreserveRatio(false);

        fundo.fitWidthProperty().bind(App.primaryStage.getScene().widthProperty());
        fundo.fitHeightProperty().bind(App.primaryStage.getScene().heightProperty());


        Pane camadaInterativa = new Pane();
        Button painelLatera = new Button();
        painelLatera.setStyle("-fx-background-color: transparent; -fx-border-color: transparent;");
        camadaInterativa.getChildren().add(painelLatera);

        painelLatera.prefWidthProperty().bind(App.primaryStage.getScene().widthProperty().multiply(160.07 / 1920));
        painelLatera.prefHeightProperty().bind(App.primaryStage.getScene().heightProperty().multiply(600.0 / 1080));
        painelLatera.layoutXProperty().bind(App.primaryStage.getScene().widthProperty().multiply(300.7 / 1920));
        painelLatera.layoutYProperty().bind(App.primaryStage.getScene().heightProperty().multiply(360.00 / 1080));

        painelLatera.setOnMouseEntered(e -> {
            painelLatera.setCursor(Cursor.HAND);
            painelLatera.setStyle("-fx-background-color: transparent; -fx-border-color: yellow; -fx-border-width: 3px;");
        });
        painelLatera.setOnMouseExited(e -> {
            painelLatera.setCursor(Cursor.DEFAULT);
            painelLatera.setStyle("-fx-background-color: transparent; -fx-border-color: transparent;");
        });

        painelLatera.setOnAction(e -> {
            
        });

        Button porta = new Button();
        porta.setStyle("-fx-background-color: transparent; -fx-border-color: transparent;");
        camadaInterativa.getChildren().add(porta);

        porta.prefWidthProperty().bind(App.primaryStage.getScene().widthProperty().multiply(1100.07 / 1920));
        porta.prefHeightProperty().bind(App.primaryStage.getScene().heightProperty().multiply(1060.0 / 1080));
        porta.layoutXProperty().bind(App.primaryStage.getScene().widthProperty().multiply(750.7 / 1920));
        porta.layoutYProperty().bind(App.primaryStage.getScene().heightProperty().multiply(10.00 / 1080));

        porta.setOnMouseEntered(e -> {
            porta.setCursor(Cursor.HAND);
            porta.setStyle("-fx-background-color: transparent; -fx-border-color: yellow; -fx-border-width: 3px;");
        });
        porta.setOnMouseExited(e -> {
            porta.setCursor(Cursor.DEFAULT);
            porta.setStyle("-fx-background-color: transparent; -fx-border-color: transparent;");
        });

        porta.setOnAction(e -> {
            TelaLadoDeFora.exibir(); 
        });


        Button voltar = Seta.buttonSeta("Voltar",
            App.primaryStage.getScene().widthProperty().multiply(180.07 / 1920),
            App.primaryStage.getScene().heightProperty().multiply(145.0 / 1080),
            "esq");
        camadaInterativa.getChildren().add(voltar);

        voltar.layoutXProperty().bind(App.primaryStage.getScene().widthProperty().multiply(0.0 / 1920));
        voltar.layoutYProperty().bind(App.primaryStage.getScene().heightProperty().multiply(900.00 / 1080));
        voltar.setOnAction(e -> {
            TelaPainelDIC.exibir(); 
        });


        
        App.root.getChildren().addAll(fundo,camadaInterativa);
    }
}
