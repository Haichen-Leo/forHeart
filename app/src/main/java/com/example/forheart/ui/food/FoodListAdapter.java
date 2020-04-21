package com.example.forheart.ui.food;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.forheart.R;
import com.example.forheart.model.Food;
import com.navigation.androidx.FragmentHelper;
import com.navigation.androidx.NavigationFragment;

import java.util.List;

public class FoodListAdapter extends RecyclerView.Adapter<FoodListAdapter.FoodViewHolder> {
    private List<Food> allFoods;
    private final LayoutInflater layoutInflater;
    private Context mContext;
    private NavigationFragment navigationFragment;

//    public FoodListAdapter(Context context, NavigationFragment navigationFragment) {

//        super(new DiffUtil.ItemCallback<Food>() {
//            @Override
//            public boolean areItemsTheSame(@NonNull Food oldItem, @NonNull Food newItem) {
//                return oldItem.getFoodId() == newItem.getFoodId();
//            }
//
//            @Override
//            public boolean areContentsTheSame(@NonNull Food oldItem, @NonNull Food newItem) {
//                return (oldItem.getFoodName().equals(newItem.getFoodName())
//                        && oldItem.getFolicAcid() == (newItem.getFolicAcid())
//                        && oldItem.getLycopene() == newItem.getLycopene());
//            }
//        });

//        layoutInflater = LayoutInflater.from(context);
//        mContext = context;
//        this.navigationFragment = navigationFragment;
//    }

    FoodListAdapter(Context context, NavigationFragment fragment) {
        layoutInflater = LayoutInflater.from(context);
        mContext = context;
        navigationFragment = fragment;
    }

    // Method navigate to food detail page
    @NonNull
    @Override
    public FoodViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = layoutInflater.inflate(R.layout.cell_normal_food, parent, false);
        FoodViewHolder holder = new FoodViewHolder(itemView);
        holder.textView.setOnClickListener(v -> {
            FoodDetailFragment fragment = new FoodDetailFragment();
            String foodId = (String) holder.itemView.getTag(R.id.food_in_view_holder);
            Bundle args = FragmentHelper.getArguments(fragment);
            args.putString(String.valueOf(R.string.nav_food_id), foodId);
            navigationFragment.pushFragment(fragment);
        });
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull FoodViewHolder holder, int position) {
        if (allFoods!=null) {
            Food food = allFoods.get(position);
            holder.setData(food.getFoodName(),position);
            holder.itemView.setTag(R.id.food_in_view_holder,food.getFoodId());
            holder.textView.setText(food.getFoodName());
        } else {
            holder.textView.setText("loading");
        }
    }

    @Override
    public int getItemCount() {
        if (allFoods != null) {
            return allFoods.size();
        } else {
            return 0;
        }
    }

    void setAllFoods(List<Food> allFood) {
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
