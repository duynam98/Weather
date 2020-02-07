package com.duynam.myapplication.viewmodel;

import android.content.Context;
import android.widget.ImageView;

import androidx.databinding.BaseObservable;
import androidx.databinding.BindingAdapter;

import com.bumptech.glide.Glide;
import com.duynam.myapplication.model.sevendayweather.Timeframe;

public class Item3HourViewModel extends BaseObservable {

    private Timeframe mtimeframe;
    private Context mContext;

    public Item3HourViewModel (Context context, Timeframe timeframe) {
        mtimeframe = timeframe;
        mContext = context;
    }

    public String getPictureProfile() {
        return mtimeframe.getWxIcon();
    }


}
