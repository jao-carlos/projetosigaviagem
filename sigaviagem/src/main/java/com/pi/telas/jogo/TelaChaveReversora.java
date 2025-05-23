package com.pi.telas.jogo;
import com.pi.App;
import com.pi.Classes.Seta;

import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
public class TelaChaveReversora {
    public static void exibir(){
        Image imagem = new Image(App.class.getResource("/imagens/chaveReversoraneutro.png").toExternalForm());
        ImageView fundo = new ImageView(imagem);
        fundo.setPreserveRatio(false);

        fundo.fitWidthProperty().bind(App.primaryStage.getScene().widthProperty());
        fundo.fitHeightProperty().bind(App.primaryStage.getScene().heightProperty());

        Button voltar = Seta.buttonSeta("Voltar",
            App.primaryStage.getScene().widthProperty().multiply(180.07 / 1920),
            App.primaryStage.getScene().heightProperty().multiply(145.0 / 1080),
            "esq");
        //camadaInterativa.getChildren().add(voltar);

        voltar.layoutXProperty().bind(App.primaryStage.getScene().widthProperty().multiply(0.0 / 1920));
        voltar.layoutYProperty().bind(App.primaryStage.getScene().heightProperty().multiply(900.00 / 1080));
        voltar.setOnAction(e -> {
            TelaPainelComando.exibir(); 
        });


        StackPane conteudo = new StackPane(fundo,voltar);
        App.root.getChildren().setAll(conteudo);
    }
}
