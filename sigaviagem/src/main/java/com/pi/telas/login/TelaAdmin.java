package com.pi.telas.login;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.pi.App;
import com.pi.classes.ControladorDeEstados;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class TelaAdmin {

    public static void exibir(ControladorDeEstados estados) {
        // Título
        Label titulo = new Label("ADMINISTRADOR");
        titulo.setFont(Font.font("Helvetica", FontWeight.BOLD, 28));
        titulo.setTextFill(Color.WHITE);
        titulo.setEffect(new DropShadow(2, Color.BLACK));

        // Campos
        TextField campoLogin = new TextField();
        campoLogin.setPromptText("Login");
        estilizarCampo(campoLogin);

        PasswordField campoSenha = new PasswordField();
        campoSenha.setPromptText("Senha");
        estilizarCampo(campoSenha);

        // Botão Login
        Button botaoLogin = criarBotao("Entrar", "#ffffff", "#0066cc");
        botaoLogin.setOnAction(e -> {
            String login = campoLogin.getText().trim();
            String senha = campoSenha.getText().trim();

            if (login.isEmpty() || senha.isEmpty()) {
                mostrarAlerta(Alert.AlertType.WARNING, "Erro", "Todos os campos devem ser preenchidos.");
                return;
            }

            try {
                if (validarAdmin(login, senha)) {
                    TelaInicial.exibir(estados);
                } else {
                    mostrarAlerta(Alert.AlertType.INFORMATION, "Acesso Negado", "Login ou senha incorretos.");
                }
            } catch (Exception ex) {
                ex.printStackTrace(); // Melhor trocar por logger futuramente
                mostrarAlerta(Alert.AlertType.ERROR, "Erro Técnico", "Erro ao conectar ao banco de dados. Tente novamente.");
            }
        });

        // Botão Voltar
        Button botaoVoltar = criarBotao("Voltar", "#ffffff", "#cc0000");
        botaoVoltar.setOnAction(e -> TelaCadastrar.exibir(estados));

        // Layout
        VBox layout = new VBox(15);
        layout.setPadding(new Insets(40));
        layout.setAlignment(Pos.CENTER);
        layout.setStyle("-fx-background-color: #0066cc;");

        layout.getChildren().addAll(titulo, campoLogin, campoSenha, botaoLogin, botaoVoltar);

        Scene cena = new Scene(layout, 600, 400);
        App.root.getChildren().setAll(layout);
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
