package me.sandlz.rxjavademo.modules.movie.view;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import me.sandlz.rxjavademo.R;
import me.sandlz.rxjavademo.modules.movie.model.MovieEntity;

/**
 * Created by liuzhu on 16/8/1.
 * Description :
 * Usage :
 */
public class MovieAdapter extends RecyclerView.Adapter{
    List<MovieEntity> movieItems;

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_movie_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ViewHolder viewHolder = (ViewHolder) holder;
        MovieEntity image = movieItems.get(position);
        Glide.with(holder.itemView.getContext()).load(image.getImages().getLarge()).into(viewHolder.imageIv);
        viewHolder.descriptionTv.setText(image.getTitle());
    }

    @Override
    public int getItemCount() {
        return movieItems == null ? 0 : movieItems.size();
    }

    public void setMovieItems(List<MovieEntity> movieItems) {
        this.movieItems = movieItems;
        notifyDataSetChanged();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.imageIv)
        ImageView imageIv;
        @Bind(R.id.descriptionTv)
        TextView descriptionTv;
        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }


}
