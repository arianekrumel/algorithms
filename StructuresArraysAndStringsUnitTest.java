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
        Assert.assertFalse(this.testObject.hasAllUniqueChars("test"));

        // Simple pass case
        Assert.assertTrue(this.testObject.hasAllUniqueChars("bar"));

        // Edge case, zero
        Assert.assertTrue(this.testObject.hasAllUniqueChars(""));

        // Edge case, one
        Assert.assertTrue(this.testObject.hasAllUniqueCharsNoStructures("K"));

        // Harder, mix of white space
        Assert.assertFalse(this.testObject
                .hasAllUniqueChars("Our mental synchronization can have but one explanation"));

        // Harder, mix of cases
        Assert.assertFalse(this.testObject.hasAllUniqueChars("FoO Bar"));
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

        // Harder, mix of white space
        Assert.assertFalse(this.testObject
                .hasAllUniqueChars("Our mental synchronization can have but one explanation"));

        // Harder, mix of cases
        Assert.assertFalse(this.testObject.hasAllUniqueChars("FoO Bar"));
    }
}
