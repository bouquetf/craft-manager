package org.jaalon.craftmanager.lib.repository;

import org.jaalon.craftmanager.lib.Component;

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
        components.put(component.getName(), component);
    }

    public void getComponent(String name) {
        components.get(name);
    }
}
