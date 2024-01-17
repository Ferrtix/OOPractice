package functions.factory;

import functions.MathFunction;
import functions.TabulatedFunction;

public interface TabulatedFunctionFactory
{ 
    TabulatedFunction create(double[] xValues, double[] yValues);
    TabulatedFunction create(MathFunction source, double x, double x2, int count);
}
