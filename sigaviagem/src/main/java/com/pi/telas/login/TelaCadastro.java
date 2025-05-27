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
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class TelaCadastro {

    public static void exibir(ControladorDeEstados estados) {
        TextField campoLogin = new TextField();
        campoLogin.setPromptText("Login");

        PasswordField campoSenha = new PasswordField();
        campoSenha.setPromptText("Senha");

        Button botaoCadastrar = new Button("Cadastrar");
        botaoCadastrar.setPrefWidth(100);

        Hyperlink linkAdmin = new Hyperlink("Entrar como administrador");
        linkAdmin.setOnAction(e -> TelaAdmin.exibir(estados));

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

        VBox layout = new VBox(15);
        layout.setPadding(new Insets(30));
        layout.setAlignment(Pos.CENTER);
        layout.getChildren().addAll(campoLogin, campoSenha, botaoCadastrar, linkAdmin);

        Scene cena = new Scene(layout, 600, 400);
        App.root.getChildren().setAll(layout);
    }

    private static boolean cadastrarUsuario(String login, String senha) {
        String sql = "INSERT INTO usuarios (login, senha) VALUES (?, ?)";

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
}
