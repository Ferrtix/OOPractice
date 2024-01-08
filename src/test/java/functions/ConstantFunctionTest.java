package functions;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ConstantFunctionTest {

    @Test
    void apply() {
        MathFunction Cons = new ConstantFunction(5.0);

        assertEquals(5.0, Cons.apply(657.0), 0.0001);
        assertEquals(5.0, Cons.apply(5671.0), 0.0001);
        assertEquals(5.0, Cons.apply(452.0), 0.0001);
        assertEquals(5.0, Cons.apply(-541.0), 0.0001);
    }
}