package com.pi.classes;

import javafx.beans.binding.DoubleBinding;
import javafx.scene.Cursor;
import javafx.scene.control.Button;

public class BotaoPersonalizado extends Button {

    public BotaoPersonalizado(DoubleBinding largura, DoubleBinding altura,
                               DoubleBinding posX, DoubleBinding posY,
                               Runnable onClickAction) {

        setStyle("-fx-background-color: transparent; -fx-border-color: transparent;");
        setCursor(Cursor.DEFAULT);

        prefWidthProperty().bind(largura);
        prefHeightProperty().bind(altura);
        layoutXProperty().bind(posX);
        layoutYProperty().bind(posY);

        setOnMouseEntered(e -> {
            setCursor(Cursor.HAND);
            setStyle("-fx-background-color: transparent; -fx-border-color: yellow; -fx-border-width: 3px;");
        });

        setOnMouseExited(e -> {
            setCursor(Cursor.DEFAULT);
            setStyle("-fx-background-color: transparent; -fx-border-color: transparent;");
        });

        setOnAction(e -> onClickAction.run());
    }

    public BotaoPersonalizado(DoubleBinding largura, DoubleBinding altura,
                               DoubleBinding posX, DoubleBinding posY) {
        this(largura, altura, posX, posY, () -> {});
    }

    public void setRotacao(double angulo) {
        setRotate(angulo);
    }
}
