package com.fachrudin.demo.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.fachrudin.demo.R;
import com.fachrudin.demo.model.ImageItem;
import com.fachrudin.demo.view.DetailActivity;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class ImageAdapter extends RecyclerView.Adapter<ImageAdapter.ImageViewHolder> {
    private List<ImageItem> imgItem;
    private int rowLayout;
    private Context context;

    public static class ImageViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.lPosts)
        LinearLayout lPost;

        @BindView(R.id.ivAlbum)
        ImageView ivAlbum;

        @BindView(R.id.tvTitle)
        TextView tvTitle;

        @BindView(R.id.tvUrl)
        TextView tvUrl;

        public ImageViewHolder(View v) {
            super(v);
            ButterKnife.bind(this, v);

            v.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Context context = v.getContext();
                    Intent intent = new Intent(context, DetailActivity.class);
                    intent.putExtra(DetailActivity.EXTRA_POSITION, getAdapterPosition());
                    context.startActivity(intent);
                }
            });
            /*
            v.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Snackbar.make(v, "Action is pressed", Snackbar.LENGTH_LONG).show();
                }
            });
            */
        }

    }

    public ImageAdapter(List<ImageItem> imgItem, int rowLayout, Context context) {
        this.imgItem = imgItem;
        this.context = context;
        this.rowLayout = rowLayout;
    }

    @Override
    public ImageAdapter.ImageViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(rowLayout, parent, false);
        return new ImageViewHolder(view);

    }

    @Override
    public void onBindViewHolder(ImageViewHolder holder, final int position) {
        Glide.with(context).load(imgItem.get(position).getThumbnailUrl()).into(holder.ivAlbum);
        holder.tvTitle.setText(imgItem.get(position).getTitle());
        holder.tvUrl.setText(imgItem.get(position).getThumbnailUrl());
    }

    @Override
    public int getItemCount() {
        return imgItem.size();
    }
}
