package com.duynam.myapplication.view.listcity;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.duynam.myapplication.R;
import com.duynam.myapplication.adapter.ListCityAdapter;
import com.duynam.myapplication.database.CurrenWeatherDAO;
import com.duynam.myapplication.database.DatabaseHelper;
import com.duynam.myapplication.databinding.FragmentListcityBinding;
import com.duynam.myapplication.model.modelUsingHttp.CurrenLocalCity;
import com.duynam.myapplication.view.home.HomeActivity;
import com.duynam.myapplication.view.searchcity.SearchCityFragment;
import java.util.List;

public class ListCityFragment extends Fragment implements ListCityAdapter.OnCLickListener {

    FragmentListcityBinding binding;
    FragmentManager fragmentManager;
    SearchCityFragment fragmentSearchCity;
    private DatabaseHelper databaseHelper;
    private CurrenWeatherDAO dao;
    private LinearLayoutManager layoutManager;
    private ListCityAdapter adapter;
    public List<CurrenLocalCity> localCityList;
    private ListCityViewModel listCityViewModel;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        getActivity().getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_listcity, container, false);
        init();
        callFragmentSearchCity();
        listCityViewModel.UpdateCitytoMenu();
        binding.setListcity(this);
        return binding.getRoot();
    }

    public void init() {
        fragmentManager = getActivity().getSupportFragmentManager();
        fragmentSearchCity = new SearchCityFragment();
        databaseHelper = new DatabaseHelper(getContext());
        dao = new CurrenWeatherDAO(databaseHelper);
        layoutManager = new LinearLayoutManager(getContext());
        localCityList = dao.getAllCity();
        listCityViewModel = new ListCityViewModel(getContext());
        adapter = new ListCityAdapter(getContext(), localCityList);
        adapter.setOnCLickListener(this);
        binding.rvListCity.setLayoutManager(layoutManager);
        binding.rvListCity.setAdapter(adapter);
        binding.btnAddCity.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.bg_edt_search_city)));
    }

    public void removeView(View view) {
        getActivity().getSupportFragmentManager().beginTransaction().remove(ListCityFragment.this).commit();
    }

    public void callFragmentSearchCity() {
        binding.btnAddCity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((HomeActivity) getActivity()).callFragmentSearchCity();
            }
        });
    }

    @Override
    public void OnCLick(CurrenLocalCity currenLocalCity) {
        Intent intent = new Intent(getContext(), HomeActivity.class);
        intent.putExtra("currenLocalCity", currenLocalCity);
        startActivity(intent);
    }

    @Override
    public void OnLongClick(CurrenLocalCity currenLocalCity, int pos) {
        listCityViewModel.deleteCity(currenLocalCity);
        localCityList.remove(pos);
        adapter.notifyItemRemoved(pos);
    }


}
