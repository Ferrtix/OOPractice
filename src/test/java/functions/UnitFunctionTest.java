package functions;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UnitFunctionTest {

    @Test
    void apply() {
        MathFunction Cons = new UnitFunction();

        assertEquals(1.0, Cons.apply(65657.0), 0.0001);
        assertEquals(1.0, Cons.apply(56671.0), 0.0001);
        assertEquals(1.0, Cons.apply(-45672.0), 0.0001);
        assertEquals(1.0, Cons.apply(-541.0), 0.0001);
    }
}