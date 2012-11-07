package org.jaalon.craftmanager.lib;

import org.jaalon.craftmanager.lib.repository.Repository;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class ComponentTest {
    private Repository repository;

    @Before
    public void setup() {
        this.repository = Repository.getInstance();
    }

    @Test
    public void testMergeComponents() {
        String componentName = "A simple component";
        new ComponentBuilder().setName(componentName).setBlackLionPrice(10).done();
        new ComponentBuilder().setName(componentName).setVendorPrice(15).done();

        Component componentFromRepository = repository.getComponent(componentName);
        assertThat(componentFromRepository.getLionPrice(), is(10));
        assertThat(componentFromRepository.getVendorPrice(), is(15));
    }
}
