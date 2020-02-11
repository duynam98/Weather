package com.duynam.myapplication.view;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.os.Build;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.FragmentTransaction;

import com.duynam.myapplication.R;
import com.duynam.myapplication.fragment.ErrorNetWorkFragment;

public class MainActivity extends AppCompatActivity {

    private FragmentTransaction fragmentTransaction;
    private ErrorNetWorkFragment fragmentErrorNetWork;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        checknetwork();
    }

    private void checknetwork() {
        fragmentErrorNetWork = new ErrorNetWorkFragment();
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        if (cm.getActiveNetworkInfo() == null) {
            fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.add(R.id.container, fragmentErrorNetWork);
            fragmentTransaction.commit();
        } else if (cm.getActiveNetworkInfo() != null) {
            startActivity(new Intent(this, HomeActivity.class));
            finish();
        }
    }




}
