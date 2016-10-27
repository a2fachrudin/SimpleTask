package com.fachrudin.demo.view;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.fachrudin.demo.R;
import com.fachrudin.demo.adapter.ImageAdapter;
import com.fachrudin.demo.model.ImageItem;
import com.fachrudin.demo.rest.ApiClient;
import com.fachrudin.demo.rest.ApiInterface;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by fachrudin on 10/26/16.
 */

public class Page1Fragment extends Fragment {
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout
        View view = inflater.inflate(R.layout.fragment_page1, container, false);

        ButterKnife.bind(this, view);

        // Set Layout Manager
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        // Set API Interface
        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);

        Call<List<ImageItem>> call = apiService.getPhotos();

        call.enqueue(new Callback<List<ImageItem>>() {
            @Override
            public void onResponse(Call<List<ImageItem>>call, Response<List<ImageItem>> response) {
                List<ImageItem> imgItem = response.body();
                recyclerView.setAdapter(new ImageAdapter(imgItem, R.layout.card_item, getActivity()));
            }

            @Override
            public void onFailure(Call<List<ImageItem>>call, Throwable t) {
            }
        });

        return view;
    }

}