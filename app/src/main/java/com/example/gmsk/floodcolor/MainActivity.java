package com.example.gmsk.floodcolor;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity {

    private int buttonNumber = 3; //
    private LinearLayout gameLayout, buttonsLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        buttonsLayout = (LinearLayout) findViewById(R.id.buttonsDisplay);
        setColoredButtons();
    }

    /**
     * add interactive 'buttons' under the board*/
    public void setColoredButtons(){

        int i;

        for(i=0; i<buttonNumber; i++){

            //adds a button to the layout
            final Button aButton = new Button(this);

            aButton.setText(i);
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
