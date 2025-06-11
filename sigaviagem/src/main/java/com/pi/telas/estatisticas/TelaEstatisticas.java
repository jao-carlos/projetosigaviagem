package com.pi.telas.estatisticas;

import com.pi.App;
import com.pi.classes.ControladorDeEstados;
import com.pi.telas.login.TelaInicial;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class TelaEstatisticas {

    public static void exibir(ControladorDeEstados estados) {
     
        CategoryAxis xAxis = new CategoryAxis();
        xAxis.setTickLabelFill(Color.WHITE);
        xAxis.setOpacity(0);

        NumberAxis yAxis = new NumberAxis();
        yAxis.setTickLabelsVisible(false);
        yAxis.setOpacity(0);

       
        BarChart<String, Number> barChart = new BarChart<>(xAxis, yAxis);
        barChart.setLegendVisible(false);
        barChart.setHorizontalGridLinesVisible(false);
        barChart.setVerticalGridLinesVisible(false);
        barChart.setAlternativeRowFillVisible(false);
        barChart.setAlternativeColumnFillVisible(false);
        barChart.setBarGap(15);
        barChart.setCategoryGap(40);
        barChart.setStyle("-fx-background-color: transparent;");

        XYChart.Series<String, Number> data = new XYChart.Series<>();
        data.getData().add(new XYChart.Data<>("Viagens", 124));
        data.getData().add(new XYChart.Data<>("Distância (km)", 678));
        data.getData().add(new XYChart.Data<>("Tempo médio (min)", 42));

        barChart.getData().add(data);

        for (XYChart.Data<String, Number> item : data.getData()) {
            item.getNode().setStyle("-fx-bar-fill: white;");

            item.getNode().setOnMouseEntered(e -> item.getNode().setStyle("-fx-bar-fill: #cce6ff;"));
            item.getNode().setOnMouseExited(e -> item.getNode().setStyle("-fx-bar-fill: white;"));
        }

        Label titulo = new Label("ESTATÍSTICAS");
        titulo.setFont(Font.font("Helvetica", FontWeight.BOLD, 28));
        titulo.setTextFill(Color.WHITE);

        Button botaoVoltar = new Button("Voltar");
        botaoVoltar.setPrefWidth(150);
        botaoVoltar.setFont(Font.font("Helvetica", FontWeight.BOLD, 14));
        botaoVoltar.setStyle(
            "-fx-background-color: white;" +
            "-fx-text-fill: #0066cc;" +
            "-fx-background-radius: 10;"
        );
        botaoVoltar.setOnMouseEntered(e -> botaoVoltar.setStyle(
            "-fx-background-color: #cce6ff;" +
            "-fx-text-fill: #003366;" +
            "-fx-background-radius: 10;"
        ));
        botaoVoltar.setOnMouseExited(e -> botaoVoltar.setStyle(
            "-fx-background-color: white;" +
            "-fx-text-fill: #0066cc;" +
            "-fx-background-radius: 10;"
        ));
        botaoVoltar.setOnAction(e -> TelaInicial.exibir(estados));

        VBox topSection = new VBox(10, titulo);
        topSection.setAlignment(Pos.CENTER);
        topSection.setPadding(new Insets(20));

        VBox bottomSection = new VBox(10, botaoVoltar);
        bottomSection.setAlignment(Pos.CENTER);
        bottomSection.setPadding(new Insets(20));

        BorderPane layout = new BorderPane();
        layout.setTop(topSection);
        layout.setCenter(barChart);
        layout.setBottom(bottomSection);
        layout.setStyle("-fx-background-color: #0066cc;");

        Scene cena = new Scene(layout, 700, 500);
        App.root.getChildren().setAll(layout);
    }
}
