package com.pi;

import com.pi.classes.ControladorDeEstados;
import com.pi.telas.jogo.TelaChaveReversora;
import com.pi.telas.jogo.TelaModuloComunicacao;
import com.pi.telas.jogo.TelaPainelExterno;
import com.pi.telas.login.TelaAutenticacao;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class App extends Application { 

    public static StackPane root; 
    public static Stage primaryStage;

    @Override
    public void start(Stage stage) {
        primaryStage = stage;

        root = new StackPane(); 
        Scene cena = new Scene(root, 1280, 720);

        stage.setScene(cena);
        stage.setFullScreen(true);
        stage.show();

        ControladorDeEstados estados = new ControladorDeEstados();

        TelaChaveReversora.exibir(estados); 
    }
    

    public static void main(String[] args) {
        launch(args);
    }
}

