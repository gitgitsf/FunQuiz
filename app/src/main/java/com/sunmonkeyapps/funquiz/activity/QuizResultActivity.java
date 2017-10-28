package com.sunmonkeyapps.funquiz.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.sunmonkeyapps.funquiz.R;
import com.sunmonkeyapps.funquiz.app.Constants;

public class QuizResultActivity extends AppCompatActivity implements View.OnClickListener {

    static final String TOTAL_QUESTIONS = "  / 10";
    TextView tvScore;
    Button btnPlayAgain;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_result);


        tvScore = (TextView) findViewById(R.id.tvScore);
        btnPlayAgain = (Button) findViewById(R.id.btnPlayAgain);
        btnPlayAgain.setOnClickListener(this); //Handle button click

        // Retrieve the passed quiz score
        Bundle bundle = getIntent().getExtras();
        int mScore = bundle.getInt(Constants.QUESTION_SCORE);

        String scoreString = getResources().getString(R.string.text_score) + mScore + TOTAL_QUESTIONS;
        tvScore.setText(scoreString);


    }

    @Override
    public void onClick(View v) {
        startActivity(new Intent(this, WelcomeActivity.class));
    }
}
