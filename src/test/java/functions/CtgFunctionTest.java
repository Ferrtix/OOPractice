package functions;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CtgFunctionTest {

    @Test
    void apply() {
        MathFunction ctg = new CtgFunction();

        assertEquals(0, ctg.apply(Math.PI/2), 0.00001);
        assertEquals(1.0, ctg.apply(Math.PI/4), 0.00001);
        assertEquals(-1.0, ctg.apply(-Math.PI/4), 0.00001);
        assertEquals(Math.sqrt(3), ctg.apply(Math.PI/6), 0.00001);
    }
}