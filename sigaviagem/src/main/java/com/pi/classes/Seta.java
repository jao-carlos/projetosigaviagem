package com.pi.classes;

import javafx.animation.FillTransition;
import javafx.animation.ScaleTransition;
import javafx.beans.value.ObservableValue;
import javafx.scene.Cursor;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import javafx.util.Duration;

public class Seta {

    public static Button buttonSeta(String textoBotao, ObservableValue<Number> largura, ObservableValue<Number> altura, String direcao) {
        Polygon flecha = new Polygon();
        // Inicializa a forma da seta baseada na direção
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

        // Estilo base
        flecha.setFill(Color.web("#5A9BD5"));         // azul profissional
        flecha.setStroke(Color.web("#2A5599"));       // contorno forte
        flecha.setStrokeWidth(2);

        // Transição de cor para hover
        FillTransition fillHover = new FillTransition(Duration.millis(250), flecha, 
            Color.web("#5A9BD5"), Color.web("#A9CFF0"));
        FillTransition fillExit = new FillTransition(Duration.millis(250), flecha, 
            Color.web("#A9CFF0"), Color.web("#5A9BD5"));

        // Animação de scale (leve zoom)
        ScaleTransition scaleHover = new ScaleTransition(Duration.millis(200), flecha);
        scaleHover.setToX(1.05);
        scaleHover.setToY(1.05);

        ScaleTransition scaleExit = new ScaleTransition(Duration.millis(200), flecha);
        scaleExit.setToX(1.0);
        scaleExit.setToY(1.0);

        // Eventos de mouse para animações suaves e cursor
        flecha.setOnMouseEntered(e -> {
            flecha.setCursor(Cursor.HAND);
            fillHover.playFromStart();
            scaleHover.playFromStart();
        });

        flecha.setOnMouseExited(e -> {
            flecha.setCursor(Cursor.DEFAULT);
            fillExit.playFromStart();
            scaleExit.playFromStart();
        });

        // Label do texto
        Label texto = new Label(textoBotao);
        texto.setStyle("-fx-font-size: 16px; -fx-text-fill: #333; -fx-font-weight: bold;");

        StackPane conteudo = new StackPane(flecha, texto);

        // Botão com estilo limpo e suporte a foco (acessibilidade)
        Button botao = new Button();
        botao.setGraphic(conteudo);
        botao.setStyle("-fx-background-color: transparent; -fx-padding: 0;");
        botao.prefWidthProperty().bind(largura);
        botao.prefHeightProperty().bind(altura);

        // Feedback visual para foco via teclado
        botao.focusedProperty().addListener((obs, oldVal, newVal) -> {
            if (newVal) {
                // Quando focado, aumenta a sombra e brilho
                flecha.setEffect(new javafx.scene.effect.DropShadow(15, Color.web("#4178BE")));
            } else {
                flecha.setEffect(null);
            }
        });

        // Ação ao pressionar ENTER para acessibilidade
        botao.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER || event.getCode() == KeyCode.SPACE) {
                // Aqui você pode disparar evento ou animação de clique
                botao.fire();
            }
        });

        return botao;
    }

    // Polígono seta direita
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

    // Polígono seta esquerda
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
