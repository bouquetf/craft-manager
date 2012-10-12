package org.jaalon.craftmanager.lib;

import org.junit.Test;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertTrue;


public class FunctionalTest {
    @Test
    public void getBoltOfLinenRecipe() {
        Component linenScrap = new ComponentBuilder().setName("Linen scrap").setBlackLionPrice(20).done();
        Production boltOfLinen = new ProductionBuilder().setName("Bolt of linen").setBlackLionPrice(41)
                .addToRecipe(2, linenScrap).done();

        assertEquals("2 Linen scrap", boltOfLinen.getIngredientsString());
    }

}
