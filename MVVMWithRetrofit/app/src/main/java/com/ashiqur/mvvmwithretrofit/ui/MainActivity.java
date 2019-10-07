package com.ashiqur.mvvmwithretrofit.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.ashiqur.mvvmwithretrofit.R;
import com.ashiqur.mvvmwithretrofit.rest_api.DataModel;
import com.ashiqur.mvvmwithretrofit.utils.ViewModelUtils;

public class MainActivity extends AppCompatActivity {

    private TextView tvStatus;
    private Button btnSuggest;
    private MainActivityViewModel mainActivityViewModel;

    void initViews() {
        tvStatus = findViewById(R.id.tv_status);
        btnSuggest = findViewById(R.id.btn_suggest_boredom_activity);
        btnSuggest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mainActivityViewModel.fetchRestApiData();
            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();


        mainActivityViewModel = (MainActivityViewModel) ViewModelUtils.GetViewModel(MainActivity.this, MainActivityViewModel.class);


        mainActivityViewModel.getTvStatusString().observe(MainActivity.this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                tvStatus.setText(s);
            }
        });

        mainActivityViewModel.getCurrentData().observe(MainActivity.this, new Observer<DataModel>() {
            @Override
            public void onChanged(DataModel dataModel) {
                tvStatus.setText(dataModel.toString());
            }
        });
        ///mainActivityViewModel.setTvStatusString("GOOD");

    }

}
