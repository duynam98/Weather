package com.duynam.myapplication.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.duynam.myapplication.R;
import com.duynam.myapplication.databinding.ItemWeather24hourBinding;
import com.duynam.myapplication.model.sevendayweather.Timeframe;

import java.util.List;

public class HourAdapter extends RecyclerView.Adapter<HourAdapter.ViewHolder> {

    Context context;
    List<Timeframe> timeframes;

    public HourAdapter(Context context, List<Timeframe> timeframes) {
        this.context = context;
        this.timeframes = timeframes;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        ItemWeather24hourBinding binding = DataBindingUtil.inflate(inflater, R.layout.item_weather_24hour, parent, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.setBinding(timeframes.get(position));
    }

    @Override
    public int getItemCount() {
        return timeframes.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {

        public int time;
        public String temp;
        private ItemWeather24hourBinding itemWeather24hourBinding;
        public String name, icon_name;
        String[] icon;

        public ViewHolder(@NonNull ItemWeather24hourBinding itemWeather24hourBinding) {
            super(itemWeather24hourBinding.getRoot());
            this.itemWeather24hourBinding = itemWeather24hourBinding;
        }

        public void setBinding(Timeframe timeframe) {
            if (itemWeather24hourBinding.getHolder() == null) {
                itemWeather24hourBinding.setHolder(this);
            }
            temp = timeframe.getTempC() + "°C";
            time = timeframe.getTime();
            name = timeframe.getWxIcon();
            icon = name.split("\\.");
            icon_name = icon[0].toLowerCase();
            itemWeather24hourBinding.setImage(icon_name);
        }

    }

}
