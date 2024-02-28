package com.rayanebouzroura.retrofit1;

import android.content.Context;
import android.util.Log;

import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;

import com.rayanebouzroura.retrofit1.http.RetrofitUtil;
import com.rayanebouzroura.retrofit1.http.Service;

import java.io.IOException;

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
        assertEquals("com.rayanebouzroura.retrofit1", appContext.getPackageName());
    }

    @Test
    public void test_Test() throws IOException {

        Service service = RetrofitUtil.get();
        Call<String> call = service.testEndpoint(1L);
        Response<String> response = call.execute();
        String resultat = response.body();
        Log.i("RETROFIT",resultat);

    }
}