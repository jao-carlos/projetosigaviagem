package com.pi.telas.login;

import com.pi.App;
import com.pi.classes.ControladorDeEstados;

import javafx.animation.PauseTransition;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.TextField;
import javafx.scene.effect.DropShadow;
<<<<<<< Updated upstream
=======
import javafx.scene.input.MouseEvent;
<<<<<<< Updated upstream
>>>>>>> Stashed changes
=======
>>>>>>> Stashed changes
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.util.Duration;

public class TelaAdmin   {

    public static void exibir(ControladorDeEstados estados) {
<<<<<<< Updated upstream
<<<<<<< Updated upstream
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

=======
        Label titulo = new Label("ADMINISTRADOR");
        titulo.setFont(Font.font("Helvetica", FontWeight.BOLD, 28));
        titulo.setTextFill(Color.WHITE);
        titulo.setEffect(new DropShadow(2, Color.BLACK));

        TextField campoExemplo = new TextField();
        campoExemplo.setPromptText("Digite algo aqui");
        estilizarCampo(campoExemplo);

        Button botaoOk = criarBotao("CONFIRMAR", "#ffffff", "#cc0000");

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
            botaoOk.setStyle("-fx-background-color: #0055b5; -fx-text-fill: white; -fx-background-radius: 10;");
        });
        botaoOk.addEventHandler(MouseEvent.MOUSE_EXITED, e -> {
            botaoOk.setStyle("-fx-background-color: #0055b5; -fx-text-fill: white; -fx-background-radius: 10;");
        });

        botaoOk.setOnAction(e -> {
            String texto = campoExemplo.getText().trim();

            erroIcone.setVisible(false);
            botaoOk.setVisible(false);
            spinner.setVisible(true);

            if (texto.isEmpty()) {
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

            // Simula uma tarefa assincrona qualquer (exemplo)
            PauseTransition tarefaSimulada = new PauseTransition(Duration.seconds(1));
            tarefaSimulada.setOnFinished(ev -> {
                spinner.setVisible(false);
                // Aqui você pode decidir o que fazer depois, por enquanto só volta o botão
                botaoOk.setVisible(true);
            });
            tarefaSimulada.play();
        });

        Label textoVoltar = new Label("Voltar para tela anterior");
        textoVoltar.setFont(Font.font("Helvetica", FontWeight.NORMAL, 13));
        textoVoltar.setTextFill(Color.WHITE);
        textoVoltar.setUnderline(true);

        textoVoltar.setOnMouseEntered(ev -> {
            textoVoltar.setTextFill(Color.LIGHTGRAY);
            textoVoltar.setScaleX(1.03);
            textoVoltar.setScaleY(1.03);
        });
        textoVoltar.setOnMouseExited(ev -> {
            textoVoltar.setTextFill(Color.WHITE);
            textoVoltar.setScaleX(1.0);
            textoVoltar.setScaleY(1.0);
        });

        textoVoltar.setOnMouseClicked(ev -> {
            // Ajuste aqui para onde quer voltar, por exemplo:
            TelaAdmin.exibir(estados);
        });

        VBox layout = new VBox(15);
        layout.setPadding(new Insets(40));
        layout.setAlignment(Pos.CENTER);
        layout.setStyle("-fx-background-color: #0066cc;");
        layout.getChildren().addAll(titulo, campoExemplo, containerBotao, textoVoltar);

        App.root.getChildren().setAll(layout);
    }

    private static void estilizarCampo(TextField campo) {
        campo.setStyle("-fx-background-radius: 10; -fx-padding: 8; -fx-font-size: 14;");
    }

    private static Button criarBotao(String texto, String corTexto, String corFundo) {
        Button botao = new Button(texto);
=======
        Label titulo = new Label("ADMINISTRADOR");
        titulo.setFont(Font.font("Helvetica", FontWeight.BOLD, 28));
        titulo.setTextFill(Color.WHITE);
        titulo.setEffect(new DropShadow(2, Color.BLACK));

        TextField campoExemplo = new TextField();
        campoExemplo.setPromptText("Digite algo aqui");
        estilizarCampo(campoExemplo);

        Button botaoOk = criarBotao("CONFIRMAR", "#ffffff", "#cc0000");

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
            botaoOk.setStyle("-fx-background-color: #0055b5; -fx-text-fill: white; -fx-background-radius: 10;");
        });
        botaoOk.addEventHandler(MouseEvent.MOUSE_EXITED, e -> {
            botaoOk.setStyle("-fx-background-color: #0055b5; -fx-text-fill: white; -fx-background-radius: 10;");
        });

        botaoOk.setOnAction(e -> {
            String texto = campoExemplo.getText().trim();

            erroIcone.setVisible(false);
            botaoOk.setVisible(false);
            spinner.setVisible(true);

            if (texto.isEmpty()) {
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

            // Simula uma tarefa assincrona qualquer (exemplo)
            PauseTransition tarefaSimulada = new PauseTransition(Duration.seconds(1));
            tarefaSimulada.setOnFinished(ev -> {
                spinner.setVisible(false);
                // Aqui você pode decidir o que fazer depois, por enquanto só volta o botão
                botaoOk.setVisible(true);
            });
            tarefaSimulada.play();
        });

        Label textoVoltar = new Label("Voltar para tela anterior");
        textoVoltar.setFont(Font.font("Helvetica", FontWeight.NORMAL, 13));
        textoVoltar.setTextFill(Color.WHITE);
        textoVoltar.setUnderline(true);

        textoVoltar.setOnMouseEntered(ev -> {
            textoVoltar.setTextFill(Color.LIGHTGRAY);
            textoVoltar.setScaleX(1.03);
            textoVoltar.setScaleY(1.03);
        });
        textoVoltar.setOnMouseExited(ev -> {
            textoVoltar.setTextFill(Color.WHITE);
            textoVoltar.setScaleX(1.0);
            textoVoltar.setScaleY(1.0);
        });

        textoVoltar.setOnMouseClicked(ev -> {
            // Ajuste aqui para onde quer voltar, por exemplo:
            TelaAdmin.exibir(estados);
        });

        VBox layout = new VBox(15);
        layout.setPadding(new Insets(40));
        layout.setAlignment(Pos.CENTER);
        layout.setStyle("-fx-background-color: #0066cc;");
        layout.getChildren().addAll(titulo, campoExemplo, containerBotao, textoVoltar);

        App.root.getChildren().setAll(layout);
    }

    private static void estilizarCampo(TextField campo) {
        campo.setStyle("-fx-background-radius: 10; -fx-padding: 8; -fx-font-size: 14;");
    }

    private static Button criarBotao(String texto, String corTexto, String corFundo) {
        Button botao = new Button(texto);
>>>>>>> Stashed changes
        botao.setPrefWidth(150);
        botao.setFont(Font.font("Helvetica", FontWeight.BOLD, 14));
        botao.setStyle(
            "-fx-background-color: " + corFundo + ";" +
            "-fx-text-fill: " + corTexto + ";" +
            "-fx-background-radius: 10;"
        );
<<<<<<< Updated upstream
>>>>>>> Stashed changes
=======
>>>>>>> Stashed changes
        return botao;
    }
}
