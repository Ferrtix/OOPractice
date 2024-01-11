package functions;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class IdentityFunctionTest {
    @Test
    void testApply() {
        MathFunction identityFunction = new IdentityFunction();

        assertEquals(0.0, identityFunction.apply(0.0), 0.0001);
        assertEquals(1.0, identityFunction.apply(1.0), 0.0001);
        assertEquals(-2.5, identityFunction.apply(-2.5), 0.0001);
    }

    @Test
    void testToString() {
        IdentityFunction F=new IdentityFunction();
        assertEquals("IdentityFunction returns what you passed to it", F.toString());
    }

    @Test
    void testEquals() {
        IdentityFunction F=new IdentityFunction();
        IdentityFunction G=new IdentityFunction();
        assertEquals(F,G);
    }

    @Test
    void testHashCode() {
        IdentityFunction F=new IdentityFunction();
        IdentityFunction G=new IdentityFunction();
        assertEquals(F.hashCode(),G.hashCode());
    }

    @Test
    void testClone() {
        IdentityFunction F=new IdentityFunction();
        Object clone = F.clone();
        assertEquals(clone,F);
    }
}
