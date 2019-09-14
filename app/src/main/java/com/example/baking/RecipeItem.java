package com.example.baking;

import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.FrameLayout;

import com.example.baking.fragment.IngredientStepsFragment;

public class RecipeItem extends AppCompatActivity {

    FrameLayout steps_ingradients_fl;
    int iItemPosition;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_item);
        steps_ingradients_fl = findViewById(R.id.steps_ingradients_fl);
        Intent intent = getIntent();
        iItemPosition = (int) intent.getIntExtra("POSITION",0);

        Bundle bundle = new Bundle();
        bundle.putInt("POSITION",iItemPosition);
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        IngredientStepsFragment recipeListFragment = new IngredientStepsFragment();
        recipeListFragment.setArguments(bundle);
        fragmentTransaction.add(R.id.steps_ingradients_fl,recipeListFragment,recipeListFragment.getTag());
        fragmentTransaction.commit();

    }
}
