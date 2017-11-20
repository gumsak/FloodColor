package com.example.gmsk.floodcolor;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity {

    private int buttonNumber = 4; //
    private LinearLayout gameLayout, buttonsLayout;
    private int[] colorsList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        buttonsLayout = (LinearLayout) findViewById(R.id.buttonsDisplay);
        initColors();
        setButtons();
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
    public void setButtons(){

        int i;

        for(i=0; i<buttonNumber; i++){

            //adds a button to the layout
            final Button aButton = new Button(this);

            //aButton.setText(i);
            aButton.setBackgroundColor(colorsList[i]);
            aButton.setTextSize(40);
            aButton.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View view) {
                    Log.i("Button", "click");
                }
            });

            buttonsLayout.addView(aButton);
        }
    }
}
