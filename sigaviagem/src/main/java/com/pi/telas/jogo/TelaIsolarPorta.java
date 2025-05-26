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

public class TelaIsolarPorta {
    public static void exibir(ControladorDeEstados estados) {
        Image imagemFundo = new Image(App.class.getResource("/imagens/painelExternoIsolarPortas.jpg").toExternalForm());
        ImageView fundo = new ImageView(imagemFundo);
        fundo.setPreserveRatio(false);

        fundo.fitWidthProperty().bind(App.primaryStage.getScene().widthProperty());
        fundo.fitHeightProperty().bind(App.primaryStage.getScene().heightProperty());

        Pane camadaInterativa = new Pane();

        Button porta1 = new Button();
        porta1.setStyle("-fx-background-color: transparent; -fx-border-color: transparent;");
        camadaInterativa.getChildren().add(porta1);

        porta1.prefWidthProperty().bind(App.primaryStage.getScene().widthProperty().multiply(150.0 / 1920));
        porta1.prefHeightProperty().bind(App.primaryStage.getScene().heightProperty().multiply(150.0 / 1080));
        porta1.layoutXProperty().bind(App.primaryStage.getScene().widthProperty().multiply(555.00 / 1920));
        porta1.layoutYProperty().bind(App.primaryStage.getScene().heightProperty().multiply(640.00 / 1080));

        porta1.setOnMouseEntered(e -> {
            porta1.setCursor(Cursor.HAND);
            porta1.setStyle("-fx-background-color: transparent; -fx-border-color: yellow; -fx-border-width: 3px;");
        });
        porta1.setOnMouseExited(e -> {
            porta1.setCursor(Cursor.DEFAULT);
            porta1.setStyle("-fx-background-color: transparent; -fx-border-color: transparent;");
        });

        Button porta2 = new Button();
        porta2.setStyle("-fx-background-color: transparent; -fx-border-color: transparent;");
        camadaInterativa.getChildren().add(porta2);

        porta2.prefWidthProperty().bind(App.primaryStage.getScene().widthProperty().multiply(150.0 / 1920));
        porta2.prefHeightProperty().bind(App.primaryStage.getScene().heightProperty().multiply(150.0 / 1080));
        porta2.layoutXProperty().bind(App.primaryStage.getScene().widthProperty().multiply(810.00 / 1920));
        porta2.layoutYProperty().bind(App.primaryStage.getScene().heightProperty().multiply(640.00 / 1080));

        porta2.setOnMouseEntered(e -> {
            porta2.setCursor(Cursor.HAND);
            porta2.setStyle("-fx-background-color: transparent; -fx-border-color: yellow; -fx-border-width: 3px;");
        });
        porta2.setOnMouseExited(e -> {
            porta2.setCursor(Cursor.DEFAULT);
            porta2.setStyle("-fx-background-color: transparent; -fx-border-color: transparent;");
        });

                Button porta3 = new Button();
        porta3.setStyle("-fx-background-color: transparent; -fx-border-color: transparent;");
        camadaInterativa.getChildren().add(porta3);

        porta3.prefWidthProperty().bind(App.primaryStage.getScene().widthProperty().multiply(150.0 / 1920));
        porta3.prefHeightProperty().bind(App.primaryStage.getScene().heightProperty().multiply(150.0 / 1080));
        porta3.layoutXProperty().bind(App.primaryStage.getScene().widthProperty().multiply(1065.00 / 1920));
        porta3.layoutYProperty().bind(App.primaryStage.getScene().heightProperty().multiply(640.00 / 1080));

        porta3.setOnMouseEntered(e -> {
            porta3.setCursor(Cursor.HAND);
            porta3.setStyle("-fx-background-color: transparent; -fx-border-color: yellow; -fx-border-width: 3px;");
        });
        porta3.setOnMouseExited(e -> {
            porta3.setCursor(Cursor.DEFAULT);
            porta3.setStyle("-fx-background-color: transparent; -fx-border-color: transparent;");
        });

        Button porta4 = new Button();
        porta4.setStyle("-fx-background-color: transparent; -fx-border-color: transparent;");
        camadaInterativa.getChildren().add(porta4);

        porta4.prefWidthProperty().bind(App.primaryStage.getScene().widthProperty().multiply(150.0 / 1920));
        porta4.prefHeightProperty().bind(App.primaryStage.getScene().heightProperty().multiply(150.0 / 1080));
        porta4.layoutXProperty().bind(App.primaryStage.getScene().widthProperty().multiply(1320.00 / 1920));
        porta4.layoutYProperty().bind(App.primaryStage.getScene().heightProperty().multiply(640.00 / 1080));

        porta4.setOnMouseEntered(e -> {
            porta4.setCursor(Cursor.HAND);
            porta4.setStyle("-fx-background-color: transparent; -fx-border-color: yellow; -fx-border-width: 3px;");
        });
        porta4.setOnMouseExited(e -> {
            porta4.setCursor(Cursor.DEFAULT);
            porta4.setStyle("-fx-background-color: transparent; -fx-border-color: transparent;");
        });



        Button voltar = Seta.buttonSeta("Voltar",
                App.primaryStage.getScene().widthProperty().multiply(180.07 / 1920),
                App.primaryStage.getScene().heightProperty().multiply(145.0 / 1080),
                "esq");
            camadaInterativa.getChildren().add(voltar);

            voltar.layoutXProperty().bind(App.primaryStage.getScene().widthProperty().multiply(0.0 / 1920));
            voltar.layoutYProperty().bind(App.primaryStage.getScene().heightProperty().multiply(900.00 / 1080));
            voltar.setOnAction(e -> {
                App.root.getChildren().clear();
                TelaPainelExterno.exibir(estados); 
            });

        StackPane conteudo = new StackPane(fundo, camadaInterativa);

        conteudo.setOnMouseClicked((MouseEvent event) -> {// função que da a posição e pixels 
            double x = event.getX();
            double y = event.getY();
            System.out.println("Clique em: (" + x + ", " + y + ")");
        });

        App.root.getChildren().setAll(conteudo);
    }
}
