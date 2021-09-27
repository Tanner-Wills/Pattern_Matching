import java.util.*;

/**
 * Your implementations of the Boyer Moore string searching algorithm.
 */
public class PatternMatching {
    /**
     * Boyer Moore algorithm that relies on a last occurrence table.
     * This algorithm Works better with large alphabets.
     *
     * Make sure to implement the buildLastTable() method, which will be
     * used in this boyerMoore() method.
     *
     * Note: You may find the getOrDefault() method from Java's Map class useful.
     *
     * You may assume that the passed in pattern, text, and comparator will not be null.
     *
     * @param pattern    The pattern you are searching for in a body of text.
     * @param text       The body of text where you search for the pattern.
     * @param comparator You MUST use this to check if characters are equal.
     * @return           List containing the starting index for each match found.
     */
    public static List<Integer> boyerMoore(CharSequence pattern, CharSequence text, CharacterComparator comparator) {
        // WRITE YOUR CODE HERE (DO NOT MODIFY METHOD HEADER)!
        /**
         * m = pattern.length
         * last = HashMap <character,index>
         * for all(i from 0 to m-l)
         *     last = put(pattern[i],i)
         * end for
         * return last
         */



        int i = 0;
        ArrayList<Integer> patternIndex = new ArrayList<>();
        Map<Character, Integer> last = buildLastTable(pattern);

        while(i <= text.length() - pattern.length()){
            int j = pattern.length() - 1;
            while(j >= 0 && comparator.compare(text.charAt(i+j),pattern.charAt(j)) == 0)
                j --;

            if(j == -1) {
                patternIndex.add(i); // pattern found
                i += pattern.length();

                //text and pattern do not match
            }else{
                int shift = last.getOrDefault(text.charAt(i + j),i);
                if(shift < j)
                    i = i+j-shift;
                else
                    i++;
            }
        }
        System.out.println("Pattern occurs at index(s): " + patternIndex);
        return patternIndex;

    }

    /**
     * Builds the last occurrence table that will be used to run the Boyer Moore algorithm.
     *
     * Note that each char x will have an entry at table.get(x).
     * Each entry should be the last index of x where x is a particular
     * character in your pattern.
     * If x is not in the pattern, then the table will not contain the key x,
     * and you will have to check for that in your Boyer Moore implementation.
     *
     * Ex. pattern = octocat
     *
     * table.get(o) = 3
     * table.get(c) = 4
     * table.get(t) = 6
     * table.get(a) = 5
     * table.get(everything else) = null, which you will interpret in
     * Boyer-Moore as -1
     *
     * If the pattern is empty, return an empty map.
     * You may assume that the passed in pattern will not be null.
     *
     * @param pattern A pattern you are building last table for.
     * @return A Map with keys of all of the characters in the pattern mapping
     *         to their last occurrence in the pattern.
     */
    public static Map<Character, Integer> buildLastTable(CharSequence pattern) {
        // WRITE YOUR CODE HERE (DO NOT MODIFY METHOD HEADER)!
        Map<Character,Integer> lastTable = new HashMap<>(pattern.length());
        if(!pattern.isEmpty()) {
            for (int i = 0; i < pattern.length(); i++) {
                if (lastTable.containsKey(pattern.charAt(i)))
                    lastTable.replace(pattern.charAt(i), i);
                else
                    lastTable.put(pattern.charAt(i), i);
            }
        }
        else{
            throw new NoSuchElementException("Pattern entry is empty!");
        }
        return lastTable;
    }
}