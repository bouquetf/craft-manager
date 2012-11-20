package org.jaalon.craftmanager.lib;

import org.jaalon.craftmanager.lib.repository.Repository;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

public class ProductionTest {

    public static final String BOLT_OF_LINEN = "Bolt of linen";
    public static final String LINEN_SCRAP = "Linen scrap";
    public static final String JUTE_SCRAP = "Jute scrap";
    public static final String BOLT_OF_JUTE = "Bolt of jute";
    public static final String PILE_OF_GLITTERING_DUST = "Pile of Glittering Dust";
    public static final String EIGHT_SLOT_INVISIBLE_BAG = "Eight Slot Invisible Bag";
    public static final String CHUTE_DE_LIN = "Chute de lin";
    public static final String SEGMENT_DE_CUIR_CORIACE = "Segment de cuir coriace";
    public static final String ROULEAU_DE_LIN = "Rouleau de lin";
    public static final String MORCEAU_DE_CUIR_CORIACE_TRAITÉ = "Morceau de cuir coriace traité";
    public static final String BOBINE_DE_FIL_DE_LIN = "Bobine de fil de lin";
    public static final String GRIFFE_ACÉRÉE = "Griffe acérée";
    public static final String INSIGNE_D_EXPLORATEUR_EN_LIN_BRODÉ = "Insigne d'explorateur en lin brodé";
    public static final String DOUBLURE_DE_MANTEAU_EN_LIN = "Doublure de manteau en lin";
    public static final String PANNEAU_DE_MANTEAU_EN_LIN = "Panneau de manteau en lin";
    public static final String TUNIQUE_D_EXPLORATEUR_AILÉE_MAÎTRE = "Tunique d'explorateur ailée (Maître)";
    private Repository repository;

    @Before
    public void setUp() {
        this.repository = Repository.getInstance();
        new ComponentBuilder().setName(LINEN_SCRAP).setBlackLionPrice(20).done();
        new ProductionBuilder().setName(BOLT_OF_LINEN).setBlackLionPrice(41).addToRecipe(2, LINEN_SCRAP).done();
        new ComponentBuilder().setName(JUTE_SCRAP).setBlackLionPrice(2).done();
        new ProductionBuilder().setName(BOLT_OF_JUTE).setBlackLionPrice(3).addToRecipe(2, JUTE_SCRAP).done();
        new ComponentBuilder().setName(PILE_OF_GLITTERING_DUST).setBlackLionPrice(1).done();
        new ProductionBuilder().setName(EIGHT_SLOT_INVISIBLE_BAG).setBlackLionPrice(5)
                .addToRecipe(10, BOLT_OF_JUTE).addToRecipe(3, PILE_OF_GLITTERING_DUST).done();
        new ComponentBuilder().setName(LINEN_SCRAP).setBlackLionPrice(20).done();
        new ProductionBuilder().setName(BOLT_OF_LINEN).setBlackLionPrice(41).addToRecipe(2, LINEN_SCRAP).done();
        new ComponentBuilder().setName(CHUTE_DE_LIN).setBlackLionPrice(19).done();
        new ComponentBuilder().setName(SEGMENT_DE_CUIR_CORIACE).setBlackLionPrice(13).done();
        new ProductionBuilder().setName(ROULEAU_DE_LIN).setBlackLionPrice(41).addToRecipe(2, CHUTE_DE_LIN).done();
        new ProductionBuilder().setName(MORCEAU_DE_CUIR_CORIACE_TRAITÉ)
                .setBlackLionPrice(29).addToRecipe(2, SEGMENT_DE_CUIR_CORIACE).done();
        new ComponentBuilder().setName(BOBINE_DE_FIL_DE_LIN).setBlackLionPrice(38).setVendorPrice(32).done();
        new ComponentBuilder().setName(GRIFFE_ACÉRÉE).setBlackLionPrice(24).done();
        new ProductionBuilder().setName(INSIGNE_D_EXPLORATEUR_EN_LIN_BRODÉ).setBlackLionPrice(398)
                .addToRecipe(1, ROULEAU_DE_LIN).addToRecipe(5, BOBINE_DE_FIL_DE_LIN).addToRecipe(8, GRIFFE_ACÉRÉE).done();
        new ProductionBuilder().setName(DOUBLURE_DE_MANTEAU_EN_LIN).setBlackLionPrice(44)
                .addToRecipe(1, ROULEAU_DE_LIN).done();
        new ProductionBuilder().setName(PANNEAU_DE_MANTEAU_EN_LIN).setBlackLionPrice(430)
                .addToRecipe(4, ROULEAU_DE_LIN).addToRecipe(1, MORCEAU_DE_CUIR_CORIACE_TRAITÉ).addToRecipe(3, BOBINE_DE_FIL_DE_LIN).done();
        new ProductionBuilder().setName(TUNIQUE_D_EXPLORATEUR_AILÉE_MAÎTRE)
                .addToRecipe(1, INSIGNE_D_EXPLORATEUR_EN_LIN_BRODÉ).addToRecipe(1, DOUBLURE_DE_MANTEAU_EN_LIN).setBlackLionPrice(84)
                .addToRecipe(1, PANNEAU_DE_MANTEAU_EN_LIN).done();
    }

    @Test
    public void testGetRecipeForSimpleRecipe() {
        Production production = (Production) repository.getComponent(BOLT_OF_LINEN);
        assertEquals("2 Linen scrap", production.toString());
    }

    @Test
    public void testGetRecipeForComplexRecipe() {
        Production production = (Production) repository.getComponent(EIGHT_SLOT_INVISIBLE_BAG);
        assertEquals("10 Bolt of jute, 3 Pile of Glittering Dust", production.getBestPricedRecipe().toString());
    }

    @Test
    public void testGetBestPricedRecipeForSimpleRecipe() {
        Recipe bestPricedRecipe = ((Production) repository.getComponent(BOLT_OF_LINEN)).getBestPricedRecipe();
        assertEquals("2 Linen scrap", bestPricedRecipe.toString());
        assertEquals(40, bestPricedRecipe.getBestPrice());
    }

    @Test
    public void testGetBestPricedRecipeForComplexeRecipe() {
        Production tunique = (Production) repository.getComponent(TUNIQUE_D_EXPLORATEUR_AILÉE_MAÎTRE);
        Recipe recipe = tunique.getBestPricedRecipe();
        String bestRecipe = "1 "+INSIGNE_D_EXPLORATEUR_EN_LIN_BRODÉ+", 1 "+DOUBLURE_DE_MANTEAU_EN_LIN+", 1 "+PANNEAU_DE_MANTEAU_EN_LIN;
        int bestPrice = 872;
        assertEquals(bestRecipe, recipe.toString());
        assertEquals(bestPrice, recipe.getBestPrice());
    }

    @Test
    public void testUseVendorName() {
        Production production = (Production) repository.getComponent(INSIGNE_D_EXPLORATEUR_EN_LIN_BRODÉ);
        Recipe bestPricedRecipe = production.getBestPricedRecipe();
        assertEquals("1 Rouleau de lin, 5 Bobine de fil de lin, 8 Griffe acérée", bestPricedRecipe.toString());
        assertEquals(393, bestPricedRecipe.getBestPrice());
    }

    @Test
    public void testMergeProductionShouldMergeEverything() {
        new ComponentBuilder().setName("a1").setVendorPrice(1).done();
        new ComponentBuilder().setName("a2").setVendorPrice(2).done();
        new ProductionBuilder().setName("a").setVendorPrice(10).addToRecipe(2, "a1").addToRecipe(5, "a2").done();
        new ProductionBuilder().setName("a").setBlackLionPrice(15).done();
        Production production = (Production) repository.getComponent("a");
        assertThat(production.getLionPrice(), is(15));
        assertThat(production.getVendorPrice(), is(10));
        assertThat(production.getBestPrice(), is(10));
    }
}
