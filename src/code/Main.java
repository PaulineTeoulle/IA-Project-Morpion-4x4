package code;

import code.Windows.TicTacToeWindows;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

//Lancement de l'appli avec JAVAFX
public class Main extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        Group root = new Group();
        primaryStage.setTitle("TicTacToe 4x4 with an AI !");
        primaryStage.setScene(new Scene(root, 1000, 700));
        
        root.getChildren().clear();
        VBox vBox = new VBox();
        Button ticTacToeButton = new Button("START THE GAME !");
        vBox.getChildren().add(ticTacToeButton);
        TicTacToe tictactoe = new TicTacToe(root);
        ticTacToeButton.setOnMouseClicked(new TicTacToeWindows(tictactoe)::restart);
        vBox.setAlignment(Pos.CENTER);
        root.getChildren().add(vBox);
        //Affichage de la fenêtre
        primaryStage.show();
    }


}
