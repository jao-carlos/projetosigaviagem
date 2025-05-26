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
        else{
            Image imagemFundo = new Image(App.class.getResource("/imagens/painelExternoAberto.jpg").toExternalForm());
            fundo = new ImageView(imagemFundo);
            fundo.setPreserveRatio(false);

            fundo.fitWidthProperty().bind(App.primaryStage.getScene().widthProperty());
            fundo.fitHeightProperty().bind(App.primaryStage.getScene().heightProperty());

            Button botaoIsolarPorta = new Button();
            botaoIsolarPorta.setStyle("-fx-background-color: transparent; -fx-border-color: transparent;");
            camadaInterativa.getChildren().add(botaoIsolarPorta);

            botaoIsolarPorta.prefWidthProperty().bind(App.primaryStage.getScene().widthProperty().multiply(620.00 / 1920));
            botaoIsolarPorta.prefHeightProperty().bind(App.primaryStage.getScene().heightProperty().multiply(450.00 / 1080));
            botaoIsolarPorta.layoutXProperty().bind(App.primaryStage.getScene().widthProperty().multiply(665.00 / 1920));
            botaoIsolarPorta.layoutYProperty().bind(App.primaryStage.getScene().heightProperty().multiply(530.00 / 1080));

            botaoIsolarPorta.setOnMouseEntered(e -> {
                botaoIsolarPorta.setCursor(Cursor.HAND);
                botaoIsolarPorta.setStyle("-fx-background-color: transparent; -fx-border-color: yellow; -fx-border-width: 3px;");
            });
            botaoIsolarPorta.setOnMouseExited(e -> {
                botaoIsolarPorta.setCursor(Cursor.DEFAULT);
                botaoIsolarPorta.setStyle("-fx-background-color: transparent; -fx-border-color: transparent;");
            });
            botaoIsolarPorta.setOnAction(e -> {
                
            });
            
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
