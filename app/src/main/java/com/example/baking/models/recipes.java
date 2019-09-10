package com.example.baking.models;

import java.util.List;

public class recipes {
    int id;
    String name;
    List<ingredients> ingredients;
    List<stepsitem> steps;
    int servings;
    String image;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<ingredients> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<ingredients> ingredients) {
        this.ingredients = ingredients;
    }

    public List<stepsitem> getStepsitem() {
        return steps;
    }

    public void setStepsitem(List<stepsitem> stepsitem) {
        this.steps = stepsitem;
    }

    public int getServings() {
        return servings;
    }

    public void setServings(int servings) {
        this.servings = servings;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

}
