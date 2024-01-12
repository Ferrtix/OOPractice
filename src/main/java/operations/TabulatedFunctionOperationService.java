package operations;
import functions.Point;
import functions.TabulatedFunction;

import java.util.Iterator;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TabulatedFunctionOperationService {
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
}
