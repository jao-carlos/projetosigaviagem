package com.pi.telas.jogo;

import com.pi.App;
import com.pi.classes.ControladorDeEstados;
import com.pi.classes.Seta;
import com.pi.classes.BotaoPersonalizado;

import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;

public class TelaPainelExterno {
    public static void exibir(ControladorDeEstados estados) {
        ImageView fundo;
        Pane camadaInterativa = new Pane();

        if (!estados.isPainelExternoAberto()) {
            Image imagemFundo = new Image(App.class.getResource("/imagens/painelExternoFechado.jpg").toExternalForm());
            fundo = new ImageView(imagemFundo);
            fundo.setPreserveRatio(false);

            fundo.fitWidthProperty().bind(App.primaryStage.getScene().widthProperty());
            fundo.fitHeightProperty().bind(App.primaryStage.getScene().heightProperty());

            BotaoPersonalizado painelExterno = new BotaoPersonalizado(
                App.primaryStage.getScene().widthProperty().multiply(605.00 / 1920),
                App.primaryStage.getScene().heightProperty().multiply(470.00 / 1080),
                App.primaryStage.getScene().widthProperty().multiply(620.00 / 1920),
                App.primaryStage.getScene().heightProperty().multiply(325.00 / 1080),
                () -> {
                    estados.setPainelExternoAberto(true);
                    TelaPainelExterno.exibir(estados);
                }
            );
            camadaInterativa.getChildren().add(painelExterno);
        } else {
            Image imagemFundo = new Image(App.class.getResource("/imagens/painelExternoAberto.jpg").toExternalForm());
            fundo = new ImageView(imagemFundo);
            fundo.setPreserveRatio(false);

            fundo.fitWidthProperty().bind(App.primaryStage.getScene().widthProperty());
            fundo.fitHeightProperty().bind(App.primaryStage.getScene().heightProperty());

            ChaveToggle chave1 = new ChaveToggle(120, 120);
            ChaveToggle chave2 = new ChaveToggle(120, 120);
            ChaveToggle chave3 = new ChaveToggle(120, 120);
            ChaveToggle chave4 = new ChaveToggle(120, 120);

            chave1.posicionar(730, 787);
            chave2.posicionar(870, 787);
            chave3.posicionar(1000, 787);
            chave4.posicionar(1130, 787);

            // Chave 1
            chave1.setAtivo(estados.isChave1Ativa());
            chave1.setOnAction(e -> {
                chave1.alternar();
                estados.setChave1Ativa(chave1.isAtivo());
            });

            // Chave 2
            chave2.setAtivo(estados.isChave2Ativa());
            chave2.setOnAction(e -> {
                chave2.alternar();
                estados.setChave2Ativa(chave2.isAtivo());
            });

            // Chave 3
            chave3.setAtivo(estados.isChave3Ativa());
            chave3.setOnAction(e -> {
                chave3.alternar();
                estados.setChave3Ativa(chave3.isAtivo());
            });

            // Chave 4
            chave4.setAtivo(estados.isChave4Ativa());
            chave4.setOnAction(e -> {
                chave4.alternar();
                estados.setChave4Ativa(chave4.isAtivo());
            });

            camadaInterativa.getChildren().addAll(chave1, chave2, chave3, chave4);

            Button fechar = Seta.buttonSeta("Fechar",

            App.primaryStage.getScene().widthProperty().multiply(180.07 / 1920),
            App.primaryStage.getScene().heightProperty().multiply(145.0 / 1080),
            "baixo");
            fechar.layoutXProperty().bind(App.primaryStage.getScene().widthProperty().multiply(900.0 / 1920));
            fechar.layoutYProperty().bind(App.primaryStage.getScene().heightProperty().multiply(200.0 / 1080));
            fechar.setOnAction(e -> {
                estados.setPainelExternoAberto(false);
                TelaPainelExterno.exibir(estados);
            });
            camadaInterativa.getChildren().add(fechar);

        }

        Button voltar = Seta.buttonSeta("Voltar",
            App.primaryStage.getScene().widthProperty().multiply(180.07 / 1920),
            App.primaryStage.getScene().heightProperty().multiply(145.0 / 1080),
            "esq");
        voltar.layoutXProperty().bind(App.primaryStage.getScene().widthProperty().multiply(0.0 / 1920));
        voltar.layoutYProperty().bind(App.primaryStage.getScene().heightProperty().multiply(900.00 / 1080));
        voltar.setOnAction(e -> {
            App.root.getChildren().clear();
            TelaPorta.exibir(estados);
        });
        camadaInterativa.getChildren().add(voltar);

        StackPane conteudo = new StackPane(fundo, camadaInterativa);
        App.root.getChildren().addAll(conteudo);
    }
}
