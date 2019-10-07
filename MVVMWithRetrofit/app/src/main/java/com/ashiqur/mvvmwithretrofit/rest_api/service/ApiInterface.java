package com.ashiqur.mvvmwithretrofit.rest_api.service;

import com.google.gson.JsonObject;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface ApiInterface {
    @GET("activity")
    Call<JsonObject> getApiData();
}
