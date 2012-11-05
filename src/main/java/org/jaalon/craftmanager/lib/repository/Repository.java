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
