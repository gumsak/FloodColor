package com.example.gmsk.floodcolor;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import java.util.Random;

/**
 * Created by mb on 19/11/17.
 *
 * Initializes and generates the board
 */

public class BoardView extends View {

    private int[][] board;
    private int boardSize = 10;
    private int cellSize = 50;
    private int colorsNumber = 4;
    private Paint[] paint;
    private int[] colorList;

    // anchors used to center the board in the view
    int boardTopAnchor;// Y position of our anchor
    int boardLeftAnchor;// X position of our anchor

    public BoardView(Context context, AttributeSet attrs){
        super(context, attrs);

        initData();

    }

    //load and/or initialize the data used
    public void initData(){

        /*load and set the colors*/
        colorList = getResources().getIntArray(R.array.boardColors);
        paint = new Paint[colorList.length];
        setPaints();

        /*initialize the anchors*/
        boardTopAnchor = (getHeight()- boardSize*cellSize)/2;
        boardLeftAnchor = (getWidth()- boardSize*cellSize)/2;
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

    /**Initialize the board in the given canvas*/
  public void setBoard(Canvas canvas){

      int i, j, k;

      for (j = 1; j <= boardSize; j++) {
          for (i = 1; i <= boardSize; i++) {
              k=getRand(colorsNumber);
              canvas.drawRect(i * cellSize + 180, j * cellSize + 180,
                      (i + 1) * cellSize + 180, (j + 1) * cellSize + 180, paint[k]);
          }
      }
  }
    /**
     * Draw in the view*/
    @Override
    protected void onDraw(Canvas canvas){

        setBoard(canvas);
    }
}
