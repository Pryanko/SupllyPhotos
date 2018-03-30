package ru.supplyphotos.presentation.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import ru.supplyphotos.R;
import ru.supplyphotos.data.storage.ItemStorageImage;

/**
 * @author Libgo on 30.03.2018.
 */
public class GalleryAdapter extends RecyclerView.Adapter<GalleryAdapter.ViewHolder>{


    private List<ItemStorageImage> itemStorageImages = new ArrayList<>();
    private Context context;

    public GalleryAdapter(Context context) {
        this.context = context;
    }


    public void updateList(List<ItemStorageImage> itemStorageImages){
        this.itemStorageImages.addAll(itemStorageImages);
    }


    @NonNull
    @Override
    public GalleryAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.gallery_image_item, parent, false);
        return new ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull GalleryAdapter.ViewHolder holder, int position) {

        Glide.with(context)
                .load(itemStorageImages.get(position).getPath())
                .override(160,160)
                .centerCrop()
                .diskCacheStrategy(DiskCacheStrategy.RESULT)
                .skipMemoryCache(true)
                .into(holder.itemGalleryImage);

        holder.itemGalleryImage.setOnClickListener(v ->
            holder.viewCount.setVisibility(View.VISIBLE)
        );

    }

   /*
    public int getItemViewType(int position) {
        return position;
    }      */

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemCount() {
        return itemStorageImages.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.image_gallery_item)
        ImageView itemGalleryImage;
        @BindView(R.id.countView)
        RelativeLayout viewCount;

        ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
