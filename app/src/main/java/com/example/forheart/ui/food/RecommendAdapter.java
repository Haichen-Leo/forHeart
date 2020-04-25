package com.example.forheart.ui.food;

import android.app.Activity;
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
import com.example.forheart.model.FoodBean;

import java.util.List;

public class RecommendAdapter extends RecyclerView.Adapter<RecommendAdapter.RecommendViewHolder>{
    private List<Food> recommendFoods;
    private final LayoutInflater layoutInflater;
    private RecommandFragment fragment;
    static final String KEY_VALUE = "groupId";

    RecommendAdapter(Context context, RecommandFragment fragment) {

        layoutInflater = LayoutInflater.from(context);
        this.fragment = fragment;
    }


    @NonNull
    @Override
    public RecommendViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = layoutInflater.inflate(R.layout.cell_normal_food, parent, false);
        RecommendViewHolder holder = new RecommendViewHolder(itemView);
        holder.textView.setOnClickListener(v -> {
            FoodBean foodBean = (FoodBean) holder.itemView.getTag(R.id.food_in_view_holder);
            Bundle data = new Bundle();
            data.putParcelable(KEY_VALUE, foodBean);
            fragment.setResult(Activity.RESULT_OK, data);
            fragment.hideDialog();
        });
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecommendAdapter.RecommendViewHolder holder, int position) {
        if (recommendFoods!=null) {
            Food food = recommendFoods.get(position);
            holder.setData(food.getFoodName(),position);
//            holder.textView.setText(food.getFoodName());
            FoodBean foodBean = new FoodBean();
            foodBean.createFoodBean(food);
            holder.itemView.setTag(R.id.food_in_view_holder, foodBean);
        } else {
            holder.textView.setText("loading");
        }
    }

    @Override
    public int getItemCount() {
        if (recommendFoods != null) {
            return recommendFoods.size();
        } else {
            return 0;
        }
    }

    void setAllFoods(List<Food> allFoods) {
        recommendFoods = allFoods;
        notifyDataSetChanged();
    }

    static class RecommendViewHolder extends RecyclerView.ViewHolder{
        private TextView textView;
        private int mPosition;
        RecommendViewHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.textViewFoodName);
        }
        void setData(String foodName, int position){
            textView.setText(foodName);
            mPosition = position;
        }

    }
}
