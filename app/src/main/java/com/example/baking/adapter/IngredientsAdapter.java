package com.example.baking.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.baking.R;
import com.example.baking.fragment.IngredientStepsFragment;
import com.example.baking.models.ingredients;

import java.util.ArrayList;
import java.util.List;

public class IngredientsAdapter extends RecyclerView.Adapter<IngredientsAdapter.MyViewHolder> {

    TextView tv_quantity,tv_item_name,tv_measure;
  //  LayoutInflater mLayoutInflater;
    List<ingredients> ingredients = new ArrayList<>();

    public IngredientsAdapter(IngredientStepsFragment context){
     //   mLayoutInflater = LayoutInflater.from(context.getContext());
        notifyDataSetChanged();

    }

    public void setIngredientItem(List<ingredients> list){
        this.ingredients = list;
    }
    public class MyViewHolder extends RecyclerView.ViewHolder {
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_quantity = itemView.findViewById(R.id.tv_item_qauntity);
            tv_item_name = itemView.findViewById(R.id.tv_item_name);
            tv_measure =itemView.findViewById(R.id.tv_item_measure_type);
        }
    }

    @NonNull
    @Override
    public IngredientsAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.ingredients_item,viewGroup,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull IngredientsAdapter.MyViewHolder myViewHolder, int i) {
        ingredients ingredients = this.ingredients.get(i);
        String quantity = Float.toString(ingredients.getQuantity());
        String measure = ingredients.getMeasure();
        String ingredient = ingredients.getIngredient();
        tv_quantity.setText((quantity==null || quantity.isEmpty()) ? "NA":quantity);
        tv_measure.setText((measure == null || measure.isEmpty())?"NA":measure);
        tv_item_name.setText((ingredient == null || ingredient.isEmpty())? "NA":ingredient);

    }

    @Override
    public int getItemCount() {
        return ingredients.size();
    }


}
