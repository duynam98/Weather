package com.duynam.myapplication.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import com.duynam.myapplication.R;
import com.duynam.myapplication.databinding.FragmentErrorNetworkBinding;

public class ErrorNetWorkFragment extends Fragment {

    private FragmentErrorNetworkBinding binding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_error_network, container, false);
        binding.setError(this);
        return binding.getRoot();
    }

    public void goback(View view){
        getActivity().finish();
    }

}
