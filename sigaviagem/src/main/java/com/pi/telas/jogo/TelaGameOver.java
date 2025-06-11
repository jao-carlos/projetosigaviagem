package com.pi.telas.jogo;

import com.pi.App;
import com.pi.classes.ControladorDeEstados;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Paint;
import javafx.scene.text.TextAlignment;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import javafx.geometry.Insets;

public class TelaGameOver {

    public static void exibir(ControladorDeEstados estados, String motivo) {
        // Fundo azul
        StackPane root = new StackPane();
        root.setBackground(new Background(new BackgroundFill(Color.web("#0070C0"), CornerRadii.EMPTY, Insets.EMPTY)));

        // Painel central (retângulo azul claro)
        VBox painel = new VBox(20);
        painel.setAlignment(Pos.CENTER);
        painel.setPadding(new Insets(50));
        painel.setPrefWidth(500);
        painel.setPrefHeight(400);
        painel.setBackground(new Background(new BackgroundFill(Color.web("#5B9BD5"), new CornerRadii(10), Insets.EMPTY)));

        // Título
        Label titulo = new Label("Falha no treinamento");
        titulo.setFont(Font.font("Arial", FontWeight.BOLD, 24));
        titulo.setTextFill(Color.RED);
        titulo.setTextAlignment(TextAlignment.CENTER);

        // Motivo
        Label motivoLabel = new Label("Motivo: " + motivo);
        motivoLabel.setFont(Font.font("Arial", 18));
        motivoLabel.setTextFill(Color.BLACK);

        // Botão para voltar (ou reiniciar, sair, etc.)
        Button botaoVoltar = new Button("Voltar");
        botaoVoltar.setPrefSize(200, 40);
        botaoVoltar.setStyle("-fx-background-color: #2E75B6; -fx-text-fill: white; -fx-font-size: 16px;");
        botaoVoltar.setOnAction(e -> {
            // Volta para a tela inicial ou reinicia o jogo
            com.pi.telas.login.TelaInicial.exibir(estados);
        });

        painel.getChildren().addAll(titulo, motivoLabel, botaoVoltar);
        root.getChildren().add(painel);

        App.root.getChildren().clear();
        App.root.getChildren().add(root);
    }
}
