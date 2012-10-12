package org.jaalon.craftmanager.lib;

public class Production extends Component {
    Recipe recipe;

    public Production() {
        super();
        recipe = new Recipe();
    }

    public void addToRecipe(int number, Component component) {
        Ingredient ingredient = new Ingredient(number, component);
        recipe.add(ingredient);
    }

    public String getIngredientsString() {
        return recipe.toString();
    }

    public Recipe getBestPricedRecipe() {
        return recipe.getBestPricedRecipe();

    }
}
