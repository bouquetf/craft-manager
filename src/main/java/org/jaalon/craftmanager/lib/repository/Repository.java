package org.jaalon.craftmanager.lib.repository;

import org.jaalon.craftmanager.lib.Component;
import org.jaalon.craftmanager.lib.ComponentBuilder;
import org.jaalon.craftmanager.lib.Production;

import java.util.HashMap;

public class Repository {
    private static Repository ourInstance = new Repository();

    public static Repository getInstance() {
        return ourInstance;
    }

    private HashMap<String, Component> components;

    private Repository() {
        this.components = new HashMap<String, Component>();
    }

    public void addComponent(Component component) {
        String componentName = component.getName();
        if (components.containsKey(componentName)) {
            Component componentFromRepository = components.get(componentName);
            components.remove(componentName);
            Integer blacklionPrice = componentFromRepository.getLionPrice();
            if (component.getLionPrice() != null) blacklionPrice = component.getLionPrice();

            Integer vendorPrice = componentFromRepository.getVendorPrice();
            if (component.getVendorPrice() != null) vendorPrice = component.getVendorPrice();

            new ComponentBuilder()
                    .setName(componentName)
                    .setBlackLionPrice(blacklionPrice)
                    .setVendorPrice(vendorPrice)
                    .done();
        } else {
            components.put(componentName, component);
        }
    }

    public void addProduction(Production production) {
        components.put(production.getName(), production);
    }

    public Component getComponent(String name) {
        return components.get(name);
    }

    public boolean contains(String content) {
        return components.keySet().contains(content);
    }

    @Override
    public String toString() {
        String contents = "";
        for (String key : components.keySet()) {
            contents += key + " : " + components.get(key).toString() + "\n";
        }
        return contents;
    }
}
