package com.example.forheart.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Plan {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "plan_id")
    private int id;
    @ColumnInfo(name = "plan_activity")
    private String activity;
    @ColumnInfo(name = "plan_type")
    private String type;
    @ColumnInfo(name = "plan_year")
    private int year;
    @ColumnInfo(name = "plan_month")
    private int month;
    @ColumnInfo(name = "plan_day")
    private int day;
    @ColumnInfo(name = "plan_hour")
    private int hour;
    @ColumnInfo(name = "plan_minute")
    private int minute;
    @ColumnInfo(name = "plan_duration")
    private int duration;
    @ColumnInfo(name = "plan_description")
    private String description;
    @ColumnInfo(name = "plan_is_done")
    private boolean isDone;

    public Plan(){}

    public Plan(String activity, String type, int year, int month, int day, int hour, int minute, int duration, String description, boolean isDone) {
        this.activity = activity;
        this.type = type;
        this.year = year;
        this.month = month;
        this.day = day;
        this.hour = hour;
        this.minute = minute;
        this.duration = duration;
        this.description = description;
        this.isDone = isDone;
    }

    public int getId() {
        return id;
    }

    public String getActivity() {
        return activity;
    }

    public int getYear() {
        return year;
    }

    public int getMonth() {
        return month;
    }

    public int getDay() {
        return day;
    }

    public int getHour() {
        return hour;
    }

    public int getMinute() {
        return minute;
    }

    public int getDuration() {
        return duration;
    }

    public String getDescription() {
        return description;
    }

    public boolean isDone() {
        return isDone;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setActivity(String activity) {
        this.activity = activity;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public void setHour(int hour) {
        this.hour = hour;
    }

    public void setMinute(int minute) {
        this.minute = minute;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setDone(boolean done) {
        isDone = done;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
