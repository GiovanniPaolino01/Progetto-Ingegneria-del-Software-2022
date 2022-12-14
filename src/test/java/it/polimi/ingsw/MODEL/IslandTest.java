package it.polimi.ingsw.MODEL;

import junit.framework.TestCase;
import org.junit.jupiter.api.Test;
import org.hamcrest.core.Is;

import java.util.ArrayList;
import java.util.List;

public class IslandTest extends TestCase {

    @Test
    public void testSetMotherNature() {
        int num = 0;
        Island i = new Island(num);

        i.setMotherNature(true);
        assertTrue(i.getHasMotherNature());

        i.setMotherNature(false);
        assertFalse(i.getHasMotherNature());
    }

    @Test
    public void testGetHasMotherNature() {
        int num=0;
        Island i = new Island(num);

        Boolean b = i.getHasMotherNature();
        assertTrue(b instanceof Boolean);
    }

    @Test
    public void testGetNumIsland() {
        Island i = new Island(0);

        assertEquals(0,i.getNumIsland());

    }

    @Test
    public void testAddStudent() {
        int num = 0;
        Island i = new Island(num);
        int col_Before, col_After;

        col_Before = i.countStudentsOfColour(Colour.GREEN);
        i.addStudent(Colour.GREEN);
        col_After = i.countStudentsOfColour(Colour.GREEN);

        assertEquals(col_After, col_Before+1);
    }

    @Test
    public void testCountStudentsOfColour() {
        Island i = new Island(0);
        i.addStudent(Colour.GREEN);

        assertEquals(1,i.countStudentsOfColour(Colour.GREEN));
    }
}