package com.pi.telas.jogo;

import com.pi.App;
import com.pi.classes.ControladorDeEstados;
import com.pi.classes.Seta;
import javafx.scene.Cursor;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;

public class TelaPorta {
    public static void exibir(ControladorDeEstados estados) {
        Image imagemFundo;

        if (estados.isPortaComFita()) {
            imagemFundo = new Image(App.class.getResource("/imagens/portaComFita.jpg").toExternalForm());
        } else if (estados.isPortaFechada()) {
            imagemFundo = new Image(App.class.getResource("/imagens/portaFechada.jpg").toExternalForm());
        } else {
            imagemFundo = new Image(App.class.getResource("/imagens/porta.jpg").toExternalForm());
        }

        ImageView fundo = new ImageView(imagemFundo);
        fundo.setPreserveRatio(false);
        fundo.fitWidthProperty().bind(App.primaryStage.getScene().widthProperty());
        fundo.fitHeightProperty().bind(App.primaryStage.getScene().heightProperty());

        Pane camadaInterativa = new Pane();

        Button voltar = Seta.buttonSeta("Voltar",
            App.primaryStage.getScene().widthProperty().multiply(180.07 / 1920),
            App.primaryStage.getScene().heightProperty().multiply(145.0 / 1080),
            "esq");
        voltar.layoutXProperty().bind(App.primaryStage.getScene().widthProperty().multiply(0.0 / 1920));
        voltar.layoutYProperty().bind(App.primaryStage.getScene().heightProperty().multiply(900.00 / 1080));
        voltar.setOnAction(e -> {
            App.root.getChildren().clear();
            TelaLadoDeFora.exibir(estados);
        });
        camadaInterativa.getChildren().add(voltar);

        if (!estados.isVerificouAlgoNaPorta()) {
            Button verificar = new Button("Verificar obstrução na porta");
            verificar.setStyle("-fx-background-color: lightblue; -fx-border-color: darkblue; -fx-font-size: 15px;");
            verificar.prefWidthProperty().bind(App.primaryStage.getScene().widthProperty().multiply(503.07 / 1920));
            verificar.prefHeightProperty().bind(App.primaryStage.getScene().heightProperty().multiply(222.0 / 1080));
            verificar.layoutXProperty().bind(App.primaryStage.getScene().widthProperty().multiply(700.7 / 1920));
            verificar.layoutYProperty().bind(App.primaryStage.getScene().heightProperty().multiply(800.31 / 1080));

            verificar.setOnMouseEntered(e -> {
                verificar.setCursor(Cursor.HAND);
                verificar.setStyle("-fx-background-color: lightblue; -fx-border-color: yellow; -fx-border-width: 3px; -fx-font-size: 15px;");
            });

            verificar.setOnMouseExited(e -> {
                verificar.setCursor(Cursor.DEFAULT);
                verificar.setStyle("-fx-background-color: lightblue; -fx-border-color: darkblue; -fx-font-size: 15px;");
            });

            verificar.setOnAction(e -> {
                estados.setVerificouAlgoNaPorta(true);
                camadaInterativa.getChildren().remove(verificar);
            });

            camadaInterativa.getChildren().add(verificar);
        }

        if (!estados.isPortaFechada() && estados.isChave3Ativa()) {
            Button fechar = new Button("Fechar Porta");
            fechar.setStyle("-fx-background-color: lightgreen; -fx-border-color: darkgreen; -fx-font-size: 15px;");
            fechar.prefWidthProperty().bind(App.primaryStage.getScene().widthProperty().multiply(300.0 / 1920));
            fechar.prefHeightProperty().bind(App.primaryStage.getScene().heightProperty().multiply(120.0 / 1080));
            fechar.layoutXProperty().bind(App.primaryStage.getScene().widthProperty().multiply(800.0 / 1920));
            fechar.layoutYProperty().bind(App.primaryStage.getScene().heightProperty().multiply(700.0 / 1080));

            fechar.setOnAction(e -> {
                estados.setPortaFechada(true);
                App.root.getChildren().clear();
                TelaPorta.exibir(estados);
            });

            camadaInterativa.getChildren().add(fechar);
        }

        if (estados.isPortaFechada() && !estados.isPortaComFita() && estados.isAdesivoRemovido()) {
            Button faixa = new Button("Colocar Faixa");
            faixa.setStyle("-fx-background-color: orange; -fx-border-color: darkred; -fx-font-size: 15px;");
            faixa.prefWidthProperty().bind(App.primaryStage.getScene().widthProperty().multiply(300.0 / 1920));
            faixa.prefHeightProperty().bind(App.primaryStage.getScene().heightProperty().multiply(120.0 / 1080));
            faixa.layoutXProperty().bind(App.primaryStage.getScene().widthProperty().multiply(800.0 / 1920));
            faixa.layoutYProperty().bind(App.primaryStage.getScene().heightProperty().multiply(850.0 / 1080));

            faixa.setOnAction(e -> {
                estados.setPortaComFita(true);
                App.root.getChildren().clear();
                TelaPorta.exibir(estados);
            });

            camadaInterativa.getChildren().add(faixa);
        }

        Button direita = Seta.buttonSeta("Direita",
            App.primaryStage.getScene().widthProperty().multiply(180.07 / 1920),
            App.primaryStage.getScene().heightProperty().multiply(145.0 / 1080),
            "dir");
        direita.layoutXProperty().bind(App.primaryStage.getScene().widthProperty().multiply(1720.00 / 1920));
        direita.layoutYProperty().bind(App.primaryStage.getScene().heightProperty().multiply(900.00 / 1080));
        direita.setOnAction(e -> {
            App.root.getChildren().clear();
            TelaPainelExterno.exibir(estados);
        });
        camadaInterativa.getChildren().add(direita);

        StackPane conteudo = new StackPane(fundo, camadaInterativa);
        App.root.getChildren().addAll(conteudo);
    }
}
