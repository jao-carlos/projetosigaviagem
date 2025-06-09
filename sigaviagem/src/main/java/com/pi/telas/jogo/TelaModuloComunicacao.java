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

public class TelaModuloComunicacao {
    public static void exibir(ControladorDeEstados estados) {
        Image imagem = new Image(App.class.getResource("/imagens/painelModuloComunicacao.jpg").toExternalForm());
        ImageView fundo = new ImageView(imagem);
        fundo.setPreserveRatio(false);

        fundo.fitWidthProperty().bind(App.primaryStage.getScene().widthProperty());
        fundo.fitHeightProperty().bind(App.primaryStage.getScene().heightProperty());

        Pane camadaInterativa = new Pane();

        BotaoPersonalizado botaoPA = new BotaoPersonalizado(
            App.primaryStage.getScene().widthProperty().multiply(80.07 / 1920),
            App.primaryStage.getScene().heightProperty().multiply(80.0 / 1080),
            App.primaryStage.getScene().widthProperty().multiply(300.0 / 1920),
            App.primaryStage.getScene().heightProperty().multiply(150.31 / 1080),
            () -> {
                App.root.getChildren().clear();
                TelaADU.exibir(estados);
            }
        );
        camadaInterativa.getChildren().add(botaoPA);
        
        Button voltar = Seta.buttonSeta("Voltar",
            App.primaryStage.getScene().widthProperty().multiply(180.07 / 1920),
            App.primaryStage.getScene().heightProperty().multiply(145.0 / 1080),
            "esq");
        

        voltar.layoutXProperty().bind(App.primaryStage.getScene().widthProperty().multiply(0.0 / 1920));
        voltar.layoutYProperty().bind(App.primaryStage.getScene().heightProperty().multiply(900.00 / 1080));
        voltar.setOnAction(e -> {
            App.root.getChildren().clear();
            TelaPainelComando.exibir(estados); 
        });
        camadaInterativa.getChildren().add(voltar);
        StackPane conteudo = new StackPane(fundo,camadaInterativa);
        App.root.getChildren().setAll(conteudo);
    }
}
