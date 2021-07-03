package main;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.layout.Background;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.awt.*;

public class Main extends Application {

    Scrabble scrabble = new Scrabble();

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Scrabbot");
        GridPane scrabbleBoard = new GridPane();

        scrabble.setBoard();

        Button btnBest = new Button();
        btnBest.setText("Find Best Word");
        btnBest.setOnAction(event -> System.out.println("To be implemented"));

        for(int r = 0; r < 15; r++) {
            for(int c = 0; c < 15; c++) {
                TextField space = new TextField();
                char tile = scrabble.board[r][c];
                String tileStr = null;
                String color;

                switch(tile) {
                    case('3'):
                        color = "#AB2424";
                        tileStr = "TW";
                        break;
                    case('2'):
                        color = "#3857C6";
                        tileStr = "DW";
                        break;
                    case('t'):
                        color = "#FFAFAF";
                        tileStr = "TL";
                        break;
                    case('d'):
                        color = "#BEE0FE";
                        tileStr = "DL";
                        break;
                    case('*'):
                        color = "#3857C6";
                        tileStr = "*";
                        break;
                    default:
                        color = "#E4DEDA";
                        tileStr = "";
                }

                space.setText(tileStr);
                space.setStyle("-fx-border-color: black;" +
                        "-fx-background-color: " + color + ";" +
                        "-fx-text-fill: white;" +
                        "-fx-alignment: center;" +
                        "-fx-font-size: 16px;" +
                        "-fx-font-weight: bold;");
                space.setPrefSize(50, 50);


                scrabbleBoard.add(space, c, r);
            }
        }

        StackPane root = new StackPane();
        root.getChildren().add(scrabbleBoard);
        primaryStage.setScene(new Scene(root, 840, 840));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
