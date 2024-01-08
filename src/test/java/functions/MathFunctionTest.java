package functions;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MathFunctionTest {

    @Test
    void andThen() {
        MathFunction f = new SqrFunction();
        MathFunction g = new CtgFunction();
        MathFunction h = new IdentityFunction();

        MathFunction comp = f.andThen(g).andThen(h);
        MathFunction comp2 = f.andThen(f);

        assertEquals(1.0, comp.apply(Math.PI/4), 0.0001);
        assertEquals(Math.sqrt(8), comp2.apply(64), 0.0001);
    }
}