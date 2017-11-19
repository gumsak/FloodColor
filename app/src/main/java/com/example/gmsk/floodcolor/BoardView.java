package com.example.gmsk.floodcolor;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by mb on 19/11/17.
 *
 * Initializes and generates the board
 */

public class BoardView extends View {

    private int[][] board;
    private int boardSize = 3;
    private int cellSize = 1;
    private int colorsNumber = 3;
    private Paint[] paint;

    public BoardView(Context context, AttributeSet attrs){
        super(context, attrs);

    }

    public void setPaints(){

        int[] j;
        j = getResources().getIntArray(R.array.boardColors);

        for(int i = 0; i< colorsNumber; i++){
            paint[i].setColor(j[i]);
        }

    }

    /**
     * Draw the board*/
    @Override
    protected void onDraw(Canvas canvas){

        setPaints();
        int i, j;

        for (j = 0; j < boardSize; j++) {
            for (i = 0; i < boardSize; i++) {
                canvas.drawRect(i * cellSize + 1, j * cellSize + 1,
                        (i + 1) * cellSize + 1, (j + 1) * cellSize + 1, paint[i]);
            }
        }

    }
}
