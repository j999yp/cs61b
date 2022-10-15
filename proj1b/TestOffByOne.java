import org.junit.Test;
import static org.junit.Assert.*;

public class TestOffByOne {

    // You must use this CharacterComparator and not instantiate
    // new ones, or the autograder might be upset.
    static CharacterComparator offByOne = new OffByOne();

    @Test
    public void testOffByOne() {
        assertTrue(offByOne.equalChars('x', (char) ('x' + 1)));
        assertTrue(offByOne.equalChars('y', (char) ('y' - 1)));
        assertFalse(offByOne.equalChars('a', 'c'));
        assertFalse(offByOne.equalChars('A', 'b'));
        assertTrue(offByOne.equalChars('%', '&'));
    }
}
