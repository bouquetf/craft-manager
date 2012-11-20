package org.jaalon.craftmanager.lib;

import org.jaalon.craftmanager.lib.repository.Repository;

public class Ingredient {
    int number;
    Component component;
    private Integer price;

    public Ingredient(int number, String component) {
        Repository repository = Repository.getInstance();
        this.component = repository.getComponent(component);
        this.number = number;
        this.price = null;
    }

    public int getNumber() {
        return number;
    }

    public String getName() {
        return component.getName();
    }

    public Component getComponent() {
        return component;
    }

    public int getPrice() {
        if (price == null) {
            price = number * this.component.getBestPrice();
        }
        return price;
    }

    public void addNumber(int number) {
        this.number += number;
    }

    public void setNumber(int number) {
        this.number = number;
    }
}
