package com.pi.telas.login;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.pi.App;
import com.pi.classes.ControladorDeEstados;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class TelaCadastro {

    public static void exibir(ControladorDeEstados estados) {
        // Título
        Label titulo = new Label("CADASTRE-SE");
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

        // Botão Cadastrar
        Button botaoCadastrar = criarBotao("Cadastrar", "#ffffff", "#0066cc");

        botaoCadastrar.setOnAction(e -> {
            String login = campoLogin.getText().trim();
            String senha = campoSenha.getText().trim();

            if (login.isEmpty() || senha.isEmpty()) {
                mostrarAlerta("Erro de Cadastro", "Todos os campos devem ser preenchidos.");
                return;
            }

            if (cadastrarUsuario(login, senha)) {
                mostrarInfo("Cadastro realizado com sucesso!");
                TelaAutenticacao.exibir(estados);
            }
        });

        // Link Admin
        Hyperlink linkAdmin = new Hyperlink("Entrar como administrador");
        linkAdmin.setTextFill(Color.WHITE);
        linkAdmin.setOnAction(e -> TelaAdmin.exibir(estados));

        // Layout
        VBox layout = new VBox(15);
        layout.setPadding(new Insets(40));
        layout.setAlignment(Pos.CENTER);
        layout.setStyle("-fx-background-color: #0066cc;");

        layout.getChildren().addAll(titulo, campoLogin, campoSenha, botaoCadastrar, linkAdmin);

        Scene cena = new Scene(layout, 600, 400);
        App.root.getChildren().setAll(layout);
    }

    private static boolean cadastrarUsuario(String login, String senha) {
        String sql = "INSERT INTO usuario (login, password) VALUES (?, ?)";

        try (Connection conn = ConexaoBD.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, login);
            stmt.setString(2, senha);

            int linhasAfetadas = stmt.executeUpdate();
            return linhasAfetadas > 0;

        } catch (SQLException e) {
            String msg = e.getMessage().toLowerCase();

            if (msg.contains("login_metro_check")) {
                mostrarAlerta("Erro de Cadastro", "Login deve terminar com '@metrosp.com.br'.");
            } else if (msg.contains("usuarios_login_key") || msg.contains("unique constraint")) {
                mostrarAlerta("Erro de Cadastro", "Login já cadastrado.");
            } else {
                e.printStackTrace();
                mostrarAlerta("Erro", "Erro inesperado ao cadastrar.");
            }
            return false;
        } catch (Exception e) {
            e.printStackTrace();
            mostrarAlerta("Erro", "Erro inesperado ao cadastrar.");
            return false;
        }
    }

    private static void mostrarAlerta(String titulo, String mensagem) {
        Alert alerta = new Alert(Alert.AlertType.ERROR);
        alerta.setTitle(titulo);
        alerta.setHeaderText(mensagem);
        alerta.showAndWait();
    }

    private static void mostrarInfo(String mensagem) {
        Alert alerta = new Alert(Alert.AlertType.INFORMATION);
        alerta.setTitle("Sucesso");
        alerta.setHeaderText(mensagem);
        alerta.showAndWait();
    }

    // Estiliza campos
    private static void estilizarCampo(TextField campo) {
        campo.setStyle("-fx-background-radius: 10; -fx-padding: 8; -fx-font-size: 14;");
    }

    // Cria botão com estilo
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
