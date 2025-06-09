package com.pi.telas.jogo;

import com.pi.App;
import com.pi.classes.BotaoPersonalizado;
import com.pi.classes.ControladorDeEstados;
import com.pi.classes.Seta;

import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;

public class TelaPainelDIC {
    public static void exibir(ControladorDeEstados estados){
        Image imagemFundo = new Image(App.class.getResource("/imagens/painelDIC.jpg").toExternalForm());
        ImageView fundo = new ImageView(imagemFundo);
        fundo.setPreserveRatio(false);

        fundo.fitWidthProperty().bind(App.primaryStage.getScene().widthProperty());
        fundo.fitHeightProperty().bind(App.primaryStage.getScene().heightProperty());


        Pane camadaInterativa = new Pane();

        BotaoPersonalizado chaveCBTC = new BotaoPersonalizado(
            App.primaryStage.getScene().widthProperty().multiply(135.07 / 1920),
            App.primaryStage.getScene().heightProperty().multiply(145.0 / 1080),
            App.primaryStage.getScene().widthProperty().multiply(825.7 / 1920),
            App.primaryStage.getScene().heightProperty().multiply(880.00 / 1080),
            () -> {
                App.root.getChildren().clear();
                TelaChaveCBTC.exibir(estados);
            }
        );
        camadaInterativa.getChildren().add(chaveCBTC);


        Button voltar = Seta.buttonSeta("Esquerda",
            App.primaryStage.getScene().widthProperty().multiply(180.07 / 1920),
            App.primaryStage.getScene().heightProperty().multiply(145.0 / 1080),
            "esq");
        camadaInterativa.getChildren().add(voltar);

        voltar.layoutXProperty().bind(App.primaryStage.getScene().widthProperty().multiply(0.0 / 1920));
        voltar.layoutYProperty().bind(App.primaryStage.getScene().heightProperty().multiply(900.00 / 1080));
        voltar.setOnAction(e -> {
            App.root.getChildren().clear();
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
            App.root.getChildren().clear();
            TelaPortaLateralDireita.exibir(estados); 
        });

        StackPane conteudo = new StackPane(fundo, camadaInterativa);
        
        App.root.getChildren().addAll(conteudo);
    }
}
