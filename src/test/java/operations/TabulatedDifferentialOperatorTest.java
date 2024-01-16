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
        LinkedListTabulatedFunctionFactory factory = new LinkedListTabulatedFunctionFactory();
        TabulatedDifferentialOperator operation = new TabulatedDifferentialOperator(factory);

        ArrayTabulatedFunction array = new ArrayTabulatedFunction(xValues, yValues);
        TabulatedFunction function = operation.derive(array);
        assertEquals(1, function.getY(0));
        assertEquals(1, function.getY(1));
        assertEquals(1, function.getY(2));
        assertEquals(1, function.getY(3));
    }

    @Test
    public void derive2() {
        double[] xValues = {1, 2, 3, 4};
        double[] yValues = {1, 1, 1, 1};
        LinkedListTabulatedFunctionFactory factory = new LinkedListTabulatedFunctionFactory();
        TabulatedDifferentialOperator operation = new TabulatedDifferentialOperator(factory);
        LinkedListTabulatedFunction list = new LinkedListTabulatedFunction(xValues, yValues);
        TabulatedFunction function = operation.derive(list);
        assertEquals(0, function.getY(0));
        assertEquals(0, function.getY(1));
        assertEquals(0, function.getY(2));
        assertEquals(0, function.getY(3));
    }

    @Test
    public void deriveSynchronously() {
        double[] xValues = {1, 2, 3, 4};
        double[] yValues = {1, 1, 1, 1};
        LinkedListTabulatedFunctionFactory factory = new LinkedListTabulatedFunctionFactory();
        TabulatedDifferentialOperator operation = new TabulatedDifferentialOperator(factory);
        LinkedListTabulatedFunction list = new LinkedListTabulatedFunction(xValues, yValues);
        TabulatedFunction function = operation.deriveSynchronously(list);
        assertEquals(0, function.getY(0));
        assertEquals(0, function.getY(1));
        assertEquals(0, function.getY(2));
        assertEquals(0, function.getY(3));
    }
}