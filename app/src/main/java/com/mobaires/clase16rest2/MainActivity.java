package com.mobaires.clase16rest2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;

import org.json.JSONObject;

import java.io.IOException;
import java.util.List;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public class MainActivity extends AppCompatActivity {

    private EditText mText;

    public static final String MY_KEY = "bb2f7ea4d1451f7170f7a7e1bc372efb";

    @SuppressWarnings("SpellCheckingInspection")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mText = (EditText) findViewById(R.id.text);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://api.openweathermap.org")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        WeatherService service = retrofit.create(WeatherService.class);

        Call<Forecast5> cityForecastCall = service.cityForecast("Buenos Aires", MY_KEY, "metric");

        cityForecastCall.enqueue(new Callback<Forecast5>() {
            @Override
            public void onResponse(Call<Forecast5> call, Response<Forecast5> response) {
                try {
                    mText.setText(
                            response.body() != null ?
                                    response.body().toString() :
                                    response.errorBody().string());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            @Override
            public void onFailure(Call<Forecast5> call, Throwable t) {
            }
        });

    }




    public interface WeatherService {
        @GET("/data/2.5/forecast/city")
        Call<Forecast5> cityForecast (@Query("q") String query,
                                      @Query("APPID") String appId,
                                      @Query("units") String units );
    }

}
