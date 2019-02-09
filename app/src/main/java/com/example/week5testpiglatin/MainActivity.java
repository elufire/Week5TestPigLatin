package com.example.week5testpiglatin;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.example.week5testpiglatin.school.SAT;
import com.example.week5testpiglatin.school.School;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    RecyclerViewAdapter recyclerViewAdapter;
    TextView tvMath;
    TextView tvReading;
    TextView tvWriting;
    ArrayList<School> schoolArrayList;
    ArrayList<SAT> satArrayList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.rvView);
        tvMath = findViewById(R.id.tvSATMath);
        tvReading = findViewById(R.id.tvSATReading);
        tvWriting = findViewById(R.id.tvSATWriting);
        tvWriting.setVisibility(TextView.GONE);
        tvReading.setVisibility(TextView.GONE);
        tvMath.setVisibility(TextView.GONE);
        OkHttpHelper.ascyncOkHttpApi("https://data.cityofnewyork.us/resource/97mf-9njv.json");
        OkHttpHelper.ascyncOkHttpApiSAT("https://data.cityofnewyork.us/resource/734v-jeq5.json");
    }


    @Subscribe(threadMode = ThreadMode.MAIN)
    public void getSchoolEvent(List<School> schoolList){
        System.out.println(schoolList.get(0));
        schoolArrayList = new ArrayList<>(schoolList);
        recyclerViewAdapter = new RecyclerViewAdapter(schoolArrayList);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(recyclerViewAdapter);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void getSATEvent(SAT[] satArray){
        List<SAT> satList = Arrays.asList(satArray);
        satArrayList = new ArrayList<>(satList);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void getStringFromClick(String status){
        tvWriting.setVisibility(TextView.VISIBLE);
        tvReading.setVisibility(TextView.VISIBLE);
        tvMath.setVisibility(TextView.VISIBLE);
        getSatScores(status);

    }

    @Override
    protected void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    protected void onStop() {
        EventBus.getDefault().unregister(this);
        super.onStop();

    }

    public void getSatScores(String dbn){
        for(SAT sat : satArrayList){
            if( sat.getDbn().equals(dbn)){
                tvMath.setText("Math SAT: " + sat.getSatMathAvgScore());
                tvReading.setText("Reading SAT: " +sat.getSatCriticalReadingAvgScore());
                tvWriting.setText("Writing SAT: " + sat.getSatWritingAvgScore());
            }
        }
    }
}
