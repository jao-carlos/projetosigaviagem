package com.pi.telas.jogo;

import com.pi.App;
import com.pi.classes.ControladorDeEstados;
import com.pi.classes.Seta;

import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;

public class TelaDDU {
    public static void exibir(ControladorDeEstados estados){
        Image imagemFundo = new Image(App.class.getResource("/imagens/painelDDU.jpg").toExternalForm());
        ImageView fundo = new ImageView(imagemFundo);
        fundo.setPreserveRatio(false);

        fundo.fitWidthProperty().bind(App.primaryStage.getScene().widthProperty());
        fundo.fitHeightProperty().bind(App.primaryStage.getScene().heightProperty());
        
        Pane camadaInterativa = new Pane();
        String estadoPorta;
        if(estados.isPortaFechada()){
            estadoPorta = "dduPortaSelada";
        }
        else{
            estadoPorta = "dduPortaAberta";
        }
        Image imagemPorta = new Image(App.class.getResource("/imagens/" + estadoPorta + ".png").toExternalForm());
        ImageView portas = new ImageView(imagemPorta);
        portas.fitWidthProperty().bind(App.primaryStage.getScene().widthProperty().multiply(883.0 / 1920));
        portas.fitHeightProperty().bind(App.primaryStage.getScene().heightProperty().multiply(80.0 / 1080));
        portas.layoutXProperty().bind(App.primaryStage.getScene().widthProperty().multiply(527.00/1920));
        portas.layoutYProperty().bind(App.primaryStage.getScene().widthProperty().multiply(312.00/1920));
        portas.setRotate(0.3);

       
        camadaInterativa.getChildren().add(portas);
        Button voltar = Seta.buttonSeta("Voltar",
            App.primaryStage.getScene().widthProperty().multiply(180.07 / 1920),
            App.primaryStage.getScene().heightProperty().multiply(145.0 / 1080),
            "esq");
        camadaInterativa.getChildren().add(voltar);

        voltar.layoutXProperty().bind(App.primaryStage.getScene().widthProperty().multiply(0.0 / 1920));
        voltar.layoutYProperty().bind(App.primaryStage.getScene().heightProperty().multiply(900.00 / 1080));
        voltar.setOnAction(e -> {
            App.root.getChildren().clear();
            TelaPainelComando.exibir(estados); 
        });
        
        StackPane conteudo = new StackPane(fundo, camadaInterativa);
        App.root.getChildren().addAll(conteudo);
    }
}
