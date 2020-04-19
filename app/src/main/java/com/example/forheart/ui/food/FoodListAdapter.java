package com.example.forheart.ui.food;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.forheart.R;
import com.example.forheart.model.Food;

import java.util.List;

public class FoodListAdapter extends RecyclerView.Adapter<FoodListAdapter.FoodViewHolder> {
    private List<Food> allFoods;
    private final LayoutInflater layoutInflater;
    private Context mContext;

    public FoodListAdapter(Context context) {
        layoutInflater = LayoutInflater.from(context);
        mContext = context;
    }

    @NonNull
    @Override
    public FoodViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = layoutInflater.inflate(R.layout.cell_normal_food, parent, false);
        FoodViewHolder holder = new FoodViewHolder(itemView);
//        holder.textView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//            }
//        });
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull FoodViewHolder holder, int position) {
        if (allFoods!=null) {
            Food food = allFoods.get(position);
            holder.setData(food.getFoodName(),position);
        } else {
            holder.textView.setText("loading");
        }
//        holder.itemView.setTag(R.id.food_in_view_holder,food);
//        holder.textView.setText(food.getFoodName());
    }

    @Override
    public int getItemCount() {
        if (allFoods != null) {
            return allFoods.size();
        } else {
            return 0;
        }

    }

    public void setAllFoods(List<Food> allFood) {
        allFoods = allFood;
        notifyDataSetChanged();
    }

    static class FoodViewHolder extends RecyclerView.ViewHolder {
        private TextView textView;
        private int mPosition;
        FoodViewHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.textViewFoodName);
        }
        void setData(String foodName, int position){
            textView.setText(foodName);
            mPosition = position;
        }
    }
}
