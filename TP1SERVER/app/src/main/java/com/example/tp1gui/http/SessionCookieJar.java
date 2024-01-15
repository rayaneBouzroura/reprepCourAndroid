package com.example.tp1gui.http;

import android.util.Log;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import okhttp3.Cookie;
import okhttp3.CookieJar;
import okhttp3.HttpUrl;

public class SessionCookieJar implements CookieJar {

    private List<Cookie> cookies;

    @Override
    public void saveFromResponse(HttpUrl url, List<Cookie> cookies) {

        //if the url contains api/id then we save the cookies
        if (url.toString().contains("api/id")){
            Log.d("SessionCookieJar","Saving cookies for url :"+url.toString());
            this.cookies = new ArrayList<>(cookies);
            Log.d("SessionCookieJar", "Saving cookies from signin/signup: " + cookies);
        }

    }

    /**
     * return the cookies
     * @param url
     * @return the cookies
     */
    @Override
    public List<Cookie> loadForRequest(HttpUrl url) {

        //if the url contains api/id then we return the cookies
        if (cookies != null && !cookies.isEmpty()) {
            //loop through all cookies
            for (Cookie cookie : cookies){
                Log.d("SessionCookieJar","Loading cookie :"+cookie.toString());
            }
        // TODO verifier qu'il n'est pas expire (stream through la liste des cookies et return une liste filtrer
            return cookies;
        }
        return Collections.emptyList();
    }
}