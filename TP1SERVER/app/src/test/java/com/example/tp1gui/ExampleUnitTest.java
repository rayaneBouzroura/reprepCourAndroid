package com.example.tp1gui;

import org.junit.Test;

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
    public void addition_isCorrect() {
        assertEquals(4, 2 + 2);
    }

    @Test
    public void test_Test() throws IOException {

        Service service = RetrofitUtil.get();
        Call<String> call = service.testEndpoint();
        Response<String> response = call.execute();
        String resultat = response.body();
        Log.i("RETROFIT",resultat);

    }



}