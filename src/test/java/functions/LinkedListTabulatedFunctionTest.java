package functions;

import exceptions.ArrayIsNotSortedException;
import exceptions.DifferentLengthOfArraysException;
import exceptions.InterpolationException;
import org.junit.jupiter.api.Test;

import java.util.Iterator;

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
    public void setY() {
        linkedList.setY(3, 10);
        assertEquals(10.0, linkedList.getY(3), 0.00001);
        linkedList.setY(3, 8);
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
    public void interpolate1() {
        boolean isException = false;
        try {
            assertEquals(7.0, linkedList.interpolate(6.0, 2), 0.00001);
        } catch (InterpolationException exception) {
            isException = true;
        }
        assertFalse(isException);

    }

    @Test
    public void interpolate2() {
        assertEquals(19.0/3.0, linkedList.interpolate(3.0, 1, 4, 3, 8), 0.00001);
    }

    @Test
    public void apply() {
        assertEquals(-1.0, linkedList.apply(-2.0), 0.00001);
        assertEquals(14.0, linkedList.apply(13.0), 0.00001);
        assertEquals(7.0, linkedList.apply(6.0), 0.00001);
    }

    @Test
    public void toString_NodeTest() {
        LinkedListTabulatedFunction.Node node = new LinkedListTabulatedFunction.Node(10.0, 77.7);
        assertEquals("(10.0, 77.7)", node.toString());
    }
    LinkedListTabulatedFunction.Node node1 = new LinkedListTabulatedFunction.Node(1, 2);
    LinkedListTabulatedFunction.Node node2 = new LinkedListTabulatedFunction.Node(1, 2);
    LinkedListTabulatedFunction.Node node3 = new LinkedListTabulatedFunction.Node(1, 3);
    @Test
    public void equals_NodeTest() {
        assertTrue(node1.equals(node2));
        assertFalse(node1.equals(node3));
    }

    @Test
    public void hashCode_NodeTest() {
        assertEquals(node1.hashCode(), node2.hashCode());
        assertNotEquals(node1.hashCode(), node3.hashCode());
    }

    @Test
    public void clone_NodeTest() {
        Object Copy1 = node1.clone();
        Object Copy2 = node3.clone();
        assertEquals(node1, Copy1);
        assertNotEquals(node1, Copy2);
    }

    @Test
    public void ToString_ListTest() {
        assertEquals("(1.0, 2.0) (3.0, 4.0) (5.0, 6.0) (7.0, 8.0) ", linkedList.toString());
        assertNotEquals("(1.0, 3.0) (3.0, 4.0) (5.0, 6.0) (7.0, 8.0) ", linkedList.toString());
    }

    @Test
    public void equals_ListTest() {
        double[] ArrX2 = {1, 2, 3, 4};
        double[] ArrY2 = {8, 9, 10, 11};
        LinkedListTabulatedFunction List2 = new LinkedListTabulatedFunction(ArrX, ArrY);
        LinkedListTabulatedFunction List3 = new LinkedListTabulatedFunction(ArrX2, ArrY2);
        assertTrue(linkedList.equals(List2));
        assertFalse(linkedList.equals(List3));
    }

    @Test
    public void ListHashCodeTest() {
        double[] ArrX2 = {1, 2, 3, 4};
        double[] ArrY2 = {8, 9, 10, 11};
        LinkedListTabulatedFunction List2 = new LinkedListTabulatedFunction(ArrX, ArrY);
        LinkedListTabulatedFunction List3 = new LinkedListTabulatedFunction(ArrX2, ArrY2);
        assertEquals(linkedList.hashCode(), List2.hashCode());
        assertNotEquals(linkedList.hashCode(), List3.hashCode());
    }

    @Test
    public void ListCloneTest() {
        Object Clone = linkedList.clone();
        assertEquals(linkedList, Clone);
    }

    @Test
    public void LinkedListException_Test() {
        boolean isException = false;
        double[] x2 = {1};
        double[] y2 = {2};
        try {
            LinkedListTabulatedFunction listExp1 = new LinkedListTabulatedFunction(x2, y2);
        } catch (IllegalArgumentException exception) {
            isException = true;
        }
        assertTrue(isException);

        isException = false;
        double[] x3 = {1, 2, 3, 4};
        double[] y3 = {1, 2, 3};
        try {
            LinkedListTabulatedFunction listExp1 = new LinkedListTabulatedFunction(x3, y3);
        } catch (DifferentLengthOfArraysException exception) {
            isException = true;
        }
        assertTrue(isException);

        isException = false;
        double[] x4 = {1, 5, 2, 7};
        double[] y4 = {1, 2, 3, 4};
        try {
            LinkedListTabulatedFunction listExp1 = new LinkedListTabulatedFunction(x4, y4);
        } catch (ArrayIsNotSortedException exception) {
            isException = true;
        }
        assertTrue(isException);
    }

    @Test
    public void iterator() {
        Iterator<Point> iterator = linkedList.iterator();
        LinkedListTabulatedFunction.Node node;

        node = linkedList.getNode(0);
        while (iterator.hasNext()) {
            Point point = iterator.next();
            assertEquals(node.x, point.x);
            assertEquals(node.y, point.y);
            node = node.next;
        }

        node = linkedList.getNode(0);
        for (Point point : linkedList) {
            assertEquals(node.x, point.x);
            assertEquals(node.y, point.y);
            node = node.next;
        }
    }
}