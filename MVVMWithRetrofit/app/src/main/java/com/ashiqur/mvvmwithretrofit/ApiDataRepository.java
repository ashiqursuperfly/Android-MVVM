package com.ashiqur.mvvmwithretrofit;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import com.ashiqur.mvvmwithretrofit.rest_api.ApiClient;
import com.ashiqur.mvvmwithretrofit.rest_api.DataModel;
import com.ashiqur.mvvmwithretrofit.rest_api.service.ApiInterface;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ApiDataRepository {

    private static final String TAG = "ApiDataRepository";
    private MutableLiveData<DataModel> currentLiveData;
    private DataModel currentData;

    public ApiDataRepository() {
        currentData = new DataModel();
        currentLiveData = new MutableLiveData<>();
        fetchRestApiData();
    }

    public void fetchRestApiData() {

        //NOTE: we dont need to fetch data on AsyncTask since retrofit call.enqueue() does the job asynchronously

        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
        Call<JsonObject> call = apiInterface.getApiData();

        call.enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(@NonNull Call<JsonObject> call,@NonNull Response<JsonObject> response) {
                if (response.isSuccessful()) {

                    //Log.wtf(TAG,response.body().getAsJsonPrimitive("activity").getAsString());

                    assert response.body() != null;
                    parseJsonData(response.body());


                }
            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {
                //TODO:
                currentData.setError(true);
            }
        });
    }

    private void parseJsonData(JsonObject mapObj) {
        currentData.setActivity(Objects.requireNonNull(mapObj.get("activity")).getAsString());
        currentData.setAccessiblityRating(Objects.requireNonNull(mapObj.get("accessibility")).getAsString());
        currentData.setPriceRating(Objects.requireNonNull(mapObj.get("price")).getAsString());
        currentData.setParticipants(Objects.requireNonNull(mapObj.get("participants")).getAsString());
        currentData.setError(false);
        currentLiveData.setValue(currentData);
    }

    public MutableLiveData<DataModel> getCurrentLiveData() {
        return currentLiveData;
    }
}
