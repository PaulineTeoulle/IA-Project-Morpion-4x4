package code.Windows;

import javafx.scene.Group;
import javafx.scene.input.MouseEvent;
import code.Grid;

//Interface pour avoir les boutons obligatoires
//TODO : le restart marche pas encore
public interface GameInterface {

    void restart(MouseEvent mouseEvent);

    void setMenuButtons(Group root, Grid grid);

}
