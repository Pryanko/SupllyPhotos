package ru.supplyphotos.presentation.adapters;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import ru.supplyphotos.R;
import ru.supplyphotos.data.answers.category.ItemCategory;
import ru.supplyphotos.presentation.adapters.ContractsAdapters.ItemCategoryTouch;

/**
 * @author Libgo on 20.01.2018.
 */

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.ViewHolder>  {

    private List<ItemCategory> categoryList = new ArrayList<>();
    private ItemCategoryTouch itemCategoryTouch;

    public void addCategoryList(List<ItemCategory> list){
        if (categoryList.size() == 0){
        this.categoryList = list;

        }
        notifyDataSetChanged();
    }

    public void setItemCategoryTouch(ItemCategoryTouch itemCategoryTouch){
        this.itemCategoryTouch = itemCategoryTouch;
    }
    
    @Override
    public CategoryAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.category_card_item, parent, false);
        return new ViewHolder(view);
       /* return new ViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.category_card_item, parent, false));    */
    }

    @Override
    public void onBindViewHolder(CategoryAdapter.ViewHolder holder, int position) {
        ItemCategory itemCategory = categoryList.get(position);
        holder.textCardCategory.setText(itemCategory.getName());
        holder.categoryCardImage.setImageURI(itemCategory.getImage480());
        holder.categoryCard.setOnClickListener(v -> itemCategoryTouch
                .touchItemCategory(itemCategory));

    }



    @Override
    public int getItemCount() {
        if(categoryList.size() != 0){
            return categoryList.size();
        }
        return 0;
    }

     class ViewHolder extends RecyclerView.ViewHolder {
         @BindView(R.id.category_card_image)
         SimpleDraweeView categoryCardImage;
         @BindView(R.id.text_card_category)
         TextView textCardCategory;
         @BindView(R.id.card_category)
         CardView categoryCard;
         

        ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
