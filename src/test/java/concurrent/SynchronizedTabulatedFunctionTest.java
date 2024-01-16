package concurrent;

import static org.junit.jupiter.api.Assertions.*;

import functions.ArrayTabulatedFunction;
import functions.Point;
import org.junit.jupiter.api.Test;

import java.util.Iterator;

class SynchronizedTabulatedFunctionTest {
    double[] xValues = {1, 2, 3, 4, 5};
    double[] yValues = {1, 4, 9, 16, 25};

    ArrayTabulatedFunction function = new ArrayTabulatedFunction(xValues, yValues);
    SynchronizedTabulatedFunction synchronizedFunction = new SynchronizedTabulatedFunction(function);

    @Test
    public void GetCount() {
        assertEquals(5, synchronizedFunction.GetCount());
    }

    @Test
    public void getX() {
        assertEquals(3, synchronizedFunction.getX(2));
    }

    @Test
    public void getY() {
        assertEquals(9, synchronizedFunction.getY(2));
    }

    @Test
    public void setY() {
        synchronizedFunction.setY(3, -1);
        assertEquals(-1, synchronizedFunction.getY(3));
        synchronizedFunction.setY(3, 16);
    }

    @Test
    public void indexOfX() {
        assertEquals(3, synchronizedFunction.indexOfX(4));
    }

    @Test
    public void indexOfY() {
        assertEquals(3, synchronizedFunction.indexOfY(16));
    }

    @Test
    public void rightBound() {
        assertEquals(5, synchronizedFunction.rightBound());
    }

    @Test
    public void leftBound() {
        assertEquals(1, synchronizedFunction.leftBound());
    }

    @Test
    public void iterator() {
        Iterator<Point> iterator = synchronizedFunction.iterator();

        for (int i = 0; iterator.hasNext(); i++) {
            Point point = iterator.next();
            assertEquals(xValues[i], point.x);
            assertEquals(yValues[i], point.y);
        }
    }
    @Test
    public void doSynchronously() {
        SynchronizedTabulatedFunction.Operation<Double> operation = f -> {
            double result = 0;

            for (Point point : synchronizedFunction)
                if(point.y % 2 == 0)
                    result += point.y;

            return result;
        };

        assertEquals(20, synchronizedFunction.doSynchronously(operation));
    }
}