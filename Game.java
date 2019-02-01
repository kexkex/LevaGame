package com.example.levagame;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class Game extends AppCompatActivity implements View.OnClickListener {

    TextView operandA;
    TextView operandB;
    TextView currentTextScore;
    TextView currentTextLevel;
    Button buttonChoise1;
    Button buttonChoise2;
    Button buttonChoise3;
    int currentLevel = 1;
    int currentScore = 0;
    int correctAnswer;
    Random randInt = new Random();



    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        operandA = findViewById(R.id.textViewA);
        operandB = findViewById(R.id.textViewB);
        currentTextScore = findViewById(R.id.textViewScore);
        currentTextLevel = findViewById(R.id.textViewLevel);
        buttonChoise1 = findViewById(R.id.buttonChoise1);
        buttonChoise2 = findViewById(R.id.buttonChoise2);
        buttonChoise3 = findViewById(R.id.buttonChoise3);

        buttonChoise1.setOnClickListener(this);
        buttonChoise2.setOnClickListener(this);
        buttonChoise3.setOnClickListener(this);

        currentTextLevel.setText("Your Level is "+currentLevel);
        currentTextScore.setText("Your Score is "+currentScore);
        setQuestion();
    }

    @Override
    public void onClick(View v) {
        int answerGiven = 0;
        switch (v.getId()) {
            case R.id.buttonChoise1:
                answerGiven=Integer.parseInt(""+buttonChoise1.getText());
                break;
            case R.id.buttonChoise2:
                answerGiven=Integer.parseInt(""+buttonChoise2.getText());
                break;
            case R.id.buttonChoise3:
                answerGiven=Integer.parseInt(""+buttonChoise3.getText());
                break;
        }
        setNewLevelNScore(answerGiven);
        setQuestion();
    }

    void setQuestion ()
    {
        int numberRange = currentLevel * 2;
        int intOperA = randInt.nextInt(numberRange)+currentLevel*2;

        int intOperB = randInt.nextInt(numberRange)+currentLevel*2;

        operandA.setText("" + intOperA);
        operandB.setText("" + intOperB);
        correctAnswer = intOperA * intOperB;
        int wrongAnswer1 = correctAnswer + randInt.nextInt(currentLevel) + 1;
        int wrongAnswer2 = correctAnswer - randInt.nextInt(currentLevel) - 1;
        int switchCase = randInt.nextInt(3);
        switch (switchCase) {
            case 0:
                buttonChoise1.setText("" + correctAnswer);
                buttonChoise2.setText("" + wrongAnswer1);
                buttonChoise3.setText("" + wrongAnswer2);
                break;
            case 1:
                buttonChoise1.setText("" + wrongAnswer1);
                buttonChoise2.setText("" + correctAnswer);
                buttonChoise3.setText("" + wrongAnswer2);
                break;
            case 2:
                buttonChoise1.setText("" + wrongAnswer2);
                buttonChoise2.setText("" + wrongAnswer1);
                buttonChoise3.setText("" + correctAnswer);
                break;
        }
    }

    void setNewLevelNScore (int answerGiven)
    {
        if (isCorrect(answerGiven))
            {
            currentScore=currentScore+currentLevel;
            currentLevel++;
            } else
                {
                    currentScore=0;
                    currentLevel=1;
                }
        currentTextLevel.setText("Your Level is "+currentLevel);
        currentTextScore.setText("Your Score is "+currentScore);

    }

    boolean isCorrect(int answerGiven)
    {
        boolean itsCorrectOrNot;
        if (answerGiven==correctAnswer) {
            Toast.makeText(getApplicationContext(),"YUHOO!!!",Toast.LENGTH_LONG).show();
            itsCorrectOrNot = true;
        } else {
            Toast.makeText(getApplicationContext(),"ITS WRONG, MTHRFUKR!!!",Toast.LENGTH_LONG).show();
            itsCorrectOrNot = false;
        }
        return itsCorrectOrNot;
    }


}
