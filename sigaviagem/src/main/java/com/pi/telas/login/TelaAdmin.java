package com.pi.telas.login;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.pi.App;
import com.pi.classes.ControladorDeEstados;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

public class TelaAdmin {

    public static void exibir(ControladorDeEstados estados) {
        TextField campoLogin = new TextField();
        campoLogin.setPromptText("Login");

        PasswordField campoSenha = new PasswordField();
        campoSenha.setPromptText("Senha");

        Button botaoLogin = new Button("Entrar");
        botaoLogin.setPrefWidth(100);

        Button botaoVoltar = new Button("Voltar");
        botaoVoltar.setPrefWidth(100);

        botaoLogin.setOnAction(e -> {
            String login = campoLogin.getText().trim();
            String senha = campoSenha.getText().trim();

            if (login.isEmpty() || senha.isEmpty()) {
                mostrarAlerta("Erro", "Todos os campos devem ser preenchidos.");
                return;
            }

            try {
                if (validarAdmin(login, senha)) {
                    TelaInicial.exibir(estados);
                } else {
                    mostrarAlerta("Acesso Negado", "Login ou senha de administrador incorretos.");
                }
            } catch (Exception ex) {
                ex.printStackTrace(); // ideal: usar logger
                mostrarAlerta("Erro Técnico", "Erro ao conectar ao banco de dados. Tente novamente.");
            }
        });

        botaoVoltar.setOnAction(e -> TelaCadastro.exibir(estados));

        VBox layout = new VBox(15);
        layout.setPadding(new Insets(30));
        layout.setAlignment(Pos.CENTER);
        layout.getChildren().addAll(campoLogin, campoSenha, botaoLogin, botaoVoltar);

        App.root.getChildren().setAll(layout);
    }

    private static boolean validarAdmin(String login, String senha) throws Exception {
        String sql = "SELECT * FROM administrador WHERE login = ? AND senha = ?";

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

    private static void mostrarAlerta(String titulo, String mensagem) {
        Alert alerta = new Alert(Alert.AlertType.ERROR);
        alerta.setTitle(titulo);
        alerta.setHeaderText(null);
        alerta.setContentText(mensagem);
        alerta.showAndWait();
    }
}
