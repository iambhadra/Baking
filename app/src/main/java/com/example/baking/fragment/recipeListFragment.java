package com.example.baking.fragment;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.Intent;
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
import android.widget.TextView;

import com.example.baking.MainActivity;
import com.example.baking.R;
import com.example.baking.RecipeItem;
import com.example.baking.adapter.RecipeListAdapter;
import com.example.baking.models.recipes;
import com.example.baking.models.viewModel;
import com.example.baking.retrofit_connection.Connection;
import com.example.baking.retrofit_connection.ConnectionInterface;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.baking.Global.ReceipeResponse;

public class recipeListFragment extends Fragment implements RecipeListAdapter.recipeClickListener {
    RecyclerView rv_recipeList;
    List<recipes> recipesList = new ArrayList<>();
    final RecipeListAdapter mRecipeListAdapter = new RecipeListAdapter(this, this);
    //  private viewModel mRecipeListLiveData;
    //  public  RecipeSelection recipeSelectionListener;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final List<recipes>[] recipeList = null;

       /* mRecipeListLiveData= ViewModelProviders.of(this).get(viewModel.class);

        // Add an observer on the LiveData returned by getAlphabetizedWords.
        // The onChanged() method fires when the observed data changes and the activity is
        // in the foreground.
        mRecipeListLiveData.LoadRecipeList().observe(this, new Observer<List<recipes>>() {
            @Override
            public void onChanged(@Nullable final List<recipes> words) {
                // Update the cached copy of the words in the adapter.
                mRecipeListAdapter.setRecipesList(words);
            }
        });*/


    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.recipe_list_fragment, container, false);
        rv_recipeList = view.findViewById(R.id.rv_recipeList);
        PrepareRecipeList();
        return view;
    }

    private void PrepareRecipeList() {
        //mRecipeListAdapter = new RecipeListAdapter(recipesList);
        loadRecipeFromInternet();
     /*   List<recipes> recipesList = new ArrayList<>();
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
        }*/

    }

    /*  public interface RecipeSelection{
          public void onRecipeSelected
      }*/
    @Override
    public void onRecipeClicked(int position) {
        Intent intent = new Intent(getContext(), RecipeItem.class);
        intent.putExtra("POSITION", position);
        startActivity(intent);
    }

    private void loadRecipeFromInternet() {
        Call<List<recipes>> call = null;

        /*
         * The connection properties will be set.
         * By call.enqueue method the internet request will be done at back ground thread.
         * OnResponse will be called once the back ground process was completed and result will be handled in onResponse/onFailure methods
         * */
        ConnectionInterface apiService = Connection.getClient().create(ConnectionInterface.class);

            call = apiService.getRecipeList();

        Objects.requireNonNull(call).enqueue(new Callback<List<recipes>>() {
            @Override
            public void onResponse(Call<List<recipes>> call, Response<List<recipes>> response) {
                if (response != null) {
                    if (response.body() != null) {
                        recipesList = response.body();
                        Log.d("Number of movies: ", Integer.toString(recipesList.size()));
                        if (recipesList != null) {
                            RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getContext());
                            mRecipeListAdapter.setRecipesList(recipesList);
                            rv_recipeList.setLayoutManager(mLayoutManager);
                            rv_recipeList.setItemAnimator(new DefaultItemAnimator());
                            rv_recipeList.setAdapter(mRecipeListAdapter);
                        }
                    }
                } else {
                    Log.d("resp is not received", "null");
                }
            }

            @Override
            public void onFailure(Call<List<recipes>> call, Throwable t) {
                Log.e("exception", t.toString());
            }
        });
    }
}
