package com.example.baking.adapter;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.baking.R;
import com.example.baking.fragment.recipeListFragment;
import com.example.baking.models.recipes;

import java.util.ArrayList;
import java.util.List;

public class RecipeListAdapter extends RecyclerView.Adapter<RecipeListAdapter.MyViewHolder> {
    private List<recipes> recipesList = new ArrayList<>();
    final private recipeClickListener onRecipeClicked;
  //  private final LayoutInflater mInflater;
    recipeListFragment context;



    public RecipeListAdapter(recipeClickListener recipeClickListener, recipeListFragment ctx) {
//        mInflater = LayoutInflater.from(context.getActivity());
        this.context = ctx;
        onRecipeClicked = recipeClickListener;
        notifyDataSetChanged();
    }

   class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public CardView cvRecipeName;
        public TextView tvRecipeName;

        public MyViewHolder(View view) {
            super(view);
            tvRecipeName = view.findViewById(R.id.tv_recipeName);
            view.setOnClickListener(this);
        }

       public void onClick(View v) {
           onRecipeClicked.onRecipeClicked(getAdapterPosition());
       }
    }

    public void setRecipesList(List<recipes> recipesList) {
        this.recipesList = recipesList;
    }


    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
      //  View recipeItemView = mInflater.inflate(R.layout.recipe_item, viewGroup, false);
        View recipeItemView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.recipe_item, viewGroup, false);
        return new MyViewHolder(recipeItemView);
    }

    @Override
    public void onBindViewHolder( MyViewHolder myViewHolder, int i) {
        recipes recipes = recipesList.get(i);
        myViewHolder.tvRecipeName.setText(recipes.getName());

    }
    @Override
    public int getItemCount() {
        return recipesList.size();
    }


    public interface recipeClickListener{
        void onRecipeClicked(int position);
    }
}
