package org.jaalon.craftmanager.lib;

import org.jaalon.craftmanager.lib.repository.Repository;

public class ComponentBuilder {
    Repository repository;
    Component component;
    public ComponentBuilder() {
        repository = Repository.getInstance();
        component = new Component();
    }

    public ComponentBuilder setVendorPrice(int vendorPrice) {
        component.setVendorPrice(vendorPrice);
        return this;
    }

    public ComponentBuilder setBlackLionPrice(int price) {
        component.setBlackLionPrice(price);
        return this;
    }

    public ComponentBuilder setName(String name) {
        component.setName(name);
        return this;
    }

    public Component done() {
        repository.addComponent(component);
        return component;
    }
}
