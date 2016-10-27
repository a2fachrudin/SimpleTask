package com.fachrudin.demo.rest;

import com.fachrudin.demo.model.ImageItem;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ApiInterface {
    // Getter from http://jsonplaceholder.typicode.com/photos
    @GET("photos")
    Call<List<ImageItem>> getPhotos();
    @GET("photos/{id}")
    Call<ImageItem> getPhotosDetails(@Path("id") int id);
}
