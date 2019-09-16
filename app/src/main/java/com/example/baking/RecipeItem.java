package com.example.baking;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.FrameLayout;

import com.example.baking.fragment.IngredientStepsFragment;
import com.example.baking.fragment.recipeListFragment;

public class RecipeItem extends AppCompatActivity {

    FrameLayout steps_ingradients_fl;
    int iItemPosition;
    IngredientStepsFragment recipeListFragment;
    private static Bundle mBundle = new Bundle();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_item);
        steps_ingradients_fl = findViewById(R.id.steps_ingradients_fl);
        Intent intent = getIntent();
        iItemPosition = (int) intent.getIntExtra("POSITION",0);
        if(savedInstanceState!=null){
            recipeListFragment = (IngredientStepsFragment) getSupportFragmentManager().getFragment(savedInstanceState,"recipeListFragment");
        }else{
            recipeListFragment = new IngredientStepsFragment();
        }

        Bundle bundle = new Bundle();
        bundle.putInt("POSITION",iItemPosition);
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        recipeListFragment.setArguments(bundle);
        fragmentTransaction.add(R.id.steps_ingradients_fl,recipeListFragment,recipeListFragment.getTag());
        fragmentTransaction.commit();

    }

    @Override
    protected void onPause() {
        super.onPause();
        onSaveInstanceState(mBundle);
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
          getSupportFragmentManager().putFragment(outState,"recipeListFragment", recipeListFragment);
    }
}
