package com.example.baking;

import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
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
    private static Bundle mBundleFragmentViewState;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        fl_one_container = findViewById(R.id.fl_one_container);
        fl_two_container = findViewById(R.id.fl_two_container);
        fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        if(savedInstanceState != null)
        {
            recipeListFragment  = (recipeListFragment) getSupportFragmentManager().getFragment(savedInstanceState, "recipeItemsFragment");
        }
        else{
            recipeListFragment = new recipeListFragment();
        }
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
        getSupportFragmentManager().putFragment(outState,"recipeItemsFragment",fragmentManager.getFragment(outState,"recipeListFragment"));
    }

    /*@Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        fragmentManager = savedInstanceState.getBundle("recipeItemsFragment");
    }*/
}
