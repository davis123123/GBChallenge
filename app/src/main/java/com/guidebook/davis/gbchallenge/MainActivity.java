package com.guidebook.davis.gbchallenge;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;

public class MainActivity extends AppCompatActivity {
    MainAdapter mainAdapter;
    RecyclerView recyclerView;
    List<UpcomingGuides.Data> dataList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dataList = new ArrayList<>();
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        getData();

    }

    //for getting data from guidebook server
    final String get_url = "https://guidebook.com/service/v2/";

    public interface Interface {
        @GET("upcomingGuides")
        Call<UpcomingGuides> getData();
    }

    public void getData(){

        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        httpClient.addInterceptor(logging);

        Retrofit retrofit = new Retrofit.Builder()
                .client(httpClient.build())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(get_url)
                .build();
        Interface service = retrofit.create(Interface.class);

        Call<UpcomingGuides> call = service.getData();
        call.enqueue(new Callback<UpcomingGuides>() {
            @Override
            public void onResponse(Call<UpcomingGuides> call, Response<UpcomingGuides> response) {
                if(response.isSuccessful()) {
                    Log.i("STATE","Retrofit POST Success");
                    //serverResponse = response.body().toString();
                    UpcomingGuides upcomingGuides = response.body();
                    dataList = upcomingGuides.data;
                    Log.i("SERVER",upcomingGuides.total);
                    //parseJS(serverResponse);
                    mainAdapter = new MainAdapter(dataList, getApplicationContext());
                    recyclerView.setAdapter(mainAdapter);
                }
                else {
                    Log.i("Retrofit Error Code:", String.valueOf(response.code()));
                    Log.i("Retrofit Error Body", response.errorBody().toString());
                }
            }

            @Override
            public void onFailure(Call<UpcomingGuides> call, Throwable t) {
                Log.i("STATE","Retrofit Failure");
            }
        });
    }
}
