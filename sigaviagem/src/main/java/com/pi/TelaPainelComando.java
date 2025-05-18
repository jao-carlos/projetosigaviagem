package com.pi;

import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class TelaPainelComando {
    public static void exibir(Stage stage) {
        BorderPane layout = new BorderPane();

        
        Image testeFundo = new Image(App.class.getResource("/imagens/painelatualizado.jpg").toExternalForm());

        
        ImageView fundo = new ImageView(testeFundo);
        fundo.setPreserveRatio(false); 
        fundo.setFitWidth(1920);      
        fundo.setFitHeight(1080);

        
        StackPane root = new StackPane();
        root.getChildren().addAll(fundo, layout); 

    
        Scene cena = new Scene(root, 500, 400);
        fundo.fitWidthProperty().bind(cena.widthProperty());
        fundo.fitHeightProperty().bind(cena.heightProperty());

        
        cena.setOnMouseClicked((MouseEvent event) -> {
            double x = event.getX();
            double y = event.getY();
            System.out.println("Clique em: (" + x + ", " + y + ")");
        });

       
        stage.setFullScreen(true);
        stage.setScene(cena);
        stage.setTitle("Segunda Tela");
        stage.show();
    }
}
