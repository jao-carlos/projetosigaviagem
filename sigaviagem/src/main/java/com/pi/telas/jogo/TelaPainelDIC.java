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

        Image chaveImg = new Image(App.class.getResource("/imagens/ChaveAssetCBTC.png").toExternalForm());
        ImageView chaveView = new ImageView(chaveImg);

        chaveView.setFitWidth(App.primaryStage.getScene().getWidth() * (98.0 / 1920));
        chaveView.setPreserveRatio(true);

        chaveView.layoutXProperty().bind(App.primaryStage.getScene().widthProperty().multiply(843.0 / 1920));
        chaveView.layoutYProperty().bind(App.primaryStage.getScene().heightProperty().multiply(890.0 / 1080));

        double[] angulos = {-45, 0, 45};

        chaveView.setRotate(angulos[estados.getPosChaveCBTC()]);

        Button btnChave = new Button();
        btnChave.setStyle("-fx-background-color: transparent;");
        btnChave.setPrefSize(100, 100);
        btnChave.layoutXProperty().bind(chaveView.layoutXProperty());
        btnChave.layoutYProperty().bind(chaveView.layoutYProperty());

        btnChave.setOnAction(e -> {
            int novaPos = (estados.getPosChaveCBTC() + 1) % 3;
            estados.setPosChaveCBTC(novaPos);
            chaveView.setRotate(angulos[novaPos]);
        });

        camadaInterativa.getChildren().addAll(chaveView, btnChave);

        // Botão de seta esquerda
        Button voltar = Seta.buttonSeta("Esquerda",
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

        // Botão de seta direita
        Button direita = Seta.buttonSeta("direita",
            App.primaryStage.getScene().widthProperty().multiply(180.07 / 1920),
            App.primaryStage.getScene().heightProperty().multiply(145.0 / 1080),
            "dir");
        direita.layoutXProperty().bind(App.primaryStage.getScene().widthProperty().multiply(1720.00 / 1920));
        direita.layoutYProperty().bind(App.primaryStage.getScene().heightProperty().multiply(900.00 / 1080));
        direita.setOnAction(e -> {
            App.root.getChildren().clear();
            TelaPortaLateralDireita.exibir(estados); 
        });

        camadaInterativa.getChildren().add(direita);

        StackPane conteudo = new StackPane(fundo, camadaInterativa);
        App.root.getChildren().addAll(conteudo);
    }
}
