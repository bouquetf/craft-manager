package org.jaalon.craftmanager.lib;

public class ComponentBuilder {
    Component component;
    public ComponentBuilder() {
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
        assert (component.getLionPrice() != null) || (component.getVendorPrice() != null);
        return component;
    }
}
