package com.pi.telas.login;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

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
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class TelaAutenticacao {

    public static void exibir(ControladorDeEstados estados) {
        // Título
        Label titulo = new Label("SIGA VIAGEM");
        titulo.setFont(Font.font("Helvetica", FontWeight.BOLD, 28));
        titulo.setTextFill(Color.WHITE);
        titulo.setEffect(new DropShadow(2, Color.BLACK));

        // Campo Login
        TextField campoLogin = new TextField();
        campoLogin.setPromptText("Login");
        estilizarCampo(campoLogin);

        // Campo Senha
        PasswordField campoSenha = new PasswordField();
        campoSenha.setPromptText("Senha");
        estilizarCampo(campoSenha);

        // Botão OK
        Button botaoOk = criarBotao("OK", "#ffffff", "#0066cc");

        // Efeito de hover no botão OK
        botaoOk.addEventHandler(MouseEvent.MOUSE_ENTERED, e -> {
            botaoOk.setStyle("-fx-background-color: #005bb5; -fx-text-fill: white; -fx-background-radius: 10;");
        });
        botaoOk.addEventHandler(MouseEvent.MOUSE_EXITED, e -> {
            botaoOk.setStyle("-fx-background-color: #0066cc; -fx-text-fill: white; -fx-background-radius: 10;");
        });

        botaoOk.setOnAction(e -> {
            String login = campoLogin.getText().trim();
            String senha = campoSenha.getText().trim();

            if (login.isEmpty() || senha.isEmpty()) {
                mostrarAlerta("Erro de Login", "Por favor, preencha todos os campos.");
                return;
            }

            if (autenticarUsuario(login, senha)) {
                TelaInicial.exibir(estados);
            } else {
                mostrarAlerta("Erro de Login", "Credenciais inválidas");
            }
        });

        // Texto interativo para admin
        Label textoAdmin = new Label("É administrador? Faça login aqui");
        textoAdmin.setFont(Font.font("Helvetica", FontWeight.NORMAL, 13));
        textoAdmin.setTextFill(Color.WHITE);
        textoAdmin.setUnderline(true);
        
        // Hover sutil
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

        // Layout
        VBox layout = new VBox(15);
        layout.setPadding(new Insets(40));
        layout.setAlignment(Pos.CENTER);
        layout.setStyle("-fx-background-color: #0066cc;");

        layout.getChildren().addAll(titulo, campoLogin, campoSenha, botaoOk, textoAdmin);

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

    private static void mostrarAlerta(String titulo, String mensagem) {
        Alert alerta = new Alert(Alert.AlertType.ERROR);
        alerta.setTitle(titulo);
        alerta.setHeaderText(mensagem);
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
