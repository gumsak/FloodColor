package com.example.gmsk.floodcolor;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;

import java.util.Random;

/**
 * Created by mb on 19/11/17.
 *
 * Initializes and generates the board
 */

public class BoardView extends View {

    private Cell[][] board;//2D board made out of cells
    private int boardSize = 20;
    private int cellSize;
    private int colorsNumber = 4;
    private Paint[] paint;
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
    }

    /**
     * generate a random int between 0 and the given number-1
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

    /**Initialize the board in the given canvas*/
  public void setBoard(Canvas canvas){

      int i, j, k;

      for (j = 0; j < boardSize; j++) {
          for (i = 0; i < boardSize; i++) {
              k=getRand(colorsNumber);
              //draw the cells (squares)
              //void drawRect(float left, float top, float right, float bottom, Paint paint)
            //  Rect r = new Rect();

              board[j][i].setRect(i * cellSize + boardLeftAnchor, j * cellSize + boardTopAnchor,
                      (i + 1) * cellSize + boardLeftAnchor, (j + 1) * cellSize + boardTopAnchor);

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
  public void changeColor(){



  }

  /**
   * Check a cell's neighbors and return the ones with the same color*/
  public void checkNeighbor(Cell cell){



  }

    /**
     * Draw in the view*/
    @Override
    protected void onDraw(Canvas canvas){

        setBoard(canvas);
    }
}
