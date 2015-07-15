import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class StructuresArraysAndStringsUnitTest {
    /*
     * Test object
     */
    private StructuresArraysAndStrings testObject;

    @Before
    public void setUp() {
        this.testObject = new StructuresArraysAndStrings();
    }

    /**
     * Tests hasAllUniqueChars()
     */
    @Test
    public void testHasAllUniqueChars() {
        // Simple fail case
        boolean actual = this.testObject.hasAllUniqueChars("test");
        Assert.assertFalse(actual);

        // Simple pass case
        actual = this.testObject.hasAllUniqueChars("bar");
        Assert.assertTrue(actual);

        // Edge case, zero
        actual = this.testObject.hasAllUniqueChars("");
        Assert.assertTrue(actual);

        // Harder, mix of white space
        actual = this.testObject
                .hasAllUniqueChars("Our mental synchronization can have but one explanation");
        Assert.assertFalse(actual);

        // Harder, mix of cases
        actual = this.testObject.hasAllUniqueChars("FoO Bar");
        Assert.assertFalse(actual);
    }

    /**
     * Tests hasAllUniqueCharsNoStructures()
     */
    @Test
    public void testHasAllUniqueCharsNoStructures() {
        // Simple fail case
        Assert.assertFalse(this.testObject.hasAllUniqueChars("test"));

        // Simple pass case
        Assert.assertTrue(this.testObject.hasAllUniqueChars("bar"));

        // Edge case, zero
        Assert.assertTrue(this.testObject.hasAllUniqueCharsNoStructures(""));

        // Edge case, one
        Assert.assertTrue(this.testObject.hasAllUniqueCharsNoStructures("K"));

        // Harder, mix of white space
        Assert.assertFalse(this.testObject
                .hasAllUniqueChars("Our mental synchronization can have but one explanation"));

        // Harder, mix of cases
        Assert.assertFalse(this.testObject.hasAllUniqueChars("FoO Bar"));
    }
}
