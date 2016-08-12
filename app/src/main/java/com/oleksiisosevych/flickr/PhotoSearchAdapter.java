package com.oleksiisosevych.flickr;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.oleksiisosevych.flickr.data.model.Photo;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PhotoSearchAdapter extends RecyclerView.Adapter<PhotoSearchAdapter.ViewHolder> {
    private Context context;
    private List<Photo> photos;

    public PhotoSearchAdapter(Context context, List<Photo> photos) {
        this.context = context;
        this.photos = photos;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.picture_grid_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        final Photo item = photos.get(position);

        Picasso.with(context).load(getPhotoUrl(item)).into(holder.picture);
        holder.title.setText(item.getTitle());
    }

    private String getPhotoUrl(Photo photo) {
        return String.format("http://farm%s.static.flickr.com/%s/%s_%s.jpg",
                photo.getFarm(),
                photo.getServer(),
                photo.getId(),
                photo.getSecret());
    }

    @Override
    public int getItemCount() {
        return photos == null ? 0 : photos.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.picture) ImageView picture;
        @BindView(R.id.title) TextView title;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
