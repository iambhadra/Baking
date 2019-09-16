package com.example.baking;

import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.widget.FrameLayout;

import com.example.baking.adapter.RecipeListAdapter;
import com.example.baking.fragment.recipeListFragment;

public class MainActivity extends AppCompatActivity{

    FrameLayout fl_one_container,fl_two_container;
    FragmentManager fragmentManager;
    recipeListFragment recipeListFragment;
    private final String KEY_FRAGMENT_STATE = "fragment_state";
    private static Bundle mBundleFragmentViewState = new Bundle();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        fl_one_container = findViewById(R.id.fl_one_container);
        fl_two_container = findViewById(R.id.fl_two_container);
        fragmentManager = getSupportFragmentManager();
        if(savedInstanceState != null)
        {

            //fragmentManager.getFragment(savedInstanceState,"recipeListFragment");
            recipeListFragment  = (recipeListFragment) getSupportFragmentManager().getFragment(savedInstanceState, "recipeItemsFragment");
        }
        else{

            recipeListFragment = new recipeListFragment();
        }
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.fl_one_container,recipeListFragment,"recipeListFragment");
        fragmentTransaction.commit();


    }

    @Override
    protected void onPause() {
        super.onPause();
        onSaveInstanceState(mBundleFragmentViewState);
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
           getSupportFragmentManager().putFragment(outState,"recipeItemsFragment", recipeListFragment);
    }
}
