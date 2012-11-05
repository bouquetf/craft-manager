package org.jaalon.craftmanager.lib.repository;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import static org.junit.matchers.JUnitMatchers.containsString;

public class CsvTest {
    Csv csv;
    Repository repository;

    @Before
    public void setUp() throws Exception {
        csv = new Csv();
        repository = Repository.getInstance();
    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void testImportRepositoryFromCsv() throws Exception {
        csv.importRepositoryFromCsv("target/test-classes/test.csv");
        assertTrue(repository.contains("Insigne d'explorateur en lin brodé"));
        assertTrue(repository.contains("Bobine de fil de lin"));
        assertTrue(repository.contains("Morceau de cuir coriace traité"));
        assertTrue(repository.contains("Panneau de manteau en lin"));
        assertTrue(repository.contains("Griffe acérée"));
        assertTrue(repository.contains("Tunique d'explorateur ailée (Maître)"));
        assertTrue(repository.contains("Rouleau de lin"));
        assertTrue(repository.contains("Chute de lin"));
        assertTrue(repository.contains("Doublure de manteau en lin"));
        assertTrue(repository.contains("Segment de cuir coriace"));
        assertThat(repository.getComponent("Panneau de manteau en lin").toString(), containsString("4 Rouleau de lin"));
        assertThat(repository.getComponent("Panneau de manteau en lin").toString(), containsString("1 Morceau de cuir coriace traité"));
        assertThat(repository.getComponent("Panneau de manteau en lin").toString(), containsString("3 Bobine de fil de lin"));
    }
}
