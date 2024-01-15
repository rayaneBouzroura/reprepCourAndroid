package com.example.tp1gui.http;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class RetrofitUtil {
    private static String ServerUrl = "https://kmb-server-tp.onrender.com/";
    //we do not utiliser localhost (since c le localhost de l'emulateur)
    //mais plutot l'adresse ip de la machine hote 10.0.2.2
    private static String localUrl = "http://10.0.2.2:8080/";

    private static Service instance;

    /**
     * get the service
     * @param local if true, use local url, else use server url
     * @return  the service
     */
    public static Service get(boolean local) {
        //if instance null create
        if (instance == null){
            //log the creation of the service
            Log.d("RetrofitUtil", "Creating service with local = " + local);
            String url = local ? localUrl : ServerUrl;
            //create gson with date format that has no timezones
            Gson gson = new GsonBuilder()
                    .setDateFormat("yyyy-MM-dd'T'HH:mm:ss")
                    .create();
            //create the gson converter factory with the previous gson object
            GsonConverterFactory gsonConverterFactory = GsonConverterFactory.create(gson);
            Retrofit retrofit = new Retrofit.Builder()
                    .addConverterFactory(ScalarsConverterFactory.create())
                    .addConverterFactory(gsonConverterFactory)
                    .client(client())
                    .baseUrl(url)
                    .build();

            instance = retrofit.create(Service.class);
            return instance;
        }
        //else return instance
        else{
            return instance;
        }

    }

    //create static okhttp client
    public static OkHttpClient client() {


         //create login interceptor
         HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
         interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
         //create cookie jar
        SessionCookieJar cookieJar = new SessionCookieJar();
        //create okhttp client w interceptor and cookie jar
        OkHttpClient client = new OkHttpClient.Builder()
                .cookieJar(cookieJar)
                .addInterceptor(interceptor)
                .build();
        return client;
    }
}
