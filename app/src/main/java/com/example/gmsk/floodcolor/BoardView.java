package com.example.gmsk.floodcolor;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import java.util.Random;

/**
 * Created by mb on 19/11/17.
 *
 * Initializes and generates the board
 */

public class BoardView extends View {

    private Cell[][] board;//2D board made out of cells
    private int boardSize;
    private int cellSize;
    private int colorsNumber = 4;
    private Paint[] paint;
    private Paint testPaint;
    private int[] colorList;
    private int mSize = 1050;//screen's width/height

    // anchors used to center the board in the view
    int boardTopAnchor;// Y position of our anchor
    int boardLeftAnchor;// X position of our anchor

    public BoardView(Context context, AttributeSet attrs){
        super(context, attrs);

        initData();
        initBoard();
    }

    public void setBoardSize(int size){

        this.boardSize = size;
    }

    //load and/or initialize the data used
    public void initData(){

        /*load and set the colors*/
        colorList = getResources().getIntArray(R.array.boardColors);
        paint = new Paint[colorList.length];
        setPaints();

         /*initialize the cells' size*/
        cellSize = mSize / boardSize;

        /*initialize the anchors*/
        boardTopAnchor = (mSize - boardSize*cellSize)/2;
        boardLeftAnchor = (mSize - boardSize*cellSize)/2;

        /*Initialize the board's size*/
        board = new Cell[boardSize][boardSize];
    }

    public void setPaints(){

        for(int i = 0; i< colorsNumber; i++){
            paint[i] = new Paint();
            paint[i].setColor(colorList[i]);
        }
        testPaint=new Paint();
        testPaint.setColor(Color.BLACK);
    }

    /**
     * generate a random int between 0 and n-1
     * */
    public int getRand(int n){

        Random rand = new Random();
        return rand.nextInt(n);
    }

    /**
     * Initialize the board's data (size, cell's color, etc...)*/
    public void initBoard(){

        int i, j;

        /*give each cell a random color*/
        for (j = 0; j < boardSize; j++) {
            for (i = 0; i < boardSize; i++) {
                board[j][i] = new Cell(paint[getRand(colorsNumber)]);
            }
        }
    }

    /**Draw the board in the given canvas*/
    public void setBoard(Canvas canvas){

        int i, j, k;

        for (j = 0; j < boardSize; j++) {
            for (i = 0; i < boardSize; i++) {

              /*draw the cells : void drawRect(float left, float top, float right, float bottom, Paint paint)*/
                board[j][i].setRect(i * cellSize + boardLeftAnchor, j * cellSize + boardTopAnchor,
                        (i + 1) * cellSize + boardLeftAnchor, (j + 1) * cellSize + boardTopAnchor);

                setColor(j,i);///////

                canvas.drawRect(board[j][i].getRect(), board[j][i].getCellColor());

              /*canvas.drawRect(i * cellSize + boardLeftAnchor, j * cellSize + boardTopAnchor,
                      (i + 1) * cellSize + boardLeftAnchor, (j + 1) * cellSize + boardTopAnchor, paint[k]);*/
            }
        }

      /*for (j = 0; j < boardSize; j++) {
          for (i = 0; i < boardSize; i++) {
              k=getRand(colorsNumber);
              //draw the cells (squares)
              //void drawRect(float left, float top, float right, float bottom, Paint paint)
              canvas.drawRect(i * cellSize + boardLeftAnchor, j * cellSize + boardTopAnchor,
                      (i + 1) * cellSize + boardLeftAnchor, (j + 1) * cellSize + boardTopAnchor, paint[k]);
          }
      }*/
    }

    /**method used to change the board's active color when the user clicks a colored action button */
    public void setColor(int i, int j){
if(board[i][j].getCellColor() == paint[1])
        board[i][j].setCellColor(testPaint);
    }

    /**
     * Check a cell's neighbors and return the ones with the same color*/
 /*   public void checkNeighbor(Cell[][] cell) {

        int s = cell.length;
        Paint cellC;

        for (int j = 0; j < s; j++) {
            for (int i = 0; i < s; i++) {
                //if the cell is part of the flood
                //TODO : change to cell[i][j].getState() ie to true
                if (!cell[j][i].getState()) {
                    //then check its neighbors : right neigh, bot neigh, left neigh, top neigh
                    if(!cell[j][i+1].getState() && compareColors(cell[j][i],cell[j][i+1])){
                        changeColor();
                    }
                    else if(!cell[j+1][i].getState() && compareColors(cell[j][i],cell[j+1][i])){
                        changeColor();
                    }
                    else if(!cell[j][i-1].getState() && compareColors(cell[j][i],cell[j][i-1])){
                        changeColor();
                    }
                    else if(!cell[j-1][i].getState() && compareColors(cell[j][i],cell[j-1][i])){
                        changeColor();
                    }
                    else Log.e("Erreur","PB couleur");
                }
            }
        }
    }*/

    /**Compare 2 cells' colors
     * @return true if the colors match ; false if they don't*/
    public boolean compareColors(Cell c1, Cell c2){

        return(c1.getCellColor() == c2.getCellColor());
    }

    /**
     * Draw in the view*/
    @Override
    protected void onDraw(Canvas canvas){

        setBoard(canvas);
        //checkNeighbor(board);
    }
}
