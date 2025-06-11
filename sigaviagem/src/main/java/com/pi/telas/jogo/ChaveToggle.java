package com.pi.telas.jogo;

import com.pi.App;
import javafx.animation.RotateTransition;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

public class ChaveToggle extends Button {
    private boolean ativo = false;
    private final ImageView imagemView;

    public ChaveToggle(double larguraProporcional, double alturaProporcional) {
        Image imagem = new Image(App.class.getResource("/imagens/ChavesExterna.png").toExternalForm());
        imagemView = new ImageView(imagem);
        imagemView.setPreserveRatio(true);

        setGraphic(imagemView);
        setStyle("-fx-background-color: transparent;");
    
        prefWidthProperty().bind(App.primaryStage.getScene().widthProperty().multiply(larguraProporcional / 1920));
        prefHeightProperty().bind(App.primaryStage.getScene().heightProperty().multiply(alturaProporcional / 1080));

        imagemView.fitWidthProperty().bind(prefWidthProperty());
        imagemView.fitHeightProperty().bind(prefHeightProperty());
    }

    public void alternar() {
        ativo = !ativo;

        RotateTransition rt = new RotateTransition(Duration.millis(100), imagemView);
        rt.setByAngle(ativo ? 90 : -90);
        rt.play();
    }

    public void posicionar(double xProporcional, double yProporcional) {
        layoutXProperty().bind(App.primaryStage.getScene().widthProperty().multiply(xProporcional / 1920));
        layoutYProperty().bind(App.primaryStage.getScene().heightProperty().multiply(yProporcional / 1080));
    }

    public boolean isAtivo() {
        return ativo;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
        imagemView.setRotate(ativo ? 90 : 0);
    }

}
