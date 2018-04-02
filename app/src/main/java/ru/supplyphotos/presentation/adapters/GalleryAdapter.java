package ru.supplyphotos.presentation.adapters;

import android.annotation.SuppressLint;
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
import io.reactivex.disposables.Disposable;
import ru.supplyphotos.R;
import ru.supplyphotos.data.storage.ItemStorageImage;
import ru.supplyphotos.design.GalleryCheckBox;
import ru.supplyphotos.rx.binding.RxViews;

/**
 * @author Libgo on 30.03.2018.
 */
public class GalleryAdapter extends RecyclerView.Adapter<GalleryAdapter.ViewHolder>{


    private List<ItemStorageImage> itemStorageImages = new ArrayList<>();
    private Context context;
    private ContractsAdapters.GalleryTouchManager galleryTouchManager;

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

    public void setGalleryTouchManager(ContractsAdapters.GalleryTouchManager galleryTouchManager) {
        this.galleryTouchManager = galleryTouchManager;
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
        @BindView(R.id.gallery_check_box)
        GalleryCheckBox checkBoxGallery;
      //  @BindView(R.id.check_selected_image)
      //  CheckBox checkBox;

        ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

        }



        void bind(ItemStorageImage itemStorageImage){

        /* Disposable disposable = RxViews.clicksSelectedImageItem(checkBoxGallery, itemGalleryImage, viewCount)
                    .subscribe(aBoolean -> {
                        Log.d("Click Booooo", String.valueOf(aBoolean));
                        galleryTouchManager
                                .switchSelectedItemImage(itemStorageImage.getId_item(), aBoolean);
                        itemStorageImage.setSelected(aBoolean);
                    });    */
         
            if(itemStorageImage.getSelected()){
                viewCount.setVisibility(View.VISIBLE);
                textCount.setText(String.valueOf(itemStorageImage.getCountPrint()));
                checkBoxGallery.setChecked(true);
            }

            countAdd.setOnClickListener(v -> {
                String s = String.valueOf(textCount.getText());
                        textCount.setText(String.valueOf(Integer.parseInt(s) + 1));
                        galleryTouchManager.updateCountPrintImage(itemStorageImage.getId_item(),
                                Integer.parseInt(String.valueOf(textCount.getText())));


                    }
             );

            countDelete.setOnClickListener(v -> {
                String s = String.valueOf(textCount.getText());
                textCount.setText(String.valueOf(Integer.parseInt(s) - 1));
                galleryTouchManager.updateCountPrintImage(itemStorageImage.getId_item(),
                        Integer.parseInt(String.valueOf(textCount.getText())));


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
                checkBoxGallery.setChecked(true, true);
               // checkBox.setHighlightColor(Color.parseColor("#ffbe75"));
                galleryTouchManager.switchSelectedItemImage(itemStorageImage.getId_item(),
                        true);
                viewCount.setVisibility(View.VISIBLE);
                textCount.setText(String.valueOf(1));
               // checkBox.setChecked(true);
            });  


        }
    }
}
