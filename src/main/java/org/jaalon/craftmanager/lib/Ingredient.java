package org.jaalon.craftmanager.lib;

public class Ingredient {
    int number;
    Component component;
    private int price;

    public Ingredient(int number, Component component) {
        this.component = component;
        this.number = number;
        this.price = number * component.getBestPrice();
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
        return price;
    }

    public void addNumber(int number) {
        this.number += number;
    }
}
