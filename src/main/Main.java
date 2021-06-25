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
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

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
                space.setText(String.valueOf(scrabble.board[r][c]));
                scrabbleBoard.add(space, c, r);
            }
        }

        StackPane root = new StackPane();
        root.getChildren().add(scrabbleBoard);
        primaryStage.setScene(new Scene(root, 720, 720));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
