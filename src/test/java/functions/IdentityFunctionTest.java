package functions;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class IdentityFunctionTest {
    @Test
    void testApply() {
        MathFunction identityFunction = new IdentityFunction();

        assertEquals(0.0, identityFunction.apply(0.0), 0.0001);
        assertEquals(1.0, identityFunction.apply(1.0), 0.0001);
        assertEquals(-2.5, identityFunction.apply(-2.5), 0.0001);
    }
}
