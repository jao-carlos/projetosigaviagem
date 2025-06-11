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
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class TelaAdmin {

    public static void exibir(ControladorDeEstados estados) {
        // Título
        Label titulo = new Label("Área do Administrador");
        titulo.setFont(Font.font("Segoe UI", FontWeight.BOLD, 32));
        titulo.setTextFill(Color.WHITE);
        titulo.setEffect(new DropShadow(4, Color.BLACK));

        // Campos de entrada
        TextField campoLogin = criarCampoTexto("Login");
        PasswordField campoSenha = criarCampoSenha("Senha");

        // Botões
        Button botaoEntrar = criarBotao("Entrar", "#ffffff", "#2a71d0", "#1f56a1");
        Button botaoVoltar = criarBotao("Voltar", "#ffffff", "#d02a2a", "#a11f1f");

        // Overlay loading
        StackPane overlayLoading = criarOverlayLoading();

        // Layout do formulário
        VBox layout = new VBox(20, titulo, campoLogin, campoSenha, botaoEntrar, botaoVoltar);
        layout.setAlignment(Pos.CENTER);
        layout.setPadding(new Insets(50));
        layout.setMaxWidth(360);
        layout.setStyle("-fx-background-color: #2a71d0; -fx-background-radius: 15;");

        // Root da cena
        StackPane raiz = new StackPane(layout, overlayLoading);
        raiz.setStyle("-fx-background-color: linear-gradient(to bottom right, #1a3b72, #102a4a);");
        StackPane.setAlignment(overlayLoading, Pos.CENTER);

        // Ações dos botões
        botaoEntrar.setOnAction(event -> {
            String login = campoLogin.getText().trim();
            String senha = campoSenha.getText().trim();

            if (login.isEmpty() || senha.isEmpty()) {
                mostrarAlerta(Alert.AlertType.WARNING, "Campos vazios", "Preencha todos os campos para continuar.");
                return;
            }

            setUIEnabled(layout, false);
            overlayLoading.setVisible(true);

            Task<Boolean> tarefaLogin = new Task<>() {
                @Override
                protected Boolean call() throws Exception {
                    return validarAdmin(login, senha);
                }
            };

            tarefaLogin.setOnSucceeded(e -> {
                overlayLoading.setVisible(false);
                setUIEnabled(layout, true);

                if (tarefaLogin.getValue()) {
                    TelaInicial.exibir(estados);
                } else {
                    mostrarAlerta(Alert.AlertType.ERROR, "Acesso negado", "Login ou senha incorretos.");
                }
            });

            tarefaLogin.setOnFailed(e -> {
                overlayLoading.setVisible(false);
                setUIEnabled(layout, true);
                mostrarAlerta(Alert.AlertType.ERROR, "Erro", "Não foi possível conectar ao banco. Tente novamente.");
            });

            new Thread(tarefaLogin).start();
        });

        botaoVoltar.setOnAction(event -> TelaAutenticacao.exibir(estados));

        // Mostrar tela
        Scene cena = new Scene(raiz, 700, 480);
        App.root.getChildren().setAll(raiz);
    }

    private static void setUIEnabled(VBox container, boolean habilitar) {
        container.setDisable(!habilitar);
        container.setOpacity(habilitar ? 1 : 0.5);
    }

    private static StackPane criarOverlayLoading() {
        ProgressIndicator indicador = new ProgressIndicator();
        indicador.setPrefSize(90, 90);

        StackPane overlay = new StackPane(indicador);
        overlay.setStyle("-fx-background-color: rgba(0,0,0,0.55); -fx-background-radius: 15;");
        overlay.setVisible(false);
        return overlay;
    }

    private static TextField criarCampoTexto(String placeholder) {
        TextField campo = new TextField();
        campo.setPromptText(placeholder);
        campo.setFont(Font.font("Segoe UI", 16));
        campo.setStyle(criarEstiloCampo(false));

        campo.focusedProperty().addListener((obs, antigo, novo) -> {
            campo.setStyle(novo ? criarEstiloCampo(true) : criarEstiloCampo(false));
        });

        return campo;
    }

    private static PasswordField criarCampoSenha(String placeholder) {
        PasswordField campo = new PasswordField();
        campo.setPromptText(placeholder);
        campo.setFont(Font.font("Segoe UI", 16));
        campo.setStyle(criarEstiloCampo(false));

        campo.focusedProperty().addListener((obs, antigo, novo) -> {
            campo.setStyle(novo ? criarEstiloCampo(true) : criarEstiloCampo(false));
        });

        return campo;
    }

    private static String criarEstiloCampo(boolean focado) {
        if (focado) {
            return "-fx-background-radius: 12; -fx-background-color: white; -fx-border-radius: 12; -fx-border-color: #5599ff; -fx-border-width: 2; -fx-padding: 10 14;";
        } else {
            return "-fx-background-radius: 12; -fx-background-color: white; -fx-border-radius: 12; -fx-border-color: transparent; -fx-padding: 10 14;";
        }
    }

    private static Button criarBotao(String texto, String corTexto, String corFundo, String corHover) {
        Button botao = new Button(texto);
        botao.setPrefWidth(200);
        botao.setFont(Font.font("Segoe UI", FontWeight.BOLD, 16));
        botao.setStyle("-fx-background-color: " + corFundo + "; -fx-text-fill: " + corTexto + "; -fx-background-radius: 12;");

        botao.setOnMouseEntered(e -> botao.setStyle("-fx-background-color: " + corHover + "; -fx-text-fill: " + corTexto + "; -fx-background-radius: 12;"));
        botao.setOnMouseExited(e -> botao.setStyle("-fx-background-color: " + corFundo + "; -fx-text-fill: " + corTexto + "; -fx-background-radius: 12;"));

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
