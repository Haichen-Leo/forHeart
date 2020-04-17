package com.example.forheart.ui.food;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.forheart.R;
import com.example.forheart.model.FoodGroup;

import java.util.ArrayList;
import java.util.List;

public class FoodAdapter extends RecyclerView.Adapter<FoodAdapter.GroupViewHolder> {
    private List<FoodGroup> allFoodGroups = new ArrayList<>();

    public void setAllFoodGroups(List<FoodGroup> allFoodGroups) {
        this.allFoodGroups = allFoodGroups;
    }

    @NonNull
    @Override
    public GroupViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View itemView = layoutInflater.inflate(R.layout.cell_card_foodgroup, parent, false);
        return new GroupViewHolder((itemView));
    }

    @Override
    public void onBindViewHolder(@NonNull GroupViewHolder holder, int position) {
        FoodGroup foodGroup = allFoodGroups.get(position);
        holder.textView.setText(foodGroup.getFoodGroupName());
    }

    @Override
    public int getItemCount() {
        return allFoodGroups.size();
    }

    static class GroupViewHolder extends RecyclerView.ViewHolder {
        TextView textView;
        GroupViewHolder(@NonNull View itemView) {
            super(itemView);
            itemView.findViewById(R.id.textViewGroupName);
        }
    }
}
