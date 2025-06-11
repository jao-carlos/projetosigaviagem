package com.pi.classes;

import javafx.animation.Interpolator;
import javafx.animation.RotateTransition;
import javafx.beans.binding.DoubleBinding;
import javafx.scene.Cursor;
import javafx.scene.control.Button;
import javafx.scene.effect.DropShadow;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import javafx.util.Duration;

public class BotaoPersonalizado extends Button {

    private final DropShadow sombraHover = new DropShadow(12, Color.web("#FFD600"));
    private final DropShadow sombraFoco = new DropShadow(15, Color.web("#FFD600"));

    public BotaoPersonalizado(DoubleBinding largura, DoubleBinding altura,
                              DoubleBinding posX, DoubleBinding posY,
                              Runnable onClickAction) {

        // Estilo base
        setStyle(
            "-fx-background-color: transparent;" +
            "-fx-border-color: transparent;" +
            "-fx-border-width: 2px;" +
            "-fx-border-radius: 8px;" +
            "-fx-background-radius: 8px;" +
            "-fx-transition: all 0.3s ease-in-out;"
        );
        setCursor(Cursor.DEFAULT);

        // Responsividade
        prefWidthProperty().bind(largura);
        prefHeightProperty().bind(altura);
        layoutXProperty().bind(posX);
        layoutYProperty().bind(posY);

        // Hover refinado
        setOnMouseEntered(e -> {
            setCursor(Cursor.HAND);
            setStyle(
                "-fx-background-color: transparent;" +
                "-fx-border-color: #FFD600;" +
                "-fx-border-width: 2px;" +
                "-fx-border-radius: 8px;" +
                "-fx-background-radius: 8px;" +
                "-fx-effect: dropshadow(gaussian, #FFD600, 10, 0.3, 0, 0);" +
                "-fx-transition: all 0.3s ease-in-out;"
            );
            setEffect(sombraHover);
        });

        setOnMouseExited(e -> {
            setCursor(Cursor.DEFAULT);
            setStyle(
                "-fx-background-color: transparent;" +
                "-fx-border-color: transparent;" +
                "-fx-border-width: 2px;" +
                "-fx-border-radius: 8px;" +
                "-fx-background-radius: 8px;" +
                "-fx-transition: all 0.3s ease-in-out;"
            );
            setEffect(null);
        });

        // Foco com brilho
        focusedProperty().addListener((obs, oldVal, novo) -> {
            if (novo) {
                setEffect(sombraFoco);
            } else {
                setEffect(null);
            }
        });

        // Clique
        setOnAction(e -> onClickAction.run());

        // Acessibilidade com ENTER ou SPACE
        setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER || event.getCode() == KeyCode.SPACE) {
                fire();
            }
        });
    }

    public BotaoPersonalizado(DoubleBinding largura, DoubleBinding altura,
                              DoubleBinding posX, DoubleBinding posY) {
        this(largura, altura, posX, posY, () -> {});
    }

    // Animação de rotação
    public void setRotacao(double angulo) {
        RotateTransition rt = new RotateTransition(Duration.millis(300), this);
        rt.setToAngle(angulo);
        rt.setInterpolator(Interpolator.EASE_BOTH);
        rt.play();
    }
}
