package com.pi;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.geometry.Pos;

public class PrimeiraTela {
    public static void exibir(Stage stage) {
        BorderPane layout = new BorderPane();

        // Caixa de texto central
        TextField caixaTexto = new TextField();
        StackPane centro = new StackPane(caixaTexto);
        layout.setCenter(centro);

        // Botão no canto inferior direito
        Button botaoIr = new Button("Ir para a outra tela");
        BorderPane bottom = new BorderPane();
        bottom.setRight(botaoIr);
        layout.setBottom(bottom);

        // Ação do botão
        botaoIr.setOnAction(e -> SegundaTela.exibir(stage));

        Scene cena = new Scene(layout, 400, 300);
        stage.setScene(cena);
        stage.setTitle("Primeira Tela");
        stage.show();
    }
}

