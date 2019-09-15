package com.example.baking.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.baking.Global;
import com.example.baking.R;
import com.example.baking.StepDetails;
import com.example.baking.adapter.IngredientsAdapter;
import com.example.baking.adapter.StepsAdapter;
import com.example.baking.models.recipes;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


public class IngredientStepsFragment extends Fragment implements StepsAdapter.onStepClickListener {

    int iPosition=0;
    List<recipes> recipesItems = new ArrayList<>();

    final IngredientsAdapter mIngredientsAdapter =new IngredientsAdapter(this);
    StepsAdapter mStepsAdapter = new StepsAdapter(this);
    RecyclerView rv_ingredients,rv_steps;
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        iPosition = getArguments().getInt("POSITION");
        View view = inflater.inflate(R.layout.ingredients_steps_fragment,container,false);
        rv_ingredients=view.findViewById(R.id.rv_ingredients);
        rv_steps = view.findViewById(R.id.rv_steps);

        loadRecipeFromInternet();
        return view;
    }

    private void PrepareIngredientsList() {
       /* List<recipes> recipesList = new ArrayList<>();
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
        } catch (JSONException e) {
            e.printStackTrace();
        }
        mIngredientsAdapter.setIngredientItem(recipesList.get(iPosition).getIngredients());
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getContext());
        rv_ingredients.setLayoutManager(mLayoutManager);
        rv_ingredients.setItemAnimator(new DefaultItemAnimator());
        rv_ingredients.setAdapter(mIngredientsAdapter);*/
    }

    private void loadRecipeFromInternet() {
       /* Call<List<recipes>> call = null;

        *//*
         * The connection properties will be set.
         * By call.enqueue method the internet request will be done at back ground thread.
         * OnResponse will be called once the back ground process was completed and result will be handled in onResponse/onFailure methods
         * *//*
        ConnectionInterface apiService = Connection.getClient().create(ConnectionInterface.class);

        call = apiService.getRecipeList();

        Objects.requireNonNull(call).enqueue(new Callback<List<recipes>>() {
            @Override
            public void onResponse(Call<List<recipes>> call, Response<List<recipes>> response) {
                if (response != null) {
                    if (response.body() != null) {
                        recipesItems = response.body();
                     //   Log.d("Number of movies: ", Integer.toString(recipesList.size()));*/
                            recipesItems = Global.recipeResponse;
                        if (recipesItems != null) {
                            RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getContext());
                            mIngredientsAdapter.setIngredientItem(recipesItems.get(iPosition).getIngredients());
                            rv_ingredients.setLayoutManager(mLayoutManager);
                            rv_ingredients.setItemAnimator(new DefaultItemAnimator());
                            rv_ingredients.setAdapter(mIngredientsAdapter);

                            mStepsAdapter.setStepItems(recipesItems.get(iPosition).getStepsitem());
                            RecyclerView.LayoutManager mLayoutManagerSteps = new LinearLayoutManager(getContext());
                            rv_steps.setLayoutManager(mLayoutManagerSteps);
                            rv_steps.setItemAnimator(new DefaultItemAnimator());
                            rv_steps.setAdapter(mStepsAdapter);
                        }
              //      }
 /*               } else {
                    Log.d("resp is not received", "null");
                }
            }

            @Override
            public void onFailure(Call<List<recipes>> call, Throwable t) {
                Log.e("exception", t.toString());
            }
        });*/
    }

    @Override
    public void onStepClicked(int iPos) {
        Intent iStepDetails = new Intent(getContext(), StepDetails.class);
        iStepDetails.putExtra("Steps",(Serializable)recipesItems.get(iPosition).getStepsitem().get(iPos));
        startActivity(iStepDetails);
    }
}
