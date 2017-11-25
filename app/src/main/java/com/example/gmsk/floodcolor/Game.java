package com.example.gmsk.floodcolor;

/**
 * Created by mb on 25/11/17.
 * One individual game
 */

public class Game {

    private Cell[][] board;
    private int boardSize;
    private int colorNumber;

    /**A default game if nothing id specified*/
    public Game(){
        this.board = new Cell[5][5]; //we initialize a 2D 5*5 board
    }

    /**Build the board with the given size and colors*/
    public Game(int size, int colors){
        this.boardSize = size;
        this.colorNumber = colors;
    }

    public void initBoard(){


    }
}
