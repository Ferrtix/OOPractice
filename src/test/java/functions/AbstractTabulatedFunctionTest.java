package functions;

import exceptions.ArrayIsNotSortedException;
import exceptions.DifferentLengthOfArraysException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AbstractTabulatedFunctionTest {

    @Test
    void checkLengthIsTheSame() {
        boolean isException = false;
        double[] x = {1, 3, 7, 5};
        double[] y = {1, 2, 6, 6, 5};
        try {
            ArrayTabulatedFunction.checkLengthIsTheSame(x,y);
        } catch (DifferentLengthOfArraysException exception) {
            isException = true;
        }
        assertTrue(isException);
    }

    @Test
    void checkSorted() {
        boolean isException = false;
        double[] x = {1, 3, 8, 5};
        try {
            ArrayTabulatedFunction.checkSorted(x);
        } catch (ArrayIsNotSortedException exception) {
            isException = true;
        }
        assertTrue(isException);
    }
}