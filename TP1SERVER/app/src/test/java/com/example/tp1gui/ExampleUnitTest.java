package com.example.tp1gui;

import org.junit.Test;
import org.kickmyb.transfer.SigninRequest;
import org.kickmyb.transfer.SigninResponse;
import org.kickmyb.transfer.SignupRequest;

import static org.junit.Assert.*;

import android.util.Log;

import com.example.tp1gui.http.RetrofitUtil;
import com.example.tp1gui.http.Service;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Response;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {


    @Test
    public void test_Test() throws IOException {

        Service service = RetrofitUtil.get(true);
        Call<String> call = service.testEndpoint();
        Response<String> response = call.execute();
        String resultat = response.body();
        Log.i("RETROFIT",resultat);

    }

    @Test
    public void SignupTest() throws Exception {
        //true bcs we are running it localy
        Service service = RetrofitUtil.get(true);
        //create signup request
        SignupRequest rq = new SignupRequest();
        rq.username = "test";
        rq.password = "test";
        Call<SigninResponse> call = service.signup(rq);
        Response<SigninResponse> response = call.execute();
        if (response.isSuccessful()) {
            SigninResponse resultat = response.body();
            String a;
        }
    }
    //test signin
    @Test
    public void SigninTest() throws Exception {
        //true bcs we are running it localy
        Service service = RetrofitUtil.get(true);
        //create signup request
        SigninRequest rq = new SigninRequest();
        rq.username = "test";
        rq.password = "test";
        Call<SigninResponse> call = service.signin(rq);
        //execute the call multiple times to test the cookie
        // 10 times
        for (int i = 0; i < 10; i++) {
            Response<SigninResponse> response = call.execute();
            if (response.isSuccessful()) {
                SigninResponse resultat = response.body();
                String a;
            }
        }
    }



}