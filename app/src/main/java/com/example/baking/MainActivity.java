package com.example.baking;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.widget.FrameLayout;

import com.example.baking.adapter.RecipeListAdapter;
import com.example.baking.fragment.recipeListFragment;

public class MainActivity extends AppCompatActivity implements RecipeListAdapter.recipeClickListener{

    FrameLayout fl_one_container,fl_two_container;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        fl_one_container = findViewById(R.id.fl_one_container);
        fl_two_container = findViewById(R.id.fl_two_container);
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        recipeListFragment recipeListFragment = new recipeListFragment();
        fragmentTransaction.add(R.id.fl_one_container,recipeListFragment,"recipeListFragment");
        fragmentTransaction.commit();


    }
    @Override
    public void onRecipeClicked(int position) {

    }

}
