package org.jaalon.craftmanager.lib;

import org.jaalon.craftmanager.lib.repository.Repository;

public class ProductionBuilder {
    Repository repository;
    Production production;
    public ProductionBuilder() {
        repository = Repository.getInstance();
        production = new Production();
    }

    public ProductionBuilder setBlackLionPrice(int price) {
        production.setBlackLionPrice(price);
        return this;
    }

    public ProductionBuilder setName(String name) {
        production.setName(name);
        return this;
    }

    public ProductionBuilder addToRecipe(int number, String ingredient) {
        production.addToRecipe(number, ingredient);
        return this;
    }

    public ProductionBuilder addToRecipe(int number, Component ingredient) {
        production.addToRecipe(number, ingredient.getName());
        return this;
    }

    public ProductionBuilder setVendorPrice(int vendorPrice) {
        production.setVendorPrice(vendorPrice);
        return this;
    }

    public Production done() {
        assert production.getVendorPrice() != null || production.getLionPrice() != null;
        repository.addComponent(production);
        return production;
    }
}
