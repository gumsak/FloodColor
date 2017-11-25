package com.example.gmsk.floodcolor;

import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.PaintDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity {


    private BoardView boardView;
    private int buttonNumber = 4; //
    private LinearLayout gameLayout;
    private int[] colorsList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        LinearLayout buttonsLayout = (LinearLayout) findViewById(R.id.buttonsDisplay);
        boardView = (BoardView) findViewById(R.id.boardLayout);

        initBoard();
        initColors();
        setButtons(buttonsLayout);
    }

    /**Initialize the bord*/
    public void initBoard(){

        boardView.setBoardSize(5);
    }

    /**
     * load the colors*/
    public void initColors(){

        try{
            //Retrieve the colors from an array
            colorsList = getResources().getIntArray(R.array.boardColors);
        }
        catch(Exception e){
            Log.e("MainActivity", "Error when loading colors");
        }
    }

    /**
     * add interactive 'buttons' under the board*/
    public void setButtons(LinearLayout layout){

        int i;

        /*create the given number of buttons and add them to the layout */
        for(i=0; i<buttonNumber; i++){

            //create a new button
            final Button aButton = new Button(this);
            //aButton.setText(i);
            aButton.setBackgroundColor(colorsList[i]);
           // aButton.getBackground().setColorFilter(colorsList[i], PorterDuff.Mode.MULTIPLY);
            //aButton.setPadding(10, 10, 10, 10);
            //aButton.setTextSize(40);
            aButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    //Log.i("Button", "click");
                    getClickedColor(aButton);
                    //boardView.setColor();
                }
            });

            //add the button to the layout
            layout.addView(aButton);
        }
    }

    /**get the color clicked by the user by retrieving the button's color*/
    public void getClickedColor(Button button){

        //Drawable buttonBackground = button.getBackground();
        ColorDrawable buttonColor = (ColorDrawable) button.getBackground();
        Log.i("result",""+buttonColor);

    }
}
