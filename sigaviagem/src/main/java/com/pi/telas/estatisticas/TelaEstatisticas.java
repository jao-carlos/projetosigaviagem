package com.pi.telas.estatisticas;

import com.pi.App;
import com.pi.classes.ControladorDeEstados;
import com.pi.telas.login.TelaInicial;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class TelaEstatisticas {

    public static void exibir(ControladorDeEstados estados) {
        Label titulo = new Label("ESTATÍSTICAS FINAIS");
        titulo.setFont(Font.font("Helvetica", FontWeight.EXTRA_BOLD, 32));
        titulo.setTextFill(Color.WHITE);
        titulo.setStyle("-fx-effect: dropshadow(gaussian, black, 3, 0.5, 1, 1);");

        Label tempo = criarLabel("Tempo total: " + estados.getTempoFinal()+ " segundos");
        Label cco = criarLabel("Aviso ao CCO: " + (estados.isAvisoCCO() ? "Enviado" : "Não enviado"));
        Label pa = criarLabel("PA emitido: " + (estados.isPaSegurarPorta() || estados.isPaProblema() ? "Sim" : "Não"));
        Label chaveReversora = criarLabel("Posição da Chave Reversora: " + estados.getPosChaveReversora());
        Label chaveCBTC = criarLabel("Posição da Chave CBTC: " + estados.getPosChaveCBTC());
        Label adesivo = criarLabel("Adesivo colocado: " + (estados.isAdesivoRemovido() ? "Sim" : "Não"));
        Label painel = criarLabel("Painel externo aberto: " + (estados.isPainelExternoAberto() ? "Sim" : "Não"));
        Label verificacao = criarLabel("Porta verificada: " + (estados.isVerificouAlgoNaPorta() ? "Sim" : "Não"));

        boolean chavesErradasAtivas = estados.isChave1Ativa() || estados.isChave2Ativa() || estados.isChave4Ativa();
        Label chavesErradas = criarLabel("Portas isoladas incorretamente: " + (chavesErradasAtivas ? "Sim" : "Não"));

        int pontos = estados.calcularPontuacaoFinal();
        Label pontuacao = new Label("Pontuação estimada: " + pontos);
        pontuacao.setTextFill(pontos >= 0 ? Color.LIGHTGREEN : Color.ORANGERED);
        pontuacao.setFont(Font.font("Helvetica", FontWeight.BOLD, 20));
        pontuacao.setStyle("-fx-effect: dropshadow(gaussian, black, 1, 0.5, 1, 1);");

        Button botaoVoltar = new Button("Voltar");
        botaoVoltar.setPrefWidth(180);
        botaoVoltar.setFont(Font.font("Helvetica", FontWeight.BOLD, 14));
        botaoVoltar.setStyle(
            "-fx-background-color: white;" +
            "-fx-text-fill: #0066cc;" +
            "-fx-background-radius: 12;" +
            "-fx-border-color: #004080;" +
            "-fx-border-width: 1.5;"
        );
        botaoVoltar.setOnMouseEntered(e -> botaoVoltar.setStyle(
            "-fx-background-color: #cce6ff;" +
            "-fx-text-fill: #003366;" +
            "-fx-background-radius: 12;" +
            "-fx-border-color: #003366;" +
            "-fx-border-width: 1.5;"
        ));
        botaoVoltar.setOnMouseExited(e -> botaoVoltar.setStyle(
            "-fx-background-color: white;" +
            "-fx-text-fill: #0066cc;" +
            "-fx-background-radius: 12;" +
            "-fx-border-color: #004080;" +
            "-fx-border-width: 1.5;"
        ));
        botaoVoltar.setOnAction(e -> TelaInicial.exibir(estados));

        VBox estatisticasBox = new VBox(10,
            tempo, cco, pa, chaveReversora, chaveCBTC, adesivo, painel, verificacao, chavesErradas, pontuacao
        );
        estatisticasBox.setPadding(new Insets(20));
        estatisticasBox.setAlignment(Pos.CENTER_LEFT);
        estatisticasBox.setMaxWidth(400);
        estatisticasBox.setStyle(
            "-fx-background-color: rgba(255, 255, 255, 0.1);" +
            "-fx-background-radius: 15;" +
            "-fx-padding: 20;"
        );

        VBox conteudo = new VBox(30, titulo, estatisticasBox, botaoVoltar);
        conteudo.setAlignment(Pos.CENTER);
        conteudo.setPadding(new Insets(40));

        BorderPane layout = new BorderPane();
        layout.setCenter(conteudo);
        layout.setStyle("-fx-background-color: linear-gradient(to bottom, #004080, #0066cc);");

        App.root.getChildren().setAll(layout);
    }

    private static Label criarLabel(String texto) {
        Label label = new Label(texto);
        label.setFont(Font.font("Helvetica", 16));
        label.setTextFill(Color.WHITE);
        return label;
    }
}
