package ru.supplyphotos.presentation.adapters;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

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

           holder.bind(itemStorageImages.get(position));

    }


    public int getItemViewType(int position) {
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
        @BindView(R.id.count_text)
        TextView textCount;
        @BindView(R.id.add_count_print)
        TextView countAdd;
        @BindView(R.id.delete_count_print)
        TextView countDelete;
      //  @BindView(R.id.check_selected_image)
      //  CheckBox checkBox;

        ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

        }

        void bind(ItemStorageImage itemStorageImage){

            countAdd.setOnClickListener(v -> {
                String s = String.valueOf(textCount.getText());
                        textCount.setText(String.valueOf(Integer.parseInt(s) + 1));

                    }
             );

            Glide.with(context)
                    .load(itemStorageImage.getPath())
                    .override(160,160)
                    .centerCrop()
                    .diskCacheStrategy(DiskCacheStrategy.RESULT)
                    //.skipMemoryCache(true)
                    .into(itemGalleryImage);

            itemGalleryImage.setOnClickListener(v -> {
               // checkBox.setHighlightColor(Color.parseColor("#ffbe75"));
                viewCount.setVisibility(View.VISIBLE);
                textCount.setText(String.valueOf(1));
               // checkBox.setChecked(true);
            });


        }
    }
}
