package com.pi.telas.jogo;

import com.pi.App;

import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class TelaADU {
    public static void exibir(Stage stage){
        Image imageTeste = new Image(App.class.getResource("/imagens/painelADU.jpg").toExternalForm());
        ImageView fundao = new ImageView(imageTeste);
        fundao.setPreserveRatio(false);
        fundao.setFitHeight(1080);
        fundao.setFitWidth(1920);

        StackPane root = new StackPane(fundao);
        

        Scene cena = new Scene(root, 1280, 720);

        fundao.fitWidthProperty().bind(cena.widthProperty());
        fundao.fitHeightProperty().bind(cena.heightProperty());

        stage.setTitle("Teste painelADU");
        stage.setScene(cena);
        stage.setFullScreen(true); 
        stage.show();


        // BorderPane layout = new BorderPane();
        // Button botaoTeste = new Button("Ir pra telasDu antigo");
        // botaoTeste.setOnAction(e -> {
        //     Telas_DU.exibir(stage);
        // });

        // HBox containerBotao = new HBox();
        // containerBotao.setStyle("-fx-padding: 10; -fx-alignment: bottom-right;");
        // containerBotao.getChildren().add(botaoTeste);

        // StackPane overlay = new StackPane(fundo, camadaInterativa, containerBotao);
    }

}