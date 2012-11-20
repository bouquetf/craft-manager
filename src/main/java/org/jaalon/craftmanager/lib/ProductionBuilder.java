package org.jaalon.craftmanager.lib;

import org.jaalon.craftmanager.lib.repository.Repository;

public class ProductionBuilder {
    Repository repository;
    Production production;
    public ProductionBuilder() {
        repository = Repository.getInstance();
        production = new Production();
    }

    public ProductionBuilder setBlackLionPrice(Integer price) {
        production.setBlackLionPrice(price);
        return this;
    }

    public ProductionBuilder setName(String name) {
        production.setName(name);
        return this;
    }

    public ProductionBuilder addToRecipe(Integer number, String ingredient) {
        production.addToRecipe(number, ingredient);
        return this;
    }

    public ProductionBuilder setVendorPrice(Integer vendorPrice) {
        production.setVendorPrice(vendorPrice);
        return this;
    }

    public ProductionBuilder setRecipe(Recipe recipe) {
        production.setRecipe(recipe);
        return this;
    }

    public Production done() {
        repository.addProduction(production);
        return production;
    }
}
