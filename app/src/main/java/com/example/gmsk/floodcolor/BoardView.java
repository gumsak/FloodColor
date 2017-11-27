package com.example.gmsk.floodcolor;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

/***
 * This is the BoardView Class, it will take care of drawing a board in a View
 */

public class BoardView extends View {

    private Game game;
    private Cell[][] board;//2D board made out of cells
    private int boardSize;
    private int cellSize;
    private Paint[] paint;
    private int mSize = 1050;//screen's width/height

    // anchors used to center the board in the view
    int boardTopAnchor;// Y position of our anchor
    int boardLeftAnchor;// X position of our anchor

    public BoardView(Context context, AttributeSet attrs){
        super(context, attrs);

       // initData();
        //initBoard();
    }

    /**set the actual game we are about to play*/
    public void setGame(Game g){
        this.game = g;
        invalidate();
    }

    public void setBoardSize(int size){
        this.boardSize = size;
    }

    public void setPaint(Paint p[]){
        this.paint = p;
    }

    //load and/or initialize the data used
    public void initData(){

        /*initialize the cells' size*/
        cellSize = mSize / boardSize;

        /*initialize the anchors*/
        boardTopAnchor = (mSize - boardSize*cellSize)/2;
        boardLeftAnchor = (mSize - boardSize*cellSize)/2;

        /*Initialize the board's game*/
        this.board = game.getBoard();
    }

    /**Draw the board in the given canvas*/
    public void drawBoard(Canvas canvas){

        int i, j;

        for (j = 0; j < boardSize; j++) {
            for (i = 0; i < boardSize; i++) {

              /*draw the cells : void drawRect(float left, float top, float right, float bottom, Paint paint)*/
                board[j][i].setRect(i * cellSize + boardLeftAnchor, j * cellSize + boardTopAnchor,
                        (i + 1) * cellSize + boardLeftAnchor, (j + 1) * cellSize + boardTopAnchor);

                canvas.drawRect(board[j][i].getRect(), board[j][i].getCellColor());

              /*canvas.drawRect(i * cellSize + boardLeftAnchor, j * cellSize + boardTopAnchor,
                      (i + 1) * cellSize + boardLeftAnchor, (j + 1) * cellSize + boardTopAnchor, paint[k]);*/
            }
        }

      /*for (j = 0; j < boardSize; j++) {
          for (i = 0; i < boardSize; i++) {
              //draw the cells (squares)
              //void drawRect(float left, float top, float right, float bottom, Paint paint)
              canvas.drawRect(i * cellSize + boardLeftAnchor, j * cellSize + boardTopAnchor,
                      (i + 1) * cellSize + boardLeftAnchor, (j + 1) * cellSize + boardTopAnchor, paint[k]);
          }
      }*/
    }

    /**
     * Draw in the view*/
    @Override
    protected void onDraw(Canvas canvas){

        drawBoard(canvas);
    }
}
