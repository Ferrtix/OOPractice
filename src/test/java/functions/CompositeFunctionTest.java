package functions;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CompositeFunctionTest {

    @Test
    void apply() {
        MathFunction ln = new NaturalLogFunction();
        MathFunction ctg = new CtgFunction();
        MathFunction comp = new CompositeFunction(ctg, ln);

        assertEquals(0, comp.apply(Math.PI/4), 0.00001);
        assertEquals(Math.log(Math.sqrt(3)), comp.apply(Math.PI/6), 0.00001);
        assertEquals(Math.log(1/Math.sqrt(3)), comp.apply(Math.PI/3), 0.00001);
    }
}