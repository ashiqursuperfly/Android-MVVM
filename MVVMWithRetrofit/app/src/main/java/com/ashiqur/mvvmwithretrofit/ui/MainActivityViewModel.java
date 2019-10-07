package com.ashiqur.mvvmwithretrofit.ui;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.ashiqur.mvvmwithretrofit.ApiDataRepository;
import com.ashiqur.mvvmwithretrofit.rest_api.DataModel;

public class MainActivityViewModel extends AndroidViewModel {

    private ApiDataRepository apiDataRepository;
    private MutableLiveData<String> tvStatusString;
    private MutableLiveData<DataModel> currentData;

    public MainActivityViewModel(@NonNull Application application) {
        super(application);
        tvStatusString = new MutableLiveData<>();
        apiDataRepository = new ApiDataRepository();
        currentData = apiDataRepository.getCurrentLiveData();
    }

    public MutableLiveData<String> getTvStatusString() {
        return tvStatusString;
    }

    public void setTvStatusString(String tvStatusString) {
        this.tvStatusString.setValue(tvStatusString);
        //setValue should be called from Main Thread, postValue should be called from this
    }

    public MutableLiveData<DataModel> getCurrentData() {
        return currentData;
    }

    public void fetchRestApiData(){
        apiDataRepository.fetchRestApiData();
    }
}
