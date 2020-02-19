package com.duynam.myapplication.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ObservableField;
import androidx.recyclerview.widget.RecyclerView;

import com.duynam.myapplication.R;
import com.duynam.myapplication.databinding.ItemSearchCityBinding;
import com.duynam.myapplication.model.searchCity.Result;

import java.util.List;

public class SearchCityAdapter extends RecyclerView.Adapter<SearchCityAdapter.Viewholder> {

    private List<Result> resultList;
    private Context context;
    private OnItemClickListener listener;

    public SearchCityAdapter(List<Result> resultList, Context context) {
        this.resultList = resultList;
        this.context = context;
    }

    public void setData(List<Result> results) {
        if (results != null){
            resultList.clear();
            resultList.addAll(results);
            notifyDataSetChanged();
        }
    }

    @NonNull
    @Override
    public Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        ItemSearchCityBinding binding = DataBindingUtil.inflate(inflater, R.layout.item_search_city, parent, false);
        return new Viewholder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull Viewholder holder, int position) {
        holder.setBinding(resultList.get(position));
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.Onclick(resultList.get(position));
            }
        });
    }

    @Override
    public int getItemCount() {
        return resultList.size();
    }

    public class Viewholder extends RecyclerView.ViewHolder {

        private ItemSearchCityBinding itemSearchCityBinding;
        public ObservableField<String> name_city = new ObservableField<>();

        public Viewholder(@NonNull ItemSearchCityBinding itemSearchCityBinding) {
            super(itemSearchCityBinding.getRoot());
            this.itemSearchCityBinding = itemSearchCityBinding;
        }

        public void setBinding(Result result) {
            if (itemSearchCityBinding.getHolder() == null) {
                itemSearchCityBinding.setHolder(this);
            }
            name_city.set(result.getName());
        }

    }

    public void setListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    public interface OnItemClickListener {
        void Onclick(Result result);
    }

}
