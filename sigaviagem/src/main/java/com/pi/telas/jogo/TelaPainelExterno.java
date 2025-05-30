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

public class TelaPainelExterno {
    public static void exibir(ControladorDeEstados estados){
        ImageView fundo;
        Pane camadaInterativa = new Pane();
        if(!estados.isPainelExternoAberto()){
            Image imagemFundo = new Image(App.class.getResource("/imagens/painelExternoFechado.jpg").toExternalForm());
            fundo = new ImageView(imagemFundo);
            fundo.setPreserveRatio(false);

            fundo.fitWidthProperty().bind(App.primaryStage.getScene().widthProperty());
            fundo.fitHeightProperty().bind(App.primaryStage.getScene().heightProperty());

            Button painelExterno = new Button();
            painelExterno.setStyle("-fx-background-color: transparent; -fx-border-color: transparent;");
            camadaInterativa.getChildren().add(painelExterno);

            painelExterno.prefWidthProperty().bind(App.primaryStage.getScene().widthProperty().multiply(605.00 / 1920));
            painelExterno.prefHeightProperty().bind(App.primaryStage.getScene().heightProperty().multiply(470.00 / 1080));
            painelExterno.layoutXProperty().bind(App.primaryStage.getScene().widthProperty().multiply(620.00 / 1920));
            painelExterno.layoutYProperty().bind(App.primaryStage.getScene().heightProperty().multiply(325.00 / 1080));

            painelExterno.setOnMouseEntered(e -> {
                painelExterno.setCursor(Cursor.HAND);
                painelExterno.setStyle("-fx-background-color: transparent; -fx-border-color: yellow; -fx-border-width: 3px;");
            });
            painelExterno.setOnMouseExited(e -> {
                painelExterno.setCursor(Cursor.DEFAULT);
                painelExterno.setStyle("-fx-background-color: transparent; -fx-border-color: transparent;");
            });

            painelExterno.setOnAction(e -> {
                estados.setPainelExternoAberto(true);
                TelaPainelExterno.exibir(estados);
                
            });
        }
        else {
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

            chave1.setOnAction(e -> {
                chave1.alternar();
                estados.setAdesivoRemovido(chave1.isAtivo());
            });
            chave2.setOnAction(e -> {
                chave2.alternar();
                estados.setAdesivoRemovido(chave2.isAtivo());
            });
            chave3.setOnAction(e -> {
                chave3.alternar();
                estados.setCinturaoRemovido(chave3.isAtivo());
            });
            chave4.setOnAction(e -> {
                chave4.alternar();
                estados.setCinturaoRemovido(chave4.isAtivo());
            });

            camadaInterativa.getChildren().addAll(chave1, chave2, chave3, chave4);
        }

        Button voltar = Seta.buttonSeta("Voltar",
                App.primaryStage.getScene().widthProperty().multiply(180.07 / 1920),
                App.primaryStage.getScene().heightProperty().multiply(145.0 / 1080),
                "esq");
            camadaInterativa.getChildren().add(voltar);

            voltar.layoutXProperty().bind(App.primaryStage.getScene().widthProperty().multiply(0.0 / 1920));
            voltar.layoutYProperty().bind(App.primaryStage.getScene().heightProperty().multiply(900.00 / 1080));
            voltar.setOnAction(e -> {
                App.root.getChildren().clear();
                TelaPorta.exibir(estados); 
            });
        
        StackPane conteudo = new StackPane(fundo, camadaInterativa);
        App.root.getChildren().addAll(conteudo);
    }
}
