package operations;
import exceptions.InconsistentFunctionsException;
import functions.Point;
import functions.TabulatedFunction;
import functions.factory.ArrayTabulatedFunctionFactory;
import functions.factory.TabulatedFunctionFactory;

public class TabulatedFunctionOperationService {
    private TabulatedFunctionFactory factory;
    public TabulatedFunctionOperationService(TabulatedFunctionFactory factory) {
        this.factory = factory;
    }
    public TabulatedFunctionOperationService() {
        this.factory = new ArrayTabulatedFunctionFactory();
    }

    public TabulatedFunctionFactory GetFactory() {
        return factory;
    }
    public void SetFactory(TabulatedFunctionFactory factory) {
        this.factory = factory;
    }

    public static Point[] asPoints(TabulatedFunction tabulatedFunction){
        int length = tabulatedFunction.GetCount();
        Point [] pArray = new Point[length];
        int i = 0;
        for (Point point: tabulatedFunction)
        {
            pArray[i] = point;
            i++;
        }
        return pArray;
    }

    private interface BiOperation{
        double apply(double u, double v);
    }
    private TabulatedFunction doOperation(TabulatedFunction a, TabulatedFunction b, BiOperation operation)  {
        if (a.GetCount() !=  b.GetCount())
            throw new InconsistentFunctionsException("different lengths of functions arrays");

        Point[] pointsA = asPoints(a);
        Point[] pointsB = asPoints(b);
        int count = a.GetCount();

        double[] xValues = new double[count];
        double[] yValues = new double[count];

        for (int i = 0; i < count; i++) {
            xValues[i] = pointsA[i].x;
            yValues[i] = operation.apply(pointsA[i].y, pointsB[i].y);
        }

        return factory.create(xValues, yValues);
    }


    public TabulatedFunction Add(TabulatedFunction f, TabulatedFunction g) {
        BiOperation operation = (a, b) -> a + b;
        return doOperation(f, g, operation);
    }
    public TabulatedFunction Sub(TabulatedFunction f, TabulatedFunction g) {
        BiOperation operation = (a, b) -> a - b;
        return doOperation(f, g, operation);
    }

    public TabulatedFunction Mult(TabulatedFunction f, TabulatedFunction g) {
        BiOperation operation = (a, b) -> a * b;
        return doOperation(f, g, operation);
    }

    public TabulatedFunction Div(TabulatedFunction f, TabulatedFunction g) {
        BiOperation operation = (a, b) -> {
            if (b != 0)
                return a / b;
            else
                throw new ArithmeticException("Dividing on 0");
        };
        return doOperation(f, g, operation);
    }
}
