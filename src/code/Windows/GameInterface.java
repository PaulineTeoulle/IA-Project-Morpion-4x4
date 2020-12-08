package code.Windows;

import javafx.scene.Group;
import javafx.scene.input.MouseEvent;
import code.Grid;

//Interface pour avoir les boutons
public interface GameInterface {
    void restart(MouseEvent mouseEvent);

    void setMenuButtons(Group root, Grid grid);
}
