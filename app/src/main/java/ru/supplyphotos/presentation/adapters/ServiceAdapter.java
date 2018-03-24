package ru.supplyphotos.presentation.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import ru.supplyphotos.R;
import ru.supplyphotos.data.answers.services.ItemService;

/**
 * @author Libgo on 24.03.2018.
 */

public class ServiceAdapter extends RecyclerView.Adapter<ServiceAdapter.ViewHolder> {

    private List<ItemService> serviceList = new ArrayList<>();


    @NonNull
    @Override
    public ServiceAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.service_card_item, parent, false);
        return new ViewHolder(view);
    }

    public void updateList(List<ItemService> list){
        this.serviceList = list;
        notifyDataSetChanged();
    }

    @Override
    public void onBindViewHolder(@NonNull ServiceAdapter.ViewHolder holder, int position) {
        holder.textView_name.setText(serviceList.get(position).getName());
        holder.textView_price.setText(serviceList.get(position).getPrice());

    }



    @Override
    public int getItemCount() {
        return serviceList.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.name_service)
        TextView textView_name;
        @BindView(R.id.text_price)
        TextView textView_price;


        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
