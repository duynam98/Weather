package com.duynam.myapplication.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ObservableField;
import androidx.recyclerview.widget.RecyclerView;

import com.duynam.myapplication.R;
import com.duynam.myapplication.databinding.Item7dayBinding;
import com.duynam.myapplication.model.sevendayweather.Day;
import com.duynam.myapplication.model.sevendayweather.Forecast;
import com.duynam.myapplication.model.sevendayweather.Timeframe;
import com.duynam.myapplication.view.HomeActivity;

import java.sql.Time;
import java.util.List;

import retrofit2.Callback;

public class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.Viewholder> {

    Context context;
    List<Day> dayList;

    public HomeAdapter(Context context, List<Day> dayList) {
        this.context = context;
        this.dayList = dayList;
    }

    public void setData(List<Day> days) {
        if (days != null) {
            dayList.clear();
            dayList.addAll(days);
            notifyDataSetChanged();
        }
    }

    public void addData(List<Day> days) {
        if (days != null) {
            int oldsize = dayList.size();
            dayList.addAll(days);
            notifyItemRangeChanged(oldsize, days.size());
        }
    }

    @NonNull
    @Override
    public Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        Item7dayBinding binding = DataBindingUtil.inflate(inflater, R.layout.item_7day, parent, false);
        return new Viewholder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull Viewholder holder, int position) {
        holder.setBinding(dayList.get(position));
    }

    @Override
    public int getItemCount() {
        return dayList.size();
    }


    public class Viewholder extends RecyclerView.ViewHolder {

        public ObservableField<String> date = new ObservableField<>();
        public String temp;
        private Item7dayBinding item7dayBinding;

        public Viewholder(@NonNull Item7dayBinding item7dayBinding) {
            super(item7dayBinding.getRoot());
            this.item7dayBinding = item7dayBinding;
        }

        public void setBinding(Day day) {
            if (item7dayBinding.getHolder() == null) {
                item7dayBinding.setHolder(this);
            }
            date.set(day.getDate());
            temp = day.getTempMinC() + "-" + day.getTempMaxC() + "°C";
            item7dayBinding.setImage(HomeActivity.checkWeather(day));
        }
    }

}
