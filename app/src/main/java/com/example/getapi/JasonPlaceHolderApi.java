package com.example.getapi;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface JasonPlaceHolderApi {
    @GET("posts")
    Call<List<Post>> getPosts();
}
