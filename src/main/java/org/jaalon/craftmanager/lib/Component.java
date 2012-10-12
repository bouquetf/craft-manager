package org.jaalon.craftmanager.lib;

public class Component {
    private Integer lionPrice;
    private Integer vendorPrice;
    private String name;

    public void setBlackLionPrice(int lionPrice) {
        this.lionPrice = lionPrice;
    }

    public Integer getLionPrice() {
        return lionPrice;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public Integer getVendorPrice() {
        return vendorPrice;
    }

    public void setVendorPrice(int vendorPrice) {
        this.vendorPrice = vendorPrice;
    }

    public Integer getBestPrice() {
        return vendorIsBestPrice() ? vendorPrice : lionPrice;
    }

    private boolean vendorIsBestPrice() {
        return vendorPrice!=null && lionPrice != null && vendorPrice < lionPrice;
    }
}
