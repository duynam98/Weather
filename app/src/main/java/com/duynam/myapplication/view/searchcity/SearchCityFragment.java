package com.duynam.myapplication.view.searchcity;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;

import com.duynam.myapplication.R;
import com.duynam.myapplication.adapter.SearchCityAdapter;
import com.duynam.myapplication.databinding.FragmentSearchCityBinding;
import com.duynam.myapplication.model.searchCity.Result;
import com.duynam.myapplication.view.home.HomeActivity;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.List;

public class SearchCityFragment extends Fragment implements SearchCityAdapter.OnItemClickListener, SearchCityViewModel.OnSearchCity {

    private FragmentSearchCityBinding binding;
    private List<Result> resultList;
    private SearchCityAdapter searchCityAdapter;
    private GridLayoutManager gridLayoutManager;
    private SearchCityViewModel searchCityViewModel;
    private View viewSnackBar;
    private String mess;
    private int durSnackbar;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_search_city, container, false);
        init();
        searchCity();
        return binding.getRoot();
    }

    private void init() {
        resultList = new ArrayList<>();
        searchCityViewModel = new SearchCityViewModel(binding.wp7Progress);
        searchCityViewModel.setOnSearchCity(this);
        gridLayoutManager = new GridLayoutManager(getContext(), 4);
        binding.rvListCitySearch.setLayoutManager(gridLayoutManager);
        binding.rvListCitySearch.setAdapter(searchCityAdapter);
        binding.setSearchcity(this);
        searchCityAdapter = new SearchCityAdapter(resultList, getContext());
        viewSnackBar = binding.layoutMainSnackbar;
        mess  = getContext().getResources().getString(R.string.no_city);
        durSnackbar = Snackbar.LENGTH_INDEFINITE;
    }

    @Override
    public void Onclick(Result result) {
        Intent intent = new Intent(getContext(), HomeActivity.class);
        intent.putExtra("latlongcity", result);
        intent.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
        startActivity(intent);
    }

    public void searchCity() {
        binding.edtSearchCity.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    searchCityViewModel.getCity(binding.edtSearchCity.getText().toString().trim(), viewSnackBar, mess, durSnackbar);
                    return true;
                }
                return false;
            }
        });
    }

    public void deleteSearchText(View view) {
        binding.edtSearchCity.setText("");
    }

    public void removeView(View view) {
        getActivity().getSupportFragmentManager().beginTransaction().remove(SearchCityFragment.this).commit();
    }

    @Override
    public void getCity(List<Result> results) {
        searchCityAdapter.setData(results);
        searchCityAdapter.setListener(this);
        binding.rvListCitySearch.setLayoutManager(gridLayoutManager);
        binding.rvListCitySearch.setAdapter(searchCityAdapter);
    }


}
