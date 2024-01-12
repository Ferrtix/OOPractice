package functions.factory;

import functions.TabulatedFunction;

public interface TabulatedFunctionFactory
{ 
    TabulatedFunction create(double[] xValues, double[] yValues);
}
