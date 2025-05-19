package com.pi.telas.login;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class TelaAutenticacao {
    public static void exibir(Stage stage) {
        TextField campoLogin = new TextField();
        campoLogin.setPromptText("Login");

        PasswordField campoSenha = new PasswordField();
        campoSenha.setPromptText("Senha");

        Button botaoOk = new Button("OK");
        botaoOk.setPrefWidth(100);

        botaoOk.setOnAction(e -> {
            String login = campoLogin.getText().trim();
            String senha = campoSenha.getText().trim();

            if (login.isEmpty() || senha.isEmpty()) {
                mostrarAlerta("Erro de Login", "Por favor, preencha todos os campos.");
                return;
            }

            if (autenticarUsuario(login, senha)) {
                TelaInicial.exibir(stage);
            } else {
                mostrarAlerta("Erro de Login", "Credenciais inválidas");
            }
        });

        VBox layout = new VBox(15);
        layout.setPadding(new Insets(30));
        layout.setAlignment(Pos.CENTER);
        layout.getChildren().addAll(campoLogin, campoSenha, botaoOk);

        Scene cena = new Scene(layout, 600, 400);
        stage.setScene(cena);
        stage.setTitle("Siga Viagem");
        stage.show();
    }

    private static boolean autenticarUsuario(String login, String senha) {
        String sql = "SELECT * FROM usuarios WHERE login = ? AND senha = ?";

        try (Connection conn = ConexaoBD.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, login);
            stmt.setString(2, senha);

            try (ResultSet rs = stmt.executeQuery()) {
                return rs.next(); // se encontrar o usuário, retorna true
            }

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    private static void mostrarAlerta(String titulo, String mensagem) {
        Alert alerta = new Alert(Alert.AlertType.ERROR);
        alerta.setTitle(titulo);
        alerta.setHeaderText(mensagem);
        alerta.showAndWait();
    }
}
