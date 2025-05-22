package com.pi.Classes;

import javafx.beans.value.ObservableValue;
import javafx.scene.Cursor;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;

public class Seta {

    
    public static Button buttonSeta(String textoBotao, ObservableValue<Number> largura, ObservableValue<Number> altura, String direcao) {

        Polygon flecha = new Polygon();

        // Inicializa os pontos da seta
        if (direcao.equalsIgnoreCase("esq")) {
            updatePolygonEsq(flecha, largura.getValue().doubleValue(), altura.getValue().doubleValue());

            largura.addListener((obs, oldVal, newVal) ->
                updatePolygonEsq(flecha, newVal.doubleValue(), altura.getValue().doubleValue())
            );
            altura.addListener((obs, oldVal, newVal) ->
                updatePolygonEsq(flecha, largura.getValue().doubleValue(), newVal.doubleValue())
            );

        } else {
            updatePolygonDir(flecha, largura.getValue().doubleValue(), altura.getValue().doubleValue());

            largura.addListener((obs, oldVal, newVal) ->
                updatePolygonDir(flecha, newVal.doubleValue(), altura.getValue().doubleValue())
            );
            altura.addListener((obs, oldVal, newVal) ->
                updatePolygonDir(flecha, largura.getValue().doubleValue(), newVal.doubleValue())
            );
        }

        flecha.setFill(Color.LIGHTBLUE);
        flecha.setStroke(Color.DARKBLUE);
        flecha.setStrokeWidth(2);

        flecha.setOnMouseEntered(e -> {
            flecha.setCursor(Cursor.HAND);
            flecha.setFill(Color.ALICEBLUE);
        });

        flecha.setOnMouseExited(e -> {
            flecha.setCursor(Cursor.DEFAULT);
            flecha.setFill(Color.LIGHTBLUE);
        });

        Label texto = new Label(textoBotao);
        texto.setStyle("-fx-font-size: 16px; -fx-text-fill: black;");

        StackPane conteudo = new StackPane(flecha, texto);
        Button botao = new Button();
        botao.setGraphic(conteudo);
        botao.setStyle("-fx-background-color: transparent; -fx-padding: 0;");

        botao.prefWidthProperty().bind(largura);
        botao.prefHeightProperty().bind(altura);

        return botao;
    }

    // Forma da seta para a direita
    private static void updatePolygonDir(Polygon polygon, double w, double h) {
        polygon.getPoints().setAll(
            0.0, 0.25 * h,
            0.714 * w, 0.25 * h,
            0.714 * w, 0.0,
            w, 0.5 * h,
            0.714 * w, h,
            0.714 * w, 0.75 * h,
            0.0, 0.75 * h
        );
    }

    // Forma da seta para a esquerda
    private static void updatePolygonEsq(Polygon polygon, double w, double h) {
        polygon.getPoints().setAll(
            w, 0.25 * h,
            0.286 * w, 0.25 * h,
            0.286 * w, 0.0,
            0.0, 0.5 * h,
            0.286 * w, h,
            0.286 * w, 0.75 * h,
            w, 0.75 * h
        );
    }
}
