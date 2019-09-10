package com.example.baking.retrofit_connection;
/*
 * Title :- FavouriteMovie Bazaar Application
 * Version :- 1.0.0
 * Usage :- This class is used to establish the API end points to the movie DB.
         Here method of connection, request and response formats will be specified along with th query parameters.
 * Creator :- Veerabhadrarao kona
 * Date :- 01-06-2019
 * */


import com.example.baking.models.recipes;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ConnectionInterface {
    @GET("baking.json")
    Call<List<recipes>> getRecipeList();


}

