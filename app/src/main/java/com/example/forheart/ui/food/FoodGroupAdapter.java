package com.example.forheart.ui.food;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.example.forheart.R;
import com.example.forheart.model.FoodGroup;

import java.util.ArrayList;
import java.util.List;

public class FoodGroupAdapter extends RecyclerView.Adapter<FoodGroupAdapter.GroupViewHolder> {
    private List<FoodGroup> allFoodGroups = new ArrayList<>();
//    private FoodViewModel foodViewModel;


    public FoodGroupAdapter() {
    }

    public void setAllFoodGroups(List<FoodGroup> allFoodGroups) {
        this.allFoodGroups = allFoodGroups;
    }

    /**
     * Method to configure view holder for food group
     * @param parent
     * @param viewType
     * @return
     */
    @NonNull
    @Override
    public GroupViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View itemView = layoutInflater.inflate(R.layout.cell_card_foodgroup, parent, false);
        final GroupViewHolder holder = new GroupViewHolder(itemView);
        holder.textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                int id = (int) holder.itemView.getTag(R.id.food_group_in_view_holder);
                bundle.putInt(String.valueOf(R.string.nav_food_id), id);
                NavController navController = Navigation.findNavController(v);
                navController.navigate(R.id.action_navigation_food_to_foodListFragment,bundle);
            }
        });
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull GroupViewHolder holder, int position) {
        final FoodGroup foodGroup = allFoodGroups.get(position);
        // save foodgroup object in tag for navigation
        holder.itemView.setTag(R.id.food_group_in_view_holder,foodGroup.getFoodGroupId());
        holder.textView.setText(foodGroup.getFoodGroupName());
    }

    @Override
    public int getItemCount() {
        return allFoodGroups.size();
    }

    /**
     *  Self-defined view holder for food group
     */
    static class GroupViewHolder extends RecyclerView.ViewHolder {
        TextView textView;
        GroupViewHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.textViewGroupName);
        }
    }
}
