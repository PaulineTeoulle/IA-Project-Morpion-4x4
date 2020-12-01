package code;

import code.Windows.TicTacToeWindows;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

//Lancement de l'appli avec JAVAFX
public class Main extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) {
        Group root = new Group();
        stage.setTitle("TicTacToe 4x4 with an AI !");
        stage.setScene(new Scene(root, 800, 800));
        root.getChildren().clear();
        HBox hBox = new HBox();
        hBox.setPadding(new Insets(50, 50, 50, 60));
        hBox.setStyle("-fx-background-color: #336699;");
        hBox.setAlignment(Pos.CENTER);
        Button humanVShuman = new Button("Human vs Human !");
        humanVShuman.setPrefSize(150, 20);

        Button humanVSaiMinMax = new Button("Human vs AI MinMax !");
        humanVSaiMinMax.setPrefSize(150, 20);

        Button humanVSaiAlphaBeta = new Button("Human vs AI AlphaBetaMinMax !");
        humanVSaiMinMax.setPrefSize(150, 20);

        Button aiVSai = new Button("AI MinMax vs AI AlphaBetaMinMax !");
        aiVSai.setPrefSize(220, 20);

        hBox.getChildren().addAll(humanVShuman,humanVSaiMinMax,humanVSaiAlphaBeta,aiVSai);
        setButtonsOnMouseClicked(root, humanVShuman, humanVSaiMinMax, humanVSaiAlphaBeta, aiVSai);
        root.getChildren().add(hBox);
        //Affichage de la fenêtre
        stage.show();


    }

    private void setButtonsOnMouseClicked(Group root, Button humanVShuman, Button humanVSaiMinMax, Button humanVSaiAlphaBeta, Button aiVSai) {
        TicTacToe humanVShumanGamePlay = new TicTacToe(root,1);
        TicTacToe humanVSaiGamePlay = new TicTacToe(root,2);
        TicTacToe humanVSaiAlphaBetaGamePlay = new TicTacToe(root,3);
        TicTacToe aiVSaiGamePlay = new TicTacToe(root,4);

        humanVShuman.setOnMouseClicked(new TicTacToeWindows(humanVShumanGamePlay)::restart);
        humanVSaiMinMax.setOnMouseClicked(new TicTacToeWindows(humanVSaiGamePlay):: restart);
        humanVSaiAlphaBeta.setOnMouseClicked(new TicTacToeWindows(humanVSaiAlphaBetaGamePlay):: restart);
        aiVSai.setOnMouseClicked(new TicTacToeWindows(aiVSaiGamePlay)::restart);
    }


}
