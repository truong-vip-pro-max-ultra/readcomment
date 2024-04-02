package com.example.readcomment.utils;


import com.example.readcomment.enumtype.RequestMethod;
import com.example.readcomment.fbapi.CallApi;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

public class GetUtils {
    public static String getUIDFromCookies(String cookies){
        String uid = CutString.cut(cookies,"c_user=",true);
        uid = CutString.cut(uid,";",false);
        return uid;
    }

    public static String getIPAddress(){
        try {
            String url = "https://whoer.net/";
            String resp = CallApi.getResponse(RequestMethod.GET,url,null,null,false);
            String result = CutString.cut(resp,"<strong data-clipboard-target=\".your-ip\" class=\"your-ip\">",true);
            result = CutString.cut(result,"</strong>",false);
            return result;
        }
        catch (Exception e){
            return null;
        }
    }

    public static boolean internetIsConnected() {
        try {
            String command = "ping -c 1 google.com";
            return (Runtime.getRuntime().exec(command).waitFor() == 0);
        } catch (Exception e) {
            return false;
        }
    }

    public static String getParameterUrl(String query,String keyParam) {
        query = query.substring(query.indexOf("?")+1);
        String[] params = query.split("&");
        for (String param : params) {
            String paramSplit[] = param.split("=");
            String key = paramSplit[0];
            if(key.equals(keyParam))
                return paramSplit[1];
        }
        return null;
    }

    public static Date getDateTimeNowVietNam() throws ParseException {
        Date date = new Date();
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        df.setTimeZone(TimeZone.getTimeZone("Asia/Ho_Chi_Minh"));
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return Date.from(
                formatter.parse(df.format(date)).toInstant());
    }

}
