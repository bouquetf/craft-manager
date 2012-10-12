package org.jaalon.craftmanager.lib;

/**
 * Copyright (C) 2012 BonitaSoft S.A.
 * BonitaSoft, 31 rue Gustave Eiffel - 38000 Grenoble
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 2.0 of the License, or
 * (at your option) any later version.
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */
public class ProductionBuilder {
    Production production;
    public ProductionBuilder() {
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

    public ProductionBuilder addToRecipe(int number, Component ingredientType) {
        production.addToRecipe(number, ingredientType);
        return this;
    }

    public ProductionBuilder setVendorPrice(int vendorPrice) {
        production.setVendorPrice(vendorPrice);
        return this;
    }

    public Production done() {
        assert production.getVendorPrice() != null || production.getLionPrice() != null;
        return production;
    }
}
