package com.pi;

import javafx.application.Application;
import javafx.stage.Stage;

public class App extends Application {
    @Override
    public void start(Stage stage) {
        TelaAutenticacao.exibir(stage);
    }

    public static void main(String[] args) {
        launch(args);
    }
}

