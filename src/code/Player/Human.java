package code.Player;
import code.Windows.Circle;
import code.Grid;
import code.TicTacToe;
import java.util.Arrays;

//Classe de l'humain
public class Human extends Player {
    public int column;
    public int row;
    private final int gameChoice;

    public Human(Grid grid, Circle circle, TicTacToe ticTacToe, int gameChoice) {
        super(grid, circle, ticTacToe);
        this.gameChoice = gameChoice;
    }

    //Placement du pion
    @Override
    public void play() {
        //Si la case est dispo, on paint, on rajoute dans la grille, et on print la grille
        if (cellIsEmpty(grid, column, row)) {
            if (gameChoice == 1) {
                if (grid.player == 1) {
                    super.circle.paint(grid.getGraphicsContext2D(), column, row, grid.getScale());
                    grid.grid[column][row] = 1;
                    System.out.println(Arrays.deepToString(grid.grid));
                } else if (grid.player == 2) {
                    super.circle.paint(grid.getGraphicsContext2D(), column, row, grid.getScale());
                    grid.grid[column][row] = 2;
                    System.out.println(Arrays.deepToString(grid.grid));
                }
            } else {
                super.circle.paint(grid.getGraphicsContext2D(), column, row, grid.getScale());
                grid.grid[column][row] = 1;
                System.out.println(Arrays.deepToString(grid.grid));
            }
        }
    }

}
