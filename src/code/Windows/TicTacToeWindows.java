package code.Windows;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import code.Grid;
import code.TicTacToe;

import java.util.Arrays;

//Classe qui regroupe la fenêtre du TicTacToe avec boutons et restart
public class TicTacToeWindows implements GameInterface {
    TicTacToe ticTacToe;

    public TicTacToeWindows(TicTacToe ticTacToe) {
        this.ticTacToe = ticTacToe;
    }

    @Override
    public void restart(MouseEvent mouseEvent) {
        ticTacToe.grid.grid = new int[ticTacToe.grid.getColumnCount()][ticTacToe.grid.getRowCount()];
        for (int column = 0; column < ticTacToe.grid.getColumnCount(); column++) {
            for (int row = 0; row < ticTacToe.grid.getRowCount(); row++) {
                ticTacToe.grid.grid[column][row] = 0;
            }
        }
        ticTacToe.grid.drawGrid();
        System.out.println(Arrays.deepToString(ticTacToe.grid.grid));
        ticTacToe.grid.setOnMouseClicked(ticTacToe::gamePlay);
        setMenuButtons(ticTacToe.root, ticTacToe.grid);
    }

    @Override
    public void setMenuButtons(Group root, Grid grid) {
        root.getChildren().remove(root.getChildren().size() - 1);
        HBox hBox = new HBox();
        hBox.setAlignment(Pos.TOP_CENTER);
        Button restartButton = new Button("Restart");
        restartButton.setOnMouseClicked(this::restart);
        hBox.getChildren().addAll(restartButton);

        VBox vBox = new VBox();
        vBox.setPadding(new Insets(50, 50, 50, 50));
        vBox.getChildren().addAll(grid.label);

        vBox.getChildren().addAll(hBox, grid);
        root.getChildren().add(vBox);
    }

}
