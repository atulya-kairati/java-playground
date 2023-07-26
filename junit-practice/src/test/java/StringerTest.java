import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class StringerTest {

    @Test
    public void checkReversal() {
//        String text = "Hi I am going to eat my hand while I write with my burger";
        String text = "burger";

        Stringer stringer = new Stringer();

        assertEquals("regrub", stringer.reverse(text));

    }

    @Test
    public void checkVowelRemoval() {
        String text = "burger";

        Stringer stringer = new Stringer();

        assertEquals("brgr", stringer.removeVowels(text));
    }

    @Test
    public void duckCheckFalse() {
        Stringer s = new Stringer();

        String text = "berserk";

        assertFalse(s.isDuck(text));
    }

    @Test
    public void duckCheckTrue() {
        Stringer s = new Stringer();

        String text = "fffff";

        assertTrue(s.isDuck(text));
    }
}
