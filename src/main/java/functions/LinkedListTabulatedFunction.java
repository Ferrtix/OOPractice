package functions;

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

        public Object clone() {
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
        for(int i=0; i<xValues.length; i++) {
            addNode(xValues[i], yValues[i]);
        }
        count=xValues.length;
    }

    LinkedListTabulatedFunction(MathFunction source, double xFrom, double xTo, int count) {
        if(xFrom>xTo) {
            double temp=xFrom;
            xFrom=xTo;
            xTo=temp;
        }

        if(xFrom==xTo) {
            for(int i=0;i<count;++i) {
                addNode(xFrom, source.apply(xFrom));
            }
        }
        else {
            double step = (xTo-xFrom)/(count-1);
            int i=0;
            for(double x=xFrom; x<=(xTo+step/2); x+=step) {
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
        for(int i=0;i<count-1;++i){
            if(Double.compare(x, getX(i))==0){
                return i;
            }
        }
        return -1;
    }
    @Override
    public int indexOfY(double y) {
        for(int i=0;i<count-1;++i){
            if(Double.compare(y,getY(i))==0){
                return i;
            }
        }
        return -1;
    }
    @Override
    protected int floorIndexOfX(double x) {
        if(x<getX(0)) return 0;

        for(int i=0;i<count-1;i++){
            if((x>getX(i))&&(x<getX(i+1))){
                return i;
            }
        }
        return count-1;
    }
    @Override
    protected double extrapolateLeft(double x) {
        if (head.next == head) {
            return head.y;
        } else {
            return (getY(0)+((getY(1)-getY(0))/
                    (getX(1)-getX(0)))*(x-getX(0)));
        }
    }

    @Override
    protected double extrapolateRight(double x) {
        if (head.next == head) {
            return head.y;
        } else {
            return (getY(count-2)+((getY(count-1)-getY(count-2))/
                    (getX(count-1)-getX(count-2)))*(x-getX(count-2)));
        }
    }

    @Override
    protected double interpolate(double x, int floorIndex) {
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
}