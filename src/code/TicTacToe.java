package code;

import code.Player.AI;
import code.Player.AIAlphaBeta;
import code.Player.Human;
import code.Player.Player;
import code.Windows.Circle;
import javafx.scene.Group;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;

public class TicTacToe extends Check {
    public Grid grid; //La grille de jeu
    public Group root; //Pour l'ajouter au Canva
    public AI AI; //ai
    public Human human; //humain
    public AIAlphaBeta AIAlphaBeta;
    public int tour = 2; //Tour de jeu
    public int scale; //ajustement à la fenetre
    public Circle[] shape = new Circle[2]; //Nombre de pions possibles: ici 2

    public TicTacToe(Group root) {
        this.root = root;
        this.grid = new Grid(new Label(""), root.getScene().getWidth(), root.getScene().getHeight(),
                4, 4);
        this.scale = grid.getScale();
        shape[0] = new Circle(scale, scale, Color.RED);
        shape[1] = new Circle(scale, scale, Color.BLUE);

        this.human = new Human(this.grid, shape[0], this);
        this.AI = new AI(this.grid, shape[1], this);
        this.AIAlphaBeta = new AIAlphaBeta(this.grid, shape[1], this);
        grid.widthProperty().bind(
                root.getScene().widthProperty().divide(1));
        grid.heightProperty().bind(
                root.getScene().heightProperty().divide(1));
    }


    //GamePlay
    public void gamePlay(MouseEvent mouseEvent) {
        //Récupération de l'ajustement de la grille
        int scale = (int) (Math.min(grid.getWidth() / grid.getColumnCount(), grid.getHeight() / grid.getRowCount()) * 0.8);
        //Récupération de la colonne et de la ligne selon la souris

        grid.label.setText("");
        //Changement de tour
        tour = (tour % 2) + 1;
        grid.player = tour;

        //Si l'humain joue
        if (tour == 1) {
            int column = (int) (mouseEvent.getX() / scale);
            int row = (int) (mouseEvent.getY() / scale);
            if (!coordsAreInGrid(column, row, grid.getColumnCount(), grid.getRowCount())) {
                System.out.println("Les coordonnées ne sont pas dans la grille");
                grid.label.setText("Les coordonnées ne sont pas dans la grille");
            }

            human.row = row;
            human.column = column;
            human.play();
            //Check de win
            if (checkHumanWin(grid)) {
                System.out.println("Human WON");
                grid.label.setText("HUMAN WON");
                return;
            }
        }
        //Si l'ia joue
        if (tour == 2) {
            //AI.play();
            AIAlphaBeta.play();
            //Check de win
            if (checkIaWin(grid)) {
                System.out.println("IA WON");
                grid.label.setText("IA WON");
                return;
            }
        }

        //Check de l'égalité
        if (grid.gridIsFull()) {
            System.out.println("Egalité");
            grid.label.setText("EGALITE");
        }
    }
}