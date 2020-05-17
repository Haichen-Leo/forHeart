package com.example.forheart.ui.fitness;

import android.app.Activity;
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
import com.example.forheart.model.Exercise;
import com.example.forheart.model.ExerciseBean;
import com.navigation.androidx.NavigationFragment;

import java.util.List;

public class ExerciseListAdapter extends RecyclerView.Adapter<ExerciseListAdapter.ExerciseViewHolder>{

    private List<Exercise> allExercises;
    private final LayoutInflater layoutInflater;
    private Context mContext;
    private NavigationFragment navigationFragment;

    ExerciseListAdapter(Context context, NavigationFragment fragment) {
        layoutInflater = LayoutInflater.from(context);
        mContext = context;
        navigationFragment = fragment;
    }

    @NonNull
    @Override
    public ExerciseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = layoutInflater.inflate(R.layout.cell_card_exercise, parent, false);
        ExerciseViewHolder holder = new ExerciseViewHolder(itemView);
        holder.cardView.setOnClickListener(v -> {
            ExerciseBean bean = (ExerciseBean) holder.itemView.getTag(R.id.exercise_in_view_holder);
            Bundle result = new Bundle();
            result.putParcelable("suggest_food", bean);
            navigationFragment.setResult(Activity.RESULT_OK, result);
            navigationFragment.dismissFragment();
        });
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ExerciseViewHolder holder, int position) {
        if (allExercises != null) {
            Exercise exercise = allExercises.get(position);
            holder.setData(exercise.getName(), position);
            ExerciseBean bean = new ExerciseBean();
            bean.setName(exercise.getName());
            bean.setDuration(exercise.getDuration());
            holder.itemView.setTag(R.id.exercise_in_view_holder, bean);
        } else {
            holder.textView.setText("loading");
        }
    }

    public int getItemCount() {
        if (allExercises != null) {
            return allExercises.size();
        } else {
            return 0;
        }
    }

    void setAllExercise(List<Exercise> allExercise) {
        allExercises = allExercise;
        notifyDataSetChanged();
    }

    static class ExerciseViewHolder extends RecyclerView.ViewHolder {
        private TextView textView;
        private CardView cardView;
        private int mPosition;
        ExerciseViewHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.textView_exercise);
            cardView = itemView.findViewById(R.id.cardView_exercise);
        }
        void setData(String exerciseName, int position) {
            textView.setText(exerciseName);
            mPosition = position;
        }
    }
}
