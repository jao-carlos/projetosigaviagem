package com.pi;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class PrimeiraTela {
    public static void exibir(Stage stage) {
        TextField campoCodigo = new TextField();
        campoCodigo.setPromptText("Login");

        TextField campoNome = new TextField();
        campoNome.setPromptText("Senha");

        Button botaoOk = new Button("OK");
        botaoOk.setPrefWidth(100);

        botaoOk.setOnAction(e -> {
            String login = campoCodigo.getText().trim();
            String senha = campoNome.getText().trim();

            if (login.equals("admin") && senha.equals("admin")) {
                SegundaTela.exibir(stage);
            } else {
                Alert alerta = new Alert(Alert.AlertType.ERROR);
                alerta.setTitle("Erro de Login");
                alerta.setHeaderText("Credenciais inválidas");
                alerta.showAndWait();
            }
        });

        VBox layout = new VBox(15);
        layout.setPadding(new Insets(30));
        layout.setAlignment(Pos.CENTER);
        layout.getChildren().addAll(campoCodigo, campoNome, botaoOk);

        Scene cena = new Scene(layout, 600, 400);
        stage.setScene(cena);
        stage.setTitle("Siga Viagem");
        stage.show();
    }
}
