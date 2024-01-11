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

        double[] ArrX = {1, 3, 5, 7};
        double[] ArrY = {2, 4, 6, 8};

        MathFunction sqr = new SqrFunction();

        LinkedListTabulatedFunction linkedList = new LinkedListTabulatedFunction(ArrX, ArrY);
        MathFunction comp2 = new CompositeFunction(linkedList, sqr);

        assertEquals(14*14, comp2.apply(13.0), 0.00001);
        assertEquals(7*7, comp2.apply(6.0), 0.00001);

        ArrayTabulatedFunction Arr = new ArrayTabulatedFunction(ArrX, ArrY);
        MathFunction comp3 = new CompositeFunction(Arr, sqr);

        assertEquals(14*14, comp3.apply(13.0), 0.00001);
        assertEquals(7*7, comp3.apply(6.0), 0.00001);
    }
}