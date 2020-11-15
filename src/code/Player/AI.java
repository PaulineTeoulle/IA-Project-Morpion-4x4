package code.Player;
import code.Windows.Circle;
import code.Grid;
import code.TicTacToe;
import java.util.Arrays;

//Classe d'IA qui étend la super class Player
//Pour l'instant, joue comme un humain normal
public class AI extends Player {
    public int column;
    public int row;

    public AI(Grid grid, Circle circle, TicTacToe ticTacToe) {
        super("O", grid,circle, ticTacToe);
    }

    //Manière de jouer
    @Override
    public void play(){
            //Si la case est dispo, on paint, on rajoute dans la grille, et on print la grille
            if(grid.grid[column][row] ==0) {
                super.circle.paint(grid.getGraphicsContext2D(), column, row, grid.getScale());
                grid.grid[column][row] = 2;
                System.out.println(Arrays.deepToString(grid.grid));
            }
    }

}
