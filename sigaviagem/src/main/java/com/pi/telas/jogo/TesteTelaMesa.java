// package com.pi.telas.jogo;

// import com.pi.App;
// import com.pi.classes.ControladorDeEstados;

// import javafx.scene.control.Button;
// import javafx.scene.image.Image;
// import javafx.scene.image.ImageView;
// import javafx.scene.input.MouseEvent;
// import javafx.scene.layout.Pane;
// import javafx.scene.layout.StackPane;

// public class TesteTelaMesa {
//     public static void exibir(ControladorDeEstados estados){
//         Image imagemFundo = new Image(App.class.getResource("/imagens/mesa.jpg").toExternalForm());
//         ImageView fundo = new ImageView(imagemFundo);
//         fundo.setPreserveRatio(false);

//         fundo.fitWidthProperty().bind(App.primaryStage.getScene().widthProperty());
//         fundo.fitHeightProperty().bind(App.primaryStage.getScene().heightProperty());

//         Pane camadaInterativa = new Pane();

//         Button adesivo = new Button();
//         //adesivo.setStyle("-fx-background-color: transparent; -fx-border-color: transparent;");
//         camadaInterativa.getChildren().add(adesivo);

//         adesivo.prefWidthProperty().bind(App.primaryStage.getScene().widthProperty().multiply(700.00/1920));
//         adesivo.prefHeightProperty().bind(App.primaryStage.getScene().heightProperty().multiply(400.00 / 1080));
//         adesivo.layoutXProperty().bind(App.primaryStage.getScene().widthProperty().multiply(250.00 / 1920));
//         adesivo.layoutYProperty().bind(App.primaryStage.getScene().heightProperty().multiply(400.00 / 1080));

//         Image cinturaoImg = new Image(App.class.getResource("/imagens/Cinturao.png").toExternalForm());
//         Button cinturao = new Button();
//         ImageView cinturaoView = new ImageView(cinturaoImg);
//         cinturaoView.setPreserveRatio(true);
//         cinturao.setStyle("-fx-background-color: transparent;");
//         cinturao.setGraphic(cinturaoView);
//         camadaInterativa.getChildren().add(cinturao);

//         //cinturao.prefWidthProperty().bind(App.primaryStage.getScene().widthProperty().multiply(450.00/1920));
//         //cinturao.prefHeightProperty().bind(App.primaryStage.getScene().heightProperty().multiply(500.00 / 1080));
//         cinturao.layoutXProperty().bind(App.primaryStage.getScene().widthProperty().multiply(1100.00 / 1920));
//         cinturao.layoutYProperty().bind(App.primaryStage.getScene().heightProperty().multiply(400.00 / 1080));
//         cinturaoView.fitWidthProperty().bind(App.primaryStage.getScene().widthProperty().multiply(450.00/1920));
//         cinturaoView.fitHeightProperty().bind(App.primaryStage.getScene().heightProperty().multiply(500.00 / 1080));

        
//         StackPane conteudo = new StackPane(fundo, camadaInterativa);
//         conteudo.setOnMouseClicked((MouseEvent event) -> {// função que da a posição e pixels 
//             double x = event.getX();
//             double y = event.getY();
//             System.out.println("Clique em: (" + x + ", " + y + ")");
//         });

//         App.root.getChildren().addAll(conteudo);
//     }
// }
