package com.pi.telas.jogo;

import com.pi.App;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class TelaDDU {
    public static void exibir(){
        Image imagemFundo = new Image(App.class.getResource("/imagens/painelDDU.jpg").toExternalForm());
        ImageView fundo = new ImageView(imagemFundo);
        fundo.setPreserveRatio(false);

        fundo.fitWidthProperty().bind(App.primaryStage.getScene().widthProperty());
        fundo.fitHeightProperty().bind(App.primaryStage.getScene().heightProperty());

        
        App.root.getChildren().addAll(fundo);
    }
}
