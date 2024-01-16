package operations;

import concurrent.SynchronizedTabulatedFunction;
import functions.Point;
import functions.TabulatedFunction;
import functions.factory.ArrayTabulatedFunctionFactory;
import functions.factory.TabulatedFunctionFactory;

public class TabulatedDifferentialOperator implements DifferentialOperator<TabulatedFunction> {
    private TabulatedFunctionFactory factory;

    public TabulatedDifferentialOperator(TabulatedFunctionFactory factory) {
        this.factory = factory;
    }

    public TabulatedDifferentialOperator() {
        factory = new ArrayTabulatedFunctionFactory();
    }

    public TabulatedFunctionFactory getFactory() {
        return factory;
    }

    public void setFactory(TabulatedFunctionFactory factory) {
        this.factory = factory;
    }

    public TabulatedFunction derive(TabulatedFunction function) {
        Point[] points = TabulatedFunctionOperationService.asPoints(function);
        double[] xValues = new double[points.length];
        double[] yValues = new double[points.length];

        int i = 0;
        while (i < xValues.length - 1) {
            xValues[i] = points[i].x;
            yValues[i] = (points[i + 1].y - points[i].y) / (points[i + 1].x - points[i].x);
            i++;
        }
        xValues[i] = points[i].x;
        yValues[i] = yValues[i - 1];
        return factory.create(xValues, yValues);
    }

    public TabulatedFunction deriveSynchronously(TabulatedFunction tabulatedFunction) {
        if(tabulatedFunction instanceof SynchronizedTabulatedFunction) {
            return derive((SynchronizedTabulatedFunction) tabulatedFunction);
        }
        else {
            SynchronizedTabulatedFunction synchronizedFunction = new SynchronizedTabulatedFunction(tabulatedFunction);
            return synchronizedFunction.doSynchronously(this::derive);
        }
    }
}
