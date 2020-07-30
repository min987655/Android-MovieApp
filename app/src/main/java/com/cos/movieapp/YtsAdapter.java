package com.cos.movieapp;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class YtsAdapter extends RecyclerView.Adapter<YtsAdapter.MyViewHolder> {

    private static final String TAG = "YtsAdapter";
    private List<YtsData.MyData.Movie> movies = new ArrayList<>();

    public void addItem(YtsData.MyData.Movie movie){
        movies.add(movie);
    }

    public void addItems(List<YtsData.MyData.Movie> movies) {
        this.movies = movies;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Log.d(TAG, "onCreateViewHolder: ");
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View cardItemView = inflater.inflate(R.layout.card_item, parent, false);
        return new MyViewHolder(cardItemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Log.d(TAG, "onBindViewHolder: ");
        holder.setItem(movies.get(position));
    }

    @Override
    public int getItemCount() {
        Log.d(TAG, "getItemCount: ");
        return movies.size();
    }

    // 따로 메모리에 new할게 아니라서 static으로 만들기, 규칙
    // 책꽂이(View들을 채워두면 됨)
    public static class MyViewHolder extends RecyclerView.ViewHolder {

        private ImageView ivPoster;
        private TextView tvTitle;
        private TextView tvRating;
        private RatingBar ratingBar;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            ivPoster = itemView.findViewById(R.id.iv_poster);
            tvTitle = itemView.findViewById(R.id.tv_title);
            tvRating = itemView.findViewById(R.id.tv_rating);
            ratingBar = itemView.findViewById(R.id.rating_bar);

        }

        public void setItem(YtsData.MyData.Movie movie){
            tvTitle.setText(movie.getTitle());
            tvRating.setText(movie.getRating()+"");
            Picasso.get().load(movie.getMedium_cover_image()).into(ivPoster);

        }
    }
}
