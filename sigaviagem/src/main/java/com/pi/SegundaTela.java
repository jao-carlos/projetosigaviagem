package com.pi;

import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class SegundaTela {
    public static void exibir(Stage stage) {
        BorderPane layout = new BorderPane();

        // Carrega imagem do fundo
        Image testeFundo = new Image(App.class.getResource("/imagens/painel.jpg").toExternalForm());

        // Cria ImageView e faz ocupar a tela toda
        ImageView fundo = new ImageView(testeFundo);
        fundo.setPreserveRatio(false); // cobre toda a tela mesmo que distorça
        fundo.setFitWidth(1920);       // tamanho inicial (será ligado à tela)
        fundo.setFitHeight(1080);

        // Container principal com fundo e layout sobreposto
        StackPane root = new StackPane();
        root.getChildren().addAll(fundo, layout); // fundo primeiro, depois conteúdo

        // Cena e ligação de tamanho da imagem ao tamanho da janela
        Scene cena = new Scene(root, 500, 400);
        fundo.fitWidthProperty().bind(cena.widthProperty());
        fundo.fitHeightProperty().bind(cena.heightProperty());

        // Clique para debug de posição
        cena.setOnMouseClicked((MouseEvent event) -> {
            double x = event.getX();
            double y = event.getY();
            System.out.println("Clique em: (" + x + ", " + y + ")");
        });

        // Força tela cheia
        stage.setFullScreen(true);
        stage.setScene(cena);
        stage.setTitle("Segunda Tela");
        stage.show();
    }
}
