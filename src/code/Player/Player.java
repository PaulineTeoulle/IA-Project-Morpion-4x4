package code.Player;
import code.Check;
import code.Windows.Circle;
import code.Grid;
import code.TicTacToe;

//Class abstraite Player
public abstract class Player extends Check {

    public Grid grid; //la grille du jeu
    public Circle circle; //le cercle à peindre
    public TicTacToe ticTacToe; //le jeu du tictactoe

    public Player(Grid grid, Circle circle, TicTacToe ticTacToe) {
    this.grid = grid;
    this.circle = circle;
    this.ticTacToe = ticTacToe;
    }

    public abstract void play();

}

