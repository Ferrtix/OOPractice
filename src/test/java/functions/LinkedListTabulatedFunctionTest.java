package functions;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LinkedListTabulatedFunctionTest {
    double[] ArrX = {1, 3, 5, 7};
    double[] ArrY = {2, 4, 6, 8};
    LinkedListTabulatedFunction linkedList = new LinkedListTabulatedFunction(ArrX, ArrY);

    @Test
    public void getCount() {
        assertEquals(4, linkedList.GetCount());
    }

    @Test
    public void getX() {
        assertEquals(5.0, linkedList.getX(2), 0.00001);
    }

    @Test
    public void getY() {
        assertEquals(6.0, linkedList.getY(2), 0.00001);
    }



    @Test
    public void leftBound() {
        assertEquals(1.0, linkedList.leftBound(), 0.00001);
    }

    @Test
    public void rightBound() {
        assertEquals(7.0, linkedList.rightBound(), 0.00001);
    }

    @Test
    public void indexOfX() {
        assertEquals(2.0, linkedList.indexOfX(5.0), 0.00001);
    }

    @Test
    public void indexOfY() {
        assertEquals(0, linkedList.indexOfY(2.0), 0.00001);
    }

    @Test
    public void floorIndexOfX() {
        assertEquals(1, linkedList.floorIndexOfX(4.0));
        assertEquals(2, linkedList.floorIndexOfX(6.0));
        assertEquals(3, linkedList.floorIndexOfX(777.0));
    }

    @Test
    public void extrapolateLeft() {
        assertEquals(-1.0, linkedList.extrapolateLeft(-2.0), 0.00001);
    }

    @Test
    public void extrapolateRight() {
        assertEquals(14.0, linkedList.extrapolateRight(13.0), 0.00001);
    }

    @Test
    public void interpolate() {
        assertEquals(7.0, linkedList.interpolate(6.0, 2), 0.00001);
        assertEquals(19.0/3.0, linkedList.interpolate(3.0, 1, 4, 3, 8), 0.00001);
    }

    @Test
    public void apply() {
        assertEquals(-1.0, linkedList.apply(-2.0), 0.00001);
        assertEquals(14.0, linkedList.apply(13.0), 0.00001);
        assertEquals(7.0, linkedList.apply(6.0), 0.00001);
    }

    @Test
    public void setY() {
        linkedList.setY(3, 10);
        assertEquals(10.0, linkedList.getY(3), 0.00001);

    }
}