package com.example.gmsk.floodcolor;

import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;

/**
 * Created by mb on 20/11/17.
 * represents a cell (seen as a colored box)
 */
public class Cell {

    private int number;

    private Rect r;
    private  boolean cState; //the cell's state (true = is part of the flood; false = is not)
    private Paint paint; //the cell's color

    /*default Cell constructor*/
    public Cell(){

        this.cState = false;

        /*set gray as default color*/
        this.paint = new Paint();
        paint.setColor(Color.GRAY);
    }

    /*Cell constructor with specific paint*/
    public Cell(Paint p){

        this.cState = false;
        //this.paint = new Paint();
        this.paint = p;
    }

    /**
     * setter used to set a specific cell's color*/
    public void setCellColor(Cell cell, Paint p){

        cell.paint = p;
    }

    /**set the cell's state */
    public void setState(Cell cell, boolean state){

        cell.cState = state;
    }
    /**
     * getter used to get a specific cell's color*/
    public Paint getCellColor(Cell cell){

        return cell.paint;
    }

    /**get the cell's state */
    public boolean getState(Cell cell){

        return cell.cState;
    }
}
