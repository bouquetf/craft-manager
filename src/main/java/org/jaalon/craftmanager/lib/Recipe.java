package org.jaalon.craftmanager.lib;

import java.util.ArrayList;
import java.util.List;

public class Recipe {
    List<Ingredient> ingredientList;
    private int price;

    public Recipe() {
        ingredientList = new ArrayList<Ingredient>();
        price = 0;
    }

    public void add(Ingredient ingredient) {
        ingredientList.add(ingredient);
        price += ingredient.getPrice();
    }

    public String toString() {
        String recipeString = "";
        for (Ingredient ingredient : ingredientList) {
            recipeString += ingredient.getNumber() + " " + ingredient.getName() + ", ";
        }

        return recipeString.substring(0, recipeString.length() - 2);
    }

    public int getBestPrice() {
        return price;
    }

    public Recipe getBestPricedRecipe() {
        Recipe bestPricedRecipe = new Recipe();
        for (Ingredient ingredient : ingredientList) {
            Recipe bestRecipe;
            if (ingredient.getComponent() instanceof Production) {
                bestRecipe = bestRecipeBetweenProductionAndItsSubRecipe(ingredient);
            } else {
                bestRecipe = new Recipe();
                bestRecipe.add(ingredient);
            }
            bestPricedRecipe.addAll(bestRecipe);
        }
        return bestPricedRecipe;
    }

    private void addAll(Recipe recipe) {
        for (Ingredient ingredient : recipe.getAllIngredients()) {
            int index = ingredientList.indexOf(ingredient);
            if (index >= 0) {
                ingredientList.get(index).addNumber(ingredient.getNumber());
                price+=ingredient.getPrice();
            }   else {
                add(ingredient);
            }
        }
    }

    private List<Ingredient> getAllIngredients() {
        return ingredientList;
    }

    private Recipe bestRecipeBetweenProductionAndItsSubRecipe(Ingredient ingredient) {
        Recipe bestRecipe;
        Production ingredientProduction = (Production) ingredient.getComponent();
        Recipe bestPricedIngredientRecipe = ingredientProduction.getBestPricedRecipe();
        if (ingredient.getPrice()< ingredient.getPrice() * bestPricedIngredientRecipe.getBestPrice()) {
            bestRecipe = new Recipe();
            bestRecipe.add(ingredient);
        } else {
            bestRecipe = bestPricedIngredientRecipe.multiplyBy(ingredient.getNumber());
        }
        return bestRecipe;
    }

    private Recipe multiplyBy(int number) {
        for (Ingredient ingredient : ingredientList) {
            ingredient.setNumber(ingredient.getNumber()*number);
        }
        return this;
    }

}
