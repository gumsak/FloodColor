package com.example.gmsk.floodcolor;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private BoardView boardView;
    private LinearLayout buttonsLayout;
    private Game game;
    private int colorsCount = 3, boardSize = 5;
    private Paint[] paint;
    private int[] colorsList;

    private int screenX;// the screen's width

    /*the clickable buttons' size
    * TODO : set their size dynamically*/
    private int buttonSize;

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

        setButtonSize();

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
            params.setMargins(5, 0, 5, 0);
            params.height = buttonSize;
            params.width = buttonSize;
            aButton.setLayoutParams(params);

            aButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    //game.setSelectedColor(getClickedColor(aButton));
                    game.checkNeighbor(getClickedColor(aButton));
                    boardView.setGame(game);

                    //check if the game is finished
                    checkEndGame();
                }
            });
            //add the button to the layout
            layout.addView(aButton);
        }
    }

    /**Check if the game has ended : if the player won or if he lost*/
    public void checkEndGame(){

        /*if the player got all the cells*/
        if(game.getGameStatus())
            //display the "WIN" dialog
            endGameDialog(1);

        /*else if the player lost ......*/

    }

    /**create a dialog window when the game is finished
     * @param result : this is the game's result, 1 if the player won, 0 if he lost*/
    public void endGameDialog(int result) {

        // custom dialog
        final Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);////////////////////
        dialog.setContentView(R.layout.end_choice_layout);
        dialog.setCancelable(false);//can't dismiss the dialog by clicking outside

        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));//////////

        TextView text = dialog.findViewById(R.id.text);
        Button replayButton = dialog.findViewById(R.id.buttonReplay);
        Button nextButton = dialog.findViewById(R.id.buttonNext);

        // if the "replay" button is clicked, then reload the game
        replayButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                replayLevel();
                dialog.dismiss();
            }
        });

        /*In case of a win load the win texts in the dialog*/
        if(result == 1){

            text.setText(getString(R.string.you_win));

            // if the "next" button is clicked, go to next level
            nextButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    nextLevel();
                    dialog.dismiss();
                }
            });
        }
        /*In case we lose, set the appropriate dialog title (lose),
         keep the "replay" button and hide the "next" one*/
        else if (result == 0){

            text.setText(getString(R.string.you_lose));
            nextButton.setVisibility(View.GONE);
        }

        dialog.show();
    }

    /**reload the board with the same size and the same amount of colors to replay the game*/
    public void replayLevel(){

        this.buttonsLayout.removeAllViews();/*delete the buttons so we can recreate an amount that*
        matches the new amount of colors, otherwise the buttons would add up on the layout */

        //start a new game
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

    /**When the user clicks a button we retrieve that button's color */
    public int getClickedColor(Button button){

        /*get the button's color*/
        ColorDrawable buttonColor = (ColorDrawable) button.getBackground();
        //int colorId = buttonColor.getColor();
        //Log.i("result",""+buttonColor);

        /*return it as an int*/
        return buttonColor.getColor();
    }

    /**get the screen's width*/
    public int getScreenSize(){
        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        return size.x;
    }

    /**
     * generate a random int between 0 and n-1
     * */
    public int getRand(int n){
        Random rand = new Random();
        return rand.nextInt(n);
    }

    /**set the buttons' size according to screen's size*/
    public void setButtonSize(){
        int a;
        a = getScreenSize();
        this.buttonSize =  (a / this.colorsCount) - 30;
    }

    /**set the dialog window's size according to the screen's size*/
    public void setDialogSize(){

    }
}
