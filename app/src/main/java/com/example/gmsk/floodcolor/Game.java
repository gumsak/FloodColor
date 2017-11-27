package com.example.gmsk.floodcolor;

import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.ColorDrawable;
import android.util.Log;
import android.widget.Button;

import java.util.Random;

/**
 * One individual game : ie the size of the board, the number of colors, number of points, timer,
 * etc...
 */

public class Game {

    private Cell[][] board;
    private int boardSize;
    private int colorNumber;
    private Paint[] paint;

    /**A default game if nothing id specified*/
    public Game(){
        this.board = new Cell[5][5]; //we initialize a 2D 5*5 board
        this.colorNumber = 4; //we set it to 4 different colors
    }

    /**Build the game with the given size, number of colors, and said colors (p)*/
    public Game(int size, int colors, Paint[] p){
        this.boardSize = size;
        this.colorNumber = colors;
        this.paint = p;

        initBoard();
    }

    /**
     * Initialize the board's data (size, cell's color, etc...)*/
    public void initBoard(){

        int i, j;

        /*create a new board with the given dimension*/
        board = new Cell[boardSize][boardSize];

        /*create a new cell and give it a random color*/
        for (j = 0; j < boardSize; j++) {
            for (i = 0; i < boardSize; i++) {
                this.board[j][i] = new Cell(paint[getRand(colorNumber)]);
                this.board[j][i].setX(i);
                this.board[j][i].setY(j);

            }
        }
        /*The 1st cell (top left) will be the starting point*/
        this.board[3][3].setState(true);
    }
    /*Change a cell's color*/
    public void changeColor(Cell cell, int newColor){

        Paint j = new Paint();
        j.setColor(newColor);
        cell.setCellColor(j);
    }

    /**
     * Check a cell's neighbors and return the ones with the same color
     * @param button : the button that was clicked (we retrieve its color)*/
    public void checkNeighbor(Button button) {

        int bLen = board.length; //the board's length

        /*get the button's color*/
        ColorDrawable buttonColor = (ColorDrawable) button.getBackground();
        /*convert it as an int*/
        int colorId = buttonColor.getColor();

        for (int j = 0; j < bLen; j++) {
            for (int i = 0; i < bLen; i++) {
                //if the cell is part of the flood
                if (board[j][i].getState()){
                    /*then check its neighbors : right neigh, bot neigh, left neigh, top neigh
                    are out of the flood and have the same color*/
                    if(!board[j][i+1].getState() && compareColors(board[j][i+1],colorId)){
                        changeColor(board[j][i], colorId);
                        board[j][i+1].setState(true);
                        checkNeighbor(button);
                    }
                    if(!board[j+1][i].getState() && compareColors(board[j+1][i],colorId)){
                        changeColor(board[j][i], colorId);
                        board[j+1][i].setState(true);
                        checkNeighbor(button);
                    }
                    if(!board[j][i-1].getState() && compareColors(board[j][i-1],colorId)){
                        changeColor(board[j][i], colorId);
                        board[j][i-1].setState(true);
                        checkNeighbor(button);
                    }
                    if(!board[j-1][i].getState() && compareColors(board[j-1][i],colorId)){
                        changeColor(board[j][i], colorId);
                        board[j-1][i].setState(true);
                        checkNeighbor(button);
                    }
                    else Log.e("Erreur","PB couleur");
                }
            }
        }
    }

    /**Compare 2 cells' colors
     * @return true if the colors match ; false if they don't*/
    public boolean compareColors(Cell c1, int colorPicked){

        return(c1.getColor() == colorPicked);
    }

    /**return true if the selected cell is not in the board (ie it doesn't exist)*/
   /* public boolean isOutOfBound(Cell cell){

        int j, i;
        if(cell[][])
    }*/

    /**
     * generate a random int between 0 and n-1
     * */
    public int getRand(int n){
        Random rand = new Random();
        return rand.nextInt(n);
    }

    /**return the board used in the current game*/
    public Cell[][] getBoard(){
        return board;
    }

}
