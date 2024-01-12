package operations;

import functions.ArrayTabulatedFunction;
import functions.Point;
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
}