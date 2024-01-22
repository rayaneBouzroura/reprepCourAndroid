package com.example.tp1gui.http;

import org.kickmyb.transfer.AddTaskRequest;
import org.kickmyb.transfer.HomeItemResponse;
import org.kickmyb.transfer.SigninRequest;
import org.kickmyb.transfer.SigninResponse;
import org.kickmyb.transfer.SignupRequest;
import org.kickmyb.transfer.TaskDetailResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

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
    Call<List<HomeItemResponse>> getHomeTasks();

    //api call to "/api/detail/{id}" with id as a parameter
    @GET("/api/detail/{id}")
    Call<TaskDetailResponse> getDetailTask(@Path("id") Long id);

    @GET("/test")
    Call<String> testEndpoint();

}
