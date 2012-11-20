package org.jaalon.craftmanager.lib;

public class Production extends Component {
    Recipe recipe;

    public Production() {
        super();
        recipe = new Recipe();
    }

    public void addToRecipe(int number, String component) {
        Ingredient ingredient = new Ingredient(number, component);
        recipe.add(ingredient);
    }

    public String toString() {
        return recipe.toString();
    }

    public Recipe getBestPricedRecipe() {
        return recipe.getBestPricedRecipe();
    }

    public Recipe getRecipe() {
        return recipe;
    }

    public void setRecipe(Recipe recipe) {
        this.recipe = recipe;
    }
}
