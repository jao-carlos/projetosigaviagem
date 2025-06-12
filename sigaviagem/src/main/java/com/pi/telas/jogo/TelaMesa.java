package com.pi.telas.jogo;

import com.pi.App;
import com.pi.classes.ControladorDeEstados;
import com.pi.classes.Seta;

import javafx.scene.Cursor;
import javafx.scene.control.Button;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;

public class TelaMesa {
    
    public static void exibir(ControladorDeEstados estados){
        Image imagemFundo = new Image(App.class.getResource("/imagens/mesa.jpg").toExternalForm());
        ImageView fundo = new ImageView(imagemFundo);
        fundo.setPreserveRatio(false);

        fundo.fitWidthProperty().bind(App.primaryStage.getScene().widthProperty());
        fundo.fitHeightProperty().bind(App.primaryStage.getScene().heightProperty());
        
        Pane camadaInterativa = new Pane();

        Button direita = Seta.buttonSeta("Direita",
            App.primaryStage.getScene().widthProperty().multiply(180.07 / 1920),
            App.primaryStage.getScene().heightProperty().multiply(145.0 / 1080),
            "dir");
        camadaInterativa.getChildren().add(direita);

        direita.layoutXProperty().bind(App.primaryStage.getScene().widthProperty().multiply(1720.00 / 1920));
        direita.layoutYProperty().bind(App.primaryStage.getScene().heightProperty().multiply(900.00 / 1080));
        direita.setOnAction(e -> {
            App.root.getChildren().clear();
            TelaPainelComando.exibir(estados); 
        });

        DropShadow highlight = new DropShadow();
        highlight.setColor(Color.YELLOW);
        highlight.setRadius(20);
        highlight.setSpread(0.5);

        if (!estados.isAdesivoRemovido()) {

            Image adesivoImg = new Image(App.class.getResource("/imagens/Adesivo.png").toExternalForm());
            Button adesivo = new Button();
            ImageView adesivoView = new ImageView(adesivoImg);
            adesivoView.setPreserveRatio(true);

            adesivoView.fitWidthProperty().bind(App.primaryStage.getScene().widthProperty().multiply(900.0 / 1920));
            adesivoView.fitHeightProperty().bind(App.primaryStage.getScene().heightProperty().multiply(800.0 / 1080));
            adesivo.setGraphic(adesivoView);
            adesivo.setStyle("-fx-background-color: transparent;");
            adesivo.setCursor(Cursor.HAND);

            adesivo.layoutXProperty().bind(App.primaryStage.getScene().widthProperty().multiply(150.00 / 1920));
            adesivo.layoutYProperty().bind(App.primaryStage.getScene().heightProperty().multiply(300.00 / 1080));

            adesivo.setOnMouseEntered(e -> adesivo.setEffect(highlight));
            adesivo.setOnMouseExited(e -> adesivo.setEffect(null));
            
            adesivo.setOnAction(e -> {
                estados.setAdesivoRemovido(true);
                camadaInterativa.getChildren().remove(adesivo);
                System.out.println("Adesivo clicado e removido");
            });
            
            camadaInterativa.getChildren().add(adesivo);
        }


        if (!estados.isCinturaoRemovido()) {
            Image cinturaoImg = new Image(App.class.getResource("/imagens/Cinturao.png").toExternalForm());
            Button cinturao = new Button();
            ImageView cinturaoView = new ImageView(cinturaoImg);
            cinturaoView.setPreserveRatio(true);

            cinturaoView.fitWidthProperty().bind(App.primaryStage.getScene().widthProperty().multiply(600.0 / 1920));
            cinturaoView.fitHeightProperty().bind(App.primaryStage.getScene().heightProperty().multiply(600.0 / 1080));
            cinturao.setGraphic(cinturaoView);
            cinturao.setStyle("-fx-background-color: transparent;");
            cinturao.setCursor(Cursor.HAND);

            cinturao.layoutXProperty().bind(App.primaryStage.getScene().widthProperty().multiply(1000.00 / 1920));
            cinturao.layoutYProperty().bind(App.primaryStage.getScene().heightProperty().multiply(400.00 / 1080)); 

            cinturao.setOnMouseEntered(e -> cinturao.setEffect(highlight));
            cinturao.setOnMouseExited(e -> cinturao.setEffect(null));
            
            cinturao.setOnAction(e -> {
                estados.setCinturaoRemovido(true);
                camadaInterativa.getChildren().remove(cinturao);
                System.out.println("Cinturao clicado e removido");
            });

            camadaInterativa.getChildren().add(cinturao);
        }

        StackPane conteudo = new StackPane(fundo, camadaInterativa);

        conteudo.setOnMouseClicked((MouseEvent event) -> {
            double x = event.getX();
            double y = event.getY();
            System.out.println("Clique em: (" + x + ", " + y + ")");
        });

        App.root.getChildren().setAll(conteudo);
    }
}
