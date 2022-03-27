package com.example.connect3;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.gridlayout.widget.GridLayout;

import androidx.appcompat.app.AppCompatActivity;

import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity  {
    // 0:yellow , 1: red , 2: empty.

 int activePlayer = 0;
 int[] gameState ={2,2,2,2,2,2,2,2,2};
 int[][] winningPositions = {{1,2,3},{4,5,6},{7,8,9},{1,4,7},{2,5,8},{3,6,9},{1,5,9},{3,5,7},};
 String winner = "";
 boolean gameActive = true;

    public void dropIn(View view) throws IllegalStateException,ArrayIndexOutOfBoundsException, InvocationTargetException {
        ImageView counter = (ImageView) view;

        int tappedCounter = Integer.parseInt(counter.getTag().toString());



     if(gameState[tappedCounter]==2 && gameActive)
     {
        counter.setTranslationY(-1500);
        gameState[tappedCounter] = activePlayer;

        if (activePlayer == 0) {
            counter.setImageResource(R.drawable.yellow);
            activePlayer = 1;
        } else {
            counter.setImageResource(R.drawable.red);
            activePlayer = 0;
        }
        counter.animate().translationYBy(1500).rotation(3600).setDuration(900);

        for (int[] winningPosition : winningPositions) {
            if (gameState[winningPosition[0]] == gameState[winningPosition[1]] && gameState[winningPosition[1]] == gameState[winningPosition[2]] && gameState[winningPosition[0]] != 2) {
               gameActive = false;
                if (activePlayer == 1) {
                    winner = "Yellow";
                } else {
                    winner = "Red";
                }

                Button playAgainButton = (Button) findViewById(R.id.playAgainButton);
                TextView winnerTextView = (TextView) findViewById(R.id.winnerTextView);
                winnerTextView.setText(winner+" has won!");
                playAgainButton.setVisibility(View.VISIBLE);
                winnerTextView.setVisibility(View.VISIBLE);

            }
        }
      }
    }
  public void playAgain(View view){
      Button playAgainButton = (Button) findViewById(R.id.playAgainButton);
      TextView winnerTextView = (TextView) findViewById(R.id.winnerTextView);

      playAgainButton.setVisibility(View.INVISIBLE);
      winnerTextView.setVisibility(View.INVISIBLE);

      GridLayout gridLayout = (GridLayout) findViewById(R.id.gridLayout);
      for (int i =0;i<gridLayout.getChildCount();i++){
          ImageView counter = (ImageView) gridLayout.getChildAt(i);
          counter.setImageDrawable(null);
      }
       activePlayer = 0;

      Arrays.fill(gameState, 2);

       gameActive = true;
  }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}