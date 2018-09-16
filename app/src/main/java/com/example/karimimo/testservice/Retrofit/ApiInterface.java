package com.example.karimimo.testservice.Retrofit;

import android.content.ClipData;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiInterface {


    @GET("api/scanner/1/loc/send/{lat},{lon}")
    Call<ResponseBody> Send(@Path("lat") double lat,
                            @Path("lon") double lon);
}
