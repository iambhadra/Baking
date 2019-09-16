package com.example.baking.fragment;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.Handler;
import android.os.Parcelable;
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
    private final String KEY_RECYCLER_STEPS_STATE = "recycler_state";
    private static Bundle mBundleRecyclerViewStepsState;
    private Parcelable mListStepsState = null;
    private final String KEY_RECYCLER_INGREDIENTS_STATE = "recycler_state";
    private static Bundle mBundleRecyclerViewIngredientsState;
    private Parcelable mListIngredientsState = null;
    RecyclerView.LayoutManager mLayoutManagerIngredients;
    RecyclerView.LayoutManager mLayoutManagerSteps;
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
                            mLayoutManagerIngredients = new LinearLayoutManager(getContext());
                            mIngredientsAdapter.setIngredientItem(recipesItems.get(iPosition).getIngredients());
                            rv_ingredients.setLayoutManager(mLayoutManagerIngredients);
                            rv_ingredients.setItemAnimator(new DefaultItemAnimator());
                            rv_ingredients.setAdapter(mIngredientsAdapter);

                            mStepsAdapter.setStepItems(recipesItems.get(iPosition).getStepsitem());
                            mLayoutManagerSteps = new LinearLayoutManager(getContext());
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
        iStepDetails.putExtra("Steps",(Serializable)recipesItems.get(iPosition).getStepsitem());
        iStepDetails.putExtra("StepPosition",iPos);
        startActivity(iStepDetails);
    }

    /*
     * Here we are saving the state of the recycer view to restore after rotation
     * */
    @Override
    public void onPause() {
        super.onPause();
        mBundleRecyclerViewStepsState = new Bundle();
        mListStepsState = rv_steps.getLayoutManager().onSaveInstanceState();
        mBundleRecyclerViewStepsState.putParcelable(KEY_RECYCLER_STEPS_STATE, mListStepsState);

        mBundleRecyclerViewIngredientsState = new Bundle();
        mListIngredientsState = rv_ingredients.getLayoutManager().onSaveInstanceState();
        mBundleRecyclerViewIngredientsState.putParcelable(KEY_RECYCLER_INGREDIENTS_STATE, mListIngredientsState);
    }
    /*
     * In the below configuration , we are defining the restored state along the number columns on orientation of device.
     * */
    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        if (mBundleRecyclerViewStepsState != null) {
            new Handler().postDelayed(new Runnable() {

                @Override
                public void run() {
                    mListStepsState = mBundleRecyclerViewStepsState.getParcelable(KEY_RECYCLER_STEPS_STATE);
                    rv_steps.getLayoutManager().onRestoreInstanceState(mListStepsState);

                }
            }, 50);
        }
        rv_steps.setLayoutManager(mLayoutManagerSteps);
        if (mBundleRecyclerViewIngredientsState != null) {
            new Handler().postDelayed(new Runnable() {

                @Override
                public void run() {
                    mListIngredientsState = mBundleRecyclerViewIngredientsState.getParcelable(KEY_RECYCLER_INGREDIENTS_STATE);

                    rv_ingredients.getLayoutManager().onRestoreInstanceState(mListIngredientsState);

                }
            }, 50);
        }
        rv_ingredients.setLayoutManager(mLayoutManagerIngredients);

        /*// Checks the orientation of the screen
        if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE) {

            mLayoutManager.setSpanCount(2);

        } else if (newConfig.orientation == Configuration.ORIENTATION_PORTRAIT) {

            mLayoutManager.setSpanCount(1);

        }
        rv_recipeList.setLayoutManager(mLayoutManager);*/
    }

}
