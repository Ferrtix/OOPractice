package functions.factory;

import functions.LinkedListTabulatedFunction;
import functions.TabulatedFunction;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LinkedListTabulatedFunctionFactoryTest {

    double[] xValues = {1, 3, 5};
    double[] yValues = {2, 4, 6};
    @Test
    public void create() {
        TabulatedFunctionFactory factory = new LinkedListTabulatedFunctionFactory();
        TabulatedFunction function = factory.create(xValues, yValues);
        LinkedListTabulatedFunction function2 = new LinkedListTabulatedFunction(xValues, yValues);

        assertTrue(function instanceof LinkedListTabulatedFunction);

        for (int i = 0; i < xValues.length; i++) {
            assertEquals(function2.toString(), function.toString());
        }
    }
}