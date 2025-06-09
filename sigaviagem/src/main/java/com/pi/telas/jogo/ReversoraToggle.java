package com.pi.telas.jogo;

import com.pi.App;
import javafx.animation.RotateTransition;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

public class ReversoraToggle extends Button {
    private final ImageView imagemView;
    private int contChaveRev;
    private int posicaoChaveRev;

    public ReversoraToggle(double larguraProporcional, double alturaProporcional) {
        Image imagem = new Image(App.class.getResource("/imagens/SoChaveReversora.png").toExternalForm());
        imagemView = new ImageView(imagem);
        imagemView.setPreserveRatio(true);

        setGraphic(imagemView);
        setStyle("-fx-background-color: transparent;");
    
        prefWidthProperty().bind(App.primaryStage.getScene().widthProperty().multiply(larguraProporcional / 1920));
        prefHeightProperty().bind(App.primaryStage.getScene().heightProperty().multiply(alturaProporcional / 1080));

        imagemView.fitWidthProperty().bind(prefWidthProperty());
        imagemView.fitHeightProperty().bind(prefHeightProperty());
    }

    public void alternarSelecao() {
        contChaveRev++;
        posicaoChaveRev = contChaveRev%3;
        RotateTransition rt = new RotateTransition(Duration.millis(100), imagemView);
        switch (posicaoChaveRev) {
            case 0:
                rt.setToAngle(0);
                break;
            case 1:
                rt.setToAngle(-90);
                break;
            case 2:
                rt.setToAngle(90);
                break;
        }
        rt.play();
        System.out.println(posicaoChaveRev);
    }

    public void posicionar(double xProporcional, double yProporcional) {
        layoutXProperty().bind(App.primaryStage.getScene().widthProperty().multiply(xProporcional / 1920));
        layoutYProperty().bind(App.primaryStage.getScene().heightProperty().multiply(yProporcional / 1080));
    }

    public int neutroFrenteRe(){
        return posicaoChaveRev;
    }

    public void setNFR(int posicaoChaveRev){
        this.posicaoChaveRev = posicaoChaveRev;
        this.contChaveRev = posicaoChaveRev;
        switch (posicaoChaveRev) {
            case 0:
                setRotate(0);
                break;
            case 1:
                setRotate(-90);
                break;
            case 2:
                setRotate(90);
                break;
        }
    }
    
}