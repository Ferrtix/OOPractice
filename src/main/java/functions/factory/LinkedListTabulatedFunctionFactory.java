package functions.factory;

import functions.LinkedListTabulatedFunction;
import functions.MathFunction;
import functions.TabulatedFunction;

public class LinkedListTabulatedFunctionFactory implements TabulatedFunctionFactory {
    public LinkedListTabulatedFunctionFactory() {
    }
    public LinkedListTabulatedFunction create(double[] xValues, double[] yValues) {
        return new LinkedListTabulatedFunction(xValues, yValues);
    }

    public LinkedListTabulatedFunction create(MathFunction source, double xFrom, double xTo, int count) {
        return new LinkedListTabulatedFunction(source, xFrom, xTo, count);
    }
}
