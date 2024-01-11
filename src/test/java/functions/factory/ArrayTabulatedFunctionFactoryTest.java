package functions.factory;

import functions.ArrayTabulatedFunction;
import functions.TabulatedFunction;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ArrayTabulatedFunctionFactoryTest {

    double[] xValues = {1, 3, 5};
    double[] yValues = {2, 4, 6};
    @Test
    public void create() {
        TabulatedFunctionFactory factory = new ArrayTabulatedFunctionFactory();
        TabulatedFunction function = factory.create(xValues, yValues);
        ArrayTabulatedFunction function2 = new ArrayTabulatedFunction(xValues, yValues);

        assertTrue(function instanceof ArrayTabulatedFunction);

        for (int i = 0; i < xValues.length; i++) {
            assertEquals(function2.toString(), function.toString());
        }
    }
}