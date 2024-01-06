package com.example.tp1gui.http;

import org.kickmyb.transfer.AddTaskRequest;
import org.kickmyb.transfer.HomeItemResponse;
import org.kickmyb.transfer.SigninRequest;
import org.kickmyb.transfer.SigninResponse;
import org.kickmyb.transfer.SignupRequest;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface Service {

    @POST("/api/id/signin")
    Call<SigninResponse> signin(@Body SigninRequest request);

    @POST("/api/id/signup")
    Call<SigninResponse> signup(@Body SignupRequest request);

    @POST("/api/id/signout")
    Call<Void> signout();

    @POST("/api/add")
    Call<Void> addTask(@Body AddTaskRequest request);

    @GET("/api/home")
    Call<HomeItemResponse> getHomeTasks();

    @GET("/test")
    Call<String> testEndpoint();

}
