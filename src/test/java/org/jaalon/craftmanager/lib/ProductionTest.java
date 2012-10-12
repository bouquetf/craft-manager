package org.jaalon.craftmanager.lib;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ProductionTest {
    @Test
    public void testGetRecipeForSimpleRecipe() {
        Component linenScrap = new ComponentBuilder().setName("Linen scrap").setBlackLionPrice(20).done();
        Production boltOfLinen = new ProductionBuilder().setName("Bolt of linen").setBlackLionPrice(41)
                .addToRecipe(2, linenScrap).done();

        assertEquals("2 Linen scrap", boltOfLinen.getIngredientsString());
    }

    @Test
    public void testGetRecipeForComplexRecipe() {
        Component juteScrap = new ComponentBuilder().setName("Jute scrap").setBlackLionPrice(0).done();
        Production boltOfJute = new ProductionBuilder().setName("Bolt of jute").setBlackLionPrice(0)
                .addToRecipe(2, juteScrap).done();
        Component pileOfGlitteringDust = new ComponentBuilder().setName("Pile of Glittering Dust").setBlackLionPrice(0)
                .done();
        Production eightSlotInvisibleBag = new ProductionBuilder().setName("Eight Slot Invisible Bag")
                .setBlackLionPrice(0).addToRecipe(10, boltOfJute).addToRecipe(3, pileOfGlitteringDust).done();

        assertEquals("10 Bolt of jute, 3 Pile of Glittering Dust", eightSlotInvisibleBag.getIngredientsString());
    }

    @Test
    public void testGetBestPricedRecipeForSimpleRecipe() {
        Component linenScrap = new ComponentBuilder().setName("Linen scrap").setBlackLionPrice(20).done();
        Production boltOfLinen = new ProductionBuilder().setName("Bolt of linen").setBlackLionPrice(41)
                .addToRecipe(2, linenScrap).done();

        Recipe bestPricedRecipe = boltOfLinen.getBestPricedRecipe();
        assertEquals("2 Linen scrap", bestPricedRecipe.toString());
        assertEquals(40, bestPricedRecipe.getBestPrice());
    }

    @Test
    public void testGetBestPricedRecipeForComplexeRecipe() {
        Component chute = new ComponentBuilder().setName("Chute de lin").setBlackLionPrice(19).done();
        Component segment = new ComponentBuilder().setName("Segment de cuir coriace").setBlackLionPrice(13).done();
        Production rouleau = new ProductionBuilder().setName("Rouleau de lin").setBlackLionPrice(41)
                .addToRecipe(2, chute).done();
        Production morceauDeCuir = new ProductionBuilder().setName("Morceau de cuir coriace traité")
                .setBlackLionPrice(29).addToRecipe(2, segment).done();
        Component bobine = new ComponentBuilder().setName("Bobine de fil de lin").setBlackLionPrice(38)
                .setVendorPrice(32).done();
        Component griffe = new ComponentBuilder().setName("Griffe acérée").setBlackLionPrice(24).done();
        Production insigne = new ProductionBuilder().setName("Insigne d'explorateur en lin brodé").setBlackLionPrice(398)
                .addToRecipe(1, rouleau).addToRecipe(5, bobine).addToRecipe(8, griffe).done();
        Production doublure = new ProductionBuilder().setName("Doublure de manteau en lin").setBlackLionPrice(44)
                .addToRecipe(1, rouleau).done();
        Production panneau = new ProductionBuilder().setName("Panneau de manteau en lin").setBlackLionPrice(430)
                .addToRecipe(4, rouleau).addToRecipe(1, morceauDeCuir).addToRecipe(3, bobine).done();
        Production tunique = new ProductionBuilder().setName("Tunique d'explorateur ailée (Maître)")
                .addToRecipe(1, insigne).addToRecipe(1, doublure).setBlackLionPrice(84)
                .addToRecipe(1, panneau).done();

        Recipe recipe = tunique.getBestPricedRecipe();
        String bestRecipe = "8 Chute de lin, 5 Bobine de fil de lin," +
                " 8 Griffe acérée, 2 Segment de cuir coriace, 3 Bobine de fil de lin";
        int bestPrice = 588;
        assertEquals(bestRecipe, recipe.toString());
        assertEquals(bestPrice, recipe.getBestPrice());
    }
}
