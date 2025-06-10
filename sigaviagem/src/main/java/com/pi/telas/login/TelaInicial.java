package com.pi.telas.login;

import com.pi.App;
import com.pi.classes.ControladorDeEstados;
import com.pi.telas.estatisticas.TelaEstatisticas;
import com.pi.telas.jogo.TelaPainelComando;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class TelaInicial {

    public static void exibir(ControladorDeEstados estados) {

        // Imagem do símbolo do metrô (substitua pelo caminho correto)
        ImageView logoMetro = new ImageView(new Image(App.class.getResourceAsStream("/imagens/logometro.png")));
        logoMetro.setFitHeight(120);
        logoMetro.setPreserveRatio(true);

        // Título
        Label titulo = new Label("SIGA VIAGEM");
        titulo.setFont(Font.font("Helvetica", FontWeight.EXTRA_BOLD, 36));
        titulo.setTextFill(Color.WHITE);
        titulo.setEffect(new DropShadow(4, Color.BLACK));

        // Botões
        Button botaoIniciarJogo = criarBotao("Iniciar Jogo");
        Button botaoEstatisticas = criarBotao("Estatísticas");

        // Ações dos botões
        botaoIniciarJogo.setOnAction(e -> {
            estados.marcarInicio();
            App.root.getChildren().clear();
            TelaPainelComando.exibir(estados);
        });

        botaoEstatisticas.setOnAction(e -> {
            App.root.getChildren().clear();
            TelaEstatisticas.exibir(estados);
        });

        // Layout central
        VBox layout = new VBox(30);
        layout.setAlignment(Pos.CENTER);
        layout.getChildren().addAll(logoMetro, titulo, botaoIniciarJogo, botaoEstatisticas);

        // Fundo azul oficial do metrô de São Paulo
        StackPane conteudo = new StackPane();
        conteudo.setStyle("-fx-background-color: #004d99;");
        conteudo.getChildren().add(layout);

        // Mostra na tela
        App.root.getChildren().setAll(conteudo);
    }

    private static Button criarBotao(String texto) {
        Button botao = new Button(texto);
        botao.setPrefWidth(220);
        botao.setFont(Font.font("Helvetica", FontWeight.BOLD, 16));
        botao.setStyle("-fx-background-color: white; " +
                       "-fx-text-fill: #0066cc; " +
                       "-fx-background-radius: 10px; " +
                       "-fx-cursor: hand; " +
                       "-fx-effect: dropshadow(two-pass-box, rgba(0,0,0,0.3), 4, 0.3, 0, 2);");

        botao.setOnMouseEntered(e -> botao.setStyle("-fx-background-color: #e6f0ff; " +
                                                    "-fx-text-fill: #003366; " +
                                                    "-fx-background-radius: 10px; " +
                                                    "-fx-cursor: hand; " +
                                                    "-fx-effect: dropshadow(two-pass-box, rgba(0,0,0,0.4), 6, 0.4, 0, 2);"));

        botao.setOnMouseExited(e -> botao.setStyle("-fx-background-color: white; " +
                                                   "-fx-text-fill: #0066cc; " +
                                                   "-fx-background-radius: 10px; " +
                                                   "-fx-cursor: hand; " +
                                                   "-fx-effect: dropshadow(two-pass-box, rgba(0,0,0,0.3), 4, 0.3, 0, 2);"));
        return botao;
    }
}
