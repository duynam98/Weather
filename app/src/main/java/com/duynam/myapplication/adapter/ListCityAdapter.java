package com.duynam.myapplication.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ObservableField;
import androidx.recyclerview.widget.RecyclerView;

import com.duynam.myapplication.R;
import com.duynam.myapplication.databinding.ItemCityBinding;
import com.duynam.myapplication.model.City;
import com.duynam.myapplication.model.modelUsingHttp.CurrenLocalCity;
import com.duynam.myapplication.untils.Untils;

import java.util.List;

public class ListCityAdapter extends RecyclerView.Adapter<ListCityAdapter.ListCityHolder> {

    private Context context;
    private List<CurrenLocalCity>  localCityList;

    public ListCityAdapter(Context context, List<CurrenLocalCity> localCityList) {
        this.context = context;
        this.localCityList = localCityList;
    }

    @NonNull
    @Override
    public ListCityHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        ItemCityBinding binding = DataBindingUtil.inflate(inflater, R.layout.item_city, parent, false);
        return new ListCityHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ListCityHolder holder, int position) {
        holder.setBinding(localCityList.get(position));
    }

    @Override
    public int getItemCount() {
        return localCityList.size();
    }

    public class ListCityHolder extends RecyclerView.ViewHolder {
        private ItemCityBinding itemCityBinding;
        public ObservableField<String> nameCity = new ObservableField<>();
        public Double tempC;
        public ListCityHolder(@NonNull ItemCityBinding itemCityBinding) {
            super(itemCityBinding.getRoot());
            this.itemCityBinding = itemCityBinding;
        }

        public void setBinding(CurrenLocalCity city){
            if (itemCityBinding.getListCityHolder() == null){
                itemCityBinding.setListCityHolder(this);
            }
            nameCity.set(city.getName());
            tempC = city.getTemp();
            itemCityBinding.setLocalCity("http://openweathermap.org/img/w/" + city.icon + ".png");
        }

    }
}
