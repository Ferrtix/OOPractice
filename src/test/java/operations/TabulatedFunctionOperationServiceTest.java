package operations;

import functions.ArrayTabulatedFunction;
import functions.LinkedListTabulatedFunction;
import functions.Point;
import functions.TabulatedFunction;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TabulatedFunctionOperationServiceTest {

    @Test
    void asPoints() {
        double[] xVal = {11, 11.2, 12, 13, 15};
        double[] yVal = {20, 21, 24, 25, 29};
        double[] yVal2 = {18, 19, 20, 24, 32};
        ArrayTabulatedFunction func1 = new ArrayTabulatedFunction(xVal, yVal);
        Point[] arrayOfPoints = TabulatedFunctionOperationService.asPoints(func1);
        int i = 0;
        for (Point point : arrayOfPoints) {
            assertEquals(point.x, xVal[i]);
            assertEquals(point.y, yVal[i]);
            ++i;
        }
    }

    double[] xValues = {1, 2, 3, 4};
    double[] yValues = {2, 3, 1, 2};
    LinkedListTabulatedFunction list = new LinkedListTabulatedFunction(xValues, yValues);
    double[] xValues2 = {1, 2, 3, 4};
    double[] yValues2 = {2, 2, 2, 4};
    LinkedListTabulatedFunction list1 = new LinkedListTabulatedFunction(xValues2, yValues2);
    TabulatedFunctionOperationService operation = new TabulatedFunctionOperationService();
    @Test
    public void addTest() {
        TabulatedFunction func = operation.Add(list, list1);
        for (int i = 0; i != func.GetCount(); i++) {
            assertEquals(yValues[i] + yValues2[i], func.getY(i));
        }
    }

    @Test
    void sub() {
        TabulatedFunction func = operation.Sub(list, list1);
        for (int i = 0; i != func.GetCount(); i++) {
            assertEquals(yValues[i] - yValues2[i], func.getY(i));
        }
    }
    @Test
    public void mult() {
        TabulatedFunction func = operation.Mult(list, list1);
        for (int i = 0; i != func.GetCount(); i++) {
            assertEquals(yValues[i] * yValues2[i], func.getY(i));
        }
    }

    @Test
    public void div() {
        TabulatedFunction func = operation.Div(list, list1);
        for (int i = 0; i != func.GetCount(); i++) {
            assertEquals(yValues[i] / yValues2[i], func.getY(i));
        }
    }
}