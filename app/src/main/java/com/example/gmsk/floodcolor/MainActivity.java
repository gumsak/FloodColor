package com.example.gmsk.floodcolor;

import android.app.AlertDialog;
import android.app.Dialog;
import android.graphics.Paint;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Layout;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private BoardView boardView;
    private LinearLayout buttonsLayout;
    private Game game;
    private int colorsCount = 3, boardSize = 5;
    private Paint[] paint;
    private int[] colorsList;

    /*the clickable buttons' size
    * TODO : set their size dynamically*/
    private int buttonHeight = 160;
    private int buttonWidth = 160;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.buttonsLayout = (LinearLayout) findViewById(R.id.buttonsDisplay);
        boardView = (BoardView) findViewById(R.id.boardLayout);

        initColors();
        //setButtons(buttonsLayout);
        startGame();
    }

    public void startGame(){

        setButtons(buttonsLayout);
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

    /**add interactive 'buttons' under the board*/
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
                    //alertDial();
                    //if the game is finished we call the endGameDialog() method
                    if(game.getGameStatus()) endGameDialog();
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

    /**create a dialog window when the game is finished*/
    public void endGameDialog() {

        // custom dialog
        final Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.end_choice_layout);
        dialog.setTitle("Title...");

        // set the custom dialog components - text, image and button
        TextView text = dialog.findViewById(R.id.text);
        text.setText(getString(R.string.you_win));
       /* ImageView image = (ImageView) dialog.findViewById(R.id.image);
        image.setImageResource(R.drawable.ic_launcher);*/

        Button nextButton = dialog.findViewById(R.id.buttonNext);
        Button replayButton = dialog.findViewById(R.id.buttonReplay);

        // if the "next" button is clicked, go to next level
        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nextLevel();
                dialog.dismiss();
            }
        });

        // if the "replay" button is clicked, then reload the game
        replayButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                replayLevel();
                dialog.dismiss();
            }
        });

        dialog.show();
    }

    /**IN PROGRESS*/
    public void alertDial(){

// 1. Instantiate an AlertDialog.Builder with its constructor
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

// 2. Chain together various setter methods to set the dialog characteristics
        builder.setMessage("ehehe")
                .setTitle("T");

// 3. Get the AlertDialog from create()
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    /**reload the board with the same size and the same amount of colors to replay the game*/
    public void replayLevel(){

        this.buttonsLayout.removeAllViews();/*delete the buttons so we can recreate an amount that*
        matches the new amount of colors, otherwise the buttons would add up on the layout */

        startGame();
    }

    /**we got to the next level : more colors and a bigger board*/
    public void nextLevel(){

        this.boardSize += 5;//add 5 more cells horizontally and vertically
        this.colorsCount ++;//add 1 more color
        this.buttonsLayout.removeAllViews();/*delete the buttons so we can recreate an amount that*
        matches the new amount of colors*/

        startGame();
    }
}
