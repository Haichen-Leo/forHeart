package com.example.forheart.model;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;

public class ExerciseBean implements Parcelable {
    private String name;
    private int duration;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        Bundle bundle = new Bundle();
        bundle.putString("name", getName());
        bundle.putInt("duration", getDuration());
        dest.writeBundle(bundle);
    }

    public static final Creator<ExerciseBean> CREATOR = new Creator<ExerciseBean>() {
        @Override
        public ExerciseBean createFromParcel(Parcel source) {
            Bundle bundle = source.readBundle();
            ExerciseBean bean = new ExerciseBean();
            bean.setName(bundle.getString("name"));
            bean.setDuration(bundle.getInt("duration"));
            return bean;
        }

        @Override
        public ExerciseBean[] newArray(int size) {
            return new ExerciseBean[size];
        }
    };
}
