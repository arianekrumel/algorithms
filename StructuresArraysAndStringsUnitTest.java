import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.Ignore;
import org.junit.Test;

public class StructuresArraysAndStringsUnitTest {

    @Ignore
    public void testDefault() {
        fail("Not yet implemented");
    }

    /**
     * Tests hasAllUniqueChars()
     */
    @Test
    public void testIsAllUnique() {
        StructuresArraysAndStrings testObject = new StructuresArraysAndStrings();

        // Simple fail case
        boolean expected = false;
        boolean actual = testObject.hasAllUniqueChars("test");
        assertEquals(expected, actual);

        // Simple pass case
        expected = true;
        actual = testObject.hasAllUniqueChars("bar");
        assertEquals(expected, actual);

        // Edge case, zero
        expected = true;
        actual = testObject.hasAllUniqueChars("");
        assertEquals(expected, actual);

        // Harder, mix of white space
        expected = false;
        actual = testObject
                .hasAllUniqueChars("Our mental synchronization can have but one explanation");
        assertEquals(expected, actual);

        // Harder, mix of cases
        expected = false;
        actual = testObject.hasAllUniqueChars("FoO Bar");
        assertEquals(expected, actual);
    }
}
