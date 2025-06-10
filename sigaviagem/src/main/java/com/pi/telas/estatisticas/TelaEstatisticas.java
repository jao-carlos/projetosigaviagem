package com.pi.telas.estatisticas;

import com.pi.App;
import com.pi.classes.ControladorDeEstados;
import com.pi.telas.login.TelaInicial;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class TelaEstatisticas {

    public static void exibir(ControladorDeEstados estados) {
        // Título
        Label titulo = new Label("ESTATÍSTICAS");
        titulo.setFont(Font.font("Helvetica", FontWeight.BOLD, 28));
        titulo.setTextFill(Color.WHITE);
        titulo.setEffect(new DropShadow(2, Color.BLACK));

        // Estatísticas simuladas (pode ser substituído por dados reais)
        Label totalViagens = criarLabelEstatistica("Total de viagens: 124");
        Label distanciaTotal = criarLabelEstatistica("Distância total: 678 km");
        Label mediaTempo = criarLabelEstatistica("Tempo médio por viagem: 42 min");

        // Botão Voltar
        Button botaoVoltar = new Button("Voltar");
        botaoVoltar.setPrefWidth(150);
        botaoVoltar.setFont(Font.font("Helvetica", FontWeight.BOLD, 14));
        botaoVoltar.setStyle(
            "-fx-background-color: #ffffff;" +
            "-fx-text-fill: #0066cc;" +
            "-fx-background-radius: 10;"
        );

        botaoVoltar.setOnAction(e -> {
            App.root.getChildren().clear();
            TelaInicial.exibir(estados);
        });
      
        // Layout
        VBox layout = new VBox(20);
        layout.setPadding(new Insets(40));
        layout.setAlignment(Pos.CENTER);
        layout.setStyle("-fx-background-color: #0066cc;");

        layout.getChildren().addAll(titulo, totalViagens, distanciaTotal, mediaTempo, botaoVoltar);

        Scene cena = new Scene(layout, 600, 400);
        App.root.getChildren().setAll(layout);
    }

    private static Label criarLabelEstatistica(String texto) {
        Label label = new Label(texto);
        label.setFont(Font.font("Helvetica", FontWeight.NORMAL, 16));
        label.setTextFill(Color.WHITE);
        return label;
    }
}
