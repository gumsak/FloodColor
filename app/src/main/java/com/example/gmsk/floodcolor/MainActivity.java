package com.example.gmsk.floodcolor;

import android.graphics.Paint;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity {

    private BoardView boardView;
    private Game game;
    private int colorsCount = 6, boardSize = 15;
    private Paint[] paint;
    private int[] colorsList;

    /*the clickable buttons' size*/
    private int buttonHeight = 160;
    private int buttonWidth = 160;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        LinearLayout buttonsLayout = (LinearLayout) findViewById(R.id.buttonsDisplay);
        boardView = (BoardView) findViewById(R.id.boardLayout);

        initColors();
        setButtons(buttonsLayout);
        startGame();
    }

    public void startGame(){
        this.game = new Game(boardSize, colorsCount, paint);
        initBoard();
    }

    /**Initialize the bord*/
    public void initBoard(){

        boardView.setGame(game);
        boardView.setBoardSize(boardSize);
        boardView.initData();
    }

    /** load the colors from the resources*/
    public void initColors(){

        try{
            //Retrieve the colors from an array
            colorsList = getResources().getIntArray(R.array.boardColors);
            //convert the colors into paints to be used with drawRect()
            setPaints();
        }
        catch(Exception e){
            Log.e("MainActivity", "Error when loading colors");
        }
    }

    /**load the paints that will be used*/
    public void setPaints(){

        paint = new Paint[colorsList.length];

        for(int i = 0; i< colorsList.length; i++){
            paint[i] = new Paint();
            paint[i].setColor(colorsList[i]);
        }
        boardView.setPaint(paint);
    }

    /**
     * add interactive 'buttons' under the board*/
    public void setButtons(LinearLayout layout){

        int i;

        /*create the given number of buttons and add them to the layout */
        for(i=0; i< colorsCount; i++){

            //create a new button
            final Button aButton = new Button(this);
            //set the button's color
            aButton.setBackgroundColor(colorsList[i]);
            //aButton.getBackground().setColorFilter(colorsList[i], PorterDuff.Mode.MULTIPLY);
            /*initialize the button's parameters for the layout*/
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
            );
            params.setMargins(10, 0, 10, 0);
            params.height = buttonHeight;
            params.width = buttonWidth;
            aButton.setLayoutParams(params);

            aButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    //game.setSelectedColor(getClickedColor(aButton));
                    game.checkNeighbor(getClickedColor(aButton));
                    boardView.setGame(game);
                }
            });
            //add the button to the layout
            layout.addView(aButton);
        }
    }

    /**When the user clicks a button we retrieve that button's color */
    public int getClickedColor(Button button){

         /*get the button's color*/
        ColorDrawable buttonColor = (ColorDrawable) button.getBackground();
        //int colorId = buttonColor.getColor();
        //Log.i("result",""+buttonColor);

        /*return it as an int*/
        return buttonColor.getColor();
    }
}
