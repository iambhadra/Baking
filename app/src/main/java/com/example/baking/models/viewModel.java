package com.example.baking.models;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.example.baking.adapter.RecipeListAdapter;
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

public class viewModel extends AndroidViewModel{

    private List<recipes> recipesList;
    public viewModel(Application application){
        super(application);
        recipesList = LoadRecipeList();

    }

    //public LiveData<getRecipeList
    public List<recipes> LoadRecipeList() {
       // MutableLiveData<List<recipes>> liveRecipesList= new MutableLiveData<>();
       // List<recipes> liveRecipesList= new ArrayList<>();
        List<recipes> recipesList = new ArrayList<>();
        try {

            JSONArray recipeResponse = new JSONArray(ReceipeResponse);
            for (int i = 0; i < recipeResponse.length(); i++) {
                JSONObject recipeItem = recipeResponse.getJSONObject(i);
                Gson gson = new Gson();
                recipes recipes = gson.fromJson(recipeItem.toString(),recipes.class);
                recipesList.add(recipes);
                Log.d(">>>ItemNumber->",Integer.toString(i));
                Log.d(">>>recipeItem",recipeItem.toString());
            }
           // liveRecipesList.setValue(recipesList);

            return recipesList;

        } catch (JSONException e) {
            e.printStackTrace();
        }
       // liveRecipesList.setValue(recipesList);
        return recipesList;
    }
 /* public LiveData<List<recipes>> LoadRecipeList() {
      Call<recipes> call = null;
      *//*
       * The connection properties will be set.
       * By call.enqueue method the internet request will be done at back ground thread.
       * OnResponse will be called once the back ground process was completed and result will be handled in onResponse/onFailure methods
       * *//*
      ConnectionInterface apiService = Connection.getClient().create(ConnectionInterface.class);

          call = apiService.getRecipeList();

      Objects.requireNonNull(call).enqueue(new Callback<recipes>() {
          @Override
          public void onResponse(Call<recipes> call, Response<recipes> response) {
              if (response != null) {
                  if (response.body() != null) {
                      List<Ingr> IngredientsList = response.body().getIngredients();
                      Log.d("Number of IngredientsList: ", Integer.toString(IngredientsList.size()));
                      if (IngredientsList != null && IngredientsList.size() > 0) {
                          onSuccess(movies);
                      }
                  }
              } else {
                  Log.d("resp is not received", "null");
              }
          }

          @Override
          public void onFailure(Call<recipes> call, Throwable t) {
              Log.e("exception", t.toString());
          }
      });
  }*/
}