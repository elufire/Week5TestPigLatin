package com.example.week5testpiglatin;

import android.util.Log;

import com.example.week5testpiglatin.school.SAT;
import com.example.week5testpiglatin.school.School;
import com.google.gson.Gson;

import org.greenrobot.eventbus.EventBus;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class OkHttpHelper {

    public OkHttpHelper() {
    }

    public static void ascyncOkHttpApi(String url){
        OkHttpClient okHttpClient = new OkHttpClient();

        Request request = new Request.Builder()
                .url(url)
                .build();

        okHttpClient.newCall(request).enqueue(new Callback() {
            String jsonResonse;
            @Override
            public void onFailure(Call call, IOException e) {
                Log.e("TAG", "onFailure: ");
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                jsonResonse = response.body().string();

                    School[] schoolArray = new Gson().fromJson(jsonResonse, School[].class);
                    List<School> schoolList = Arrays.asList(schoolArray);
                    EventBus.getDefault().post(schoolList);
                    Log.d("TAG", "onResponse: " + schoolArray[1].getAttendanceRate());


            }
        });
    }


    public static void ascyncOkHttpApiSAT(String url){
        OkHttpClient okHttpClient = new OkHttpClient();

        Request request = new Request.Builder()
                .url(url)
                .build();

        okHttpClient.newCall(request).enqueue(new Callback() {
            String jsonResonse;
            @Override
            public void onFailure(Call call, IOException e) {
                Log.e("TAG", "onFailure: ");
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                jsonResonse = response.body().string();

                SAT[] satArray = new Gson().fromJson(jsonResonse, SAT[].class);
               // List<SAT> satList = Arrays.asList(satArray);
                EventBus.getDefault().post(satArray);


            }
        });
    }

}
