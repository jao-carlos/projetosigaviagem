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

    private final DropShadow sombraFoco = new DropShadow(15, Color.web("#FFD600"));

    public BotaoPersonalizado(DoubleBinding largura, DoubleBinding altura,
                             DoubleBinding posX, DoubleBinding posY,
                             Runnable onClickAction) {

        // Estilo base: transparente, sem borda visível e cursor default
        setStyle("-fx-background-color: transparent; -fx-border-color: transparent; -fx-border-width: 3px;");
        setCursor(Cursor.DEFAULT);

        // Bind propriedades para responsividade
        prefWidthProperty().bind(largura);
        prefHeightProperty().bind(altura);
        layoutXProperty().bind(posX);
        layoutYProperty().bind(posY);

        // Animações para borda e sombra (hover e foco)
        Timeline bordaHover = new Timeline(
            new KeyFrame(Duration.ZERO, new KeyValue(borderColorProperty(), Color.TRANSPARENT)),
            new KeyFrame(DURACAO_ANIMACAO, new KeyValue(borderColorProperty(), Color.web("#FFD600")))
        );

        Timeline bordaExit = new Timeline(
            new KeyFrame(Duration.ZERO, new KeyValue(borderColorProperty(), Color.web("#FFD600"))),
            new KeyFrame(DURACAO_ANIMACAO, new KeyValue(borderColorProperty(), Color.TRANSPARENT))
        );

        // Eventos mouse enter/exit
        setOnMouseEntered(e -> {
            setCursor(Cursor.HAND);
            setStyle("-fx-background-color: transparent; -fx-border-color: yellow; -fx-border-width: 3px; -fx-transition: border-color 250ms ease-in-out;");
        });

        setOnMouseExited(e -> {
            setCursor(Cursor.DEFAULT);
            setStyle("-fx-background-color: transparent; -fx-border-color: transparent; -fx-border-width: 3px; -fx-transition: border-color 250ms ease-in-out;");
        });

        // Sombras no foco para acessibilidade
        focusedProperty().addListener((obs, oldVal, novo) -> {
            if (novo) {
                setEffect(sombraFoco);
            } else {
                setEffect(null);
            }
        });

        // Ação de clique
        setOnAction(e -> onClickAction.run());

        // Suporte a teclado: ENTER e SPACE disparam clique
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

    // Rotação suave com animação, para usar quando quiser dar mais vida ao botão
    public void setRotacao(double angulo) {
        RotateTransition rt = new RotateTransition(Duration.millis(300), this);
        rt.setToAngle(angulo);
        rt.setInterpolator(Interpolator.EASE_BOTH);
        rt.play();
    }

    // Propriedade auxiliar para alterar a cor da borda (para animação mais limpa)
    private javafx.beans.property.ObjectProperty<Color> borderColorProperty() {
        return new javafx.beans.property.SimpleObjectProperty<Color>() {
            @Override
            public void set(Color value) {
                setStyle(String.format("-fx-background-color: transparent; -fx-border-color: %s; -fx-border-width: 3px;", toRgbString(value)));
            }

            @Override
            public Color get() {
                // Não necessário implementar o get, pois não usamos
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
