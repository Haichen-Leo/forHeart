package com.example.forheart.ui.food;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.forheart.R;
import com.example.forheart.model.FoodGroup;
import com.navigation.androidx.FragmentHelper;
import com.navigation.androidx.NavigationFragment;

import java.util.List;

public class FoodGroupAdapter extends RecyclerView.Adapter<FoodGroupAdapter.GroupViewHolder> {
    private List<FoodGroup> allFoodGroups;
    private final LayoutInflater layoutInflater;
    private Context mContext;
    private NavigationFragment navigationFragment;

    FoodGroupAdapter(Context context, NavigationFragment navigationFragment) {
        layoutInflater = LayoutInflater.from(context);
        mContext = context;
        this.navigationFragment = navigationFragment;
    }

    void setAllFoodGroups(List<FoodGroup> allFoodGroups) {
        this.allFoodGroups = allFoodGroups;
        notifyDataSetChanged();
    }

    /**
     * Method to configure view holder for food group
     */
    @NonNull
    @Override
    public GroupViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View itemView = layoutInflater.inflate(R.layout.cell_card_foodgroup, parent, false);
        GroupViewHolder holder = new GroupViewHolder(itemView);
        holder.cardView.setOnClickListener(v -> {
            int id = (int) holder.itemView.getTag(R.id.food_group_in_view_holder);
            FoodListFragment listFragment = new FoodListFragment();
            Bundle args = FragmentHelper.getArguments(listFragment);
            args.putInt(String.valueOf(R.string.nav_food_group_id),id);
            navigationFragment.pushFragment(listFragment);
        });
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull GroupViewHolder holder, int position) {
        if (allFoodGroups!=null){
            FoodGroup foodGroup = allFoodGroups.get(position);
            holder.setData(foodGroup.getFoodGroupName(), position);
            holder.itemView.setTag(R.id.food_group_in_view_holder,foodGroup.getFoodGroupId());
//            holder.textView.setText(foodGroup.getFoodGroupName());
        } else {
            holder.textView.setText("loading");
        }
    }

    @Override
    public int getItemCount() {
        if (allFoodGroups != null) {
            return allFoodGroups.size();
        } else {
            return 0;
        }
    }

    /**
     *  Self-defined view holder for food group
     */
    static class GroupViewHolder extends RecyclerView.ViewHolder {
        private CardView cardView;
        TextView textView;
        private int mPosition;
        GroupViewHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.textViewGroupName);
            cardView = itemView.findViewById(R.id.cardView_foodgroup);
        }
        void setData(String foodGroupName, int position){
            textView.setText(foodGroupName);
            mPosition = position;
        }
    }
}
