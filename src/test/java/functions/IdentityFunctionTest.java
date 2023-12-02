package functions;

import org.junit.Test;

import static org.junit.Assert.*;

class IdentityFunctionTest {
    @Test
    public void testApply() {
        IdentityFunction f = new IdentityFunction();
        assertEquals(0.2, f.apply(0.2), 0.0);
        assertEquals(3546.1, f.apply(3546.1), 0.0);
        assertEquals(-2.0, f.apply(-2.0), 0.0);
        assertEquals(565.0, f.apply(565.0), 0.0);
    }
}