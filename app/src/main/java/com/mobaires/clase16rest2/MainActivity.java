package com.mobaires.clase16rest2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;

import org.json.JSONObject;

import java.io.IOException;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public class MainActivity extends AppCompatActivity {

    private EditText mText;
    private EditText mQueryText;
    private Button mSearchButton;
    private ProgressBar mProgressBar;
    private WeatherService mWeatherService;

    public static final String MY_KEY = "bb2f7ea4d1451f7170f7a7e1bc372efb";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mText = (EditText) findViewById(R.id.text);
        mQueryText = (EditText) findViewById(R.id.queryText);
        mProgressBar = (ProgressBar) findViewById(R.id.progressBar);
        mSearchButton = (Button) findViewById(R.id.search);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://api.openweathermap.org")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        mWeatherService = retrofit.create(WeatherService.class);




    }

    public void doSearch(View v) {
        Call<Forecast5> cityForecastCall =
                mWeatherService.cityForecast(
                        mQueryText.getText().toString(),
                        MY_KEY, "metric");
        mProgressBar.setVisibility(View.VISIBLE);
        mSearchButton.setEnabled(false);
        cityForecastCall.enqueue(new Callback<Forecast5>() {
            @Override
            public void onResponse(Call<Forecast5> call, Response<Forecast5> response) {
                if (response.body()!=null) {
                    mText.setText(response.body().toString());
                } else {
                    try {
                        mText.setText(response.errorBody().string());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                mProgressBar.setVisibility(View.GONE);
                mSearchButton.setEnabled(true);
            }
            @Override
            public void onFailure(Call<Forecast5> call, Throwable t) {
                t.printStackTrace();
                mProgressBar.setVisibility(View.GONE);
                mSearchButton.setEnabled(true);
            }
        });
    }

    // Retrofit turns your HTTP API into a Java interface
    public interface WeatherService {


        @GET("/data/2.5/forecast/city")
        Call<Forecast5> cityForecast (@Query("q") String query,
                                         @Query("APPID") String appId,
                                        @Query("units") String units
        );


    }

}
