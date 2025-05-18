package com.pi;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class TelaInicial {

    public static void exibir(Stage stage) {
        Button botaoIniciarJogo = new Button("Iniciar Jogo");
        Button botaoEstatisticas = new Button("Estatísticas");

        botaoIniciarJogo.setPrefWidth(200);
        botaoEstatisticas.setPrefWidth(200);

        botaoIniciarJogo.setOnAction(e -> {
            TelaPainelComando.exibir(stage);
        });

        botaoEstatisticas.setOnAction(e -> {
          
            mostrarInfo("Estatísticas ainda não implementadas.");
        });

        VBox layout = new VBox(20);
        layout.setPadding(new Insets(40));
        layout.setAlignment(Pos.CENTER);
        layout.getChildren().addAll(botaoIniciarJogo, botaoEstatisticas);

        Scene cena = new Scene(layout, 600, 400);
        stage.setScene(cena);
        stage.setTitle("Menu Principal - Siga Viagem");
        stage.show();
    }

    private static void mostrarInfo(String mensagem) {
        Alert alerta = new Alert(Alert.AlertType.INFORMATION);
        alerta.setTitle("Informação");
        alerta.setHeaderText(mensagem);
        alerta.showAndWait();
    }
}

