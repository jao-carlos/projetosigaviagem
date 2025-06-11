package com.pi.telas.estatisticas;
import com.pi.App;
import com.pi.classes.ControladorDeEstados;
import com.pi.telas.login.TelaInicial;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class TelaEstatisticas {

    public static void exibir(ControladorDeEstados estados) {
        Label titulo = new Label("ESTATÍSTICAS FINAIS");
        titulo.setFont(Font.font("Helvetica", FontWeight.BOLD, 28));
        titulo.setTextFill(Color.WHITE);

        Label tempo = new Label("Tempo total: " + estados.tempoDecorridoEmSegundos() + " segundos");
        Label cco = new Label("Aviso ao CCO: " + (estados.isAvisoCCO() ? "Enviado" : "Não enviado"));
        Label pa = new Label("PA emitido: " + (estados.isPaSegurarPorta() || estados.isPaProblema() ? "Sim" : "Não"));
        Label chaveReversora = new Label("Posição da Chave Reversora: " + estados.getPosChaveReversora());
        Label chaveCBTC = new Label("Posição da Chave CBTC: " + estados.getPosChaveCBTC());
        Label adesivo = new Label("Adesivo colocado: " + (estados.isAdesivoRemovido() ? "Sim" : "Não"));
        Label cinturao = new Label("Cinturão retirado: " + (estados.isCinturaoRemovido() ? "Sim" : "Não"));
        Label painel = new Label("Painel externo aberto: " + (estados.isPainelExternoAberto() ? "Sim" : "Não"));
        Label verificacao = new Label("Porta verificada: " + (estados.isVerificouAlgoNaPorta() ? "Sim" : "Não"));

        boolean chavesErradasAtivas = estados.isChave1Ativa() || estados.isChave2Ativa() || estados.isChave4Ativa();
        Label chavesErradas = new Label("Portas isoladas incorretamente: " + (chavesErradasAtivas ? "Sim" : "Não"));

        int pontos = estados.calcularPontuacaoFinal();
        Label pontuacao = new Label("Pontuação estimada: " + pontos);
        pontuacao.setTextFill(pontos >= 0 ? Color.LIGHTGREEN : Color.ORANGERED);
        pontuacao.setFont(Font.font("Helvetica", FontWeight.BOLD, 18));

        for (Label label : new Label[]{tempo, cco, pa, chaveReversora, chaveCBTC, adesivo, cinturao, painel, verificacao, chavesErradas}) {
            label.setTextFill(Color.WHITE);
            label.setFont(Font.font("Helvetica", 16));
        }

        Button botaoVoltar = new Button("Voltar");
        botaoVoltar.setPrefWidth(150);
        botaoVoltar.setFont(Font.font("Helvetica", FontWeight.BOLD, 14));
        botaoVoltar.setStyle(
            "-fx-background-color: white;" +
            "-fx-text-fill: #0066cc;" +
            "-fx-background-radius: 10;"
        );
        botaoVoltar.setOnMouseEntered(e -> botaoVoltar.setStyle(
            "-fx-background-color: #cce6ff;" +
            "-fx-text-fill: #003366;" +
            "-fx-background-radius: 10;"
        ));
        botaoVoltar.setOnMouseExited(e -> botaoVoltar.setStyle(
            "-fx-background-color: white;" +
            "-fx-text-fill: #0066cc;" +
            "-fx-background-radius: 10;"
        ));
        botaoVoltar.setOnAction(e -> TelaInicial.exibir(estados));

        VBox conteudo = new VBox(10, titulo, tempo, cco, pa, chaveReversora, chaveCBTC, adesivo, cinturao, painel, verificacao, chavesErradas, pontuacao, botaoVoltar);
        conteudo.setAlignment(Pos.CENTER);
        conteudo.setPadding(new Insets(30));

        BorderPane layout = new BorderPane();
        layout.setCenter(conteudo);
        layout.setStyle("-fx-background-color: #0066cc;");

        App.root.getChildren().setAll(layout);
    }
}
