package com.pi.telas.jogo;

import com.pi.App;
import com.pi.classes.ControladorDeEstados;
import com.pi.telas.login.TelaInicial;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;


public class FimTreinamento {
    
    public static void exibir(String motivo, ControladorDeEstados estados) {
        VBox fundo = new VBox();
        fundo.setStyle("-fx-background-color: #0066CC;");
        Pane camadaInterativa = new Pane();
        VBox caixa = new VBox();
        caixa.setStyle("-fx-background-color: #5599FF; -fx-border-color: darkblue; -fx-font-size: 50px; -fx-padding: 30; -fx-spacing: 30;");
    
        caixa.prefWidthProperty().bind(App.primaryStage.getScene().widthProperty().multiply(600.00/1920));
        caixa.prefHeightProperty().bind(App.primaryStage.getScene().heightProperty().multiply(600.00 / 1080));

        Label mensagem = new Label("Falha no treinamento.");
        mensagem.setStyle("-fx-text-fill: red");
        mensagem.setMaxWidth(Double.MAX_VALUE);
        mensagem.setAlignment(Pos.CENTER);
        mensagem.setWrapText(true);

        Label mot = new Label("Motivo: " + motivo);
        mot.setWrapText(true);
        
        Button voltar = criarBotao("Voltar para a tela de inicio");
        voltar.setMaxWidth(Double.MAX_VALUE);
        voltar.setAlignment(Pos.BOTTOM_CENTER);
        voltar.setOnAction(e -> {
            App.root.getChildren().clear();
            TelaInicial.exibir(estados);
        });
        Region espaco = new Region();
        VBox.setVgrow(espaco, javafx.scene.layout.Priority.ALWAYS);

        caixa.getChildren().addAll(mensagem, mot,espaco,voltar);
        
        caixa.layoutXProperty().bind(App.primaryStage.getScene().widthProperty().subtract(caixa.prefWidthProperty()).divide(2));
        caixa.layoutYProperty().bind(App.primaryStage.getScene().heightProperty().subtract(caixa.prefHeightProperty()).divide(2));
        camadaInterativa.getChildren().addAll(caixa);
        
        StackPane conteudo = new StackPane(fundo,camadaInterativa);
        App.root.getChildren().setAll(conteudo);
    }

    private static Button criarBotao(String texto) {
        Button botao = new Button(texto);
        botao.setPrefWidth(220);
        botao.setFont(Font.font("Helvetica", FontWeight.BOLD, 16));
        botao.setStyle("-fx-background-color: white; " +
                       "-fx-text-fill: #0066cc; " +
                       "-fx-background-radius: 10px; " +
                       "-fx-cursor: hand; " +
                       "-fx-effect: dropshadow(two-pass-box, rgba(0,0,0,0.3), 4, 0.3, 0, 2);");

        botao.setOnMouseEntered(e -> botao.setStyle("-fx-background-color: #e6f0ff; " +
                                                    "-fx-text-fill: #003366; " +
                                                    "-fx-background-radius: 10px; " +
                                                    "-fx-cursor: hand; " +
                                                    "-fx-effect: dropshadow(two-pass-box, rgba(0,0,0,0.4), 6, 0.4, 0, 2);"));

        botao.setOnMouseExited(e -> botao.setStyle("-fx-background-color: white; " +
                                                   "-fx-text-fill: #0066cc; " +
                                                   "-fx-background-radius: 10px; " +
                                                   "-fx-cursor: hand; " +
                                                   "-fx-effect: dropshadow(two-pass-box, rgba(0,0,0,0.3), 4, 0.3, 0, 2);"));
        return botao;
    }

    public static void exibir(int nota, ControladorDeEstados estados, long tempo) {
        if(nota <= 0){
            FimTreinamento.exibir("Foram realizados erros de mais!",estados);
            return;
            
        }
        VBox fundo = new VBox();
        fundo.setStyle("-fx-background-color: #0066CC;");
        Pane camadaInterativa = new Pane();
        VBox caixa = new VBox();
        caixa.setStyle("-fx-background-color: #5599FF; -fx-border-color: darkblue; -fx-font-size: 50px; -fx-padding: 30; -fx-spacing: 30;");
    
        caixa.prefWidthProperty().bind(App.primaryStage.getScene().widthProperty().multiply(600.00/1920));
        caixa.prefHeightProperty().bind(App.primaryStage.getScene().heightProperty().multiply(600.00 / 1080));

        Label mensagem = new Label("Parabens você concluiu o Treinamento");
        mensagem.setStyle("-fx-text-fill: rgb(55, 240, 49)");
        mensagem.setMaxWidth(Double.MAX_VALUE);
        mensagem.setAlignment(Pos.CENTER);
        mensagem.setWrapText(true);

        Label mot = new Label("Acerto: " + nota + "/14");
        mot.setWrapText(true);
        mot.setStyle("-fx-font-size: 50px");
        
        Label tempotxt = new Label("Demorou: " + tempo);
        tempotxt.setWrapText(true);
        tempotxt.setStyle("-fx-font-size: 50px");
        
        Button voltar = criarBotao("Voltar para a tela de inicio");
        voltar.setMaxWidth(Double.MAX_VALUE);
        voltar.setAlignment(Pos.BOTTOM_CENTER);
        voltar.setOnAction(e -> {
            App.root.getChildren().clear();
            TelaInicial.exibir(estados);
        });
        Region espaco = new Region();
        VBox.setVgrow(espaco, javafx.scene.layout.Priority.ALWAYS);

        caixa.getChildren().addAll(mensagem, mot,tempotxt,espaco,voltar);
        
        caixa.layoutXProperty().bind(App.primaryStage.getScene().widthProperty().subtract(caixa.prefWidthProperty()).divide(2));
        caixa.layoutYProperty().bind(App.primaryStage.getScene().heightProperty().subtract(caixa.prefHeightProperty()).divide(2));
        camadaInterativa.getChildren().addAll(caixa);
        
        StackPane conteudo = new StackPane(fundo,camadaInterativa);
        App.root.getChildren().setAll(conteudo);
    }

}
