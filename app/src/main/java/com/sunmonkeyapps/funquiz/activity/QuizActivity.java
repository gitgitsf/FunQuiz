package com.sunmonkeyapps.funquiz.activity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.sunmonkeyapps.funquiz.R;
import com.sunmonkeyapps.funquiz.api.QuizQuestionApi;
import com.sunmonkeyapps.funquiz.app.Constants;
import com.sunmonkeyapps.funquiz.model.QuizQuestion;
import com.sunmonkeyapps.funquiz.model.Results;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class QuizActivity extends AppCompatActivity {

    private static final String TAG = "QuizActivity";
    private static final String QUESTION_OF = " of ";

    List<Results> mQuestionList;

    int mIndex = 0;
    int mCorrectAnswerCount = 0;

    TextView tvCategory;
    CardView cvQuestion;
    TextView tvQuestion;
    TextView tvQuestionNbr;
    TextView tvTotalQuestio;
    Button btnFalse;
    Button btnTrue;

    Results mQuestion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        setupView();

        // Retrieve quiz question from https://opentdb.com/api.php
        getQuizQuestions();

    }


    private void getQuizQuestions() {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(QuizQuestionApi.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        // Create an instance of our GitHub API interface.
        final QuizQuestionApi mQuizQuestionApi = retrofit.create(QuizQuestionApi.class);

        Call<QuizQuestion> call = mQuizQuestionApi.getQuizQuestions(Constants.QUESTION_AMOUNT,
                Constants.QUESTION_LEVEL, Constants.QUESTION_TYPE);


        call.enqueue(new Callback<QuizQuestion>() {
            @Override
            public void onResponse(Call<QuizQuestion> call, Response<QuizQuestion> response) {

                Log.d(TAG, "onResponse: size= " + response.body().getResults().size());
                mQuestionList = response.body().getResults();
                showQuestions();

            }

            @Override
            public void onFailure(Call<QuizQuestion> call, Throwable t) {

                Log.e(TAG, "onFailure: " + t.getMessage().toString());
            }
        });

    }

    private void setupView() {
        tvCategory = (TextView) findViewById(R.id.tvCategory);

        cvQuestion = (CardView) findViewById(R.id.cvQuestion);
        tvQuestion = (TextView) findViewById(R.id.tvQuestion);
        tvQuestionNbr = (TextView) findViewById(R.id.tvQuestionNbr);
        btnFalse = (Button) findViewById(R.id.btnFalse);
        btnTrue = (Button) findViewById(R.id.btnTrue);

    }

    private void showQuestions() {

        mQuestion = mQuestionList.get(mIndex);
        tvCategory.setText(mQuestion.getCategory());

        if (Build.VERSION.SDK_INT >= 24) {
            tvQuestion.setText(Html.fromHtml(mQuestion.getQuestion(), Html.TO_HTML_PARAGRAPH_LINES_CONSECUTIVE));// for 24 api and more
        } else {
            tvQuestion.setText(Html.fromHtml(mQuestion.getQuestion())); // or for older api
        }

        tvQuestionNbr.setText(mIndex + 1 + QUESTION_OF + mQuestionList.size());

    }

    public void showNextQuestion(View view) {

        Log.d(TAG, "showNextQuestion: ");

        switch (view.getId()) {
            case R.id.btnFalse:

                if (mQuestion.getCorrectAnswer().equals(Constants.QUESTION_FALSE)) {
                    mCorrectAnswerCount++;
                }
                break;

            case R.id.btnTrue:
                if (mQuestion.getCorrectAnswer().equals(Constants.QUESTION_TRUE)) {
                    mCorrectAnswerCount++;
                }
                break;

            default:
                throw new RuntimeException("Unknow button ID");
        }

        mIndex++;
        if (mIndex >= mQuestionList.size()) {
            showQuizResultActivity();
        } else {
            showQuestions();
        }
    }

    private void showQuizResultActivity() {

        Intent intent = new Intent(this, QuizResultActivity.class);
        intent.putExtra(Constants.QUESTION_SCORE, mCorrectAnswerCount);

        startActivity(intent);
    }

}
