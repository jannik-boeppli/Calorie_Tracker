package com.accenture.calorie_tracker.domain.nutrition;

import com.accenture.calorie_tracker.core.generic.AbstractEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.UUID;

@Entity
@Table(name = "nutrition")
public class Nutrition extends AbstractEntity {
    @Column(name = "calories")
    private int calories;

    @Column(name = "carbs")
    private int carbs;

    @Column(name = "fat")
    private int fat;

    @Column(name = "protein")
    private int protein;

    public Nutrition(int calories, int carbs, int fat, int protein) {
        this.calories = calories;
        this.carbs = carbs;
        this.fat = fat;
        this.protein = protein;
    }

    public Nutrition() {
    }

    @Override
    public Nutrition setId(UUID id) {
        this.id = id;
        return this;
    }

    public int getCalories() {
        return calories;
    }

    public Nutrition setCalories(int calories) {
        this.calories = calories;
        return this;
    }

    public int getCarbs() {
        return carbs;
    }

    public Nutrition setCarbs(int carbs) {
        this.carbs = carbs;
        return this;
    }

    public int getFat() {
        return fat;
    }

    public Nutrition setFat(int fat) {
        this.fat = fat;
        return this;
    }

    public int getProtein() {
        return protein;
    }

    public Nutrition setProtein(int protein) {
        this.protein = protein;
        return this;
    }
}
