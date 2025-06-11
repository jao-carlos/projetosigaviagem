package com.pi.telas.jogo;
import com.pi.App;
import com.pi.classes.ControladorDeEstados;
import com.pi.classes.Seta;

import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
public class TelaChaveReversora {
    public static void exibir(ControladorDeEstados estados){
        Image imagem = new Image(App.class.getResource("/imagens/BaseReversora.jpg").toExternalForm());
        ImageView fundo = new ImageView(imagem);
        fundo.setPreserveRatio(false);
        Pane camadaInterativa = new Pane();

        fundo.fitWidthProperty().bind(App.primaryStage.getScene().widthProperty());
        fundo.fitHeightProperty().bind(App.primaryStage.getScene().heightProperty());
        
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

        Image chaveImg = new Image(App.class.getResource("/imagens/SoChaveReversora.png").toExternalForm());
        ImageView chaveView = new ImageView(chaveImg);

        chaveView.setFitWidth(App.primaryStage.getScene().getWidth() * (1550.0 / 1920));
        chaveView.setPreserveRatio(true);

        chaveView.layoutXProperty().bind(App.primaryStage.getScene().widthProperty().multiply(220.0 / 1920));
        chaveView.layoutYProperty().bind(App.primaryStage.getScene().heightProperty().multiply(50 / 1080));

        double[] angulos = {0, -90, 90};

        chaveView.setRotate(angulos[estados.getPosChaveReversora()]);

        Button chaveRev = new Button();
        chaveRev.setStyle("-fx-background-color: transparent;");
        chaveRev.setPrefSize(1000, 1000);
        chaveRev.layoutXProperty().bind(chaveView.layoutXProperty());
        chaveRev.layoutYProperty().bind(chaveView.layoutYProperty());

        chaveRev.setOnAction(e -> {
            int novaPos = (estados.getPosChaveReversora() + 1) % 3;
            estados.setPosChaveReversora(novaPos);
            chaveView.setRotate(angulos[novaPos]);
        });

        camadaInterativa.getChildren().addAll(chaveView, chaveRev);

        StackPane conteudo = new StackPane(fundo,camadaInterativa);
        App.root.getChildren().setAll(conteudo);
    }
}
