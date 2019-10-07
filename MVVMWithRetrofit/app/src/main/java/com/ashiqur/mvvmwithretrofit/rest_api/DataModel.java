package com.ashiqur.mvvmwithretrofit.rest_api;


/*
SAMPLE RESPONSE:
{"activity":"Have a jam session with your friends","accessibility":0.3,"type":"music","participants":5,"price":0.1,"link":"","key":"2715253"}
 */

import androidx.annotation.NonNull;

public class DataModel {


    private String activity,priceRating,accessiblityRating,participants;
    private boolean isError;

    public DataModel() {
    }

    public DataModel(String activity, String priceRating, String accessiblityRating, String participants) {
        this.activity = activity;
        this.priceRating = priceRating;
        this.accessiblityRating = accessiblityRating;
        this.participants = participants;
    }

    public String getActivity() {
        return activity;
    }

    public void setActivity(String activity) {
        this.activity = activity;
    }

    public String getPriceRating() {
        return priceRating;
    }

    public void setPriceRating(String priceRating) {
        this.priceRating = priceRating;
    }

    public String getAccessiblityRating() {
        return accessiblityRating;
    }

    public void setAccessiblityRating(String accessiblityRating) {
        this.accessiblityRating = accessiblityRating;
    }

    public String getParticipants() {
        return participants;
    }

    public void setParticipants(String participants) {
        this.participants = participants;
    }

    public boolean isError() {
        return isError;
    }

    public void setError(boolean error) {
        isError = error;
    }

    @NonNull
    @Override
    public String toString() {
        return activity+" "+priceRating+" "+accessiblityRating+" "+participants;
    }
}
