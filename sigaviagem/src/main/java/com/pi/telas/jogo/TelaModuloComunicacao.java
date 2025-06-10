package com.pi.telas.jogo;

import com.pi.App;
import com.pi.classes.BotaoPersonalizado;
import com.pi.classes.ControladorDeEstados;
import com.pi.classes.Seta;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

public class TelaModuloComunicacao {
    public static void exibir(ControladorDeEstados estados) {
        Image imagem = new Image(App.class.getResource("/imagens/painelModuloComunicacao.jpg").toExternalForm());
        ImageView fundo = new ImageView(imagem);
        fundo.setPreserveRatio(false);

        fundo.fitWidthProperty().bind(App.primaryStage.getScene().widthProperty());
        fundo.fitHeightProperty().bind(App.primaryStage.getScene().heightProperty());

        Pane camadaInterativa = new Pane();

        BotaoPersonalizado botaoPA = new BotaoPersonalizado(
            App.primaryStage.getScene().widthProperty().multiply(80.07 / 1920),
            App.primaryStage.getScene().heightProperty().multiply(80.0 / 1080),
            App.primaryStage.getScene().widthProperty().multiply(300.0 / 1920),
            App.primaryStage.getScene().heightProperty().multiply(150.31 / 1080),
            () -> {
                if(estados.isAvisoCCO()){
                    VBox aviso = avisoProblema();
                    camadaInterativa.getChildren().add(aviso);
                    estados.setPaProblema(true);
                }
                else{
                    VBox aviso = avisoPorta();
                    camadaInterativa.getChildren().add(aviso);
                    estados.setPaSegurarPorta(true);
                }
            }
        );
        camadaInterativa.getChildren().add(botaoPA);
        
        BotaoPersonalizado botaoCCO = new BotaoPersonalizado(
            App.primaryStage.getScene().widthProperty().multiply(80.07 / 1920),
            App.primaryStage.getScene().heightProperty().multiply(80.0 / 1080),
            App.primaryStage.getScene().widthProperty().multiply(300.0 / 1920),
            App.primaryStage.getScene().heightProperty().multiply(550.31 / 1080),
            () -> {
                if(estados.isBoleira() && estados.isPaSegurarPorta()){
                    VBox aviso = avisoCCO();
                    camadaInterativa.getChildren().add(aviso);
                    estados.setAvisoCCO(true);
                }
                
            }
        );
        camadaInterativa.getChildren().add(botaoCCO);

        
        Button voltar = Seta.buttonSeta("Voltar",
            App.primaryStage.getScene().widthProperty().multiply(180.07 / 1920),
            App.primaryStage.getScene().heightProperty().multiply(145.0 / 1080),
            "esq");
        

        voltar.layoutXProperty().bind(App.primaryStage.getScene().widthProperty().multiply(0.0 / 1920));
        voltar.layoutYProperty().bind(App.primaryStage.getScene().heightProperty().multiply(900.00 / 1080));
        voltar.setOnAction(e -> {
            App.root.getChildren().clear();
            TelaPainelComando.exibir(estados); 
        });
        camadaInterativa.getChildren().add(voltar);
        StackPane conteudo = new StackPane(fundo,camadaInterativa);
        App.root.getChildren().setAll(conteudo);
    }

    public static VBox avisoPorta() {
        VBox aviso = new VBox();
        aviso.setStyle("-fx-background-color: lightblue; -fx-border-color: darkblue; -fx-font-size: 15px; -fx-padding: 15; -fx-spacing: 10;");
        aviso.prefWidthProperty().bind(App.primaryStage.getScene().widthProperty().multiply(400.00/1920));
        aviso.prefHeightProperty().bind(App.primaryStage.getScene().heightProperty().multiply(120.00 / 1080));

        Label mensagem = new Label("Não segurem as portas do trem, isso causa atrasos em todo o sistema.");
        mensagem.setWrapText(true);

        HBox botoes = new HBox();
        botoes.setStyle("-fx-alignment: bottom-right;");
        Button fechar = new Button("Fechar");
        fechar.setOnAction(e -> {
            ((Pane) aviso.getParent()).getChildren().remove(aviso);
        });
        botoes.getChildren().add(fechar);

        aviso.getChildren().addAll(mensagem, botoes);

        
        aviso.layoutXProperty().bind(App.primaryStage.getScene().widthProperty().subtract(aviso.prefWidthProperty()).divide(2));
        aviso.layoutYProperty().bind(App.primaryStage.getScene().heightProperty().subtract(aviso.prefHeightProperty()).divide(2));

        return aviso;
    }

    public static VBox avisoCCO() {
        VBox aviso = new VBox();
        aviso.setStyle("-fx-background-color: lightblue; -fx-border-color: darkblue; -fx-font-size: 15px; -fx-padding: 15; -fx-spacing: 10;");
        aviso.prefWidthProperty().bind(App.primaryStage.getScene().widthProperty().multiply(400.00/1920));
        aviso.prefHeightProperty().bind(App.primaryStage.getScene().heightProperty().multiply(120.00 / 1080));

        Label mensagem = new Label("Foi avisado ao CCO o problema e você foi instruido a sair para verificar o problema.");
        mensagem.setWrapText(true);

        HBox botoes = new HBox();
        botoes.setStyle("-fx-alignment: bottom-right;");
        Button fechar = new Button("Fechar");
        fechar.setOnAction(e -> {
            ((Pane) aviso.getParent()).getChildren().remove(aviso);
        });
        botoes.getChildren().add(fechar);

        aviso.getChildren().addAll(mensagem, botoes);

        
        aviso.layoutXProperty().bind(App.primaryStage.getScene().widthProperty().subtract(aviso.prefWidthProperty()).divide(2));
        aviso.layoutYProperty().bind(App.primaryStage.getScene().heightProperty().subtract(aviso.prefHeightProperty()).divide(2));

        return aviso;
    }

    public static VBox avisoProblema() {
        VBox aviso = new VBox();
        aviso.setStyle("-fx-background-color: lightblue; -fx-border-color: darkblue; -fx-font-size: 15px; -fx-padding: 15; -fx-spacing: 10;");
        aviso.prefWidthProperty().bind(App.primaryStage.getScene().widthProperty().multiply(500.00/1920));
        aviso.prefHeightProperty().bind(App.primaryStage.getScene().heightProperty().multiply(120.00 / 1080));

        Label mensagem = new Label("Paramos devido a uma falha neste trem, estamos trabalhando para a normalização, agradecemos a compreensão.");
        mensagem.setWrapText(true);

        HBox botoes = new HBox();
        botoes.setStyle("-fx-alignment: bottom-right;");
        Button fechar = new Button("Fechar");
        fechar.setOnAction(e -> {
            ((Pane) aviso.getParent()).getChildren().remove(aviso);
        });
        botoes.getChildren().add(fechar);

        aviso.getChildren().addAll(mensagem, botoes);

        
        aviso.layoutXProperty().bind(App.primaryStage.getScene().widthProperty().subtract(aviso.prefWidthProperty()).divide(2));
        aviso.layoutYProperty().bind(App.primaryStage.getScene().heightProperty().subtract(aviso.prefHeightProperty()).divide(2));

        return aviso;
    }
   

}
