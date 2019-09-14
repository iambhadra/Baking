package com.example.baking.fragment;

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

import com.example.baking.Global;
import com.example.baking.R;
import com.example.baking.RecipeItem;
import com.example.baking.adapter.RecipeListAdapter;
import com.example.baking.models.recipes;
import com.example.baking.retrofit_connection.Connection;
import com.example.baking.retrofit_connection.ConnectionInterface;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class recipeListFragment extends Fragment implements RecipeListAdapter.recipeClickListener {
    RecyclerView rv_recipeList;
    List<recipes> recipesList = new ArrayList<>();
    final RecipeListAdapter mRecipeListAdapter = new RecipeListAdapter(this, this);

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final List<recipes>[] recipeList = null;


    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.recipe_list_fragment, container, false);
        rv_recipeList = view.findViewById(R.id.rv_recipeList);
        loadRecipeFromInternet();
        //PrepareRecipeList();
        return view;
    }

    private void PrepareRecipeList() {

        loadRecipeFromInternet();

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
                        Global.recipeResponse = recipesList;
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
