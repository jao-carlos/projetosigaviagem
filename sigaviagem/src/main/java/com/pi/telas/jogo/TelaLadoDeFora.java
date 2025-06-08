package com.pi.telas.jogo;

import com.pi.App;
import com.pi.classes.BotaoPersonalizado;
import com.pi.classes.ControladorDeEstados;
import com.pi.classes.Seta;

import javafx.scene.Cursor;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;

public class TelaLadoDeFora {
    public static void exibir(ControladorDeEstados estados){
        Image imagemFundo = new Image(App.class.getResource("/imagens/ladoDeFora.jpg").toExternalForm());
        ImageView fundo = new ImageView(imagemFundo);
        fundo.setPreserveRatio(false);

        fundo.fitWidthProperty().bind(App.primaryStage.getScene().widthProperty());
        fundo.fitHeightProperty().bind(App.primaryStage.getScene().heightProperty());


        Pane camadaInterativa = new Pane();
        


        Button voltar = Seta.buttonSeta("Esquerda",
            App.primaryStage.getScene().widthProperty().multiply(180.07 / 1920),
            App.primaryStage.getScene().heightProperty().multiply(145.0 / 1080),
            "esq");
        camadaInterativa.getChildren().add(voltar);

        voltar.layoutXProperty().bind(App.primaryStage.getScene().widthProperty().multiply(0.0 / 1920));
        voltar.layoutYProperty().bind(App.primaryStage.getScene().heightProperty().multiply(900.00 / 1080));
        voltar.setOnAction(e -> {
            App.root.getChildren().clear();
            TelaPortaLateralDireita.exibir(estados); 
        });

        BotaoPersonalizado porta = new BotaoPersonalizado(
            App.primaryStage.getScene().widthProperty().multiply(323.07 / 1920),
            App.primaryStage.getScene().heightProperty().multiply(400.0 / 1080),
            App.primaryStage.getScene().widthProperty().multiply(1500.7 / 1920),
            App.primaryStage.getScene().heightProperty().multiply(250.31 / 1080),
            () -> {
                App.root.getChildren().clear();
                TelaPorta.exibir(estados);
            }
        );
        camadaInterativa.getChildren().add(porta);



        StackPane conteudo = new StackPane(fundo, camadaInterativa);
        App.root.getChildren().addAll(conteudo);
    }
}
