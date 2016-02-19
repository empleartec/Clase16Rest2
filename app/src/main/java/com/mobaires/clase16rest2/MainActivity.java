package com.mobaires.clase16rest2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;

import org.json.JSONObject;

import java.io.IOException;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public class MainActivity extends AppCompatActivity {

    private EditText mText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mText = (EditText) findViewById(R.id.text);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://api.openweathermap.org")
                .build();
        WeatherService service = retrofit.create(WeatherService.class);


        Call<ResponseBody> cityForecastCall = service.cityForecast("Buenos Aires");


        cityForecastCall.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {
                    mText.setText(
                            response.body()!=null?
                                    response.body().string():
                                    response.errorBody().string());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

            }
        });

    }



    // Retrofit turns your HTTP API into a Java interface
    public interface WeatherService {
        @GET("/data/2.5/forecast/city")
        Call<ResponseBody> cityForecast (@Query("q") String query);
    }

}
