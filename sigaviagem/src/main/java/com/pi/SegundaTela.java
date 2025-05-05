package com.pi;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class SegundaTela {
    public static void exibir(Stage stage) {
        BorderPane layout = new BorderPane();

        // Botão central (não faz nada)
        Button botaoCentral = new Button("Botão Inativo");
        layout.setCenter(new StackPane(botaoCentral));

        // Botão no canto inferior esquerdo
        Button botaoVoltar = new Button("Voltar");
        BorderPane bottom = new BorderPane();
        bottom.setLeft(botaoVoltar);
        layout.setBottom(bottom);

        // Ação de voltar
        botaoVoltar.setOnAction(e -> PrimeiraTela.exibir(stage));

        Scene cena = new Scene(layout, 400, 300);
        stage.setScene(cena);
        stage.setTitle("Segunda Tela");
        stage.show();
    }
}
