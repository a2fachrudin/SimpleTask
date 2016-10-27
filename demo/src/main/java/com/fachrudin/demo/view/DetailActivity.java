package com.fachrudin.demo.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.fachrudin.demo.R;
import com.fachrudin.demo.model.ImageItem;
import com.fachrudin.demo.rest.ApiClient;
import com.fachrudin.demo.rest.ApiInterface;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailActivity extends AppCompatActivity {
    public static final String EXTRA_POSITION = "position";
    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.ivAlbum)
    ImageView ivAlbum;

    @BindView(R.id.tvTitle)
    TextView tvTitle;

    @BindView(R.id.tvUrl)
    TextView tvUrl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        // Set API Interface
        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);

        Call<List<ImageItem>> call = apiService.getPhotos();

        call.enqueue(new Callback<List<ImageItem>>() {
            @Override
            public void onResponse(Call<List<ImageItem>>call, Response<List<ImageItem>> response) {
                int position = getIntent().getIntExtra(EXTRA_POSITION, 0);
                List<ImageItem> imgItem = response.body();
                Glide.with(getApplicationContext()).load(imgItem.get(position).getThumbnailUrl()).into(ivAlbum);
                tvTitle.setText(imgItem.get(position).getTitle());
                tvUrl.setText(imgItem.get(position).getThumbnailUrl());
            }

            @Override
            public void onFailure(Call<List<ImageItem>>call, Throwable t) {
            }
        });
    }
}
