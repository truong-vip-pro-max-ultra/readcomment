package com.example.readcomment.fbapi;


import com.example.readcomment.enumtype.RequestMethod;

public class Authentication {
    public static boolean checkInvite(){
        String url = "";
        String resp = CallApi.getResponse(RequestMethod.GET,url,null,null,false);
        return resp.replace("\n","").equals("true");
    }
}
