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

    /**
     * Determine if string has all unique characters with no additional data
     * structures
     *
     * @param str
     *            String to process
     * @return true or false
     */
    protected boolean hasAllUniqueCharsNoStructures(String str) {
        // recursively check every character with all others
        boolean ans = true;
        int lMax = str.length();
        // base case: if there is only one or less character left in string
        if (lMax <= 1) {
            return ans;
        }

        // otherwise, check if the first letter matches any other
        Character lFirst = Character.toLowerCase(str.charAt(0));
        Character lCurrentChar;
        for (int i = 1; i < lMax; i++) {
            lCurrentChar = Character.toLowerCase(str.charAt(i));
            if (lCurrentChar.equals(lFirst)) {
                return false;
            }
        }

        // send smaller problem back into method
        return this.hasAllUniqueCharsNoStructures(str);
    }
}