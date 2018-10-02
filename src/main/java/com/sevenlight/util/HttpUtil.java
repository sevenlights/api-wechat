package com.sevenlight.util;

import okhttp3.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by zhangweihong on 2018/9/27.
 */
public class HttpUtil {

    public static String post(String url, Map<String, String> params){
        OutputStreamWriter out = null;
        BufferedReader in  = null;
        StringBuilder result = new StringBuilder();
        try{
            URL postUrl = new URL(url);
            HttpURLConnection conn = (HttpURLConnection) postUrl.openConnection();
            conn.setDoInput(true);
            conn.setDoOutput(true);
            conn.setRequestMethod("POST");
            conn.setRequestProperty("accept", "*/*");
            conn.setRequestProperty("connection", "Keep-Alive");
            conn.setRequestProperty("user-agent",
                    "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            conn.connect();

            out = new OutputStreamWriter(conn.getOutputStream(),"UTF-8");

            if(params != null){
                StringBuilder param = new StringBuilder();
                for (Map.Entry<String, String> entry : params.entrySet()) {
                    if (param.length() > 0) {
                        param.append("&");
                    }
                    param.append(entry.getKey());
                    param.append("=");
                    param.append(entry.getValue());
                }
                out.write(param.toString());
            }
            out.flush();

            in = new BufferedReader(new InputStreamReader(conn.getInputStream(),"UTF-8"));

            String line;
            while((line = in.readLine()) != null) {
                result.append(line);
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            try{
                if(out != null){
                    out.close();
                }
                if(in != null) {
                    in.close();
                }
            }catch (IOException ex){
                ex.printStackTrace();
            }
        }
        return result.toString();
    }

    public static Response okHttpGet(String url, Map<String, String> params) {
        url = appendUri(url, params);
        OkHttpClient httpClient = new OkHttpClient();
        Request.Builder builder = new Request.Builder();
        Request request1 = builder.get().url(url).build();
        Call call = httpClient.newCall(request1);
        Response response = null;
        try{
            response = call.execute();
        }catch (IOException e){
            e.printStackTrace();
        }
        return response;
    }

    public static Response okHttpPost(String url, Map<String, String> bodyParams) {
        OkHttpClient httpClient = new OkHttpClient();
        RequestBody body = setRequestBody(bodyParams);
        Request.Builder requestBuilder = new Request.Builder();
        Request request = requestBuilder.post(body).url(url).build();
        Call call = httpClient.newCall(request);
        Response response = null;
        try {
            response = call.execute();
        }catch (IOException e){
            e.printStackTrace();
        }
        return response;
    }

    private static RequestBody setRequestBody(Map<String, String> bodyParams) {
        RequestBody body = null;
        FormBody.Builder formBodyBuilder = new FormBody.Builder();
        if(bodyParams != null){
            Iterator<String> iterator = bodyParams.keySet().iterator();
            String key = "";
            while (iterator.hasNext()) {
                key = iterator.next().toString();
                formBodyBuilder.add(key, bodyParams.get(key));
            }
        }
        body = formBodyBuilder.build();
        return body;
    }

    public static String appendUri(String uri, Map<String, String> params) {
        URI newUri = null;
        try {
            URI oldUri = new URI(uri);
            String newQuery = oldUri.getQuery();

            if (params != null) {
                Iterator<String> iterator = params.keySet().iterator();
                while (iterator.hasNext()) {
                    String key = iterator.next().toString();
                    if (newQuery == null) {
                        newQuery = key + "=" + params.get(key);
                    } else {
                        newQuery += "&" + key + "=" + params.get(key);
                    }
                }
            }
            newUri = new URI(oldUri.getScheme(), oldUri.getAuthority(),
                    oldUri.getPath(), newQuery, oldUri.getFragment());
        }catch (URISyntaxException exp){
            exp.printStackTrace();
        }
        return newUri.toString();
    }
}
