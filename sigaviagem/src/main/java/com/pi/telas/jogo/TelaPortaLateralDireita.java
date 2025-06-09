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

public class TelaPortaLateralDireita {
     public static void exibir(ControladorDeEstados estados){
        Image imagemFundo = new Image(App.class.getResource("/imagens/portaLateralDireita.jpg").toExternalForm());
        ImageView fundo = new ImageView(imagemFundo);
        fundo.setPreserveRatio(false);

        fundo.fitWidthProperty().bind(App.primaryStage.getScene().widthProperty());
        fundo.fitHeightProperty().bind(App.primaryStage.getScene().heightProperty());

        Pane camadaInterativa = new Pane();
    
        BotaoPersonalizado botaoAbrir = new BotaoPersonalizado(    //Acredito que isso é só um aviso não uma tela
            App.primaryStage.getScene().widthProperty().multiply(70.07 / 1920),
            App.primaryStage.getScene().heightProperty().multiply(60.0 / 1080),
            App.primaryStage.getScene().widthProperty().multiply(345.7 / 1920),
            App.primaryStage.getScene().heightProperty().multiply(640.00 / 1080)

        );
        camadaInterativa.getChildren().add(botaoAbrir);
        BotaoPersonalizado botaoFechar = new BotaoPersonalizado(    //Acredito que isso é só um aviso não uma tela
            App.primaryStage.getScene().widthProperty().multiply(70.07 / 1920),
            App.primaryStage.getScene().heightProperty().multiply(70.0 / 1080),
            App.primaryStage.getScene().widthProperty().multiply(350.7 / 1920),
            App.primaryStage.getScene().heightProperty().multiply(780.00 / 1080)

        );
        camadaInterativa.getChildren().add(botaoFechar);

        BotaoPersonalizado porta = new BotaoPersonalizado(
            App.primaryStage.getScene().widthProperty().multiply(1100.07 / 1920),
            App.primaryStage.getScene().heightProperty().multiply(1060.0 / 1080),
            App.primaryStage.getScene().widthProperty().multiply(750.7 / 1920),
            App.primaryStage.getScene().heightProperty().multiply(10.00 / 1080),
            () -> TelaLadoDeFora.exibir(estados)
        );
        camadaInterativa.getChildren().add(porta);

        Button voltar = Seta.buttonSeta("Voltar",
            App.primaryStage.getScene().widthProperty().multiply(180.07 / 1920),
            App.primaryStage.getScene().heightProperty().multiply(145.0 / 1080),
            "esq");
        camadaInterativa.getChildren().add(voltar);

        voltar.layoutXProperty().bind(App.primaryStage.getScene().widthProperty().multiply(0.0 / 1920));
        voltar.layoutYProperty().bind(App.primaryStage.getScene().heightProperty().multiply(900.00 / 1080));
        voltar.setOnAction(e -> {
            TelaPainelDIC.exibir(estados); 
        });


        StackPane conteudo = new StackPane(fundo, camadaInterativa);
        App.root.getChildren().addAll(conteudo);
    }
}
