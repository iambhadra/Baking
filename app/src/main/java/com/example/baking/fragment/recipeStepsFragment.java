package com.example.baking.fragment;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.baking.MainActivity;
import com.example.baking.R;
import com.example.baking.RecipeItem;
import com.example.baking.adapter.IngredientsAdapter;
import com.example.baking.adapter.RecipeListAdapter;
import com.example.baking.models.recipes;
import com.example.baking.models.viewModel;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import static com.example.baking.Global.ReceipeResponse;


public class recipeStepsFragment extends Fragment {

    int iPosition=0;
    private viewModel mRecipeListLiveData;
    List<recipes> recipesItems = new ArrayList<>();

    final IngredientsAdapter mIngredientsAdapter =new IngredientsAdapter(this);
    RecyclerView rv_ingredients,rv_steps;
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

     /*   mRecipeListLiveData= ViewModelProviders.of(this).get(viewModel.class);

        // Add an observer on the LiveData returned by getAlphabetizedWords.
        // The onChanged() method fires when the observed data changes and the activity is
        // in the foreground.
        mRecipeListLiveData.LoadRecipeList().observe(this, new Observer<List<recipes>>() {
            @Override
            public void onChanged(@Nullable final List<recipes> words) {
                // Update the cached copy of the words in the adapter.
                mIngredientsAdapter.setIngredientItem(words);
            }
        });

*/
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        iPosition = getArguments().getInt("POSITION");
        View view = inflater.inflate(R.layout.step_ingredients_fragment,container,false);
        rv_ingredients=view.findViewById(R.id.rv_ingredients);
        rv_steps = view.findViewById(R.id.rv_steps);

      /*  mRecipeListLiveData= ViewModelProviders.of(this).get(viewModel.class);

        // Add an observer on the LiveData returned by getAlphabetizedWords.
        // The onChanged() method fires when the observed data changes and the activity is
        // in the foreground.
        recipesItems = mRecipeListLiveData.LoadRecipeList().getValue();
        mRecipeListLiveData.LoadRecipeList().observe(this, new Observer<List<recipes>>() {
            @Override
            public void onChanged(@Nullable final List<recipes> words) {
                // Update the cached copy of the words in the adapter.
                recipesItems = words;
                mIngredientsAdapter.setIngredientItem(words.get(iPosition).getIngredients());
            }
        });*/
        PrepareIngredientsList();
        return view;
    }

    private void PrepareIngredientsList() {
        List<recipes> recipesList = new ArrayList<>();
        try {

            JSONArray recipeResponse = new JSONArray(ReceipeResponse);
            for (int i = 0; i < recipeResponse.length(); i++) {
                JSONObject recipeItem = recipeResponse.getJSONObject(i);
                Gson gson = new Gson();
                recipes recipes = gson.fromJson(recipeItem.toString(), recipes.class);
                recipesList.add(recipes);
                Log.d(">>>ItemNumber->", Integer.toString(i));
                Log.d(">>>recipeItem", recipeItem.toString());
            }
            // liveRecipesList.setValue(recipesList);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        //mRecipeListAdapter = new RecipeListAdapter(recipesList);
        //recipes recipes = recipesItems.get(iPosition);
    //    viewModel objViewModel = new viewModel();
      //  recipes recipes = viewModel.LoadRecipeList();
        mIngredientsAdapter.setIngredientItem(recipesList.get(iPosition).getIngredients());
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getContext());
        rv_ingredients.setLayoutManager(mLayoutManager);
        rv_ingredients.setItemAnimator(new DefaultItemAnimator());
        rv_ingredients.setAdapter(mIngredientsAdapter);
    }
}
