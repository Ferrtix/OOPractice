package functions;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class NaturalLogFunctionTest {

    @Test
    void apply() {
        MathFunction elog = new NaturalLogFunction();

        assertEquals(0.0, elog.apply(1.0), 0.0001);
        assertEquals(1.0, elog.apply(2.7182818284590), 0.001);
        assertEquals(2.0, elog.apply(2.71828*2.71828), 0.0001);
        assertEquals(3.0, elog.apply(2.71828*2.71828*2.71828), 0.00001);
    }
}