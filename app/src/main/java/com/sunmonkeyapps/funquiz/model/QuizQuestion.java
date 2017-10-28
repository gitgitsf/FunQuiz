package com.sunmonkeyapps.funquiz.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by may on 10/27/17.
 */

    public class QuizQuestion {

        @SerializedName("response_code")
        @Expose
        private int responseCode;
        @SerializedName("results")
        @Expose
        private List<Results> results = null;

        public int getResponseCode() {
            return responseCode;
        }

        public void setResponseCode(int responseCode) {
            this.responseCode = responseCode;
        }

        public List<Results> getResults() {
            return results;
        }

        public void setResults(List<Results> results) {
            this.results = results;
        }


}
