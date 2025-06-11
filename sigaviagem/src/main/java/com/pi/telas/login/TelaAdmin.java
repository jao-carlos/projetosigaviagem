package com.pi.telas.login;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.pi.App;
import com.pi.classes.ControladorDeEstados;

import javafx.concurrent.Task;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.TextField;
import javafx.scene.effect.DropShadow;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class TelaAdmin {

    public static void exibir(ControladorDeEstados estados) {
       
        Label titulo = new Label("ADMINISTRADOR");
        titulo.setFont(Font.font("Helvetica", FontWeight.BOLD, 28));
        titulo.setTextFill(Color.WHITE);
        titulo.setEffect(new DropShadow(3, Color.BLACK));

        TextField campoLogin = criarCampoPersonalizado("Login");
        PasswordField campoSenha = criarSenhaPersonalizada("Senha");

     
        Button botaoLogin = criarBotao("Entrar", "#ffffff", "#3399ff", "#2673cc"); // azul mais claro no botão
        Button botaoVoltar = criarBotao("Voltar", "#ffffff", "#cc3333", "#992222");

     
        StackPane overlayLoading = criarOverlayLoading();

        
        VBox formLayout = new VBox(15, titulo, campoLogin, campoSenha, botaoLogin, botaoVoltar);
        formLayout.setAlignment(Pos.CENTER);
        formLayout.setPadding(new Insets(40));
        formLayout.setMaxWidth(320);
        formLayout.setStyle(
            "-fx-background-color: #a3c9f1;" +  
            "-fx-background-radius: 16;"
        );

        
        StackPane raiz = new StackPane(formLayout, overlayLoading);
        raiz.setStyle(
            "-fx-background-color: linear-gradient(to bottom right, #cce7ff, #99ccff);" 
        );
        StackPane.setAlignment(overlayLoading, Pos.CENTER);

        // Evento login
        botaoLogin.setOnAction(e -> {
            String login = campoLogin.getText().trim();
            String senha = campoSenha.getText().trim();

            if (login.isEmpty() || senha.isEmpty()) {
                mostrarAlerta(Alert.AlertType.WARNING, "Erro", "Todos os campos devem ser preenchidos.");
                return;
            }

            // Desativa UI, mostra loading
            setUIEnabled(formLayout, false);
            overlayLoading.setVisible(true);

            Task<Boolean> taskLogin = new Task<>() {
                @Override
                protected Boolean call() throws Exception {
                    return validarAdmin(login, senha);
                }
            };

            taskLogin.setOnSucceeded(ev -> {
                boolean valido = taskLogin.getValue();
                overlayLoading.setVisible(false);
                setUIEnabled(formLayout, true);

                if (valido) {
                    TelaInicial.exibir(estados);
                } else {
                    mostrarAlerta(Alert.AlertType.ERROR, "Acesso Negado", "Login ou senha incorretos.");
                }
            });

            taskLogin.setOnFailed(ev -> {
                overlayLoading.setVisible(false);
                setUIEnabled(formLayout, true);
                mostrarAlerta(Alert.AlertType.ERROR, "Erro Técnico", "Erro ao conectar ao banco de dados. Tente novamente.");
            });

            new Thread(taskLogin).start();
        });

        
        botaoVoltar.setOnAction(e -> TelaAutenticacao.exibir(estados));

       
        Scene cena = new Scene(raiz, 600, 400);
        App.root.getChildren().setAll(raiz);
    }

    private static void setUIEnabled(Pane container, boolean habilitar) {
        container.setDisable(!habilitar);
        container.setOpacity(habilitar ? 1.0 : 0.6);
    }

    private static StackPane criarOverlayLoading() {
        ProgressIndicator loading = new ProgressIndicator();
        loading.setPrefSize(100, 100);

        StackPane overlay = new StackPane();
        overlay.setStyle("-fx-background-color: rgba(0,0,0,0.5);");
        overlay.getChildren().add(loading);
        overlay.setVisible(false);
        return overlay;
    }

    private static TextField criarCampoPersonalizado(String placeholder) {
        TextField campo = new TextField();
        campo.setPromptText(placeholder);
        campo.setFont(Font.font("Helvetica", 14));
        campo.setStyle(
            "-fx-background-radius: 12;" +
            "-fx-background-color: white;" +
            "-fx-padding: 10 14;" +
            "-fx-border-radius: 12;" +
            "-fx-border-color: transparent;" +
            "-fx-effect: dropshadow(gaussian, rgba(0,0,0,0.12), 4, 0.7, 0, 2);"
        );
        campo.focusedProperty().addListener((obs, oldV, newV) -> {
            if (newV) {
                campo.setStyle(
                    "-fx-background-radius: 12;" +
                    "-fx-background-color: white;" +
                    "-fx-padding: 10 14;" +
                    "-fx-border-radius: 12;" +
                    "-fx-border-color: #3399ff;" +
                    "-fx-border-width: 2;" +
                    "-fx-effect: dropshadow(gaussian, rgba(0,153,255,0.5), 8, 0.7, 0, 2);"
                );
            } else {
                campo.setStyle(
                    "-fx-background-radius: 12;" +
                    "-fx-background-color: white;" +
                    "-fx-padding: 10 14;" +
                    "-fx-border-radius: 12;" +
                    "-fx-border-color: transparent;" +
                    "-fx-effect: dropshadow(gaussian, rgba(0,0,0,0.12), 4, 0.7, 0, 2);"
                );
            }
        });
        return campo;
    }

    private static PasswordField criarSenhaPersonalizada(String placeholder) {
        PasswordField campo = new PasswordField();
        campo.setPromptText(placeholder);
        campo.setFont(Font.font("Helvetica", 14));
        campo.setStyle(
            "-fx-background-radius: 12;" +
            "-fx-background-color: white;" +
            "-fx-padding: 10 14;" +
            "-fx-border-radius: 12;" +
            "-fx-border-color: transparent;" +
            "-fx-effect: dropshadow(gaussian, rgba(0,0,0,0.12), 4, 0.7, 0, 2);"
        );
        campo.focusedProperty().addListener((obs, oldV, newV) -> {
            if (newV) {
                campo.setStyle(
                    "-fx-background-radius: 12;" +
                    "-fx-background-color: white;" +
                    "-fx-padding: 10 14;" +
                    "-fx-border-radius: 12;" +
                    "-fx-border-color: #3399ff;" +
                    "-fx-border-width: 2;" +
                    "-fx-effect: dropshadow(gaussian, rgba(0,153,255,0.5), 8, 0.7, 0, 2);"
                );
            } else {
                campo.setStyle(
                    "-fx-background-radius: 12;" +
                    "-fx-background-color: white;" +
                    "-fx-padding: 10 14;" +
                    "-fx-border-radius: 12;" +
                    "-fx-border-color: transparent;" +
                    "-fx-effect: dropshadow(gaussian, rgba(0,0,0,0.12), 4, 0.7, 0, 2);"
                );
            }
        });
        return campo;
    }

    private static Button criarBotao(String texto, String corTexto, String corFundo, String corHover) {
        Button botao = new Button(texto);
        botao.setPrefWidth(180);
        botao.setFont(Font.font("Helvetica", FontWeight.BOLD, 14));
        botao.setStyle(
            "-fx-background-color: " + corFundo + ";" +
            "-fx-text-fill: " + corTexto + ";" +
            "-fx-background-radius: 12;" +
            "-fx-cursor: hand;"
        );

        botao.addEventHandler(MouseEvent.MOUSE_ENTERED, e ->
            botao.setStyle(
                "-fx-background-color: " + corHover + ";" +
                "-fx-text-fill: " + corTexto + ";" +
                "-fx-background-radius: 12;" +
                "-fx-cursor: hand;"
            )
        );

        botao.addEventHandler(MouseEvent.MOUSE_EXITED, e ->
            botao.setStyle(
                "-fx-background-color: " + corFundo + ";" +
                "-fx-text-fill: " + corTexto + ";" +
                "-fx-background-radius: 12;" +
                "-fx-cursor: hand;"
            )
        );

        return botao;
    }

    private static boolean validarAdmin(String login, String senha) throws Exception {
        String sql = "SELECT * FROM administrador WHERE login = ? AND password = ?";

        try (Connection conn = ConexaoBD.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, login);
            stmt.setString(2, senha);

            try (ResultSet rs = stmt.executeQuery()) {
                return rs.next();
            }

        } catch (SQLException e) {
            throw new Exception("Erro ao validar administrador.", e);
        }
    }

    private static void mostrarAlerta(Alert.AlertType tipo, String titulo, String mensagem) {
        Alert alerta = new Alert(tipo);
        alerta.setTitle(titulo);
        alerta.setHeaderText(null);
        alerta.setContentText(mensagem);
        alerta.showAndWait();
    }
}
