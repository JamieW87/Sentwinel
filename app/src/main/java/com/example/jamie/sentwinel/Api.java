package com.example.jamie.sentwinel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;


public interface Api {

    String BASE_URL = "https://jamiepythonapp.herokuapp.com/";

    //@GET("Trump/500")
    //@GET(MainActivity.EXTRA_TEXT + "/" + MainActivity.EXTRA_NUMBER)

    @GET("{extraText}/{extraNo}")

    Call<List<Polarity>> getPolarity(@Path("extraText") String extraText, @Path("extraNo") String extraNo);
}