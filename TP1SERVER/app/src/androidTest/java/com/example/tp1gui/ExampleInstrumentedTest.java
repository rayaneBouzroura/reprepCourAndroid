package com.example.tp1gui;

import android.content.Context;
import android.util.Log;

import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.kickmyb.transfer.AddTaskRequest;
import org.kickmyb.transfer.HomeItemResponse;
import org.kickmyb.transfer.SigninRequest;
import org.kickmyb.transfer.SigninResponse;
import org.kickmyb.transfer.SignupRequest;
import org.kickmyb.transfer.TaskDetailResponse;

import static org.junit.Assert.*;

import com.example.tp1gui.http.RetrofitUtil;
import com.example.tp1gui.http.Service;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import retrofit2.Call;
import retrofit2.Response;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {
    @Test
    public void useAppContext() {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
        assertEquals("com.example.tp1gui", appContext.getPackageName());
    }



    @Test
    public void test_Test() throws IOException {

        Service service = RetrofitUtil.get(true);
        Call<String> call = service.testEndpoint();
        Response<String> response = call.execute();
        String resultat = response.body();
        Log.i("RETROFIT",resultat);

    }

    @Test
    public void SigninTest() throws Exception {

        Service service = RetrofitUtil.get(true);
        //create signup request
        SigninRequest rq = new SigninRequest();
        rq.username = "test";
        rq.password = "test";
        //call the service
        Call<SigninResponse> call = service.signin(rq);
        Response<SigninResponse> response = call.execute();
        //check if the response is successful
        assertTrue(response.isSuccessful());

    }


    @Test
    public void SignupTest() throws IOException {
        Service service = RetrofitUtil.get(true);
        //create signup request
        SignupRequest rq = new SignupRequest();
        rq.username = "test";
        rq.password = "test";
        //call the service
        Call<SigninResponse> call = service.signup(rq);
        Response<SigninResponse> response = call.execute();
        //check if the response is successful
        assertTrue(response.isSuccessful());
    }

    @Test
    public void createTacheTest() throws Exception {

        Service service = RetrofitUtil.get(true);
        //create signup request
        SigninRequest rq = new SigninRequest();
        rq.username = "test";
        rq.password = "test";
        //call the service
        Call<SigninResponse> call = service.signin(rq);
        Response<SigninResponse> response = call.execute();
        //check if the response is successful
        //assertTrue(response.isSuccessful());
        //create add task request
        AddTaskRequest addTaskRequest = new AddTaskRequest();
        addTaskRequest.name = "testooos";
        //create deadline a year from now
        addTaskRequest.deadline = new Date(System.currentTimeMillis() + 365L * 24L * 3600L * 1000L);
        //call the service
        Call<Void> call2 = service.addTask(addTaskRequest);
        Response<Void> response2 = call2.execute();
        //check if the response is successful
        int a = 2;
        //we now retrieve the list of tasks
        Call<List<HomeItemResponse>> call3 = service.getHomeTasks();
        Response<List<HomeItemResponse>> response3 = call3.execute();
        //check if the response is successful
        assertTrue(response3.isSuccessful());


    }
    //retrieve tache test
    @Test
    public void retrieveHomeItemResponse() throws Exception {

        Service service = RetrofitUtil.get(true);
        //create signup request
        SigninRequest rq = new SigninRequest();
        rq.username = "testerino";
        rq.password = "test";
        //call the service
        Call<SigninResponse> call = service.signin(rq);
        Response<SigninResponse> response = call.execute();
        //check if the response is successful
        //assertTrue(response.isSuccessful());

        //we now retrieve the list of tasks
        Call<List<HomeItemResponse>> call3 = service.getHomeTasks();
        Response<List<HomeItemResponse>> response3 = call3.execute();
        //check if the response is successful
        assertTrue(response3.isSuccessful());
        //we now retrieve the list of tasks
        HomeItemResponse objToGetMoreDetailsOn = (HomeItemResponse) response3.body().get(0);
        String a = "";

        }



        //test to get detail task from homeitem response list
    @Test
    public void retrieveDetailTask() throws Exception {

        Service service = RetrofitUtil.get(true);
        //create signup request
        SigninRequest rq = new SigninRequest();
        rq.username = "test";
        rq.password = "test";
        //call the service
        Call<SigninResponse> call = service.signin(rq);
        Response<SigninResponse> response = call.execute();


        //we now retrieve the list of tasks
        Call<List<HomeItemResponse>> call3 = service.getHomeTasks();
        Response<List<HomeItemResponse>> response3 = call3.execute();
        //we now retrieve the list of tasks
        String a = "";

        //we now retrieve the task detail
        Call<TaskDetailResponse> call4 = service.getDetailTask(response3.body().get(0).id);
        Response<TaskDetailResponse> response4 = call4.execute();
        //check if the response is successful
        assertTrue(response4.isSuccessful());
        //we now retrieve the list of tasks
        String b = "";

    }
}