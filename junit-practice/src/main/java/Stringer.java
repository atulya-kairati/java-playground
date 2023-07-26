import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Stringer {
    /**
     * Class containing functions for string manipulation
     */

    public String reverse(String s) {
        StringBuilder strb = new StringBuilder(s);

        return  strb.reverse().toString();
    }

    public String removeVowels(String s) {
        Set<Character> vowels = new HashSet<>(List.of('a', 'e', 'i', 'o', 'u'));

        StringBuilder strb = new StringBuilder();
        for (char c : s.toCharArray()) {
            if (! vowels.contains(c)) {
                strb.append(c);
            }
        }

        return strb.toString();
    }

    public boolean isDuck(String s) {
        // is duck is all the char are same

        Set<Character> vowels = new HashSet<>();

        for (char c : s.toCharArray()) {
            vowels.add(c);
        }

        return vowels.size() == 1;
    }
}
