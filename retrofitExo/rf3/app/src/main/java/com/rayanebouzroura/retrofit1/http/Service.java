package com.rayanebouzroura.retrofit1.http;



import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface Service {

   //call to https://4n6.azurewebsites.net/exos/long/double/1
    @GET("exos/long/double/{number}")
    Call<String> testEndpoint(@Path("number") Long number);




    @GET("exos/truc/complexe")
    Call<ComplexResponse> getComplexData(@Query("name") String name);


    @GET("exos/truc/list")
    Call<List<ComplexResponse>> getComplexList();

    //https://4n6.azurewebsites.net/exos/long/list
      @GET("exos/long/list")
      Call<List<Long>> getLongList();


}
