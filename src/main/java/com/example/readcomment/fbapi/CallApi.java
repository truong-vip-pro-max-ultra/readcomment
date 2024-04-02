package com.example.readcomment.fbapi;

import com.example.readcomment.enumtype.RequestMethod;
import com.example.readcomment.utils.ResponseUtils;

public class CallApi {
    private volatile String response;
    public String get(String url,String cookies){
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                response = ResponseUtils.respGetRequest(url,cookies);
            }
        });
        thread.start();
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return response;
    }
    public String post(String url,String cookies,String data,boolean userAgent){
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                response = ResponseUtils.respPostRequest(url,cookies,data,userAgent);
            }
        });
        thread.start();
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return response;
    }
    public String redirect(String url,String cookies){
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                response = ResponseUtils.respRedirect(url,cookies);
            }
        });
        thread.start();
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return response;
    }

    public static String getResponse(RequestMethod requestMethod, String url, String cookies, String data, boolean userAgent){
        if(requestMethod.equals(RequestMethod.GET))
            return new CallApi().get(url,cookies);
        return new CallApi().post(url,cookies,data,userAgent);
    }
    public static String getRedirect(String url,String cookies){
        return new CallApi().redirect(url,cookies);
    }

}
