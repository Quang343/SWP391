package com.example.AgriculturalWarehouseManagement.Backend.components;

import com.example.AgriculturalWarehouseManagement.Backend.dtos.resquests.user.GoogleAccountRequest;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import org.apache.http.HttpRequest;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.entity.StringEntity;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.net.URI;
import org.apache.http.client.fluent.Request;

public class GoogleLogin {

    public static String getToken(String code) throws ClientProtocolException, IOException {

        // Create HttpPost request
        HttpPost post = new HttpPost(GoogleConstant.GOOGLE_LINK_GET_TOKEN);

        // Prepare the body of the POST request
        String json = "client_id=" + GoogleConstant.GOOGLE_CLIENT_ID +
                "&client_secret=" + GoogleConstant.GOOGLE_CLIENT_SECRET +
                "&redirect_uri=" + GoogleConstant.GOOGLE_REDIRECT_URI +
                "&code=" + code +
                "&grant_type=" + GoogleConstant.GOOGLE_GRANT_TYPE;

        // Set the request entity with form data
        post.setEntity(new StringEntity(json));

        // Set headers for the POST request
        post.setHeader("Content-Type", "application/x-www-form-urlencoded");

        // Execute the request and get the response
        String response = HttpClients.createDefault().execute(post, httpResponse ->
                EntityUtils.toString(httpResponse.getEntity()));

        // Parse the response to extract the access token
        JsonObject jsonObject = new Gson().fromJson(response, JsonObject.class);
        String accessToken = jsonObject.get("access_token").getAsString();

        return accessToken;
    }

    public static GoogleAccountRequest getUserInfo(String accessToken) throws ClientProtocolException, IOException {
        String link = GoogleConstant.GOOGLE_LINK_GET_USER_INFO + accessToken;
        String response = Request.Get(link).execute().returnContent().asString();
        GoogleAccountRequest googleAccountRequest = new Gson().fromJson(response, GoogleAccountRequest.class);
        return googleAccountRequest;
    }

}
