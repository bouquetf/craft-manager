package org.jaalon.craftmanager.lib;

import org.jaalon.craftmanager.lib.repository.Repository;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ProductionTest {

    public static final String BOLT_OF_LINEN = "Bolt of linen";
    public static final String LINEN_SCRAP = "Linen scrap";
    public static final String JUTE_SCRAP = "Jute scrap";
    public static final String BOLT_OF_JUTE = "Bolt of jute";
    public static final String PILE_OF_GLITTERING_DUST = "Pile of Glittering Dust";
    public static final String EIGHT_SLOT_INVISIBLE_BAG = "Eight Slot Invisible Bag";
    private Repository repository;

    @Before
    public void setUp() {
        this.repository = Repository.getInstance();
    }

    @Test
    public void testGetRecipeForSimpleRecipe() {
        new ComponentBuilder().setName(LINEN_SCRAP).setBlackLionPrice(20).done();
        new ProductionBuilder().setName(BOLT_OF_LINEN).setBlackLionPrice(41).addToRecipe(2, LINEN_SCRAP).done();

        Production production = (Production) repository.getComponent(BOLT_OF_LINEN);
        assertEquals("2 Linen scrap", production.getIngredientsString());
    }

    @Test
    public void testGetRecipeForComplexRecipe() {
        new ComponentBuilder().setName(JUTE_SCRAP).setBlackLionPrice(2).done();
        new ProductionBuilder().setName(BOLT_OF_JUTE).setBlackLionPrice(3).addToRecipe(2, JUTE_SCRAP).done();
        new ComponentBuilder().setName(PILE_OF_GLITTERING_DUST).setBlackLionPrice(1).done();
        new ProductionBuilder().setName(EIGHT_SLOT_INVISIBLE_BAG).setBlackLionPrice(5)
                .addToRecipe(10, BOLT_OF_JUTE).addToRecipe(3, PILE_OF_GLITTERING_DUST).done();

        Production production = (Production) repository.getComponent(EIGHT_SLOT_INVISIBLE_BAG);
        assertEquals("10 Bolt of jute, 3 Pile of Glittering Dust", production.getBestPricedRecipe().toString());
    }

    @Test
    public void testGetBestPricedRecipeForSimpleRecipe() {
        Component linenScrap = new ComponentBuilder().setName(LINEN_SCRAP).setBlackLionPrice(20).done();
        Production boltOfLinen = new ProductionBuilder().setName(BOLT_OF_LINEN).setBlackLionPrice(41)
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
        String bestRecipe = "1 Insigne d'explorateur en lin brodé, 1 Doublure de manteau en lin, 1 Panneau de manteau en lin";
        int bestPrice = 872;
        assertEquals(bestRecipe, recipe.toString());
        assertEquals(bestPrice, recipe.getBestPrice());
    }
}
