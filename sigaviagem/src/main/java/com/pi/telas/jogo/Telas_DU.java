package com.pi.telas.jogo;

import com.pi.App;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class Telas_DU {

    public static void exibir() {
        BorderPane layout = new BorderPane();

        ImageView imageView = new ImageView();
        imageView.setPreserveRatio(true);
        imageView.setFitWidth(1000); // ajustável

        Image imgDDU = new Image(App.class.getResource("/imagens/painelDDU.jpg").toExternalForm());
        Image imgVDU = new Image(App.class.getResource("/imagens/painelVDU.jpg").toExternalForm());
        Image imgADU = new Image(App.class.getResource("/imagens/painelADU.jpg").toExternalForm());

       
        Button btnDDU = new Button("Mostrar DDU");
        Button btnVDU = new Button("Mostrar VDU");
        Button btnADU = new Button("Mostrar ADU");
        Button btnVoltar = new Button("Voltar");

        btnDDU.setOnAction(e -> imageView.setImage(imgDDU));
        btnVDU.setOnAction(e -> imageView.setImage(imgVDU));
        btnADU.setOnAction(e -> imageView.setImage(imgADU));
        btnVoltar.setOnAction(e -> TelaPainelComando.exibir());

       
        HBox botoes = new HBox(20, btnDDU, btnVDU, btnADU, btnVoltar);
        botoes.setAlignment(Pos.CENTER);
        botoes.setPadding(new Insets(15));

     
        layout.setCenter(imageView);
        layout.setBottom(botoes);

        StackPane raiz = new StackPane(layout);
        //Scene cena = new Scene(root, 1280, 720);
        App.root.getChildren().setAll(raiz);
        // stage.setScene(cena);
        // stage.setFullScreen(true);
        // stage.setTitle("Visualizador de DU - Siga Viagem");
        // stage.show();
    }
}
