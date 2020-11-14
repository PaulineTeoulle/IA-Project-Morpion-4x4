package code.Player;
import code.Windows.Circle;
import code.Grid;
import code.TicTacToe;

//Class abstraite Player
public abstract class Player {

    public String symbol; //le symbole du joueur : X ou O
    public Grid grid; //la grille du jeu
    public Circle circle; //le cercle à peindre
    public TicTacToe ticTacToe; //le jeu du tictactoe (relie le joueur au jeu)

    public Player(String symbol, Grid grid, Circle circle, TicTacToe ticTacToe) {
    this.symbol = symbol;
    this.grid = grid;
    this.circle = circle;
    this.ticTacToe = ticTacToe;
    }

    public abstract void play();
}
