package code.Windows;

import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import code.Grid;
import code.TicTacToe;

//Classe qui regroupe la fenêtre du TicTacToe avec boutons et restart
public class TicTacToeWindows implements GameInterface {
    TicTacToe ticTacToe;
    Button restartButton = new Button("RESTART");

    public TicTacToeWindows(TicTacToe ticTacToe) {
        this.ticTacToe = ticTacToe;
    }

    //Remet la grille à 0 et clear la fenêtre
    ////TODO : le restart marche pas encore: à revoir
    @Override
    public void restart(MouseEvent mouseEvent) {
        ticTacToe.grid.grid = new int[ticTacToe.grid.getColumnCount()][ticTacToe.grid.getRowCount()];
        ticTacToe.grid.setOnMouseClicked(ticTacToe::gamePlay);
        setMenuButtons(ticTacToe.root, ticTacToe.grid);
    }

    @Override
    public void setMenuButtons(Group root, Grid grid) {
        root.getChildren().remove(root.getChildren().size() - 1);
        VBox vBox = new VBox();
        restartButton.setOnMouseClicked(this::restart);
        vBox.getChildren().addAll(grid.label);
        vBox.getChildren().addAll(new HBox(new VBox(restartButton)), grid);
        root.getChildren().add(vBox);
    }

}
