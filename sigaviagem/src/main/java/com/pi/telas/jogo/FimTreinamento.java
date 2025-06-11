package com.pi.telas.jogo;

import com.pi.App;
import com.pi.classes.ControladorDeEstados;
import com.pi.telas.login.TelaInicial;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class FimTreinamento {

    private static final int ERRO_FATAL = -9999;

    public static void exibir(int nota, ControladorDeEstados estados, long tempo) {
        VBox fundo = criarFundo();
        Pane camadaInterativa = new Pane();
        VBox caixa = criarCaixa();

        Label titulo = criarLabel("", Pos.CENTER, true);
        Label detalhes = criarLabel("", Pos.CENTER_LEFT, true);
        Label tempoLabel = criarLabel("", Pos.CENTER_LEFT, true);
        Label motivoLabel = criarLabel("", Pos.CENTER_LEFT, true);

        if (nota == ERRO_FATAL) {
            titulo.setText("GAME OVER - Falha Crítica!");
            titulo.setStyle("-fx-text-fill: red;");
            caixa.getChildren().addAll(titulo);
        } else {
            boolean passou = nota > 0;

            titulo.setText(passou ? "Treinamento Concluído com Sucesso!" : " Falha no Treinamento");
            titulo.setStyle("-fx-text-fill: " + (passou ? "rgb(55, 240, 49);" : "red;"));

            detalhes.setText("Pontuação: " + nota + " / 14");
            detalhes.setStyle("-fx-font-size: 40px;");

            tempoLabel.setText("Tempo: " + tempo + "s");
            tempoLabel.setStyle("-fx-font-size: 40px;");

            caixa.getChildren().addAll(titulo, detalhes, tempoLabel);

            if (!passou) {
                motivoLabel.setText("Você cometeu erros demais.");
                motivoLabel.setStyle("-fx-font-size: 28px;");
                caixa.getChildren().add(motivoLabel);
            }
        }

        Region espaco = new Region();
        VBox.setVgrow(espaco, Priority.ALWAYS);

        Button voltar = criarBotao("Voltar para a tela de início");
        voltar.setMaxWidth(Double.MAX_VALUE);
        voltar.setAlignment(Pos.BOTTOM_CENTER);
        voltar.setOnAction(e -> {
            App.root.getChildren().clear();
            TelaInicial.exibir(estados);
        });

        caixa.getChildren().addAll(espaco, voltar);

        centralizarCaixa(caixa);
        camadaInterativa.getChildren().add(caixa);

        StackPane conteudo = new StackPane(fundo, camadaInterativa);
        App.root.getChildren().setAll(conteudo);
    }

    private static VBox criarFundo() {
        VBox fundo = new VBox();
        fundo.setStyle("-fx-background-color: #0066CC;");
        return fundo;
    }

    private static VBox criarCaixa() {
        VBox caixa = new VBox();
        caixa.setStyle("-fx-background-color: #5599FF; -fx-border-color: darkblue; -fx-padding: 30; -fx-spacing: 30;");
        caixa.prefWidthProperty().bind(App.primaryStage.getScene().widthProperty().multiply(600.0 / 1920));
        caixa.prefHeightProperty().bind(App.primaryStage.getScene().heightProperty().multiply(600.0 / 1080));
        return caixa;
    }

    private static void centralizarCaixa(VBox caixa) {
        caixa.layoutXProperty().bind(App.primaryStage.getScene().widthProperty().subtract(caixa.prefWidthProperty()).divide(2));
        caixa.layoutYProperty().bind(App.primaryStage.getScene().heightProperty().subtract(caixa.prefHeightProperty()).divide(2));
    }

    private static Button criarBotao(String texto) {
        Button botao = new Button(texto);
        botao.setPrefWidth(220);
        botao.setFont(Font.font("Helvetica", FontWeight.BOLD, 16));
        aplicarEstiloBotao(botao);
        return botao;
    }

    private static void aplicarEstiloBotao(Button botao) {
        String estiloPadrao = "-fx-background-color: white; " +
                "-fx-text-fill: #0066cc; " +
                "-fx-background-radius: 10px; " +
                "-fx-cursor: hand; " +
                "-fx-effect: dropshadow(two-pass-box, rgba(0,0,0,0.3), 4, 0.3, 0, 2);";

        String estiloHover = "-fx-background-color: #e6f0ff; " +
                "-fx-text-fill: #003366; " +
                "-fx-background-radius: 10px; " +
                "-fx-cursor: hand; " +
                "-fx-effect: dropshadow(two-pass-box, rgba(0,0,0,0.4), 6, 0.4, 0, 2);";

        botao.setStyle(estiloPadrao);

        botao.setOnMouseEntered(e -> botao.setStyle(estiloHover));
        botao.setOnMouseExited(e -> botao.setStyle(estiloPadrao));
    }

    private static Label criarLabel(String texto, Pos alinhamento, boolean wrap) {
        Label label = new Label(texto);
        label.setWrapText(wrap);
        label.setMaxWidth(Double.MAX_VALUE);
        label.setAlignment(alinhamento);
        label.setFont(Font.font("Arial", FontWeight.NORMAL, 24));
        label.prefWidthProperty().bind(App.primaryStage.getScene().widthProperty().multiply(600.0 / 1920));
        return label;
    }
}
