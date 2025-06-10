package com.pi.telas.login;

import com.pi.App;
import com.pi.classes.ControladorDeEstados;
import com.pi.telas.estatisticas.TelaEstatisticas;
import com.pi.telas.jogo.TelaPainelComando;

import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;

public class TelaInicial {

    public static void exibir(ControladorDeEstados estados) {

        Image imagemFundo = new Image(App.class.getResource("/imagens/fundoInicial.jpg").toExternalForm());
        ImageView fundo = new ImageView(imagemFundo);

        fundo.setPreserveRatio(false);
        fundo.fitWidthProperty().bind(App.primaryStage.getScene().widthProperty());
        fundo.fitHeightProperty().bind(App.primaryStage.getScene().heightProperty());

        Pane camadaInterativa = new Pane();

        Button botaoIniciarJogo = new Button("Iniciar Jogo");
        Button botaoEstatisticas = new Button("Estatísticas");

        String estiloBotao = "-fx-background-color: white; "
                           + "-fx-text-fill: blue; "
                           + "-fx-font-size: 16px; "
                           + "-fx-font-family: 'Helvetica'; "
                           + "-fx-font-weight: bold; "
                           + "-fx-background-radius: 8px;";
                           
        String estiloHover = "-fx-background-color: #e0e0ff; "
                           + "-fx-text-fill: blue; "
                           + "-fx-font-size: 16px; "
                           + "-fx-font-family: 'Helvetica'; "
                           + "-fx-font-weight: bold; "
                           + "-fx-background-radius: 8px;";

        botaoIniciarJogo.setStyle(estiloBotao);
        botaoEstatisticas.setStyle(estiloBotao);

        botaoIniciarJogo.setPrefWidth(200);
        botaoEstatisticas.setPrefWidth(200);

        botaoIniciarJogo.setOnMouseEntered(e -> botaoIniciarJogo.setStyle(estiloHover));
        botaoIniciarJogo.setOnMouseExited(e -> botaoIniciarJogo.setStyle(estiloBotao));

        botaoEstatisticas.setOnMouseEntered(e -> botaoEstatisticas.setStyle(estiloHover));
        botaoEstatisticas.setOnMouseExited(e -> botaoEstatisticas.setStyle(estiloBotao));

        botaoIniciarJogo.setOnAction(e -> {
            estados.marcarInicio();
            App.root.getChildren().clear();
            TelaPainelComando.exibir(estados);
        });

        botaoEstatisticas.setOnAction(e -> {
            App.root.getChildren().clear();
            TelaEstatisticas.exibir(estados);
        });

        HBox botoes = new HBox(30);
        botoes.setAlignment(Pos.CENTER);
        botoes.getChildren().addAll(botaoIniciarJogo, botaoEstatisticas);

        botoes.layoutXProperty().bind(App.primaryStage.getScene().widthProperty().subtract(botoes.widthProperty()).divide(2));
        botoes.layoutYProperty().bind(App.primaryStage.getScene().heightProperty().multiply(0.8)); // ~80% da altura

        camadaInterativa.getChildren().add(botoes);

        StackPane conteudo = new StackPane(fundo, camadaInterativa);
        App.root.getChildren().setAll(conteudo);
    }

    private static void mostrarInfo(String mensagem) {
        Alert alerta = new Alert(Alert.AlertType.INFORMATION);
        alerta.setTitle("Informação");
        alerta.setHeaderText(mensagem);
        alerta.showAndWait();
    }
}
