package com.example.forheart.ui.fitness;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.forheart.R;
import com.example.forheart.model.Plan;
import com.example.forheart.util.DateTimeUtils;
import com.navigation.androidx.FragmentHelper;
import com.navigation.androidx.NavigationFragment;

import java.util.List;

/**
 * Adapter for plan list
 */
public class PlanAdapter extends RecyclerView.Adapter<PlanAdapter.PlanViewHolder> {
    private List<Plan> allPlans;
    private final LayoutInflater layoutInflater;
    private Context mContext;
    private NavigationFragment navigationFragment;

    PlanAdapter(Context context, NavigationFragment fragment) {
        layoutInflater = LayoutInflater.from(context);
        mContext = context;
        navigationFragment = fragment;
    }

    @NonNull
    @Override
    public PlanViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = layoutInflater.inflate(R.layout.cell_card_plan, parent, false);
        PlanViewHolder holder = new PlanViewHolder(itemView);
        // listener on plan card, navigate to action page
        holder.cardView.setOnClickListener(v -> {
            int planId = (int) holder.itemView.getTag(R.id.plan_in_view_holder);
            ActionFragment fragment = new ActionFragment();
            Bundle args = FragmentHelper.getArguments(fragment);
            args.putInt(String.valueOf(R.string.nav_plan_id), planId);
            navigationFragment.pushFragment(fragment);
        });
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull PlanViewHolder holder, int position) {
        if (allPlans != null) {
            Plan plan = allPlans.get(position);
            String duration = plan.getDuration() + " min";
            String datetime = plan.getDay() + " " + plan.getMonth() + " " + plan.getHour() + " " + plan.getMinute();
            holder.setData(plan.getActivity(),duration, DateTimeUtils.parseDateTimeFormat(datetime), position);
            holder.itemView.setTag(R.id.plan_in_view_holder, plan.getId());

            // check status to change card style
            if (plan.isDoing()) {
                holder.setDoing();
            } else {
                if(plan.isDone()) {
                    holder.setDone();
                }
                else {
                    holder.setNotDone();
                }
            }
        }
    }

    @Override
    public int getItemCount() {
        return allPlans.size();
    }

    void setAllPlans(List<Plan> allPlans) {
        this.allPlans = allPlans;
        notifyDataSetChanged();
    }

    static class PlanViewHolder extends RecyclerView.ViewHolder{
        private CardView cardView;
        private TextView activityView;
        private TextView durationView;
        private TextView dateTimeView;
        private int mPosition;

        // demonstration of viewholder in recycler view
        PlanViewHolder(@NonNull View itemView) {
            super(itemView);
            cardView = itemView.findViewById(R.id.cardView_plan);
            activityView = itemView.findViewById(R.id.textView_card_activity);
            durationView = itemView.findViewById(R.id.textView_card_duration);
            dateTimeView = itemView.findViewById(R.id.textView_card_datetime);
        }
        void setData(String planActivity, String planDuration, String datetime, int position) {
            activityView.setText(planActivity);
            durationView.setText(planDuration);
            dateTimeView.setText(datetime);
            mPosition = position;
        }
        // set card background to grey when plan is done
        void setDone() {
            cardView.setBackgroundColor(Color.parseColor("#e0e0e0"));
            activityView.setTextColor(Color.parseColor("#7A7A7A"));
            dateTimeView.setTextColor(Color.parseColor("#7A7A7A"));
            durationView.setTextColor(Color.parseColor("#7A7A7A"));
        }
        // set card background to white if not done
        void setNotDone() {
            cardView.setBackgroundColor(Color.parseColor("#FFFFFFFF"));
            activityView.setTextColor(Color.parseColor("#000000"));
            dateTimeView.setTextColor(Color.parseColor("#000000"));
            durationView.setTextColor(Color.parseColor("#000000"));
        }

        // set card background to green if doing
        void setDoing() {
            cardView.setBackgroundColor(Color.parseColor("#F5A391"));
            activityView.setTextColor(Color.parseColor("#000000"));
            dateTimeView.setTextColor(Color.parseColor("#000000"));
            durationView.setTextColor(Color.parseColor("#000000"));
            dateTimeView.setText("Activity Started");
        }
    }
}
