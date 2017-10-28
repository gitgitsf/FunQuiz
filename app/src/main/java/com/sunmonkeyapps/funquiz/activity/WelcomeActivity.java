package com.sunmonkeyapps.funquiz.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.sunmonkeyapps.funquiz.R;

public class WelcomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);


    }

    public void beginMyQuiz(View view) {

        startActivity(new Intent(this, QuizActivity.class));
    }
}
