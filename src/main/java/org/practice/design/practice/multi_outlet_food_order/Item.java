package org.practice.design.practice.multi_outlet_food_order;

import java.util.Map;

public class Item {
    String id;
    String name;
    Map<Ingredient, Integer> recipe;
    double price;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Map<Ingredient, Integer> getRecipe() {
        return recipe;
    }

    public void setRecipe(Map<Ingredient, Integer> recipe) {
        this.recipe = recipe;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
