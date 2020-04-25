package com.example.forheart.ui.fitness;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.forheart.R;
import com.example.forheart.model.Plan;
import com.navigation.androidx.NavigationFragment;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

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
        holder.cardView.setOnClickListener(v -> {
            Toast.makeText(mContext, "success", Toast.LENGTH_SHORT).show();
        });
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull PlanViewHolder holder, int position) {
        if (allPlans != null) {
            Plan plan = allPlans.get(position);
            String duration = plan.getDuration() + " min";
            String datetime = plan.getDay() + " " + plan.getMonth() + " " + plan.getHour() + " " + plan.getMinute();

            holder.setData(plan.getActivity(),duration, parseDateFormat(datetime), position);

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

        PlanViewHolder(@NonNull View itemView) {
            super(itemView);
            cardView = itemView.findViewById(R.id.cardView_plan);
            activityView = itemView.findViewById(R.id.textView_card_activity);
            durationView = itemView.findViewById(R.id.textView_card_duration);
            dateTimeView = itemView.findViewById(R.id.textView_card_datetime);
        }
        void setData(String planActivity, String planDuration, String datetime,int position) {
            activityView.setText(planActivity);
            durationView.setText(planDuration);
            dateTimeView.setText(datetime);
            mPosition = position;
        }
    }

    // parse date format
    private String parseDateFormat(String input) {
        SimpleDateFormat fromFormat = new SimpleDateFormat("d M HH mm");
        SimpleDateFormat toFormat = new SimpleDateFormat("d MMM HH:mm");
        try {
            String reformattedStr = toFormat.format(fromFormat.parse(input));
            return reformattedStr;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return "Null";
    }
}
