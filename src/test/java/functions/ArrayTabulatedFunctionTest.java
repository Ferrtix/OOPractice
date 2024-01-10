package functions;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ArrayTabulatedFunctionTest {

    double[] x = {1, 2, 3, 4, 5};
    double[] y= {1, 4, 9, 16, 25};
    ArrayTabulatedFunction Array=new ArrayTabulatedFunction(x,y);


    @Test
    void getCount() {
        assertEquals(5,Array.GetCount());
    }

    @Test
    void getX() {
        assertEquals(2,Array.getX(1), 0.00001);
        assertEquals(3,Array.getX(2), 0.00001);
    }

    @Test
    void getY() {
        assertEquals(9,Array.getY(2), 0.00001);
        assertEquals(16,Array.getY(3), 0.00001);
    }

    @Test
    void setY() {
        Array.setY(1,6);
        assertEquals(6,Array.getY(1), 0.00001);
        Array.setY(1,1);
    }

    @Test
    void leftBound() {
        assertEquals(1,Array.leftBound(), 0.00001);
    }

    @Test
    void rightBound() {
        assertEquals(5,Array.rightBound(), 0.00001);
    }

    @Test
    void indexOfX() {
        assertEquals(2,Array.indexOfX(3), 0.00001);
    }

    @Test
    void indexOfY() {
        assertEquals(3,Array.indexOfY(16), 0.00001);
    }

    @Test
    void floorIndexOfX() {
        assertEquals(0,Array.floorIndexOfX(1.23534156), 0.0001);
    }

    @Test
    void extrapolateLeft() {
        assertEquals(0.36,Array.extrapolateLeft(0.6), 1.001);
    }

    @Test
    void extrapolateRight() {
        assertEquals(36,Array.extrapolateRight(6), 5.001);
    }

    @Test
    void interpolate() {
        assertEquals(3.1*3.1, Array.interpolate(3.1, 2), 5.01);
    }

    @Test
    void Interpolate2() {
        assertEquals(1.4*1.4, Array.interpolate(1.4, 1,2,1,4), 5.1);
    }

    @Test
    void apply() {
        assertEquals(1.5*1.5, Array.apply(1), 5.001);
        assertEquals(6.3*6.3, Array.apply(6.3), 5.001);
        assertEquals(0, Array.apply(0), 5.001);
    }
}