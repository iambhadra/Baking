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
import com.example.baking.models.stepsitem;

import java.util.ArrayList;
import java.util.List;

public class StepsAdapter extends RecyclerView.Adapter<StepsAdapter.MyViewHolder> {

    TextView tv_step_item;
    //  LayoutInflater mLayoutInflater;
    List<stepsitem> steps = new ArrayList<>();
    private onStepClickListener onStepClicking;

    public StepsAdapter(IngredientStepsFragment context){
        //   mLayoutInflater = LayoutInflater.from(context.getContext());
        onStepClicking = context;
        notifyDataSetChanged();

    }

    public void setStepItems(List<stepsitem> list){
        this.steps = list;
    }
    public class MyViewHolder extends RecyclerView.ViewHolder  implements View.OnClickListener{
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_step_item = itemView.findViewById(R.id.tv_item_name);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            onStepClicking.onStepClicked(getAdapterPosition());
        }
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.ingredients_item,viewGroup,false);
        return new StepsAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {
        stepsitem steps = this.steps.get(i);
        tv_step_item.setText(steps.getShortDescription());
    }




    @Override
    public int getItemCount() {
        return steps.size();
    }

    public interface onStepClickListener{
        void onStepClicked(int iPosition);
    }



}
