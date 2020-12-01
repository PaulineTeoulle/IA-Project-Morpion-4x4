package code.Windows;

import javafx.scene.Group;
import javafx.scene.input.MouseEvent;
import code.Grid;
import javafx.scene.layout.StackPane;

//Interface pour avoir les boutons obligatoires
public interface GameInterface {

    void restart(MouseEvent mouseEvent);
    void setMenuButtons(Group root, Grid grid);

}
