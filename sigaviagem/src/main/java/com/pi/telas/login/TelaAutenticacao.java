package com.pi.telas.login;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.pi.App;
import com.pi.classes.ControladorDeEstados;

import javafx.animation.PauseTransition;
import javafx.concurrent.Task;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.TextField;
import javafx.scene.effect.DropShadow;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.util.Duration;

public class TelaAutenticacao {

    public static void exibir(ControladorDeEstados estados) {
      
        Label titulo = new Label("SIGA VIAGEM");
        titulo.setFont(Font.font("Helvetica", FontWeight.BOLD, 28));
        titulo.setTextFill(Color.WHITE);
        titulo.setEffect(new DropShadow(2, Color.BLACK));

     
        TextField campoLogin = new TextField();
        campoLogin.setPromptText("Login");
        estilizarCampo(campoLogin);

    
        PasswordField campoSenha = new PasswordField();
        campoSenha.setPromptText("Senha");
        estilizarCampo(campoSenha);

       
        Button botaoOk = criarBotao("OK", "#ffffff", "#0066cc");

       
        ProgressIndicator spinner = new ProgressIndicator();
        spinner.setVisible(false);
        spinner.setPrefSize(50, 50);

        
        Label erroIcone = new Label("\u2716"); 
        erroIcone.setTextFill(Color.web("#FF4C4C"));
        erroIcone.setFont(Font.font("Arial Black", FontWeight.BOLD, 40));
        erroIcone.setEffect(new DropShadow(5, Color.rgb(255, 0, 0, 0.7)));
        erroIcone.setVisible(false);
        erroIcone.setPrefSize(50, 50);
        erroIcone.setAlignment(Pos.CENTER);

        
        StackPane stackCarregamento = new StackPane(botaoOk, spinner, erroIcone);
        stackCarregamento.setMaxSize(150, 60);
        StackPane.setAlignment(botaoOk, Pos.CENTER);
        StackPane.setAlignment(spinner, Pos.CENTER);
        StackPane.setAlignment(erroIcone, Pos.CENTER);

        
        VBox containerBotao = new VBox(10, stackCarregamento);
        containerBotao.setAlignment(Pos.CENTER);

        
        botaoOk.addEventHandler(MouseEvent.MOUSE_ENTERED, e -> {
            botaoOk.setStyle("-fx-background-color: #005bb5; -fx-text-fill: white; -fx-background-radius: 10;");
        });
        botaoOk.addEventHandler(MouseEvent.MOUSE_EXITED, e -> {
            botaoOk.setStyle("-fx-background-color: #0066cc; -fx-text-fill: white; -fx-background-radius: 10;");
        });

        
        botaoOk.setOnAction(e -> {
            String login = campoLogin.getText().trim();
            String senha = campoSenha.getText().trim();

            
            erroIcone.setVisible(false);
            botaoOk.setVisible(false);
            spinner.setVisible(true);

            if (login.isEmpty() || senha.isEmpty()) {
                spinner.setVisible(false);
                erroIcone.setVisible(true);

                
                PauseTransition pause = new PauseTransition(Duration.seconds(0.5));
                pause.setOnFinished(ev -> {
                    erroIcone.setVisible(false);
                    botaoOk.setVisible(true);
                });
                pause.play();

                return;
            }

            Task<Boolean> tarefaAutenticacao = new Task<>() {
                @Override
                protected Boolean call() {
                    return autenticarUsuario(login, senha);
                }
            };

            tarefaAutenticacao.setOnSucceeded(ev -> {
                boolean sucesso = tarefaAutenticacao.getValue();

                if (sucesso) {
                    erroIcone.setVisible(false);
                    spinner.setVisible(false);
                    botaoOk.setVisible(false);
                    TelaInicial.exibir(estados);
                } else {
                    spinner.setVisible(false);
                    erroIcone.setVisible(true);

                    PauseTransition pause = new PauseTransition(Duration.seconds(0.5));
                    pause.setOnFinished(ev2 -> {
                        erroIcone.setVisible(false);
                        botaoOk.setVisible(true);
                    });
                    pause.play();
                }
            });

            tarefaAutenticacao.setOnFailed(ev -> {
                spinner.setVisible(false);
                erroIcone.setVisible(true);

                PauseTransition pause = new PauseTransition(Duration.seconds(0.5));
                pause.setOnFinished(ev2 -> {
                    erroIcone.setVisible(false);
                    botaoOk.setVisible(true);
                });
                pause.play();
            });

            new Thread(tarefaAutenticacao).start();
        });

       
        Label textoAdmin = new Label("É administrador? Faça login aqui");
        textoAdmin.setFont(Font.font("Helvetica", FontWeight.NORMAL, 13));
        textoAdmin.setTextFill(Color.WHITE);
        textoAdmin.setUnderline(true);

        textoAdmin.setOnMouseEntered(e -> {
            textoAdmin.setTextFill(Color.LIGHTGRAY);
            textoAdmin.setScaleX(1.03);
            textoAdmin.setScaleY(1.03);
        });
        textoAdmin.setOnMouseExited(e -> {
            textoAdmin.setTextFill(Color.WHITE);
            textoAdmin.setScaleX(1.0);
            textoAdmin.setScaleY(1.0);
        });

        textoAdmin.setOnMouseClicked(e -> {
            TelaAdmin.exibir(estados);
        });

        // Layout principal
        VBox layout = new VBox(15);
        layout.setPadding(new Insets(40));
        layout.setAlignment(Pos.CENTER);
        layout.setStyle("-fx-background-color: #0066cc;");
        layout.getChildren().addAll(titulo, campoLogin, campoSenha, containerBotao, textoAdmin);

        Scene cena = new Scene(layout, 600, 400);
        App.root.getChildren().setAll(layout);
    }

    private static boolean autenticarUsuario(String login, String senha) {
        String sql = "SELECT * FROM usuario WHERE login = ? AND password = ?";

        try (Connection conn = ConexaoBD.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, login);
            stmt.setString(2, senha);

            try (ResultSet rs = stmt.executeQuery()) {
                return rs.next();
            }

        } catch (Exception e) {
            e.printStackTrace(); 
            return false;
        }
    }

    private static void estilizarCampo(TextField campo) {
        campo.setStyle("-fx-background-radius: 10; -fx-padding: 8; -fx-font-size: 14;");
    }

    private static Button criarBotao(String texto, String corTexto, String corFundo) {
        Button botao = new Button(texto);
        botao.setPrefWidth(150);
        botao.setFont(Font.font("Helvetica", FontWeight.BOLD, 14));
        botao.setStyle(
            "-fx-background-color: " + corFundo + ";" +
            "-fx-text-fill: " + corTexto + ";" +
            "-fx-background-radius: 10;"
        );
        return botao;
    }
}
