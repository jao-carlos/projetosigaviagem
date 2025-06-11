package com.pi.classes;

import javafx.animation.*;
import javafx.beans.binding.DoubleBinding;
import javafx.scene.Cursor;
import javafx.scene.control.Button;
import javafx.scene.effect.DropShadow;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import javafx.util.Duration;

public class BotaoPersonalizado extends Button {

    private static final Duration DURACAO_ANIMACAO = Duration.millis(250);

    private final DropShadow sombraHover = new DropShadow(15, Color.web("#FFD600"));
    private Timeline sombraAnim;

    public BotaoPersonalizado(DoubleBinding largura, DoubleBinding altura,
                             DoubleBinding posX, DoubleBinding posY,
                             Runnable onClickAction) {

        setStyle("-fx-background-color: transparent; -fx-border-width: 3px; -fx-border-color: transparent;");
        setCursor(Cursor.DEFAULT);

        // Bindings
        prefWidthProperty().bind(largura);
        prefHeightProperty().bind(altura);
        layoutXProperty().bind(posX);
        layoutYProperty().bind(posY);

        // Inicializa sombra transparente
        sombraHover.setColor(Color.web("#FFD600", 0));
        setEffect(null);

        // Animação para sombra (hover e foco)
        sombraAnim = new Timeline(
            new KeyFrame(Duration.ZERO,
                new KeyValue(sombraHover.colorProperty(), Color.web("#FFD600", 0)),
                new KeyValue(borderColorProperty(), Color.TRANSPARENT)
            ),
            new KeyFrame(DURACAO_ANIMACAO,
                new KeyValue(sombraHover.colorProperty(), Color.web("#FFD600", 0.8)),
                new KeyValue(borderColorProperty(), Color.web("#FFD600"))
            )
        );
        sombraAnim.setAutoReverse(false);

        // Mouse entrou: animar borda e sombra
        setOnMouseEntered(e -> {
            setCursor(Cursor.HAND);
            sombraAnim.setRate(1);
            sombraAnim.playFromStart();
            setEffect(sombraHover);
        });

        // Mouse saiu: reverter animação
        setOnMouseExited(e -> {
            setCursor(Cursor.DEFAULT);
            sombraAnim.setRate(-1);
            sombraAnim.playFrom(sombraAnim.getTotalDuration());
            // Só remove sombra se não tiver foco
            if (!isFocused()) {
                setEffect(null);
            }
        });

        // Foco teclado: mantém sombra e borda ativa
        focusedProperty().addListener((obs, oldV, newV) -> {
            if (newV) {
                sombraAnim.stop();
                setEffect(sombraHover);
                setStyle("-fx-background-color: transparent; -fx-border-width: 3px; -fx-border-color: #FFD600;");
            } else {
                if (!isHover()) {
                    setEffect(null);
                    setStyle("-fx-background-color: transparent; -fx-border-width: 3px; -fx-border-color: transparent;");
                }
            }
        });

        // Ação click
        setOnAction(e -> onClickAction.run());

        // Suporte teclado ENTER e SPACE
        setOnKeyPressed(e -> {
            if (e.getCode() == KeyCode.ENTER || e.getCode() == KeyCode.SPACE) {
                fire();
            }
        });
    }

    public BotaoPersonalizado(DoubleBinding largura, DoubleBinding altura,
                             DoubleBinding posX, DoubleBinding posY) {
        this(largura, altura, posX, posY, () -> {});
    }

    // Rotação suave opcional
    public void setRotacao(double angulo) {
        RotateTransition rt = new RotateTransition(Duration.millis(300), this);
        rt.setToAngle(angulo);
        rt.setInterpolator(Interpolator.EASE_BOTH);
        rt.play();
    }

    // Helper para animar a cor da borda via CSS (não oficial, hack necessário)
    private javafx.beans.property.ObjectProperty<Color> borderColorProperty() {
        return new javafx.beans.property.SimpleObjectProperty<Color>() {
            @Override
            public void set(Color value) {
                String cor = toRgbString(value);
                setStyle(String.format("-fx-background-color: transparent; -fx-border-width: 3px; -fx-border-color: %s;", cor));
            }

            @Override
            public Color get() {
                return null; // Não usado
            }
        };
    }

    private String toRgbString(Color c) {
        return String.format("rgba(%d, %d, %d, %.2f)",
                (int)(c.getRed()*255),
                (int)(c.getGreen()*255),
                (int)(c.getBlue()*255),
                c.getOpacity());
    }
}
