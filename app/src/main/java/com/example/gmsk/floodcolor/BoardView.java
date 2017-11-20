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
    private int boardSize = 18;
    private int cellSize = 50;
    private int colorsNumber = 4;
    private Paint[] paint;
    private int[] colorList;

    public BoardView(Context context, AttributeSet attrs){
        super(context, attrs);

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
  /*
  TODO : add an anchor to center the board in the view
  */

    /**
     * Draw the board*/
    @Override
    protected void onDraw(Canvas canvas){

        colorList = getResources().getIntArray(R.array.boardColors);
        paint = new Paint[colorList.length];

        setPaints();
        int i, j, k;

        for (j = 1; j <= boardSize; j++) {
            for (i = 1; i <= boardSize; i++) {
                k=getRand(3);
                    canvas.drawRect(i * cellSize + 180, j * cellSize + 180,
                            (i + 1) * cellSize + 180, (j + 1) * cellSize + 180, paint[k]);

            }
        }

    }
}
