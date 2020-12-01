package code;

import code.Player.AIMinMax;
import code.Player.AIAlphaBeta;
import code.Player.AiRandom;
import code.Player.Human;
import code.Windows.Circle;
import javafx.scene.Group;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;

public class TicTacToe extends Check {
    public Grid grid; //La grille de jeu
    public Group root; //Pour l'ajouter au Canva

    public Human human; //humain
    public Human human2;
    public AiRandom AIRandom;
    public AIMinMax AIMinMax; //ai
    public AIAlphaBeta AIAlphaBeta;
    public int tour = 2; //Tour de jeu
    public int scale; //ajustement à la fenetre
    public Circle[] shape = new Circle[2]; //Nombre de pions possibles: ici 2
    private final int gamechoice;

    public TicTacToe(Group root, int gameChoice) {
        this.root = root;
        this.gamechoice = gameChoice;
        this.grid = new Grid(new Label(""), root.getScene().getWidth(), root.getScene().getHeight(),
                4, 4);
        this.scale = grid.getScale();
        shape[0] = new Circle(scale, scale, Color.RED);
        shape[1] = new Circle(scale, scale, Color.BLUE);

        if(gameChoice ==1){
            this.human = new Human(this.grid, shape[0], this, gameChoice);
            this.human2 = new Human(this.grid, shape[1], this, gameChoice);
        }
        else if (gameChoice ==2){
            this.human = new Human(this.grid, shape[0], this, gameChoice);
            this.AIMinMax = new AIMinMax(this.grid, shape[1], this, gameChoice);
        }
        else if (gameChoice ==3){
            this.human = new Human(this.grid, shape[0], this, gameChoice);
            this.AIAlphaBeta = new AIAlphaBeta(this.grid, shape[1], this, gameChoice);
        }
       else if (gameChoice ==4){
            this.AIMinMax = new AIMinMax(this.grid, shape[0], this, gameChoice);
            this.AIAlphaBeta = new AIAlphaBeta(this.grid, shape[1], this, gameChoice);
        }
        else if (gameChoice ==5){
            this.human = new Human(this.grid, shape[0], this, gameChoice);
            this.AIRandom = new AiRandom(this.grid, shape[1], this);
        }

        grid.widthProperty().bind(
                root.getScene().widthProperty().divide(1));
        grid.heightProperty().bind(
                root.getScene().heightProperty().divide(1));
    }

    //GamePlay
    public void gamePlay(MouseEvent mouseEvent) {
        int scale = (int) (Math.min(grid.getWidth() / grid.getColumnCount(), grid.getHeight() / grid.getRowCount()) * 0.8);
        grid.label.setText("");

        //Humain vs Humain
        if(gamechoice ==1) {
            humanVShuman(mouseEvent, scale);
        }
        //Humain vs MinMax
        if(gamechoice ==2){
            humanVSaiMinMax(mouseEvent, scale);
        }
        //Humain vs AlphaBeta
        if(gamechoice ==3){
            humanVSaiAlphaBeta(mouseEvent, scale);
        }
        //MinMax vs AlphaBeta
        if(gamechoice ==4){
            AiMinMaxVSAiAlphaBeta();
        }
        if(gamechoice ==5){
            humanVSaiRandom(mouseEvent, scale);
        }
    }

    private void humanVSaiRandom(MouseEvent mouseEvent, int scale) {
        changeTurn();
        if (tour == 1) {
            int column = (int) (mouseEvent.getX() / scale);
            int row = (int) (mouseEvent.getY() / scale);
            if (coordsAreInGrid(column, row, grid.getColumnCount(), grid.getRowCount())) {
                System.out.println("Les coordonnées ne sont pas dans la grille");
                grid.label.setText("Les coordonnées ne sont pas dans la grille");
            }
            human.row = row;
            human.column = column;
            human.play();
            //Check de win
            if (checkHumanWin(grid)) {
                System.out.println("Human1 won !");
                grid.label.setText("Human1 won !");
            }
        }

        //Si l'ia joue
        if (tour == 2) {
            AIRandom.play();
            //Check de win
            if (checkIaWin(grid)) {
                System.out.println("AI Random won !");
                grid.label.setText("AI Random won !");
            }
        }

        //Check de l'égalité
        checkDraw();
    }

    private void changeTurn() {
        tour = (tour % 2) + 1;
        grid.player = tour;
    }


    //TODO : seulement si cliqué sur bonne case : pas sur l'ia
    private void humanVShuman(MouseEvent mouseEvent, int scale) {
        changeTurn();
        if (tour == 1) {
            int column = (int) (mouseEvent.getX() / scale);
            int row = (int) (mouseEvent.getY() / scale);
            if (coordsAreInGrid(column, row, grid.getColumnCount(), grid.getRowCount())) {
                System.out.println("Les coordonnées ne sont pas dans la grille");
                grid.label.setText("Les coordonnées ne sont pas dans la grille");
            }
            human.row = row;
            human.column = column;
            human.play();
            //Check de win
            if (checkHumanWin(grid)) {
                System.out.println("Human1 won !");
                grid.label.setText("Human1 won !");
            }
        }

        //Si l'ia joue
        if (tour == 2) {
            int column = (int) (mouseEvent.getX() / scale);
            int row = (int) (mouseEvent.getY() / scale);
            if (coordsAreInGrid(column, row, grid.getColumnCount(), grid.getRowCount())) {
                System.out.println("Les coordonnées ne sont pas dans la grille");
                grid.label.setText("Les coordonnées ne sont pas dans la grille");
            }
            human2.row = row;
            human2.column = column;
            human2.play();
            //Check de win
            if (checkIaWin(grid)) {
                System.out.println("Human2 won !");
                grid.label.setText("Human2 won !");
            }
        }

        //Check de l'égalité
        checkDraw();
    }


    //TODO : seulement si cliqué sur bonne case : pas sur l'ia pour l'humain
    private void humanVSaiMinMax(MouseEvent mouseEvent, int scale) {
        changeTurn();
        //Si l'humain joue
        if (tour == 1) {
            int column = (int) (mouseEvent.getX() / scale);
            int row = (int) (mouseEvent.getY() / scale);
            if (coordsAreInGrid(column, row, grid.getColumnCount(), grid.getRowCount())) {
                System.out.println("Les coordonnées ne sont pas dans la grille");
                grid.label.setText("Les coordonnées ne sont pas dans la grille");
            }
            human.row = row;
            human.column = column;
            human.play();
            //Check de win
            if (checkHumanWin(grid)) {
                System.out.println("Human won !");
                grid.label.setText("Human won !");
            }
        }

        //Si l'ia joue
        if (tour == 2) {
            AIMinMax.play();
            //Check de win
            if (checkIaWin(grid)) {
                System.out.println("Ai MinMax won !");
                grid.label.setText("Ai MinMax won !");
            }
        }

        //Check de l'égalité
        checkDraw();
    }

    //TODO : seulement si cliqué sur bonne case : pas sur l'ia pour l'humain
    private void humanVSaiAlphaBeta(MouseEvent mouseEvent, int scale) {
        changeTurn();
        if (tour == 1) {
            int column = (int) (mouseEvent.getX() / scale);
            int row = (int) (mouseEvent.getY() / scale);
            if (coordsAreInGrid(column, row, grid.getColumnCount(), grid.getRowCount())) {
                System.out.println("Coords are not in the grid");
                grid.label.setText("Coords are not in the grid");
            }
            human.row = row;
            human.column = column;
            human.play();
            //Check de win
            if (checkHumanWin(grid)) {
                System.out.println("Human1 won !");
                grid.label.setText("Human1 won !");
            }
        }

        if (tour == 2) {
            AIAlphaBeta.play();
            //Check de win
            if (checkIaWin(grid)) {
                System.out.println("Ai AlphaBeta won !");
                grid.label.setText("Ai AlphaBeta won !");
            }
        }

        //Check de l'égalité
        checkDraw();
    }

    private void AiMinMaxVSAiAlphaBeta() {
        changeTurn();
        if (tour == 1) {
            AIMinMax.play();
            //Check de win
            if (checkIaWin(grid)) {
                System.out.println("Ai MinMax won !");
                grid.label.setText("Ai MinMax won !");
                return;
            }
        }

        //Si l'ia joue
        if (tour == 2) {
            AIAlphaBeta.play();
            //Check de win
            if (checkIaWin(grid)) {
                System.out.println("Ai AlphaBeta won !");
                grid.label.setText("Ai AlphaBeta won !");
                return;
            }
        }
        checkDraw();
    }

    private void checkDraw() {
        if(isDraw(grid)){
            System.out.println("Draw !");
            grid.label.setText("Draw !");
        }
    }

}