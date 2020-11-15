package code.Player;
import code.Windows.Circle;
import code.Grid;
import code.TicTacToe;
import java.util.Arrays;

//Classe de l'humain qui étend la super class Player
// Normalement pas besoin de la mofidier
public class Human extends Player {
    public int column;
    public int row;

    public Human(Grid grid, Circle circle, TicTacToe ticTacToe) {
        super("X", grid, circle, ticTacToe);
    }

    //Manière de jouer
    @Override
    public void play(){
            //Si la case est dispo, on paint, on rajoute dans la grille, et on print la grille
            if(grid.grid[column][row] ==0) {
                super.circle.paint(grid.getGraphicsContext2D(), column, row, grid.getScale());
                grid.grid[column][row] = 1;
                System.out.println(Arrays.deepToString(grid.grid));
            }
    }

}
