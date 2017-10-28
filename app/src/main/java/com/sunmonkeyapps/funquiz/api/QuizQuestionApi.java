package com.sunmonkeyapps.funquiz.api;

import com.sunmonkeyapps.funquiz.model.QuizQuestion;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by may on 10/27/17.
 */

public interface QuizQuestionApi {
    String BASE_URL = "https://opentdb.com/";


    @GET("api.php")
    Call<QuizQuestion> getQuizQuestions(@Query("amount") Integer amount,
                                        @Query("difficulty") String questionLevel,
                                        @Query("type") String questionType);

}
