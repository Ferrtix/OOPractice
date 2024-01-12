package operations;

import functions.ArrayTabulatedFunction;
import functions.LinkedListTabulatedFunction;
import functions.TabulatedFunction;
import functions.factory.LinkedListTabulatedFunctionFactory;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TabulatedDifferentialOperatorTest {

    @Test
    public void derive() {
        double[] xValues = {1, 3, 5, 7};
        double[] yValues = {2, 4, 6, 8};
        LinkedListTabulatedFunctionFactory fact = new LinkedListTabulatedFunctionFactory();
        TabulatedDifferentialOperator operation = new TabulatedDifferentialOperator(fact);

        ArrayTabulatedFunction array = new ArrayTabulatedFunction(xValues, yValues);
        TabulatedFunction func = operation.derive(array);
        assertEquals(1, func.getY(0));
        assertEquals(1, func.getY(1));
        assertEquals(1, func.getY(2));
        assertEquals(1, func.getY(3));
    }

    @Test
    public void derive2() {
        double[] xValues = {1, 2, 3, 4};
        double[] yValues = {1, 1, 1, 1};
        LinkedListTabulatedFunctionFactory fact = new LinkedListTabulatedFunctionFactory();
        TabulatedDifferentialOperator operation = new TabulatedDifferentialOperator(fact);
        LinkedListTabulatedFunction list = new LinkedListTabulatedFunction(xValues, yValues);
        TabulatedFunction func = operation.derive(list);
        assertEquals(0, func.getY(0));
        assertEquals(0, func.getY(1));
        assertEquals(0, func.getY(2));
        assertEquals(0, func.getY(3));
    }
}