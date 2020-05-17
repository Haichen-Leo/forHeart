package com.example.forheart.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Exercise {

    @PrimaryKey
    @ColumnInfo(name = "exercise_id")
    private int id;
    @ColumnInfo(name = "exercise_type")
    private String type;
    @ColumnInfo(name = "exercise_category")
    private String category;
    @ColumnInfo(name = "exercise_name")
    private String name;
    @ColumnInfo(name = "exercise_duration")
    private int duration;

    public Exercise(int id, String type, String category, String name, int duration) {
        this.id = id;
        this.type = type;
        this.category = category;
        this.name = name;
        this.duration = duration;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

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
}
