package com.pi.classes;

import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.RotateTransition;
import javafx.animation.Timeline;
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

        prefWidthProperty().bind(largura);
        prefHeightProperty().bind(altura);
        layoutXProperty().bind(posX);
        layoutYProperty().bind(posY);

        sombraHover.setColor(Color.web("#FFD600", 0));
        setEffect(null);

        
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

   
        setOnMouseEntered(e -> {
            setCursor(Cursor.HAND);
            sombraAnim.setRate(1);
            sombraAnim.playFromStart();
            setEffect(sombraHover);
        });

       
        setOnMouseExited(e -> {
            setCursor(Cursor.DEFAULT);
            sombraAnim.setRate(-1);
            sombraAnim.playFrom(sombraAnim.getTotalDuration());
           
            if (!isFocused()) {
                setEffect(null);
            }
        });

       
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

        
        setOnAction(e -> onClickAction.run());

       
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

   
    public void setRotacao(double angulo) {
        RotateTransition rt = new RotateTransition(Duration.millis(300), this);
        rt.setToAngle(angulo);
        rt.setInterpolator(Interpolator.EASE_BOTH);
        rt.play();
    }

   
    private javafx.beans.property.ObjectProperty<Color> borderColorProperty() {
        return new javafx.beans.property.SimpleObjectProperty<Color>() {
            @Override
            public void set(Color value) {
                String cor = toRgbString(value);
                setStyle(String.format("-fx-background-color: transparent; -fx-border-width: 3px; -fx-border-color: %s;", cor));
            }

            @Override
            public Color get() {
                return null;
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
