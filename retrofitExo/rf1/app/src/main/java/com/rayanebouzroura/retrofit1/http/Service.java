package com.rayanebouzroura.retrofit1.http;



import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface Service {

   //call to https://4n6.azurewebsites.net/exos/long/double/1
    @GET("exos/long/double/{number}")
    Call<String> testEndpoint(@Path("number") Long number);



}
