package functions;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SqrFunctionTest {

    @Test
    void apply() {
        MathFunction sqr = new SqrFunction();

        assertEquals(0.0, sqr.apply(0.0), 0.0001);
        assertEquals(1.0, sqr.apply(1.0), 0.0001);
        assertEquals(2.0, sqr.apply(4.0), 0.0001);
        assertEquals(567.0, sqr.apply(567.0*567.0), 0.0001);

    }
}