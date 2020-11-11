package dev.victorman.tictactoe;

import android.util.Log;

import java.util.Observable;

public class GameModel extends Observable {
    public int[][] gameBoard;
    private int currentPlayer;

    public GameModel() {

        gameBoard = new int[3][3];

        currentPlayer = 0;
        initBoard();
    }

    public boolean checkWin() {
        boolean win = false;
        win = checkH();
        if (win){
            return true;
        }
        win = checkV();
        if (win){
            return true;
        }
        win = checkD();

        return win;
    }

    private boolean checkD() {
        if ((gameBoard[0][0] == gameBoard[1][1]) &&
                (gameBoard[1][1] == gameBoard[2][2]) &&
                (gameBoard[0][0] == 0 || gameBoard[0][0] == 1)) {
            return true;
        }
        if ((gameBoard[0][2] == gameBoard[1][1]) &&
                (gameBoard[1][1] == gameBoard[2][0]) &&
                (gameBoard[0][2] == 0 || gameBoard[0][2] == 1)) {
            return true;
        }
        return false;
    }

    private boolean checkV() {
        for(int i=0;i<3;i++) {
            if ((gameBoard[0][i] == gameBoard[1][i]) &&
                    (gameBoard[1][i] == gameBoard[2][i]) &&
                    (gameBoard[0][i] == 0 || gameBoard[0][i] == 1)) {
                return true;
            }
        }
        return false;
    }

    private boolean checkH() {
        for(int i=0;i<3;i++) {
            if ((gameBoard[i][0] == gameBoard[i][1]) && (gameBoard[i][1] == gameBoard[i][2]) && (gameBoard[i][0] == 0 || gameBoard[i][0] == 1)) {
                return true;
            }
        }
        return false;
    }

    public void resetBoard() {
        currentPlayer = 0;
        initBoard();
        notifyObservers();
    }

    public boolean checkEnd() {
        if (checkWin()){
            notifyObservers();
            return true;
        }
        if (checkCat()){
            notifyObservers();
            return true;
        }
        return false;
    }

    private boolean checkCat() {

        for (int i=0;i<3;i++) {
            for (int j = 0; j < 3; j++) {
                if (gameBoard[i][j] == -1)
                    return false;
            }
        }
        return true;
    }

    public void initBoard() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                gameBoard[i][j] = -1;
            }
        }
        notifyObservers();
    }

    public void enterPlayerMove(int x, int y) {
        if(x > 3 || x < 0 || y > 3 || y < 0)
            return;

        gameBoard[x][y] = currentPlayer;
        currentPlayer = (currentPlayer+1) % 2;
        notifyObservers();
    }
}
