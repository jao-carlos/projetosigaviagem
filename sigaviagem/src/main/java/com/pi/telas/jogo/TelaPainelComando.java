package com.pi.telas.jogo;

import com.pi.App;
import com.pi.classes.BotaoPersonalizado;
import com.pi.classes.ControladorDeEstados;
import com.pi.classes.Seta;
import com.pi.telas.login.TelaInicial;

import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;

public class TelaPainelComando {
    public static void exibir(ControladorDeEstados estados) {
        Image imagemFundo = new Image(App.class.getResource("/imagens/painelatualizado.jpg").toExternalForm());
        ImageView fundo = new ImageView(imagemFundo);
        fundo.setPreserveRatio(false);

        fundo.fitWidthProperty().bind(App.primaryStage.getScene().widthProperty());
        fundo.fitHeightProperty().bind(App.primaryStage.getScene().heightProperty());

        Pane camadaInterativa = new Pane();

        BotaoPersonalizado botaoADU = new BotaoPersonalizado(
            App.primaryStage.getScene().widthProperty().multiply(423.07 / 1920),
            App.primaryStage.getScene().heightProperty().multiply(222.0 / 1080),
            App.primaryStage.getScene().widthProperty().multiply(700.7 / 1920),
            App.primaryStage.getScene().heightProperty().multiply(340.31 / 1080),
            () -> {
                App.root.getChildren().clear();
                TelaADU.exibir(estados);
            }
        );
        camadaInterativa.getChildren().add(botaoADU);

        BotaoPersonalizado botaoModuloComunicacao = new BotaoPersonalizado(
            App.primaryStage.getScene().widthProperty().multiply(190.00 / 1920),
            App.primaryStage.getScene().heightProperty().multiply(150.00 / 1080),
            App.primaryStage.getScene().widthProperty().multiply(460.00 / 1920),
            App.primaryStage.getScene().heightProperty().multiply(365.00 / 1080),
            () -> {
                App.root.getChildren().clear();
                TelaModuloComunicacao.exibir(estados);
            }
        );
        botaoModuloComunicacao.setRotacao(-9);
        camadaInterativa.getChildren().add(botaoModuloComunicacao);

        BotaoPersonalizado botaoCvreversora = new BotaoPersonalizado(
            App.primaryStage.getScene().widthProperty().multiply(77.00 / 1920),
            App.primaryStage.getScene().heightProperty().multiply(77.00 / 1080),
            App.primaryStage.getScene().widthProperty().multiply(597.00 / 1920),
            App.primaryStage.getScene().heightProperty().multiply(570.04 / 1080),
            () -> {
                long tempo = estados.tempoDecorridoEmSegundos();
                System.out.println("Tempo decorrido: " + tempo + " segundos.");
                App.root.getChildren().clear();
                TelaChaveReversora.exibir(estados);
            }
        );
        camadaInterativa.getChildren().add(botaoCvreversora);

        BotaoPersonalizado botaoDDu = new BotaoPersonalizado(
            App.primaryStage.getScene().widthProperty().multiply(380.00 / 1920),
            App.primaryStage.getScene().heightProperty().multiply(230.00 / 1080),
            App.primaryStage.getScene().widthProperty().multiply(50.00 / 1920),
            App.primaryStage.getScene().heightProperty().multiply(400.00 / 1080),
            () -> {
                App.root.getChildren().clear();
                TelaDDU.exibir(estados);
            }
        );
        botaoDDu.setRotacao(-15);
        camadaInterativa.getChildren().add(botaoDDu);

        BotaoPersonalizado botaoVDU = new BotaoPersonalizado(
            App.primaryStage.getScene().widthProperty().multiply(400.00 / 1920),
            App.primaryStage.getScene().heightProperty().multiply(250.00 / 1080),
            App.primaryStage.getScene().widthProperty().multiply(1420.00 / 1920),
            App.primaryStage.getScene().heightProperty().multiply(410.00 / 1080),
            () -> {
                App.root.getChildren().clear();
                TelaVDU.exibir(estados);
            }
        );
        botaoVDU.setRotacao(16);
        camadaInterativa.getChildren().add(botaoVDU);

        BotaoPersonalizado botaofinalizar = new BotaoPersonalizado(
            App.primaryStage.getScene().widthProperty().multiply(130.00 / 1920),
            App.primaryStage.getScene().heightProperty().multiply(230.00 / 1080),
            App.primaryStage.getScene().widthProperty().multiply(998.00 / 1920),
            App.primaryStage.getScene().heightProperty().multiply(630.00 / 1080),
            () -> {
                App.root.getChildren().clear();
                TelaGameOver.exibir(estados, "Alavanca de partida");
            }
        );

        camadaInterativa.getChildren().add(botaofinalizar);


        Button voltar = Seta.buttonSeta("Esquerda",
            App.primaryStage.getScene().widthProperty().multiply(180.07 / 1920),
            App.primaryStage.getScene().heightProperty().multiply(145.0 / 1080),
            "esq");
        voltar.layoutXProperty().bind(App.primaryStage.getScene().widthProperty().multiply(0.0 / 1920));
        voltar.layoutYProperty().bind(App.primaryStage.getScene().heightProperty().multiply(900.00 / 1080));
        voltar.setOnAction(e -> {
            TelaMesa.exibir(estados);
        });
        camadaInterativa.getChildren().add(voltar);

        Button painelDIC = Seta.buttonSeta("Direita",
            App.primaryStage.getScene().widthProperty().multiply(180.07 / 1920),
            App.primaryStage.getScene().heightProperty().multiply(145.0 / 1080),
            "dir");
        camadaInterativa.getChildren().add(painelDIC);

        painelDIC.layoutXProperty().bind(App.primaryStage.getScene().widthProperty().multiply(1600.0 / 1920));
        painelDIC.layoutYProperty().bind(App.primaryStage.getScene().heightProperty().multiply(900.00 / 1080));
        painelDIC.setOnAction(e -> {
            App.root.getChildren().clear();
            TelaPainelDIC.exibir(estados); 
        });

        
        StackPane conteudo = new StackPane(fundo, camadaInterativa);

       

        App.root.getChildren().setAll(conteudo);
    }
}
