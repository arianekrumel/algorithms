import java.util.HashSet;
import java.util.Set;

public class StructuresArraysAndStrings {

    /**
     * Determine if string has all unique characters
     *
     * @param str
     *            String to process
     * @return true or false
     */
    protected boolean hasAllUniqueChars(String str) {
        boolean lAns = true;
        int lMax = str.length();

        // Add all characters to set
        Set<Character> lSet = new HashSet<>(lMax, 1.0f);
        for (int i = 0; i < lMax; i++) {
            Character lCurrentChar = Character.toLowerCase(str.charAt(i));
            if (lSet.contains(lCurrentChar)) {
                // Change flag and stop if find duplicate
                lAns = false;
                break;
            } else {
                lSet.add(lCurrentChar);
            }
        }
        return lAns;
    }
}
