package com.laioffer.tinnews;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.NavigationUI;

import android.os.Bundle;
import android.util.Log;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.laioffer.tinnews.model.NewsResponse;
import com.laioffer.tinnews.network.NewsApi;
import com.laioffer.tinnews.network.RetrofitClient;

public class MainActivity extends AppCompatActivity {
    //jetpack nav component
    //specify the path and destinations
    private NavController navController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //find the bottom nav view
        BottomNavigationView navView = findViewById(R.id.nav_view);
        //fragment container view
        NavHostFragment navHostFragment = (NavHostFragment) getSupportFragmentManager()
                .findFragmentById(R.id.nav_host_fragment);
        navController = navHostFragment.getNavController();
        NavigationUI.setupWithNavController(navView,navController);
        //NewsApi api = RetrofitClient.newInstance(this).create(NewsApi.class);

    }

    //handling the top left back button on the title bar
    //The start destination of your navigation graph is considered the only top level destination.
    //On all other destinations, the ActionBar will show the Up button.
    // Call NavController.navigateUp() to handle the Up button
    @Override
    public boolean onSupportNavigateUp() {
        return navController.navigateUp();
    }
}