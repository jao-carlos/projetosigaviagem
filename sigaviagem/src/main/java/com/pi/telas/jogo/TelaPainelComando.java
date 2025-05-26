package com.pi.telas.jogo;

import com.pi.App;
import com.pi.classes.ControladorDeEstados;
import com.pi.classes.Seta;

import javafx.scene.Cursor;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
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
        Button botaoADU = new Button();
        botaoADU.setStyle("-fx-background-color: transparent; -fx-border-color: transparent;");
        camadaInterativa.getChildren().add(botaoADU);

        botaoADU.prefWidthProperty().bind(App.primaryStage.getScene().widthProperty().multiply(423.07 / 1920));
        botaoADU.prefHeightProperty().bind(App.primaryStage.getScene().heightProperty().multiply(222.0 / 1080));
        botaoADU.layoutXProperty().bind(App.primaryStage.getScene().widthProperty().multiply(700.7 / 1920));
        botaoADU.layoutYProperty().bind(App.primaryStage.getScene().heightProperty().multiply(340.31 / 1080));

        botaoADU.setOnMouseEntered(e -> {
            botaoADU.setCursor(Cursor.HAND);
            botaoADU.setStyle("-fx-background-color: transparent; -fx-border-color: yellow; -fx-border-width: 3px;");
        });
        botaoADU.setOnMouseExited(e -> {
            botaoADU.setCursor(Cursor.DEFAULT);
            botaoADU.setStyle("-fx-background-color: transparent; -fx-border-color: transparent;");
        });

        botaoADU.setOnAction(e -> {
            App.root.getChildren().clear();
            TelaADU.exibir(estados); 
        });

        Button botaoModuloComunicacao = new Button();
        botaoModuloComunicacao.setStyle("-fx-background-color: transparent; -fx-border-color: transparent;");
        camadaInterativa.getChildren().add(botaoModuloComunicacao);
        botaoModuloComunicacao.setRotate(-9);

        botaoModuloComunicacao.setOnMouseEntered(e -> {
            botaoModuloComunicacao.setCursor(Cursor.HAND);
            botaoModuloComunicacao.setStyle("-fx-background-color: transparent; -fx-border-color: yellow; -fx-border-width: 3px;");
        });
        botaoModuloComunicacao.setOnMouseExited(e -> {
            botaoModuloComunicacao.setCursor(Cursor.DEFAULT);
            botaoModuloComunicacao.setStyle("-fx-background-color: transparent; -fx-border-color: transparent;");
        });

        botaoModuloComunicacao.setOnAction(e -> {
            App.root.getChildren().clear();
            TelaModuloComunicacao.exibir(estados); 
        });

        botaoModuloComunicacao.prefWidthProperty().bind(App.primaryStage.getScene().widthProperty().multiply(190.00 / 1920));
        botaoModuloComunicacao.prefHeightProperty().bind(App.primaryStage.getScene().heightProperty().multiply(150.00/ 1080));
        botaoModuloComunicacao.layoutXProperty().bind(App.primaryStage.getScene().widthProperty().multiply(460.00 / 1920));
        botaoModuloComunicacao.layoutYProperty().bind(App.primaryStage.getScene().heightProperty().multiply(365.00 / 1080));

        Button botaoCvreversora = new Button();
        botaoCvreversora.setStyle("-fx-background-color: transparent; -fx-border-color: transparent;");
        camadaInterativa.getChildren().add(botaoCvreversora);
        botaoCvreversora.setOnMouseEntered(e -> {
            botaoCvreversora.setCursor(Cursor.HAND);
            botaoCvreversora.setStyle("-fx-background-color: transparent; -fx-border-color: yellow; -fx-border-width: 3px;");
        });
        botaoCvreversora.setOnMouseExited(e -> {
            botaoCvreversora.setCursor(Cursor.DEFAULT);
            botaoCvreversora.setStyle("-fx-background-color: transparent; -fx-border-color: transparent;");
        });
        botaoCvreversora.setOnAction(e -> {
            App.root.getChildren().clear();
            TelaChaveReversora.exibir(estados);
        });
        botaoCvreversora.prefWidthProperty().bind(App.primaryStage.getScene().widthProperty().multiply(77.00 / 1920));
        botaoCvreversora.prefHeightProperty().bind(App.primaryStage.getScene().heightProperty().multiply(77.00/ 1080));
        botaoCvreversora.layoutXProperty().bind(App.primaryStage.getScene().widthProperty().multiply(597.00 / 1920));
        botaoCvreversora.layoutYProperty().bind(App.primaryStage.getScene().heightProperty().multiply(570.04 / 1080));

        Button botaoDDu = new Button();
        botaoDDu.setStyle("-fx-background-color: transparent; -fx-border-color: transparent;");
        camadaInterativa.getChildren().add(botaoDDu);
        botaoDDu.setRotate(-15);
        botaoDDu.setOnMouseEntered(e -> {
            botaoDDu.setCursor(Cursor.HAND);
            botaoDDu.setStyle("-fx-background-color: transparent; -fx-border-color: yellow; -fx-border-width: 3px;");
        });
        botaoDDu.setOnMouseExited(e -> {
            botaoDDu.setCursor(Cursor.DEFAULT);
            botaoDDu.setStyle("-fx-background-color: transparent; -fx-border-color: transparent;");
        });

        botaoDDu.setOnAction(e -> {
            App.root.getChildren().clear();
            TelaDDU.exibir(estados); 
        });

        botaoDDu.prefWidthProperty().bind(App.primaryStage.getScene().widthProperty().multiply(380.00 / 1920));
        botaoDDu.prefHeightProperty().bind(App.primaryStage.getScene().heightProperty().multiply(230.00 / 1080));
        botaoDDu.layoutXProperty().bind(App.primaryStage.getScene().widthProperty().multiply(50.00 / 1920));
        botaoDDu.layoutYProperty().bind(App.primaryStage.getScene().heightProperty().multiply(400.00 / 1080));

        Button botaoVDU = new Button();
        botaoVDU.setStyle("-fx-background-color: transparent; -fx-border-color: transparent;");
        botaoVDU.setRotate(16);
        camadaInterativa.getChildren().add(botaoVDU);

        botaoVDU.prefWidthProperty().bind(App.primaryStage.getScene().widthProperty().multiply(400.00 / 1920));
        botaoVDU.prefHeightProperty().bind(App.primaryStage.getScene().heightProperty().multiply(250.00 / 1080));
        botaoVDU.layoutXProperty().bind(App.primaryStage.getScene().widthProperty().multiply(1420.00 / 1920));
        botaoVDU.layoutYProperty().bind(App.primaryStage.getScene().heightProperty().multiply(410.00 / 1080));

        botaoVDU.setOnMouseEntered(e -> {
            botaoVDU.setCursor(Cursor.HAND);
            botaoVDU.setStyle("-fx-background-color: transparent; -fx-border-color: yellow; -fx-border-width: 3px;");
        });
        botaoVDU.setOnMouseExited(e -> {
            botaoVDU.setCursor(Cursor.DEFAULT);
            botaoVDU.setStyle("-fx-background-color: transparent; -fx-border-color: transparent;");
        });

        botaoVDU.setOnAction(e -> {
            App.root.getChildren().clear();
            TelaVDU.exibir(estados); 
        });
        
        Button botaofinalizar = new Button();
        botaofinalizar.setStyle("-fx-background-color: transparent; -fx-border-color: transparent;");
        camadaInterativa.getChildren().add(botaofinalizar);

        botaofinalizar.prefWidthProperty().bind(App.primaryStage.getScene().widthProperty().multiply(130.00/1920));
        botaofinalizar.prefHeightProperty().bind(App.primaryStage.getScene().heightProperty().multiply(230.00 / 1080));
        botaofinalizar.layoutXProperty().bind(App.primaryStage.getScene().widthProperty().multiply(998.00 / 1920));
        botaofinalizar.layoutYProperty().bind(App.primaryStage.getScene().heightProperty().multiply(630.00 / 1080));

        botaofinalizar.setOnMouseEntered(e -> {
            botaofinalizar.setCursor(Cursor.HAND);
            botaofinalizar.setStyle("-fx-background-color: transparent; -fx-border-color: yellow; -fx-border-width: 3px;");
        });
        botaofinalizar.setOnMouseExited(e -> {
            botaofinalizar.setCursor(Cursor.DEFAULT);
            botaofinalizar.setStyle("-fx-background-color: transparent; -fx-border-color: transparent;");
        });

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

        conteudo.setOnMouseClicked((MouseEvent event) -> {// função que da a posição e pixels 
            double x = event.getX();
            double y = event.getY();
            System.out.println("Clique em: (" + x + ", " + y + ")");
        });

        App.root.getChildren().setAll(conteudo);
    }
}
