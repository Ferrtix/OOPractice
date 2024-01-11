package functions;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

public class LinkedListTabulatedFunction extends AbstractTabulatedFunction implements TabulatedFunction{

    static class Node {
        public Node next;
        public Node prev;

        public double x;
        public double y;

        Node(double x, double y) {
            this.x = x;
            this.y = y;
        }

        public String toString() {
            String strX = String.valueOf(x);
            String strY = String.valueOf(y);
            return "(" + strX + ", " + strY + ")";
        }

        public boolean equals(Object o) {
            if (this == o) return true;
            if (getClass() == o.getClass()) {
                Node node = (Node)o;
                return Double.compare(x, node.x) == Double.compare(y, node.y) == Objects.equals(next, node.next) == Objects.equals(prev, node.prev);
            }
            else {
                return false;
            }
        }

        public int hashCode() {
            return Double.hashCode(x) | Double.hashCode(y);
        }

        public Object clone(){
            Node Clone = new Node(x, y);
            Clone.prev = prev;
            Clone.next = next;
            return Clone;
        }
    }

    private Node head;
    protected int count;

    protected void addNode(double x, double y) {
        Node newNode = new Node(x, y);
        if (head == null) {
            head = newNode;
            newNode.next = newNode;
            newNode.prev = newNode;
        } else {
            Node last = head.prev;
            last.next = newNode;
            head.prev = newNode;
            newNode.prev = last;
            newNode.next = head;
        }
        count++;
    }

    Node getNode(int index) {
        if (index < 0 || index > count - 1)
            throw new IllegalArgumentException("Index out of range");
        if (index == 0) {
            return head;
        }
        Node temp = head;
        for (int i = 0; i <= index; i++) {
            temp = temp.next;
        }
        return temp.prev;
    }

    LinkedListTabulatedFunction(double[] xValues, double[] yValues) {
        if(xValues.length < 2)
            throw new IllegalArgumentException("length less than 2");
        if(xValues.length != yValues.length)
            throw new IllegalArgumentException("length isn't same");
        for(int i=1; i<xValues.length; i++) {
            if(xValues[i-1] > xValues[i]) {
                throw new IllegalArgumentException("x values isn't sorted");
            }
        }
        for(int i=0; i<xValues.length; i++) {
            addNode(xValues[i], yValues[i]);
        }
        count=xValues.length;
    }

    LinkedListTabulatedFunction(MathFunction source, double xFrom, double xTo, int count) {
        if(count < 2)
            throw new IllegalArgumentException("length less than 2");

        if(xFrom > xTo) {
            double temp=xFrom;
            xFrom=xTo;
            xTo=temp;
        }

        if(xFrom == xTo) {
            for(int i=0;i<count;++i) {
                addNode(xFrom, source.apply(xFrom));
            }
        }
        else {
            double step = (xTo - xFrom)/(count - 1);
            int i=0;
            for(double x = xFrom; x <= (xTo + step/2); x += step) {
                addNode(x, source.apply(x));
                ++i;
            }
        }
    }

    @Override
    public int GetCount() {
        return this.count;
    }

    @Override
    public double getX(int index) {
        if (index < 0 || index > count - 1) {
            throw new IllegalArgumentException("Index out of range");
        } else {
            Node temp = getNode(index);
            return temp.x;
        }
    }
    @Override
    public double getY(int index) {
        if (index < 0 || index > count - 1) {
            throw new IllegalArgumentException("Index out of range");
        } else {
            Node temp = getNode(index);
            return temp.y;
        }
    }

    @Override
    public void setY(int index, double value) {
        if (index < 0 || index > count - 1) {
            throw new IllegalArgumentException("Index out of range");
        } else {
            Node temp = getNode(index);
            temp.y = value;
        }
    }
    @Override
    public double leftBound() {
        return head.x;
    }
    @Override
    public double rightBound() {
        return head.prev.x;
    }
    @Override
    public int indexOfX(double x) {
        for(int i = 0;i < count-1; ++i){
            if(Double.compare(x, getX(i))==0){
                return i;
            }
        }
        return -1;
    }
    @Override
    public int indexOfY(double y) {
        for(int i = 0; i < count-1; ++i){
            if(Double.compare(y,getY(i))==0){
                return i;
            }
        }
        return -1;
    }
    @Override
    protected int floorIndexOfX(double x) {
        if (x < getX(0))
            throw new IllegalArgumentException("X out of range");

        for(int i=0;i<count-1;i++){
            if((x > getX(i)) && (x < getX(i+1))){
                return i;
            }
        }
        return count-1;
    }
    @Override
    protected double extrapolateLeft(double x) {
        if (x > getX(0))
            throw new IllegalArgumentException("X out of range");
        if (head.next == head) {
            return head.y;
        } else {
            return (getY(0)+((getY(1)-getY(0))/
                    (getX(1)-getX(0)))*(x-getX(0)));
        }
    }

    @Override
    protected double extrapolateRight(double x) {
        if (x < getX(count - 1))
            throw new IllegalArgumentException("X out of range");
        if (head.next == head) {
            return head.y;
        } else {
            return (getY(count-2)+((getY(count-1)-getY(count-2))/
                    (getX(count-1)-getX(count-2)))*(x-getX(count-2)));
        }
    }

    @Override
    protected double interpolate(double x, int floorIndex) {
        if (floorIndex < 1 || floorIndex > count - 1)
            throw new IllegalArgumentException("Index out of range");
        if (head.next == head) {
            return head.y;
        } else {
            return (getY(floorIndex-1)+((getY(floorIndex)-getY(floorIndex-1))/
                    (getX(floorIndex)-getX(floorIndex-1)))*(x-getX(floorIndex-1)));
        }
    }

    @Override
    protected double interpolate(double x, double leftX, double rightX, double leftY, double rightY) {
        if (head.next == head) {
            return head.y;
        } else {
            return (leftY+((rightY-leftY)/(rightX-leftX))*(x-leftX));
        }
    }

    @Override
    public double apply(double x) {
        double res = 0.0;
        if (x < getX(0)) {
            res = extrapolateLeft(x);
        }else if(x > getX(count-1)){
            res = extrapolateRight(x);
        }else {
            if(indexOfX(x)!=-1){
                res = getY(indexOfX(x));
            }else{
                res = interpolate(x,floorIndexOfX(x));
            }
        }
        return res;
    }

    public String toString() {
        String result = "";
        Node temp = head;
        do {
            result += temp.toString() + " ";
            temp = temp.next;
        } while (temp != head);

        return result;
    }

    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (getClass() != o.getClass())
            return false;
        LinkedListTabulatedFunction linkedList2 = (LinkedListTabulatedFunction)o;
        if (linkedList2.count != count)
            return false;
        Node temp1 = head;
        Node temp2 = linkedList2.head;
        for (int i = 0; i < count; i++) {
            if (temp1.x != temp2.x || temp1.y != temp2.y)
                return false;
            temp1 = temp1.next;
            temp2 = temp2.next;
        }
        return true;
    }

    public int hashCode() {
        int result = 0;
        Node temp = head;
        do {
            result += temp.hashCode();
            temp = temp.next;
        } while(temp != head);
        return result;
    }

    public Object clone() {
        double[] arrX = new double[count];
        double[] arrY = new double[count];
        Node temp = head;
        for (int i = 0; i < count; i++) {
            arrX[i] = temp.x;
            arrY[i] = temp.y;
            temp = temp.next;
        }
        return new LinkedListTabulatedFunction(arrX, arrY);
    }

    public Iterator<Point> iterator() {
        return new Iterator<Point>() {
            private Node node = head;

            @Override
            public boolean hasNext() {
                return node != null;
            }

            @Override
            public Point next() {
                if (hasNext()) {
                    Point point = new Point(node.x, node.y);
                    if (node.next == head) node = null;
                    else node = node.next;
                    return point;
                } else throw new NoSuchElementException();
            }
        };
    }
}