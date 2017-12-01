package com.example.gmsk.floodcolor;

import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;

/**
 * represents a cell (seen as a colored box)
 */
public class Cell {

    private int number;

    private Rect r;
    private  boolean isInFlood; //the cell's state (true = is part of the flood; false = is not)
    private Paint paint; //the cell's color
    private int x, y;

    /*default Cell constructor*/
    public Cell(){

        this.isInFlood = false;

        /*set gray as default color*/
        this.paint = new Paint();
        paint.setColor(Color.GRAY);
        /*a rectangle will represent the cell*/
        //this.r = new Rect(0,0,5,5);
    }

    /*Cell constructor with specific paint*/
    public Cell(Paint p){

        this.isInFlood = false;
        //this.paint = new Paint();
        this.paint = p;

        //this.r = new Rect(0,0,50,50);
    }

    /**
     * setter used to set a specific cell's color*/
    public void setCellColor(Paint p){

        this.paint = p;
    }

    /**set the cell's state */
    public void setState(boolean state){

        this.isInFlood = state;
    }

    /**set the cell's rectangle's size*/
    public void setRect(int left, int top, int right, int bottom){

        this.r = new Rect(left,top,right,bottom);
    }
    public void setX(int num){this.x = num;}

    public void setY(int num){ this.y = num;}

    /**
     * getter used to get a specific cell's color*/
    public Paint getCellPaint(){

        return this.paint;
    }
    /**used to get a cell's Paint*/
    public int getCellColor(){

        return this.paint.getColor();
    }
    /**get the cell's state */
    public boolean getState(){
        return this.isInFlood;
    }

    public int getX(){return this.x;}

    public int getY(){return this.y;}

    /**get the cell's rectangle*/
    public Rect getRect(){

        return this.r;
    }
}
